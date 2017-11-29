package com.ticket.pojo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.ticket.pojo.SqlParam.Condition;
import com.ticket.util.GeneralUtil;

/**
 * 参数组（相当于用括号括起来的一组参数）
 * @author 涂有
 */
public class SqlParamGroup {
	
	/**
	 * 添加条件类型
	 */
	public static enum Logic{
		and,
		or
	}

	/**
	 * 所有参数的集合
	 */
	protected List<Object> params = new LinkedList<Object>();
	
	/**
	 * 参数的拼接的逻辑条件
	 */
	protected List<Logic> logics = new LinkedList<SqlParamGroup.Logic>();
	
	/**
	 * 初始化的时候必须传入一个参数
	 * @param param
	 */
	public SqlParamGroup(SqlParam param) {
		
		this.params.add(param);
	}
	
	/**
	 * 初始化的时候必须传入一个参数
	 * @param field 字段名
	 * @param condition 比较条件
	 * @param param 参数值
	 */
	public SqlParamGroup(String field, Condition condition) {
		
		this.params.add(new SqlParam(field, condition));
	}
	
	/**
	 * 初始化的时候必须传入一个参数
	 * @param field 字段名
	 * @param condition 比较条件
	 * @param param 参数值
	 */
	public SqlParamGroup(String field, Condition condition, Object value) {
		
		this.params.add(new SqlParam(field, condition, value));
	}
	
	/**
	 * 初始化的时候必须传入一个参数
	 * @param field 字段名
	 * @param condition 比较条件
	 * @param param 参数值
	 */
	public SqlParamGroup(String field, Condition condition, Object value, Object value2) {
		
		this.params.add(new SqlParam(field, condition, value, value2));
	}
	
	/**
	 * 逻辑与操作
	 * @param field
	 * @param condition
	 * @param value
	 * @return
	 */
	public SqlParamGroup and(String field, Condition condition){
		
		return and(new SqlParam(field, condition));
	}
	
	/**
	 * 逻辑与操作
	 * @param field
	 * @param condition
	 * @param value
	 * @return
	 */
	public SqlParamGroup and(String field, Condition condition, Object value){
		
		return and(new SqlParam(field, condition, value));
	}
	
	/**
	 * 逻辑与操作
	 * @param field
	 * @param condition
	 * @param value
	 * @return
	 */
	public SqlParamGroup and(String field, Condition condition, Object value, Object value2){
		
		return and(new SqlParam(field, condition, value, value2));
	}
	
	/**
	 * 逻辑与一个条件
	 * @param param
	 * @return
	 */
	public SqlParamGroup and(SqlParam param){
		
		this.params.add(param);
		this.logics.add(Logic.and);
		return this;
	}
	
	/**
	 * 逻辑与一个条件组
	 * @param param
	 * @return
	 */
	public SqlParamGroup and(SqlParamGroup group){
		
		this.params.add(group);
		this.logics.add(Logic.and);
		return this;
	}
	
	/**
	 * 逻辑或一个条件
	 * @param field
	 * @param condition
	 * @param value
	 * @return
	 */
	public SqlParamGroup or(String field, Condition condition){
		
		return or(new SqlParam(field, condition));
	}
	
	/**
	 * 逻辑或一个条件
	 * @param field
	 * @param condition
	 * @param value
	 * @return
	 */
	public SqlParamGroup or(String field, Condition condition, Object value){
		
		return or(new SqlParam(field, condition, value));
	}
	
	/**
	 * 逻辑或一个条件
	 * @param field
	 * @param condition
	 * @param value
	 * @return
	 */
	public SqlParamGroup or(String field, Condition condition, Object value, Object value2){
		
		return or(new SqlParam(field, condition, value, value2));
	}
	
	/**
	 * 逻辑或一个条件
	 * @param param
	 * @return
	 */
	public SqlParamGroup or(SqlParam param){
		
		this.params.add(param);
		this.logics.add(Logic.or);
		return this;
	}
	
	/**
	 * 逻辑或一个条件组
	 * @param param
	 * @return
	 */
	public SqlParamGroup or(SqlParamGroup group){
		
		this.params.add(group);
		this.logics.add(Logic.or);
		return this;
	}
	
	/**
	 * 拼接成sql字符串
	 */
	public String toString(){
		
		String paramsStr = toString(params);
		return paramsStr;
	}
	
	/**
	 * 拼接成sql字符串
	 */
	public String toString(boolean addWhere){
		
		String paramsStr = toString(params);
		if(addWhere){
			
			return GeneralUtil.isNull(paramsStr) ? "" : "where " + paramsStr;
		}
		return paramsStr;
	}
	
	private String toString(List<Object> params){
		
		StringBuilder condition = new StringBuilder();
		for(int i = 0; i < params.size(); i++){
			
			Object param = params.get(i);
			String sql = param.toString();
			
			if(GeneralUtil.isNotNull(sql)){
				
				if(i != 0 && GeneralUtil.isNotNull(condition.toString())){
					
					Logic logic = logics.get(i - 1);
					if(logic == Logic.and){
						
						condition.append(" and ");
					}else if(logic == Logic.or){
						
						condition.append(" or ");
					}
				}
				
				if(param instanceof SqlParam){
					
					condition.append(sql);
				}else{
					
					condition.append("(").append(sql).append(')');
				}
			}
		}
		return condition.toString();
	}
	
	/**
	 * 得到参数的数组
	 * @return
	 */
	public Object[] getParamArray(){
		
		
		return getParamArray(params);
	}
	
	private Object[] getParamArray(List<Object> params){
		
		LinkedList<Object> condition = new LinkedList<Object>();
		for(int i = 0; i < params.size(); i++){
			
			Object param = params.get(i);
			if(param instanceof SqlParam){
				
				SqlParam sqlParam = (SqlParam)param;
				condition.addAll(Arrays.asList(sqlParam.getParamArray()));
			}else{
				
				SqlParamGroup group = (SqlParamGroup)param;
				condition.addAll(Arrays.asList(group.getParamArray()));
			}
		}
		return condition.toArray();
	}
	
	public static void main(String[] args) {
		
		SqlParamGroup group = new SqlParamGroup("name", Condition.like_left, "涂有").and("age", Condition.between, 0, 150);
		SqlParamGroup group2 = new SqlParam("name", Condition.like, "杨瑞").and("age", Condition.between, 20, 300);
		group.or(group2);
		
		System.out.println(group.toString());
		System.out.println(Arrays.toString(group.getParamArray()));
	}
}
