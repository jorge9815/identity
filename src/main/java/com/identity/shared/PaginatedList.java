package com.identity.shared;

import java.util.List;

public class PaginatedList<T> {
    private Long total;
    private int page;
    private int perPage;
    private Long pageAmount;
    private List<T> data;

    public PaginatedList(Long total, int page, int perPage, List<T> data) {
        this.total = total;
        this.page = page;
        this.perPage = perPage;
        this.data = data;
        this.pageAmount = total / perPage;
        if(total % perPage > 0){
            this.pageAmount++;
        }
    }

    public Long getPageAmount() {
        return pageAmount;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
