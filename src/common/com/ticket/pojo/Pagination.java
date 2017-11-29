package com.ticket.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页实体类
 * @ClassName: Pagination   
 * @Description: TODO(这里用一句话描述这个类的作用)   
 * @author HiSay  
 * @date Jul 8, 2013 6:03:06 PM   
 *
 */
@SuppressWarnings("unchecked")
public class Pagination extends BasePojo implements Serializable {

    private static final long serialVersionUID = 1L;
    public final static String FIRST_ACTION = "First"; // 执行跳到第一页操作
    public final static String NEXT_ACTION = "Next"; // 执行跳到下一页操作
    public final static String PREVIO_ACTION = "Prev"; // 执行跳到上一页操作
    public final static String LAST_ACTION = "Last"; // 执行跳到最一页操作
    public final static String CURRENT_TAG = "currentPage"; // 当前页数
    public final static String PAGINATION_ACTION_TAG = "paginationAction"; // 缓存操作
    public final static String GOTO_PAGE_ACTION = "gotoPage"; // 执行跳到指定的某一页操作
    public final static String PAGES_GOTO = "pageSelect"; // 执行goto操作时,用户所指定的页数
    private long start; // start表示当前页开始的记录数,start=每页行数*(当前页数-1)
    private long end; // 当前页结束的记录行数
    private long totalCount; // 总行数
    private long rowsPerPage = 15; // 每页行数，默认15
    private long currentPage; // 当前页数
    private long pageListSize = 9;// 页码列表大小，默认9
    private List<Long> pageNumList = new ArrayList<Long>();
	private List pageList = new ArrayList(); //分页里面包含的数据列表
	private Object[] pageObject = null;  //记录数（对象属性）
	private Integer maxPage = 1; //最大页数

    public Pagination() {
        start = 0;
        end = 0;
        currentPage = 1;
        this.totalCount = 0;
    }

    public Pagination(int totalCount) {
        start = 0;
        end = 0;
        currentPage = 1;
        this.totalCount = totalCount;
    }

    public Pagination(int totalCount, int numPerPage) {
        start = 0;
        end = 0;
        this.totalCount = totalCount;
        currentPage = 1;
        if (numPerPage > 0) {
            rowsPerPage = numPerPage;
        }
    }

    /**
	 * 执行翻页动作
	 * 
	 * @param currentPage
	 *            要翻到的目标页码
	 * @return 返回翻页对象
	 */
    public Pagination doPagination(int currentPage) {
        gotoPage(currentPage);
        return this;
    }

    // 设置起始数
    public long getStart() {
        start = rowsPerPage * (currentPage - 1);
        return start;
    }

    // 得到起始数
    public void setStart(int start) {
        if (start < 0) {
            this.start = 0;
        } else if (start >= this.totalCount) {
            this.start = this.totalCount - 1;
        } else {
            this.start = start;
        }
    }

    // 设置当前页的最后一行的在总记录中的顺序(从0开始)
    public void setEnd(int end) {
        this.end = end;
    }

    // 得到当前页的最后一行的在总记录中的顺序(从0开始)
    public long getEnd() {
        if (rowsPerPage * currentPage > this.totalCount) {
            end = this.totalCount - 1;
        } else {
            end = rowsPerPage * currentPage - 1;
        }
        return end;
    }

    // 以下4个方法供控制器(struts)调用

    // 判断能否到第一页;只要能到上一页，肯定就有第一页
    public boolean firstEnable() {
        return previousEnable();
    }

    // 判断能否到上一页
    public boolean previousEnable() {
        return currentPage > 1;// 只要不是第一页，就能到上一页
    }

    // 判断能否到下一页
    public boolean nextEnable() {
        return currentPage * rowsPerPage < this.totalCount;
    }

    // 判断能否到最后一页；只要有下一页，就肯定有最后一页.
    public boolean lastEnable() {
        return nextEnable();
    }

    // 跳到第一页
    public void firstPage() {
        currentPage = 1;
    }

    // 跳到上一页
    public void previousPage(int cPage) {
        currentPage = (cPage - 1) > 0 ? (cPage - 1) : 1;
    }

    // 跳到下一页
    public void nextPage(int cPage) {
        currentPage = cPage + 1;
        if (currentPage * rowsPerPage > this.totalCount) {
            lastPage();
        }
    }

    // 跳到最后一页
    public void lastPage() {
        if (this.totalCount % rowsPerPage == 0) {
            currentPage = this.totalCount / rowsPerPage;
        } else {
            currentPage = this.totalCount / rowsPerPage + 1;
        }
    }

    // 跳到指定的某一页
    public void gotoPage(int pageNumber) {
        if (pageNumber <= 1) {
            currentPage = 1;
        } else if (getTotalCount() < this.getRowsPerPage()) {
            currentPage = 1;
        } else if (pageNumber * rowsPerPage >= this.totalCount) {
            lastPage();
        } else {
            currentPage = pageNumber;
        }
    }

    // 设置总行数
    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    // 得到总行数
    public long getTotalCount() {
        return totalCount;
    }

    // 设置每页行数
    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    // 得到每页行数
    public long getRowsPerPage() {
        return rowsPerPage;
    }

    // 得到总页数
    public long getPages() {
        if (this.totalCount % rowsPerPage == 0)
            return this.totalCount / rowsPerPage;
        else
            return this.totalCount / rowsPerPage + 1;
    }

    // 得到当前页数
    public long getCurrentPage() {
        return currentPage;
    }

    // 设置当前页数
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getPageListSize() {
        return pageListSize;
    }

    // 设置页码列表大小
    public void setPageListSize(int pageListSize) {
        this.pageListSize = pageListSize;
    }

    // 得到页面列表
    public List<Long> getPageNumList() {
        this.pageNumList.removeAll(this.pageNumList);// 设置之前先清空
        long totalPage = getPages();
        if (totalPage > this.pageListSize) {
        	long halfSize = this.pageListSize / 2;
        	long first = 1;
            long end = 1;
            if (this.currentPage - halfSize < 1) { // 当前页靠近最小数1
                first = 1;
                end = this.pageListSize;
            } else if (totalPage - this.currentPage < halfSize) { // 当前页靠近最大数
                first = totalPage - this.pageListSize + 1;
                end = totalPage;
            } else {
                first = this.currentPage - halfSize;
                end = this.currentPage + halfSize;
            }
            for (long i = first; i <= end; i++) {
                this.pageNumList.add(i);
            }
        } else {
            for (long i = 0; i < totalPage; i++) {
                this.pageNumList.add(i + 1);
            }
        }

        return pageNumList;
    }

	public void setPageNumList(List<Long> pageNumList) {
		this.pageNumList = pageNumList;
	}

	public Object[] getPageObject() {
		return pageObject;
	}

	public void setPageObject(Object[] pageObject) {
		this.pageObject = pageObject;
	}

	public Integer getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(Integer maxPage) {
		this.maxPage = maxPage;
	}

	public void setPageList(List pageList) {
		this.pageList = pageList;
	}

	public List getPageList() {
		return pageList;
	}
}