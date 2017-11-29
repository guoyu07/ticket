package com.ticket.pojo;

import java.util.Arrays;

import com.ticket.util.GeneralUtil;

/**
 * 表示一个hql或者sql参数
 * @author 涂有
 */
public class SqlParam {
	
	/**
	 * 字段名
	 */
	private String field;
	
	/**
	 * 字段名
	 */
	private Condition condition;
	
	/**
	 * 参数值
	 * 没有（is_null, is_not_null）
	 * 一个
	 * 两个（between）
	 */
	private Object value, value2;
	
	/**
	 * 是否已经生成
	 */
//	private boolean generated;
	
	/**
	 * 条件
	 * @author 参数条件
	 */
	public static enum Condition{
		
		between,
		like, //key like '%value%'
		like_left, //前缀相同：key like '%value'，这种方式可以用到索引
		not_like, //前缀相同：key like '%value'
		not_like_left, //前缀相同：key like '%value'，这种方式可以用到索引
		is_null,
		is_not_null,
		in, //in
		not_in, //not in
		eq, //就是 EQUAL等于
		ne, //就是 NOT EQUAL不等于
		gt, //就是 GREATER THAN大于
		lt, //就是 LESS THAN小于
		ge, //就是 GREATER THAN OR EQUAL 大于等于
		le, //就是 LESS THAN OR EQUAL 小于等于
//		exists, //存在
//		not_exists, //不存在
	}
	
	/**
	 * 参数值有两个的情况，当然也可以设为null
	 * @param field
	 * @param condition
	 * @param value
	 */
	public SqlParam(String field, Condition condition){
		
		this(field, condition, null, null);
	}
	
	/**
	 * 参数值有两个的情况，当然也可以设为null
	 * @param field
	 * @param condition
	 * @param value
	 */
	public SqlParam(String field, Condition condition, Object value){
		
		this(field, condition, value, null);
	}
	
	/**
	 * 参数值有两个的情况，当然也可以设为null
	 * @param field
	 * @param condition
	 * @param value
	 */
	public SqlParam(String field, Condition condition, Object value, Object value2){
		
		this.field = field;
		this.condition = condition;
		this.value = value;
		this.value2 = value2;
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
	 * 逻辑与操作
	 * @param param
	 * @return
	 */
	public SqlParamGroup and(SqlParam param){
		
		SqlParamGroup group = new SqlParamGroup(this);
		group.and(param);
		return group;
	}
	
	/**
	 * 逻辑与操作
	 * @param g
	 * @return
	 */
	public SqlParamGroup and(SqlParamGroup g){
		
		SqlParamGroup group = new SqlParamGroup(this);
		group.and(g);
		return group;
	}
	
	/**
	 * 逻辑或操作
	 * @param field
	 * @param condition
	 * @param value
	 * @return
	 */
	public SqlParamGroup or(String field, Condition condition){
		
		return or(new SqlParam(field, condition));
	}
	
	/**
	 * 逻辑或操作
	 * @param field
	 * @param condition
	 * @param value
	 * @return
	 */
	public SqlParamGroup or(String field, Condition condition, Object value){
		
		return or(new SqlParam(field, condition, value));
	}
	
	/**
	 * 逻辑或操作
	 * @param field
	 * @param condition
	 * @param value
	 * @return
	 */
	public SqlParamGroup or(String field, Condition condition, Object value, Object value2){
		
		return or(new SqlParam(field, condition, value, value2));
	}
	
	/**
	 * 逻辑或操作
	 * @param param
	 * @return
	 */
	public SqlParamGroup or(SqlParam param){
		
		SqlParamGroup group = new SqlParamGroup(this);
		group.or(param);
		return group;
	}
	
	/**
	 * 逻辑或操作
	 * @param g
	 * @return
	 */
	public SqlParamGroup or(SqlParamGroup g){
		
		SqlParamGroup group = new SqlParamGroup(this);
		group.or(g);
		return group;
	}
	
	/**
	 * 拼接成sql字符串，条件值也拼接在sql里面
	 */
	public String toString(){
		
		StringBuilder sb = new StringBuilder();
		if(condition == Condition.is_null) {
			
			sb.append(field).append(" is null");
		}else if(condition == Condition.is_not_null) {
			
			sb.append(field).append(" is not null");
		}else if (condition == Condition.between && GeneralUtil.isNotNull(value) && GeneralUtil.isNotNull(value2)) {
			
			sb.append(field).append(" between ? and ?");
		}else if((condition == Condition.like || condition == Condition.like_left) && GeneralUtil.isNotNull(value)) {
			
			sb.append(field).append(" like ?");
		}else if((condition == Condition.not_like || condition == Condition.not_like_left) && GeneralUtil.isNotNull(value)) {
			
			sb.append(field).append(" not like ?");
		}else if(condition == Condition.eq && GeneralUtil.isNotNull(value)) {
			
			sb.append(field).append(" = ?");
		}else if(condition == Condition.ne && GeneralUtil.isNotNull(value)) {
			
			sb.append(field).append(" != ?");
		}else if(condition == Condition.gt && GeneralUtil.isNotNull(value)) {
			
			sb.append(field).append(" > ?");
		}else if(condition == Condition.lt && GeneralUtil.isNotNull(value)) {
			
			sb.append(field).append(" < ?");
		}else if(condition == Condition.ge && GeneralUtil.isNotNull(value)) {
			
			sb.append(field).append(" >= ?");
		}else if(condition == Condition.le && GeneralUtil.isNotNull(value)) {
			
			sb.append(field).append(" <= ?");
		}else if(condition == Condition.in && GeneralUtil.isNotNull(value)) {
			
			if(value instanceof Object[]){
				
				Object[] objs = (Object[])value;
				if(objs.length > 0){
					
					sb.append("(");
					for(int i = 0; i < objs.length; i++){
						
						sb.append(field).append(" = ?");
						if(i != objs.length - 1){
							
							sb.append(" or ");
						}
					}
					sb.append(")");
				}
			}
		}else if(condition == Condition.not_in && GeneralUtil.isNotNull(value)) {
			
			if(value instanceof Object[]){
				
				Object[] objs = (Object[])value;
				if(objs.length > 0){
					
					sb.append("(");
					for(int i = 0; i < objs.length; i++){
						
						sb.append(field).append(" != ?");
						if(i != objs.length - 1){
							
							sb.append(" and ");
						}
					}
					sb.append(")");
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 得到参数的数组
	 * @return
	 */
	public Object[] getParamArray(){
		
		if (condition == Condition.between && GeneralUtil.isNotNull(value) && GeneralUtil.isNotNull(value2)) {

			return new Object[]{value, value2};
		}else if((condition == Condition.like || condition == Condition.not_like) && GeneralUtil.isNotNull(value)) {
			
			return new Object[]{new StringBuilder("%").append(value).append('%').toString()};
		}else if((condition == Condition.like_left || condition == Condition.not_like_left) && GeneralUtil.isNotNull(value)) {
			
			return new Object[]{new StringBuilder().append(value).append('%').toString()};
		}else if(condition == Condition.eq && GeneralUtil.isNotNull(value)) {
			
			return new Object[]{value};
		}else if(condition == Condition.ne && GeneralUtil.isNotNull(value)) {
			
			return new Object[]{value};
		}else if(condition == Condition.gt && GeneralUtil.isNotNull(value)) {
			
			return new Object[]{value};
		}else if(condition == Condition.lt && GeneralUtil.isNotNull(value)) {
			
			return new Object[]{value};
		}else if(condition == Condition.ge && GeneralUtil.isNotNull(value)) {
			
			return new Object[]{value};
		}else if(condition == Condition.le && GeneralUtil.isNotNull(value)) {
			
			return new Object[]{value};
		}else if(condition == Condition.in && GeneralUtil.isNotNull(value)) {
			
			if(value instanceof Object[]){
				
				Object[] objs = (Object[])value;
				if(objs.length > 0){
					
					return objs;
				}
			}
		}else if(condition == Condition.not_in && GeneralUtil.isNotNull(value)) {
			
			if(value instanceof Object[]){
				
				Object[] objs = (Object[])value;
				if(objs.length > 0){
					
					return objs;
				}
			}
		}
		return new Object[]{};
	}
	
	public static void main(String[] args) {
		
		SqlParam param = new SqlParam("name", Condition.eq, "涂有");
		SqlParamGroup group = param.or("age", Condition.between, 0, 150);
		group.or("face", Condition.is_null);
		System.out.println(group.toString());
		System.out.println(Arrays.toString(group.getParamArray()));
	}
}
