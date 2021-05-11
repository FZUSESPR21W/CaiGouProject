package com.example.caigouapp;

import java.io.Serializable;

public class Step implements Serializable {
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Step(String imgUrl, String content) {
        this.imgUrl = imgUrl;
        this.content = content;
    }

    private String imgUrl;
    private String content;
}
