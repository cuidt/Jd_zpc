package com.jd_zpc.bean;

import java.util.List;

/**
 * Created by DELL on 2017/11/13.
 */

public class OrderCreate extends BaseBean {

    /**
     * data : []
     * page : 1
     */

    private String page;
    private List<?> data;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
