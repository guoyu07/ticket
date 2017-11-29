package com.ticket.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.ticket.pojo.Pagination;
import com.ticket.util.PaginationTagUtil;

/**
 * 分页标签
 * @ClassName: PaginationTag   
 * @Description: TODO(这里用一句话描述这个类的作用)   
 * @author HiSay  
 * @date Oct 12, 2013 9:05:15 AM   
 *
 */
public class PaginationTag extends TagSupport {

	private String url = null; // 访问的url(参数)
	private Integer pageSize; // 每页显示的条数
	private Integer currentPage = 1; // 当前页 (参数)
	private Integer totalPage = 0; // 每页显示的条数(参数)
	private Integer totalCount = 0; // 总的数据量(参数)

	private Integer showNumberCount = 6; // 中间显示的页码 默认显示6个(参数)

	private String firstPageHtml = null; // 首页
	private String prePageHtml = null; // 上一页
	private String nextPageHtml = null; // 下一页
	private String endPageHtml = null; // 尾页
	private String showNumberHtml = null; // 中间显示的数字
	private String targhtml = null; // 目标页
	private String pageInfoHtml = null; // 分页组件信息

	private Pagination pagination = null;
	private PaginationTagUtil paginatonUtil = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int doAfterBody() throws JspException {
		return super.doAfterBody();
	}

	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			// 初始化工作
			init();
			JspWriter out = pageContext.getOut();
			StringBuffer buff = new StringBuffer();
			buff.append("<div class='mang_page'>");
			buff.append("<div class='page_left'>");
			// 共显示几条等信息
			getPageInfo();
			buff.append(this.getPageInfoHtml());
			buff.append("</div>");
			buff.append("<div class='page_right'>");
			// 首页
			getFritPageHtml(url);
			buff.append(this.getFirstPageHtml());
			// 上一页
			getPrePageHtml(url);
			buff.append(this.getPrePageHtml());
			// 获取中间显示的页码
			getShowNumberHtml(this.currentPage, this.totalPage,
					this.showNumberCount, this.url);
			buff.append(this.getShowNumberHtml());
			// 下一页
			getNextPageHtml(url);
			buff.append(this.getNextPageHtml());
			// 尾页
			getEndPageHtml(url);
			buff.append(this.getEndPageHtml());
			// 输入目标
			getTargetHtml();
			buff.append(this.getTarghtml());
			buff.append("</div>");
			buff.append("</div>");
			out.println(buff.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	/**
	 * 获取中间显示的页码
	 * 
	 * @param currentPage
	 *            当前页
	 * @param totalPage
	 *            总页数
	 * @param showNumberCount
	 *            默认显示的页码
	 * @param url
	 *            访问路径
	 */
	private void getShowNumberHtml(Integer currentPage, Integer totalPage,
			Integer showNumberCount, String url) {
		//将偶数分页长度转换为奇数分页长度
		if(showNumberCount % 2 == 0) {
			showNumberCount = showNumberCount + 1;
		}
		
		//计算前后的步长
		int step = (showNumberCount - 1) / 2;
		//计算当前页前面的页码
		int start = currentPage - step;
		if(start <= 0) {
			start = 1;
			step = showNumberCount - 1; //修正末页
		}
		//计算当前页后面的页面
		int end = currentPage + step;
		if(end >= totalPage) {
			end = totalPage;
			start = totalPage - showNumberCount + 1; //修正首页
			if(start <= 0) {
				start = 1;
			}
		}
		
		StringBuffer buff = new StringBuffer();
		getInfoStr(buff, start, end, totalPage);
		this.setShowNumberHtml(buff.toString());
	}

	/**
	 * 
	 * @Title: PaginationTag
	 * @Description: 拼接中间页码   
	 * @param @param buff 字符缓冲
	 * @param @param index 开始页
	 * @param @param end 结束页
	 * @param @param totalPage 总页 
	 * @return 
	 * @throws
	 */
	private void getInfoStr(StringBuffer buff, int index, int end, int totalPage) {
		for (long i = index; i <= end; i++) {
			if (i == currentPage) {
				String newUrl = PaginationTagUtil.buildUrl(url, i);
				buff.append("<em><a class='hover' href='" + newUrl + "'>" + i
						+ "</a></em>");
			} else {
				String newUrl = PaginationTagUtil.buildUrl(url, i);
				buff.append("<em><a class='' href='" + newUrl + "'>" + i
						+ "</a></em>");
			}
		}
		//加前省略号
		if(index - 1 > 1) {
			String newUrl = PaginationTagUtil.buildUrl(url, 1L);
			buff.insert(0, "<em><a href='" + newUrl + "'>" + 1
					+ "</a></em>" + "<em>...</em>");
		} else if(index -1 == 1) { //连续号，不加省略号
			String newUrl = PaginationTagUtil.buildUrl(url, 1L);
			buff.insert(0, "<em><a href='" + newUrl + "'>" + 1
					+ "</a></em>");
		}
		//加后省略号
		if(end + 1 < totalPage) {
			String newUrl = PaginationTagUtil.buildUrl(url, Long.valueOf(totalPage));
			buff.append("<em>...</em>" + "<em><a href='" + newUrl + "'>" + totalPage
					+ "</a></em>");
		} else if(end + 1 == totalPage){ //连续号，不加省略号
			String newUrl = PaginationTagUtil.buildUrl(url, Long.valueOf(totalPage));
			buff.append("<em><a href='" + newUrl + "'>" + totalPage
					+ "</a></em>");
		}
	}
	
	/**
	 * 获取跳转目标html
	 */
	@SuppressWarnings("static-access")
	private void getTargetHtml() {
		this.setTarghtml(this.paginatonUtil.getTargetPage(url, this.getPagination().getRowsPerPage()));
	}

	/**
	 * 获取尾页html
	 * 
	 * @param url2
	 */
	@SuppressWarnings("static-access")
	private void getEndPageHtml(String url) {
		if (this.pagination.lastEnable()) {
			this.setEndPageHtml(this.paginatonUtil.enableLastPage(url));
		} else {
			this.setEndPageHtml(this.paginatonUtil.disableLastPage());
		}

	}

	/**
	 * 获取下一页html
	 * 
	 * @param url2
	 */
	@SuppressWarnings("static-access")
	private void getNextPageHtml(String url) {
		if (this.pagination.nextEnable()) {
			this.setNextPageHtml(this.paginatonUtil.enableNextPage(url));
		} else {
			this.setNextPageHtml(this.paginatonUtil.disableNextPage());
		}
	}

	/**
	 * 获取上一页html
	 * 
	 * @param url2
	 */
	@SuppressWarnings("static-access")
	private void getPrePageHtml(String url) {
		if (this.pagination.previousEnable()) {
			this.setPrePageHtml(this.paginatonUtil.endablePrePage(url));
		} else {
			this.setPrePageHtml(this.paginatonUtil.disablePrePage());
		}
	}

	/**
	 * 获取第一页html
	 * 
	 * @param url2
	 */
	@SuppressWarnings("static-access")
	private void getFritPageHtml(String url) {
		if (this.pagination.firstEnable()) {
			this.setFirstPageHtml(this.paginatonUtil.enableFristpage(url));
		} else {
			this.setFirstPageHtml(this.paginatonUtil.disableFristPage());
		}

	}

	/**
	 * 分页组件信息
	 */
	private void getPageInfo() {
		this.setPageInfoHtml(this.paginatonUtil.getPageInfoHtml(this.pageSize,
				this.url, this.currentPage));
	}

	/**
	 * 初始化工作
	 */
	private void init() {
		if (pageSize != null && pageSize >= 0 && totalCount != null
				&& totalCount >= 0 && this.currentPage != null
				&& this.currentPage >= 0) {
			this.pagination = PaginationTagUtil.createPage(this.pageSize,
					this.totalCount, (this.currentPage - 1) * this.pageSize);
			this.totalPage = Integer.valueOf(String.valueOf(this.pagination.getPages())) ;
		} else {
			this.pagination = PaginationTagUtil.createPage(0, 0, 0);
			this.totalPage = Integer.valueOf(String.valueOf(this.pagination.getPages())) ;
		}
		this.paginatonUtil = new PaginationTagUtil(pagination);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public String getFirstPageHtml() {
		return firstPageHtml;
	}

	public void setFirstPageHtml(String firstPageHtml) {
		this.firstPageHtml = firstPageHtml;
	}

	public String getPrePageHtml() {
		return prePageHtml;
	}

	public void setPrePageHtml(String prePageHtml) {
		this.prePageHtml = prePageHtml;
	}

	public String getNextPageHtml() {
		return nextPageHtml;
	}

	public void setNextPageHtml(String nextPageHtml) {
		this.nextPageHtml = nextPageHtml;
	}

	public String getEndPageHtml() {
		return endPageHtml;
	}

	public void setEndPageHtml(String endPageHtml) {
		this.endPageHtml = endPageHtml;
	}

	

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public PaginationTagUtil getPaginatonUtil() {
		return paginatonUtil;
	}

	public void setPaginatonUtil(PaginationTagUtil paginatonUtil) {
		this.paginatonUtil = paginatonUtil;
	}

	public Integer getShowNumberCount() {
		return showNumberCount;
	}

	public void setShowNumberCount(Integer showNumberCount) {
		this.showNumberCount = showNumberCount;
	}

	public String getShowNumberHtml() {
		return showNumberHtml;
	}

	public void setShowNumberHtml(String showNumberHtml) {
		this.showNumberHtml = showNumberHtml;
	}

	public String getTarghtml() {
		return targhtml;
	}

	public void setTarghtml(String targhtml) {
		this.targhtml = targhtml;
	}

	public String getPageInfoHtml() {
		return pageInfoHtml;
	}

	public void setPageInfoHtml(String pageInfoHtml) {
		this.pageInfoHtml = pageInfoHtml;
	}

}
