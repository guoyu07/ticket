package com.ticket.serviceImpl;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;

import com.ticket.enumer.EvaluationReportProperty;
import com.ticket.enumer.EvaluationReportType;
import com.ticket.enumer.EvaluationReportUpdateFrequency;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Evaluation;
import com.ticket.pojo.EvaluationReportTask;
import com.ticket.pojo.EvaluationReportTimeSegment;
import com.ticket.report.Classes;
import com.ticket.report.Project;
import com.ticket.report.Target;
import com.ticket.service.IEvaluationReportTaskService;
import com.ticket.util.DateUtil;
import com.ticket.util.DecoderUtil;

/**
 * 评论报表任务业务接口实现类
 * @ClassName: IEvaluationReportTaskService   
 * @Description: 提供评论报表任务操作的增删改查   
 * @author HiSay  
 * @date 2016-02-04 21:40:28
 *
 */
public class EvaluationReportTaskServiceImpl extends BaseServiceImpl<EvaluationReportTask, String> implements IEvaluationReportTaskService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(EvaluationReportTaskServiceImpl.class);
	
	@Override
	public boolean merge(String id, String name,EvaluationReportProperty property,EvaluationReportType type
			,EvaluationReportUpdateFrequency frequency,String email, Date[] startTime, Date[] endTime, boolean launch)
					throws ServiceException {
		
		EvaluationReportTask evaluationReportTask = dbDAO.queryById(this.getTableNameFromEntity(EvaluationReportTask.class), id);
		evaluationReportTask.setName(DecoderUtil.UtfDecoder(name));
		evaluationReportTask.setProperty(property);
		evaluationReportTask.setType(type);
		evaluationReportTask.setFrequency(frequency);
		evaluationReportTask.setEmail(DecoderUtil.UtfDecoder(email));
		evaluationReportTask.setLaunch(launch);
		dbDAO.merge(evaluationReportTask);
		return true;
	}

	@Override
	public boolean persist(String name,EvaluationReportProperty property,EvaluationReportType type
			,EvaluationReportUpdateFrequency frequency,String email, Date[] startTime, Date[] endTime, boolean launch)
					throws ServiceException {
		
		EvaluationReportTask evaluationReportTask = new EvaluationReportTask();
		evaluationReportTask.setName(DecoderUtil.UtfDecoder(name));
		evaluationReportTask.setProperty(property);
		evaluationReportTask.setType(type);
		evaluationReportTask.setFrequency(frequency);
		evaluationReportTask.setEmail(DecoderUtil.UtfDecoder(email));
		evaluationReportTask.setLaunch(launch);
		dbDAO.persist(evaluationReportTask);
		
		for(int i = 0; i < startTime.length; i++){
			
			EvaluationReportTimeSegment segment = new EvaluationReportTimeSegment();
			segment.setTask(evaluationReportTask);
			segment.setStartTime(startTime[i]);
			segment.setEndTime(endTime[i]);
			segment.setSort(i + 1);
			
			persist(segment);
		}
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		EvaluationReportTask evaluationReportTask = dbDAO.queryById(this.getTableNameFromEntity(EvaluationReportTask.class), id);
		dbDAO.remove(evaluationReportTask);
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void generate(EvaluationReportTask task, OutputStream out) throws ServiceException {
		
		String evaluationName = Evaluation.class.getName();
		
		String srcFilePath = null;
		Map<String, Object> map = new HashMap<String, Object>();

		List<CellRangeAddress> ranges = new ArrayList<CellRangeAddress>();
		List<int[]> formats = new ArrayList<int[]>();
		if(task.getProperty() == EvaluationReportProperty.normal){
			
			Date endTime = task.getTimes().get(0).getEndTime();
			endTime.setHours(23);
			endTime.setMinutes(59);
			endTime.setSeconds(59);
			Date startTime = task.getTimes().get(0).getStartTime();
			
			List<Object> params = new ArrayList<Object>();
			StringBuffer condition = new StringBuffer();
			StringBuffer condition2 = new StringBuffer();
			if(startTime != null){
				
				params.add(startTime);
				condition.append(" and e.status.createTime >= ?");
				condition2.append(" and e2.status.createTime >= ?");
			}
			if(endTime != null){
				
				params.add(endTime);
				condition.append(" and e.status.createTime <= ?");
				condition2.append(" and e2.status.createTime <= ?");
			}
			
			map.put("startTime", DateUtil.formatDateToShortString_ZH_CN(startTime));
			map.put("endTime", DateUtil.formatDateToShortString_ZH_CN(endTime));
			
			if(task.getType() == EvaluationReportType.classes){ //大类普通报表
				
				srcFilePath = ServletActionContext.getRequest().getRealPath("/WEB-INF/excel/template/base-classes.xlsx");
				List<Object> allParams = new ArrayList<Object>();
				allParams.addAll(params);
				allParams.addAll(params);
				List<Object> data = dbDAO.executeJPQLForQuery(
						"select c.name, count(distinct e), (case when avg(t.star)=null then 0.00 else avg(t.star) end)"
								+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 right join e2.classes c2 where e2.process.size > 0 and c2=c"+condition2.toString()+")"
								+ " from " + evaluationName + " e"
								+ " right join e.classes c left join e.items t"
								+ " where c.parent is null" + condition.toString()
								+ " group by c", allParams.toArray());
				
				map.put("data", data);
			}else if(task.getType() == EvaluationReportType.project){ //项目普通报表
				
				srcFilePath = ServletActionContext.getRequest().getRealPath("/WEB-INF/excel/template/base-project.xlsx");
				List<Object> allParams = new ArrayList<Object>();
				allParams.addAll(params);
				allParams.addAll(params);
				List<Object> data = dbDAO.executeJPQLForQuery(
						"select p.name, count(distinct e), (case when avg(t.star)=null then 0.00 else avg(t.star) end)"
								+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 join e2.project p2 where e2.process.size > 0 and p2=p"+condition2.toString()+")"
								+ " from " + evaluationName + " e"
								+ " join e.project p join e.items t"
								+ " where 1=1" + condition.toString()
								+ " group by p", allParams.toArray());
				map.put("data", data);
			}else if(task.getType() == EvaluationReportType.target){ //指标普通报表
				
				srcFilePath = ServletActionContext.getRequest().getRealPath("/WEB-INF/excel/template/base-target.xlsx");
				List<Object> allParams = new ArrayList<Object>();
				allParams.addAll(params);
				allParams.addAll(params);
				allParams.addAll(params);
				allParams.addAll(params);
				allParams.addAll(params);
				allParams.addAll(params);
				allParams.addAll(params);
				allParams.addAll(params);
				List<Object> data = dbDAO.executeJPQLForQuery(
						"select t.setting.name"
								+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting"+condition2.toString()+")"
								+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=5"+condition2.toString()+")"
								+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=4"+condition2.toString()+")"
								+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=3"+condition2.toString()+")"
								+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=2"+condition2.toString()+")"
								+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=1"+condition2.toString()+")"
								+ ", (case when avg(t.star)=null then 0.00 else avg(t.star) end)"
								+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and e2.process.size > 0"+condition2.toString()+")"
								+ " from " + evaluationName + " e"
								+ " right join e.items t"
								+ " where 1=1" + condition.toString()
								+ " group by t.setting.id", allParams.toArray());
				map.put("data", data);
			}else if(task.getType() == EvaluationReportType.together){ //汇总普通报表
				
				srcFilePath = ServletActionContext.getRequest().getRealPath("/WEB-INF/excel/template/base-together.xlsx");
				
				//遍历所有的大类
				List<Object> classes = dbDAO.executeJPQLForQuery("select c.id, c.name, (case when avg(t.star)=null then 0.00 else avg(t.star) end)"
						+ " from " + evaluationName + " e"
						+ " right join e.classes c left join e.items t"
						+ " where c.parent is null" + condition.toString()
						+ " group by c", params.toArray());
				
				List<Classes> data = new ArrayList<Classes>();
				int startRowForClass = 4, endRowForClass = 4;
				int startRowForPro = 4, endRowForPro = 4;
				for(Object row : classes){
					
					Object[] arr = (Object[])row;
					String id = (String)arr[0];
					String name = (String)arr[1];
					double avg = (Double)arr[2];
					
					Classes c = new Classes();
					c.setName(name);
					c.setAverage(avg);
					data.add(c);
					
					//遍历所有的项目
					List<Object> paramss = new ArrayList<Object>();
					paramss.add(id);
					paramss.addAll(params);
					List<Object> projects = dbDAO.executeJPQLForQuery("select p.id, p.name, (case when avg(t.star)=null then 0.00 else avg(t.star) end)"
							+ " from " + evaluationName + " e"
							+ " right join e.project p left join e.items t"
							+ " where 1=1 and e.classes.id=?" + condition.toString()
							+ " group by p", paramss.toArray());
					
					List<Project> ps = new ArrayList<Project>();
					for(Object rowp : projects){
						
						Object[] arrp = (Object[])rowp;
						String idp = (String)arrp[0];
						String namep = (String)arrp[1];
						double avgp = (Double)arrp[2];
						
						Project p = new Project();
						p.setName(namep);
						p.setAverage(avgp);
						ps.add(p);

						//遍历所有的指标
						List<Object> allParams = new ArrayList<Object>();
						allParams.addAll(params);
						allParams.addAll(params);
						allParams.addAll(params);
						allParams.addAll(params);
						allParams.addAll(params);
						allParams.addAll(params);
						allParams.addAll(params);
						allParams.add(idp);
						allParams.addAll(params);
						List<Object> targets = dbDAO.executeJPQLForQuery(
								"select t.setting.name"
										+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting"+condition2.toString()+")"
										+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=5"+condition2.toString()+")"
										+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=4"+condition2.toString()+")"
										+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=3"+condition2.toString()+")"
										+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=2"+condition2.toString()+")"
										+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=1"+condition2.toString()+")"
										+ ", (case when avg(t.star)=null then 0.00 else avg(t.star) end)"
										+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and e2.process.size > 0"+condition2.toString()+")"
										+ " from " + evaluationName + " e"
										+ " right join e.items t"
										+ " where e.project.id=?"+condition.toString()+""
										+ " group by t.setting.id", allParams.toArray());
						if(targets.size() == 0){
							
							System.out.println("");
						}
						//确定合并的单元格
						endRowForPro += targets.size();
						endRowForClass += targets.size(); 
						CellRangeAddress r3 = new CellRangeAddress(startRowForPro, endRowForPro-1, 2, 2);
						CellRangeAddress r4 = new CellRangeAddress(startRowForPro, endRowForPro-1, 3, 3);
						CellRangeAddress r5 = new CellRangeAddress(startRowForPro, endRowForPro-1, 5, 5);
						CellRangeAddress r6 = new CellRangeAddress(startRowForPro, endRowForPro-1, 12, 12);
						ranges.add(r3);
						ranges.add(r4);
						ranges.add(r5);
						ranges.add(r6);
						startRowForPro = endRowForPro;
						
						List<Target> ts = new ArrayList<Target>();
						for(Object rowt : targets){
							
							Object[] arrt = (Object[])rowt;
							String namet = (String)arrt[0];
							long count = (Long)arrt[1];
							long five = (Long)arrt[2];
							long four = (Long)arrt[3];
							long three = (Long)arrt[4];
							long two = (Long)arrt[5];
							long one = (Long)arrt[6];
							double avgt = (Double)arrt[7];
							long question = (Long)arrt[8];
							
							Target t = new Target();
							t.setName(namet);
							t.setCount(count);
							t.setFive(five);
							t.setFour(four);
							t.setThree(three);
							t.setTwo(two);
							t.setOne(one);
							t.setAverage(avgt);
							t.setProblematic(question);
							ts.add(t);
						}
						p.setTarget(ts);
					}
					c.setProject(ps);
					//确定合并的单元格
					CellRangeAddress r1 = new CellRangeAddress(startRowForClass, endRowForClass-1, 0, 0);
					CellRangeAddress r2 = new CellRangeAddress(startRowForClass, endRowForClass-1, 1, 1);
					ranges.add(r1);
					ranges.add(r2);
					startRowForClass = endRowForClass;
				}
				map.put("data", data);
			}
		}else if(task.getProperty() == EvaluationReportProperty.compare){
			
			Date endTime = task.getTimes().get(0).getEndTime();
			Date startTime = task.getTimes().get(0).getStartTime();
			endTime.setHours(23);
			endTime.setMinutes(59);
			endTime.setSeconds(59);
			
			Date endTime2 = task.getTimes().get(1).getEndTime();
			Date startTime2 = task.getTimes().get(1).getStartTime();
			endTime2.setHours(23);
			endTime2.setMinutes(59);
			endTime2.setSeconds(59);
			
			List<Object> params = new ArrayList<Object>();
			List<Object> params2 = new ArrayList<Object>();
			StringBuffer condition = new StringBuffer();
			if(startTime != null){
				
				params.add(startTime);
				params2.add(startTime2);
				condition.append(" and e2.status.createTime >= ?");
			}
			if(endTime != null){
				
				params.add(endTime);
				params2.add(endTime2);
				condition.append(" and e2.status.createTime <= ?");
			}
			map.put("startTime", DateUtil.formatDateToShortString_ZH_CN(startTime));
			map.put("endTime", DateUtil.formatDateToShortString_ZH_CN(endTime));
			map.put("startTime2", DateUtil.formatDateToShortString_ZH_CN(startTime2));
			map.put("endTime2", DateUtil.formatDateToShortString_ZH_CN(endTime2));
			
			if(task.getType() == EvaluationReportType.classes){ //大类对比报表
				
				srcFilePath = ServletActionContext.getRequest().getRealPath("/WEB-INF/excel/template/custom-classes.xlsx");
				List<Object> paramss = new ArrayList<Object>();
				paramss.addAll(params);
				paramss.addAll(params2);
				paramss.addAll(params);
				paramss.addAll(params2);
				paramss.addAll(params);
				paramss.addAll(params2);
				List<Object> data = dbDAO.executeJPQLForQuery(
						"select e.classes.name"
								+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 where e2.classes=e.classes" + condition.toString() + ")"
								+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 where e2.classes=e.classes" + condition.toString() + ")"
								+ ", (select (case when avg(t2.star)=null then 0.00 else avg(t2.star) end) from " + evaluationName + " e2 right join e2.items t2 where e2.classes=e.classes" + condition.toString() + ")"
								+ ", (select (case when avg(t2.star)=null then 0.00 else avg(t2.star) end) from " + evaluationName + " e2 right join e2.items t2 where e2.classes=e.classes" + condition.toString() + ")"
								+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 where e2.process.size > 0 and e2.classes=e.classes" + condition.toString() + ")"
								+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 where e2.process.size > 0 and e2.classes=e.classes" + condition.toString() + ")"
								+ " from " + evaluationName + " e"
								+ " group by e.classes", paramss.toArray());
				map.put("data", data);
				
				for(int i = 0; i < data.size(); i++){
					
					int[] cell = new int[2];
					cell[0] = 4 + i;
					cell[1] = 3;
					formats.add(cell);
					
					cell = cell.clone();
					cell[1] = 6;
					formats.add(cell);
					
					cell = cell.clone();
					cell[1] = 9;
					formats.add(cell);
				}
			}else if(task.getType() == EvaluationReportType.project){ //项目对比报表
				
				srcFilePath = ServletActionContext.getRequest().getRealPath("/WEB-INF/excel/template/custom-project.xlsx");
				List<Object> paramss = new ArrayList<Object>();
				paramss.addAll(params);
				paramss.addAll(params2);
				paramss.addAll(params);
				paramss.addAll(params2);
				paramss.addAll(params);
				paramss.addAll(params2);
				List<Object> data = dbDAO.executeJPQLForQuery(
						"select e.project.name"
								+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 where e2.project=e.project" + condition.toString() + ")"
								+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 where e2.project=e.project" + condition.toString() + ")"
								+ ", (select (case when avg(t2.star)=null then 0.00 else avg(t2.star) end) from " + evaluationName + " e2 right join e2.items t2 where e2.project=e.project" + condition.toString() + ")"
								+ ", (select (case when avg(t2.star)=null then 0.00 else avg(t2.star) end) from " + evaluationName + " e2 right join e2.items t2 where e2.project=e.project" + condition.toString() + ")"
								+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 where e2.process.size > 0 and e2.project=e.project" + condition.toString() + ")"
								+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 where e2.process.size > 0 and e2.project=e.project" + condition.toString() + ")"
								+ " from " + evaluationName + " e"
								+ " group by e.project.id", paramss.toArray());
				map.put("data", data);
				
				for(int i = 0; i < data.size(); i++){
					
					int[] cell = new int[2];
					cell[0] = 4 + i;
					cell[1] = 3;
					formats.add(cell);
					
					cell = cell.clone();
					cell[1] = 6;
					formats.add(cell);
					
					cell = cell.clone();
					cell[1] = 9;
					formats.add(cell);
				}
			}else if(task.getType() == EvaluationReportType.target){ //指标对比报表
				
				srcFilePath = ServletActionContext.getRequest().getRealPath("/WEB-INF/excel/template/custom-target.xlsx");
				List<Object> paramss = new ArrayList<Object>();
				paramss.addAll(params);
				paramss.addAll(params2);
				paramss.addAll(params);
				paramss.addAll(params2);
				paramss.addAll(params);
				paramss.addAll(params2);
				paramss.addAll(params);
				paramss.addAll(params2);
				paramss.addAll(params);
				paramss.addAll(params2);
				paramss.addAll(params);
				paramss.addAll(params2);
				paramss.addAll(params);
				paramss.addAll(params2);
				paramss.addAll(params);
				paramss.addAll(params2);
				List<Object> data = dbDAO.executeJPQLForQuery(
						"select t.setting.name"
								+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 where e2=t.comment" + condition.toString() + ")"
								+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 where e2=t.comment" + condition.toString() + ")"
								+ ", (select (case when count(t2)=null then 0l else count(t2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=5" + condition.toString() + ")"
								+ ", (select (case when count(t2)=null then 0l else count(t2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=5" + condition.toString() + ")"
								+ ", (select (case when count(t2)=null then 0l else count(t2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=4" + condition.toString() + ")"
								+ ", (select (case when count(t2)=null then 0l else count(t2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=4" + condition.toString() + ")"
								+ ", (select (case when count(t2)=null then 0l else count(t2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=3" + condition.toString() + ")"
								+ ", (select (case when count(t2)=null then 0l else count(t2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=3" + condition.toString() + ")"
								+ ", (select (case when count(t2)=null then 0l else count(t2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=2" + condition.toString() + ")"
								+ ", (select (case when count(t2)=null then 0l else count(t2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=2" + condition.toString() + ")"
								+ ", (select (case when count(t2)=null then 0l else count(t2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=1" + condition.toString() + ")"
								+ ", (select (case when count(t2)=null then 0l else count(t2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=1" + condition.toString() + ")"
								+ ", (select (case when avg(t2.star)=null then 0.00 else avg(t2.star) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting" + condition.toString() + ")"
								+ ", (select (case when avg(t2.star)=null then 0.00 else avg(t2.star) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting" + condition.toString() + ")"
								+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 right join e2.items t2 where e2.process.size > 0 and t2.setting=t.setting" + condition.toString() + ")"
								+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 right join e2.items t2 where e2.process.size > 0 and t2.setting=t.setting" + condition.toString() + ")"
								+ " from " + evaluationName + " e"
								+ " right join e.items t"
								+ " group by t.setting.id", paramss.toArray());
				map.put("data", data);
				
				for(int i = 0; i < data.size(); i++){
					
					int[] cell = new int[2];
					cell[0] = 4 + i;
					cell[1] = 3;
					formats.add(cell);
					
					cell = cell.clone();
					cell[1] = 6;
					formats.add(cell);
					
					cell = cell.clone();
					cell[1] = 9;
					formats.add(cell);
					
					cell = cell.clone();
					cell[1] = 12;
					formats.add(cell);
					
					cell = cell.clone();
					cell[1] = 15;
					formats.add(cell);
					
					cell = cell.clone();
					cell[1] = 18;
					formats.add(cell);
					
					cell = cell.clone();
					cell[1] = 21;
					formats.add(cell);
					
					cell = cell.clone();
					cell[1] = 24;
					formats.add(cell);
				}
			}else if(task.getType() == EvaluationReportType.together){ //汇总对比报表
				
				srcFilePath = ServletActionContext.getRequest().getRealPath("/WEB-INF/excel/template/custom-together.xlsx");
				List<Object> paramss = new ArrayList<Object>();
				paramss.addAll(params);
				paramss.addAll(params2);
				
				//遍历所有的大类
				List<Object> classes = dbDAO.executeJPQLForQuery(
						"select e.classes.id, e.classes.name"
								+ ", (select (case when avg(t2.star)=null then 0.00 else avg(t2.star) end) from " + evaluationName + " e2 right join e2.items t2 where e2.classes=e.classes" + condition.toString() + ")"
								+ ", (select (case when avg(t2.star)=null then 0.00 else avg(t2.star) end) from " + evaluationName + " e2 right join e2.items t2 where e2.classes=e.classes" + condition.toString() + ")"
								+ " from " + evaluationName + " e"
								+ " group by e.classes.id", paramss.toArray());
				
				List<Classes> data = new ArrayList<Classes>();
				int startRowForClass = 5, endRowForClass = 5;
				int startRowForPro = 5, endRowForPro = 5;
				for(Object row : classes){
					
					Object[] arr = (Object[])row;
					String id = (String)arr[0];
					String name = (String)arr[1];
					double avg = (Double)arr[2];
					double avg2 = (Double)arr[3];
					
					Classes c = new Classes();
					c.setName(name);
					c.setAverage(avg);
					c.setAverage2(avg2);
					data.add(c);
					
					//遍历所有的项目
					List<Object> paramss2 = new ArrayList<Object>();
					paramss2.addAll(params);
					paramss2.addAll(params2);
					paramss2.add(id);
					List<Object> projects = dbDAO.executeJPQLForQuery(
							"select e.project.id, e.project.name"
									+ ", (select (case when avg(t2.star)=null then 0.00 else avg(t2.star) end) from " + evaluationName + " e2 right join e2.items t2 where e2.project=e.project" + condition.toString() + ")"
									+ ", (select (case when avg(t2.star)=null then 0.00 else avg(t2.star) end) from " + evaluationName + " e2 right join e2.items t2 where e2.project=e.project" + condition.toString() + ")"
									+ " from " + evaluationName + " e "
									+ " where e.classes.id=?"
									+ " group by e.project.id", paramss2.toArray());
					
					List<Project> ps = new ArrayList<Project>();
					for(Object rowp : projects){
						
						Object[] arrp = (Object[])rowp;
						String idp = (String)arrp[0];
						String namep = (String)arrp[1];
						double avgp = (Double)arrp[2];
						double avgp2 = (Double)arrp[3];
						
						Project p = new Project();
						p.setName(namep);
						p.setAverage(avgp);
						p.setAverage2(avgp2);
						ps.add(p);
						
						//遍历所有的指标
						List<Object> paramss3 = new ArrayList<Object>();
						paramss3.addAll(params);
						paramss3.addAll(params2);
						paramss3.addAll(params);
						paramss3.addAll(params2);
						paramss3.addAll(params);
						paramss3.addAll(params2);
						paramss3.addAll(params);
						paramss3.addAll(params2);
						paramss3.addAll(params);
						paramss3.addAll(params2);
						paramss3.addAll(params);
						paramss3.addAll(params2);
						paramss3.addAll(params);
						paramss3.addAll(params2);
						paramss3.addAll(params);
						paramss3.addAll(params2);
						paramss3.add(idp);
						List<Object> targets = dbDAO.executeJPQLForQuery(
								"select t.setting.name"
										+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting" + condition.toString() + ")"
										+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting" + condition.toString() + ")"
										+ ", (select (case when count(t2)=null then 0l else count(t2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=5" + condition.toString() + ")"
										+ ", (select (case when count(t2)=null then 0l else count(t2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=5" + condition.toString() + ")"
										+ ", (select (case when count(t2)=null then 0l else count(t2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=4" + condition.toString() + ")"
										+ ", (select (case when count(t2)=null then 0l else count(t2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=4" + condition.toString() + ")"
										+ ", (select (case when count(t2)=null then 0l else count(t2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=3" + condition.toString() + ")"
										+ ", (select (case when count(t2)=null then 0l else count(t2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=3" + condition.toString() + ")"
										+ ", (select (case when count(t2)=null then 0l else count(t2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=2" + condition.toString() + ")"
										+ ", (select (case when count(t2)=null then 0l else count(t2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=2" + condition.toString() + ")"
										+ ", (select (case when count(t2)=null then 0l else count(t2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=1" + condition.toString() + ")"
										+ ", (select (case when count(t2)=null then 0l else count(t2) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting and t2.star=1" + condition.toString() + ")"
										+ ", (select (case when avg(t2.star)=null then 0.00 else avg(t2.star) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting" + condition.toString() + ")"
										+ ", (select (case when avg(t2.star)=null then 0.00 else avg(t2.star) end) from " + evaluationName + " e2 right join e2.items t2 where t2.setting=t.setting" + condition.toString() + ")"
										+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 right join e2.items t2 where e2.process.size > 0 and t2.setting=t.setting" + condition.toString() + ")"
										+ ", (select (case when count(distinct e2)=null then 0l else count(distinct e2) end) from " + evaluationName + " e2 right join e2.items t2 where e2.process.size > 0 and t2.setting=t.setting" + condition.toString() + ")"
										+ " from " + evaluationName + " e"
										+ " right join e.items t"
										+ " where e.project.id=?"
										+ " group by t.setting.id", paramss3.toArray());
						
						//确定合并的单元格
						endRowForPro += targets.size();
						endRowForClass += targets.size(); 
						CellRangeAddress r4 = new CellRangeAddress(startRowForPro, endRowForPro-1, 4, 4);
						CellRangeAddress r5 = new CellRangeAddress(startRowForPro, endRowForPro-1, 5, 5);
						CellRangeAddress r6 = new CellRangeAddress(startRowForPro, endRowForPro-1, 6, 6);
						CellRangeAddress r7 = new CellRangeAddress(startRowForPro, endRowForPro-1, 7, 7);
						
						CellRangeAddress r8 = new CellRangeAddress(startRowForPro, endRowForPro-1, 9, 9);
						CellRangeAddress r9 = new CellRangeAddress(startRowForPro, endRowForPro-1, 10, 10);
						CellRangeAddress r10 = new CellRangeAddress(startRowForPro, endRowForPro-1, 11, 11);
						
						CellRangeAddress r11 = new CellRangeAddress(startRowForPro, endRowForPro-1, 30, 30);
						CellRangeAddress r12 = new CellRangeAddress(startRowForPro, endRowForPro-1, 31, 31);
						CellRangeAddress r13 = new CellRangeAddress(startRowForPro, endRowForPro-1, 32, 32);
						
						ranges.add(r4);
						ranges.add(r5);
						ranges.add(r6);
						ranges.add(r7);
						
						ranges.add(r8);
						ranges.add(r9);
						ranges.add(r10);
						
						ranges.add(r11);
						ranges.add(r12);
						ranges.add(r13);
						
						for(int i = startRowForPro; i < endRowForPro; i++){
							
							int[] cell = new int[2];
							cell[0] = i;
							cell[1] = 3;
							formats.add(cell);
							
							cell = cell.clone();
							cell[1] = 7;
							formats.add(cell);
							
							cell = cell.clone();
							cell[1] = 11;
							formats.add(cell);
							
							cell = cell.clone();
							cell[1] = 14;
							formats.add(cell);
							
							cell = cell.clone();
							cell[1] = 17;
							formats.add(cell);
							
							cell = cell.clone();
							cell[1] = 20;
							formats.add(cell);
							
							cell = cell.clone();
							cell[1] = 23;
							formats.add(cell);
							
							cell = cell.clone();
							cell[1] = 26;
							formats.add(cell);
							
							cell = cell.clone();
							cell[1] = 29;
							formats.add(cell);
							
							cell = cell.clone();
							cell[1] = 32;
							formats.add(cell);
						}
						
						startRowForPro = endRowForPro;
						
						List<Target> ts = new ArrayList<Target>();
						for(Object rowt : targets){
							
							Object[] arrt = (Object[])rowt;
							String namet = (String)arrt[0];
							long count = (Long)arrt[1];
							long count2 = (Long)arrt[2];
							long five = (Long)arrt[3];
							long five2 = (Long)arrt[4];
							long four = (Long)arrt[5];
							long four2 = (Long)arrt[6];
							long three = (Long)arrt[7];
							long three2 = (Long)arrt[8];
							long two = (Long)arrt[9];
							long two2 = (Long)arrt[10];
							long one = (Long)arrt[11];
							long one2 = (Long)arrt[12];
							double avgt = (Double)arrt[13];
							double avgt2 = (Double)arrt[14];
							long question = (Long)arrt[15];
							long question2 = (Long)arrt[16];
							
							Target t = new Target();
							t.setName(namet);
							t.setCount(count);
							t.setCount2(count2);
							t.setFive(five);
							t.setFive2(five2);
							t.setFour(four);
							t.setFour2(four2);
							t.setThree(three);
							t.setThree2(three2);
							t.setTwo(two);
							t.setTwo2(two2);
							t.setOne(one);
							t.setOne2(one2);
							t.setAverage(avgt);
							t.setAverage2(avgt2);
							t.setProblematic(question);
							t.setProblematic2(question2);
							ts.add(t);
						}
						p.setTarget(ts);
					}
					c.setProject(ps);
					//确定合并的单元格
					CellRangeAddress r1 = new CellRangeAddress(startRowForClass, endRowForClass-1, 0, 0);
					CellRangeAddress r2 = new CellRangeAddress(startRowForClass, endRowForClass-1, 1, 1);
					CellRangeAddress r3 = new CellRangeAddress(startRowForClass, endRowForClass-1, 2, 2);
					CellRangeAddress r4 = new CellRangeAddress(startRowForClass, endRowForClass-1, 3, 3);
					ranges.add(r1);
					ranges.add(r2);
					ranges.add(r3);
					ranges.add(r4);
					startRowForClass = endRowForClass;
				}
				map.put("data", data);
			}
		}
		
		//生成文件，写到输入流，然后删除文件
		String destFilePath = ServletActionContext.getRequest().getRealPath("/WEB-INF/excel/generate/") + "/" + UUID.randomUUID().toString() + ".xlsx";
		try {
			InputStream inJxls = new FileInputStream(srcFilePath);
			OutputStream outJxls = new FileOutputStream(destFilePath);
			
			Workbook wb = new XLSTransformer().transformXLS(inJxls, map);
			
			//合并单元格
			Sheet sheet = wb.getSheetAt(0);
			for(int i = 0; i < ranges.size(); i++){
				
				CellRangeAddress cellRange = ranges.get(i);
				if(cellRange.getFirstRow() < cellRange.getLastRow()){
					
					sheet.addMergedRegion(cellRange);
				}
			}
			
			//特殊处理汇总对比的bug
			if(task.getProperty() == EvaluationReportProperty.compare && task.getType() == EvaluationReportType.together){
				
				List<Classes> list = (List<Classes>)map.get("data");
				int rowIndex = 5;
				for(Classes c : list){
					
					for(Project p : c.getProject()){
						
						for(Target t : p.getTarget()){
							
							Row row = sheet.getRow(rowIndex);
							Cell cell = row.getCell(9);
							cell.setCellValue(t.getCount());
							cell = row.getCell(10);
							cell.setCellValue(t.getCount2());
							cell = row.getCell(12);
							cell.setCellValue(t.getFive());
							cell = row.getCell(13);
							cell.setCellValue(t.getFive2());
							cell = row.getCell(15);
							cell.setCellValue(t.getFour());
							cell = row.getCell(16);
							cell.setCellValue(t.getFour2());
							cell = row.getCell(18);
							cell.setCellValue(t.getThree());
							cell = row.getCell(19);
							cell.setCellValue(t.getThree2());
							cell = row.getCell(21);
							cell.setCellValue(t.getTwo());
							cell = row.getCell(22);
							cell.setCellValue(t.getTwo2());
							cell = row.getCell(24);
							cell.setCellValue(t.getOne());
							cell = row.getCell(25);
							cell.setCellValue(t.getOne2());
							cell = row.getCell(27);
							cell.setCellValue(t.getAverage());
							cell = row.getCell(28);
							cell.setCellValue(t.getAverage2());
							cell = row.getCell(30);
							cell.setCellValue(t.getProblematic());
							cell = row.getCell(31);
							cell.setCellValue(t.getProblematic2());
							rowIndex++;
						}
					}
				}
			}
			
			CellStyle style_red = wb.createCellStyle();
			style_red.setBorderTop(CellStyle.BORDER_THIN);
			style_red.setBorderRight(CellStyle.BORDER_THIN);
			style_red.setBorderBottom(CellStyle.BORDER_THIN);
			style_red.setBorderLeft(CellStyle.BORDER_THIN);
			style_red.setAlignment(CellStyle.ALIGN_LEFT);
			style_red.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			Font font_red = wb.createFont();
			font_red.setColor(HSSFColor.RED.index);
			style_red.setFont(font_red);
			
			CellStyle style_green = wb.createCellStyle();
			style_green.setBorderTop(CellStyle.BORDER_THIN);
			style_green.setBorderRight(CellStyle.BORDER_THIN);
			style_green.setBorderBottom(CellStyle.BORDER_THIN);
			style_green.setBorderLeft(CellStyle.BORDER_THIN);
			style_green.setAlignment(CellStyle.ALIGN_LEFT);
			style_green.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			Font font_green = wb.createFont();
			font_green.setColor(HSSFColor.BLUE.index);
			style_green.setFont(font_green);
			
			for(int[] cellIndex : formats){
				
				Row row = sheet.getRow(cellIndex[0]);
				Cell cell = row.getCell(cellIndex[1]);
				
				Cell cell_1 = row.getCell(cellIndex[1] - 1);
				Cell cell_2 = row.getCell(cellIndex[1] - 2);
				double v1 = cell_1.getNumericCellValue(); 
				double v2 = cell_2.getNumericCellValue();
				
				if(v2 == 0){
					
					cell.setCellValue("+∞");
				}else{
					
					double v = (v1 - v2) / v2;
					if(v > 0){
						
						cell.setCellStyle(style_red);
					}else if(v < 0){
						
						cell.setCellStyle(style_green);
					}
					cell.setCellValue(v);
				}
			}
			
			wb.write(outJxls);
			File destFile = new File(destFilePath);
			
			if(destFile.exists()){
				
				InputStream in = new FileInputStream(destFile);
				byte[] buffer = new byte[1024];
				int readed = 0;
				while((readed = in.read(buffer)) > 0){
					
					out.write(buffer, 0, readed);
				}
				inJxls.close();
				outJxls.close();
				in.close();
				out.close();
				destFile.delete();
			}
		} catch (ParsePropertyException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		File file = generateExcel(timeSegments, sheet, out);
//		EmailUtil.send(EmailUtil.username, task.getName(), "系统自动生成报表", file);
	}
}