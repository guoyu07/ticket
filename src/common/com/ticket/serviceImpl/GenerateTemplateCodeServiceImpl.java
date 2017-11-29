package com.ticket.serviceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.TemplateCode;
import com.ticket.service.IGenerateTemplateCodeService;
import com.ticket.util.ConvertUtil;
import com.ticket.util.DecoderUtil;
import com.ticket.util.FreeMarkerUitl;
import com.ticket.util.GeneralUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 模板代码生成工具业务接口实现层
 * @ClassName: IGenerateTemplateCodeService   
 * @Description: 可以根据模板文件生成对应的java代码,js代码,jsp代码等.   
 * @author HiSay  
 * @date Jul 31, 2013 3:37:30 PM   
 *
 */
public class GenerateTemplateCodeServiceImpl extends BaseServiceImpl<TemplateCode, Long> implements
		IGenerateTemplateCodeService {

	private static final Log log = LogFactory.getLog(GenerateTemplateCodeServiceImpl.class);
	private static FreeMarkerUitl ftl = new FreeMarkerUitl();
	private static ConvertUtil<String> convertUtil = new ConvertUtil<String>();

	@Override
	public boolean generateJavaCode(String entityName, String entityTitle, String entityDescript, String entityAttribute,
			HttpServletRequest request, HttpServletResponse response, String savePath, String pojoIdType) throws ServiceException {
		try {
			entityName = DecoderUtil.UtfDecoder(entityName);
			entityTitle = DecoderUtil.UtfDecoder(GeneralUtil.filterInvalidCharacterFromContent(entityTitle));
			entityDescript = DecoderUtil.UtfDecoder(GeneralUtil.filterInvalidCharacterFromContent(entityDescript));
			entityAttribute = DecoderUtil.UtfDecoder(entityAttribute);
			List<TemplateCode> attrList = this.parseAttributesToList(entityAttribute);
			String attributesValue = this.getAttributesValue(attrList);
			String jsAttributesValue = this.getJsAttributesValue(attrList);
			String serviceAttributesValue = this.getServiceAttributesValue(attrList);
			ftl.initConfiguration(request, response);
			ftl.writeJavaCode(request, entityName, entityTitle, entityDescript, attrList, 
					attributesValue, jsAttributesValue, serviceAttributesValue, savePath, pojoIdType);
			return true;
		} catch(Exception e) {
			log.info("根据Action.ftl生成对应的Action代码文件失败: " + e.fillInStackTrace());
			return false;
		}
	}
	
	/**
	 * 获取属性并排的字符串
	 * @Title: GenerateTemplateCodeServiceImpl
	 * @Description: 格式如下: String name, String password   
	 * @param @param attrList
	 * @param @return    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	private String getAttributesValue(List<TemplateCode> attrList) {
		try {
			if(attrList != null && !attrList.isEmpty()) {
				StringBuffer attrBuffer = new StringBuffer();
				for(TemplateCode code : attrList) {
					attrBuffer.append(code.getAttrDataType()).append(" ").append(code.getAttrName()).append(",");
				}
				return attrBuffer.toString();
			}
			return "";
		} catch(Exception e) {
			return "";
		}
	}
	
	/**
	 * 获取属性并排的字符串
	 * @Title: GenerateTemplateCodeServiceImpl
	 * @Description: 格式如下: name|password|loginId   
	 * @param @param attrList
	 * @param @return    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	private String getJsAttributesValue(List<TemplateCode> attrList) {
		try {
			if(attrList != null && !attrList.isEmpty()) {
				StringBuffer attrBuffer = new StringBuffer();
				for(TemplateCode code : attrList) {
					attrBuffer.append(code.getAttrName()).append("|");
				}
				String value = attrBuffer.toString();
				value = value.substring(0, value.length() - 1);
				return value;
			}
			return "";
		} catch(Exception e) {
			return "";
		}
	}
	
	/**
	 * 获取属性并排的字符串
	 * @Title: GenerateTemplateCodeServiceImpl
	 * @Description: 格式如下: name,loginId   
	 * @param @param attrList
	 * @param @return    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	private String getServiceAttributesValue(List<TemplateCode> attrList) {
		try {
			if(attrList != null && !attrList.isEmpty()) {
				StringBuffer attrBuffer = new StringBuffer();
				for(TemplateCode code : attrList) {
					attrBuffer.append(code.getAttrName()).append(", ");
				}
				String value = attrBuffer.toString();
				value = value.substring(0, value.length() - 1);
				return value;
			}
			return "";
		} catch(Exception e) {
			return "";
		}
	}
	
	/**
	 * 把属性字段解析为列表
	 * @Title: GenerateTemplateCodeServiceImpl
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @param entityAttribute 属性值 格式如下:String##name##@Column(length=50)##用户名称$$
	 * @param @return    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	private List<TemplateCode> parseAttributesToList(String entityAttribute) {		
		try {
			List<String> list = convertUtil.stringToList(entityAttribute, "$$");
			if(list != null && !list.isEmpty()) {
				List<TemplateCode> resultList = new ArrayList<TemplateCode>();
				for(String attributeStr : list) {
					List<String> attrList = convertUtil.stringToList(attributeStr, "##");
					if(attrList != null && !attrList.isEmpty()) {
						TemplateCode code = new TemplateCode();
						code.setAttrAnnotation(attrList.get(2).trim());
						code.setAttrComment(attrList.get(3).trim());
						code.setAttrDataType(attrList.get(0).trim());
						code.setAttrName(attrList.get(1).trim());
						code.setAttrUnicodeComment(this.string2Unicode(code.getAttrComment()).trim());
						String firstLetter = code.getAttrName().substring(0, 1).toUpperCase();
						String entityLowerCase = code.getAttrName().substring(1, code.getAttrName().length());
						code.setFirstCapAttrName(firstLetter + entityLowerCase);
						resultList.add(code);
					}
				}
				return resultList;
			}
			return null;
		} catch(Exception e) {
			log.info("根据entityAttributes生成模板代码List失败 :" + e.fillInStackTrace());
			return null;
		}
	}
	
	/**
	 * 把属性的注释转换为Unicode注释
	 * @Title: GenerateTemplateCodeServiceImpl
	 * @Description:    
	 * @param @param attrComment
	 * @param @return    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	private String string2Unicode(String attrComment) {
		try {
			StringBuffer out = new StringBuffer("");
			byte[] bytes = attrComment.getBytes("unicode");
			for (int i = 2; i < bytes.length - 1; i += 2) {
				out.append("u");
				String str = Integer.toHexString(bytes[i + 1] & 0xff);
				for (int j = str.length(); j < 2; j++) {
					out.append("0");
				}
				String str1 = Integer.toHexString(bytes[i] & 0xff);

				out.append(str);
				out.append(str1);
				out.append(" ");
			}
			return out.toString().toUpperCase();
		} catch (Exception e) {
			log.info("根据entityAttributes生成模板代码List失败 :" + e.fillInStackTrace());
			return null;
		}
	}

	@Override
	public boolean delete(String entityName, Integer isDeleteAction, Integer isDeleteService, Integer isDeleteServiceImpl, Integer isDeleteJs, 
			Integer isDeleteJsp, Integer isDeleteMessage, Integer isDeletePojo, String deletePath) throws ServiceException {
		try {
			//获取工程所在的根目录
			String targetBasePath = GeneralUtil.getPropertyValue("project_base_path");
			File file = null;
			String fileName = null;
			String lastFileName = null;
			String firstLetter = entityName.substring(0, 1).toLowerCase();
			String entityLowerCase = entityName.substring(1, entityName.length());
			String lowerCase = firstLetter + entityLowerCase;
			
			if(isDeletePojo.intValue() == ContextConstants.STATUS_OF_ONE) {
				fileName = entityName + ".java";
				lastFileName = targetBasePath + GeneralUtil.getPropertyValue(deletePath) + "pojo/" + fileName;
				file = new File(lastFileName);
				if(file.exists()) {
					file.delete();
				}
			}
			
			if(isDeleteAction.intValue() == ContextConstants.STATUS_OF_ONE) {
				fileName = entityName + "Action.java";
				lastFileName = targetBasePath + GeneralUtil.getPropertyValue(deletePath) + "action/" + fileName;
				file = new File(lastFileName);
				if(file.exists()) {
					file.delete();
				}
			}
			
			if(isDeleteMessage.intValue() == ContextConstants.STATUS_OF_ONE) {
				fileName = entityName + "Action_zh_CN.properties";
				lastFileName = targetBasePath + GeneralUtil.getPropertyValue(deletePath) + "action/" + fileName;
				file = new File(lastFileName);
				if(file.exists()) {
					file.delete();
				}
			}
			
			if(isDeleteService.intValue() == ContextConstants.STATUS_OF_ONE) {
				fileName = "I" + entityName + "Service.java";
				lastFileName = targetBasePath + GeneralUtil.getPropertyValue(deletePath) + "service/" + fileName;
				file = new File(lastFileName);
				if(file.exists()) {
					file.delete();
				}
			}
			
			if(isDeleteServiceImpl.intValue() == ContextConstants.STATUS_OF_ONE) {
				fileName = entityName + "ServiceImpl.java";
				lastFileName = targetBasePath + GeneralUtil.getPropertyValue(deletePath) + "serviceImpl/" + fileName;
				file = new File(lastFileName);
				if(file.exists()) {
					file.delete();
				}
			}
			
			if(isDeleteJs.intValue() == ContextConstants.STATUS_OF_ONE) {
				fileName = lowerCase + ".js";
				lastFileName = targetBasePath + GeneralUtil.getPropertyValue("manager_js_path") + "/" + fileName;
				file = new File(lastFileName);
				if(file.exists()) {
					file.delete();
				}
			}
			
			if(isDeleteJsp.intValue() == ContextConstants.STATUS_OF_ONE) {
				//addEntity.jsp
				fileName = "add" + entityName + ".jsp";
				lastFileName = targetBasePath + GeneralUtil.getPropertyValue("manager_jsp_path") + GeneralUtil.firstCharacterToLowerCase(entityName) + "/" + fileName;
				file = new File(lastFileName);
				if(file.exists()) {
					file.delete();
				}
				//editEntity.jsp
				fileName = "edit" + entityName + ".jsp";
				lastFileName = targetBasePath + GeneralUtil.getPropertyValue("manager_jsp_path") + GeneralUtil.firstCharacterToLowerCase(entityName) + "/" + fileName;
				file = new File(lastFileName);
				if(file.exists()) {
					file.delete();
				}
				//manageEntity.jsp
				fileName = "manage" + entityName + ".jsp";
				lastFileName = targetBasePath + GeneralUtil.getPropertyValue("manager_jsp_path") + GeneralUtil.firstCharacterToLowerCase(entityName) + "/" + fileName;
				file = new File(lastFileName);
				if(file.exists()) {
					file.delete();
				}
				//recycleEntity.jsp
				fileName = "recycle" + entityName + ".jsp";
				lastFileName = targetBasePath + GeneralUtil.getPropertyValue("manager_jsp_path") + GeneralUtil.firstCharacterToLowerCase(entityName) + "/" + fileName;
				file = new File(lastFileName);
				if(file.exists()) {
					file.delete();
				}
			}
			return true;
		} catch(Exception e) {
			log.info("根据entityName实体名称删除对应的文件失败: " + e.fillInStackTrace());
			return false;
		}
	} 

}
