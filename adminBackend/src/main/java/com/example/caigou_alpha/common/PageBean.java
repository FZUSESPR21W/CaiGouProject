package com.example.caigou_alpha.common;

public class PageBean {
    private Integer currentPage;
    private Integer startPage;
    private Integer endPage;
    private Integer maxShowPageCount = 10;
    public Integer getEndPage() {
        return endPage;
    }
    public void setEndPage(Integer endPage) {
        this.endPage = endPage;
    }
    public Integer getStartPage() {
        return startPage;
    }
    private Integer startIndex;
    private Integer pageSize=5;
    private Integer totalCount;
    private Integer totalPage;
    public void fix()
    {
        startPage = currentPage - maxShowPageCount / 2;
        if (startPage < 1)
            startPage = 1;
        endPage = currentPage + maxShowPageCount / 2;
        if (endPage > totalPage)
            endPage = totalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        this.startIndex=(this.currentPage-1)*this.pageSize;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public Integer getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        //计算总页数
        this.totalPage=(int)Math.ceil((this.totalCount*1.0/this.pageSize));
    }
    public Integer getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
    public Integer getStartIndex() {
        return startIndex;
    }
    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

}
