package com.example.caigouapp.data;

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

    public static class TagsBean implements Serializable {

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
}
