package com.example.caigouapp.data;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.List;

public class UserTagResponse implements Serializable {


    private String msg;
    private String code;
    private List<TagsBean> tags;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

}
