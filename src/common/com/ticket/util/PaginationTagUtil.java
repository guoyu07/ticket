package com.ticket.util;

import com.ticket.pojo.Pagination;

/**
 * 分页标签辅助类
 * @ClassName: PaginationTagUtil   
 * @Description: TODO(这里用一句话描述这个类的作用)   
 * @author HiSay  
 * @date Oct 12, 2013 9:07:05 AM   
 *
 */
public class PaginationTagUtil {
	
	private static Pagination pagination = null;
	public PaginationTagUtil(){
		
	}
	
	public PaginationTagUtil(Pagination  pagination){
		PaginationTagUtil.pagination = pagination;
	}
	
	/**
	 * 
	 * 获取分页组件html
	 */
	@SuppressWarnings("static-access")
	public  String  getPageInfoHtml(Integer  pageSize,String url,Integer currentPage){
		StringBuffer buff=new StringBuffer();
		buff.append("共"+this.pagination.getTotalCount()+"条记录  每页"+this.pagination.getRowsPerPage()+"条  共"+this.pagination.getPages()+"页");
		return buff.toString();
	}
	
	/**
	 * 启用首页
	 */
	public  static String  enableFristpage(String url){
		String  fristPageHtml=null;
		if(pagination.firstEnable()){
			StringBuffer  buff=new StringBuffer();
			String newUrl = buildUrl(url, 1L);
			buff.append("<span class='first'><a href='"+newUrl+"'>首页</a></span>");
			fristPageHtml=buff.toString();
		}
	    return fristPageHtml;	
	}
	
	/**
	 * 关闭首页
	 */
	public static String  disableFristPage(){
		String  fristPageHtml=null;
		if(!pagination.firstEnable()){
			StringBuffer  buff=new StringBuffer();
			buff.append("<span class='first2'>首页</span>");
			fristPageHtml=buff.toString();
		}
	      return fristPageHtml;	
	}
	
	/**
	 * 启用上一页
	 * @param url
	 * @return
	 */
	public  static  String endablePrePage(String url){
		String prePageHtml=null;
		if(pagination.previousEnable()){
			StringBuffer  buff=new StringBuffer();
			String newUrl = buildUrl(url, pagination.getCurrentPage()-1);
			buff.append("<span class='prev'><a href='"+newUrl+"'>上一页</a></span>");
			prePageHtml=buff.toString();
		}
		return prePageHtml;
	}
	
	public static String buildUrl(String url,Long currentPage) {
		int i=-1;
		if(url!=null&&!"".equals(url)){
			i=url.lastIndexOf("?");
			StringBuffer buffer=new StringBuffer(url);
            if(i==-1){
            	//没有"?"
            	buffer.append("?cp="+currentPage+"&ps="+pagination.getRowsPerPage());
            }else{
            	buffer.append("&cp="+currentPage+"&ps="+pagination.getRowsPerPage());
            }	
            return buffer.toString();
		}
		return "";
	}

	/**
	 * 关闭上一页
	 * @return
	 */
	public static  String disablePrePage(){
		String prePageHtml=null;
		if(!pagination.previousEnable()){
			StringBuffer  buff=new StringBuffer();
			buff.append("<span class='prev2'>上一页</span>");
			prePageHtml=buff.toString();
		}
		return prePageHtml;
	}
	/**
	 * 启用下一页
	 * @param url
	 * @return
	 */
	public static String  enableNextPage(String url){
		String nextPageHtml=null;
		if(pagination.nextEnable()){
	    String newUrl=buildUrl(url, pagination.getCurrentPage()+1);
		StringBuffer  buff=new StringBuffer();
		nextPageHtml=buff.append("<B class='next'><a href='"+newUrl+"'>下一页</a></B>").toString();
		}
		return nextPageHtml;
	}
	
	/**
	 * 关闭上一页
	 * @return
	 */
	public  static String disableNextPage(){
		String nextPageHtml=null;
		if(!pagination.nextEnable()){
		StringBuffer  buff=new StringBuffer();
		nextPageHtml=buff.append("<B class='next2'>下一页</B>").toString();
		}
		return nextPageHtml;
	}
	/**
	 * 启用尾页
	 * @return
	 */
	public  static String  enableLastPage(String url){
		String  endPageHtml=null;
		if(pagination.lastEnable()){
			StringBuffer  buff=new StringBuffer();
			 String newUrl=buildUrl(url, pagination.getPages());
			buff.append("<B class='last'><a href='"+newUrl+"'>末页</a></B>");
			endPageHtml=buff.toString();
		}
		return endPageHtml;
	}
	
	/**
	 * 
	 * 关闭尾页
	 */
	public  static String  disableLastPage(){
		String  endPageHtml=null;
		if(!pagination.lastEnable()){
			StringBuffer  buff=new StringBuffer();
			buff.append("<B class='last2'>末页</B>");
			endPageHtml=buff.toString();
		}
		return endPageHtml;
	}
	
	/**
	 * 改变点击的样式
	 * @return
	 */
	public  static String changeClickStatu(){
		
    	  return null;	
	}
	
	/**
	 * 在输入框中输入要到的页面时 先做判断是否输入的页码是和否的页码，是  然后跳到该页面上去
	 * 
	 * 跳转到目标页
	 * @return
	 */
	public  static  String  getTargetPage(String url, Long pageSize){
		StringBuffer  buff=new StringBuffer();
		buff.append("<input class='input' name='' type='text' value='' onblur='getTargetPageNumber(this,"+pagination.getPages()+");' />").
		append("<input class='buttom' name='' type='button' value='' onclick='return false'/>");
		//写在外部引入
		buff.append("<script type='text/javascript'>");
		buff.append("function getTargetPageNumber(obj,totalCount){");
		buff.append("var  page=obj.value;");
		buff.append("if(page==null||page==''){alert('请输入要跳转的页面'); return false;}");
		buff.append("if(isNaN(page)){");
		buff.append("alert('请输入合法的数字'); return false;");
		buff.append("}");
		buff.append("if(page<0||page>totalCount){alert('请输入合法的页码'); return false;}");
		buff.append("else{ window.location.href='"+url+"?cp='+page+'&ps="+ pageSize +"'; return true;}");
		buff.append("}");
		buff.append("</script>");
		return buff.toString();
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		PaginationTagUtil.pagination= pagination;
	}
	
	public static Pagination createPage(Integer pageSize, Integer totalCount,
			Integer pagerOffset) {
		Pagination p = new Pagination();
		p.setRowsPerPage(pageSize);
		p.setTotalCount(totalCount);
		p.setStart(pagerOffset);
		p.setCurrentPage(pagerOffset/pageSize + 1);
		return p;
	}
	
}
