package com.example.caigouapp.data;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class TagsBean extends LitePalSupport implements Serializable {

    private int tag_id;
    private String tag;
    private int status = 0;

    public TagsBean() {
    }

    public TagsBean(int tag_id, String tag) {
        this.tag_id = tag_id;
        this.tag = tag;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
