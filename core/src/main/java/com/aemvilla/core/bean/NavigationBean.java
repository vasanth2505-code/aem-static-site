package com.aemvilla.core.bean;

public class NavigationBean {
    private String text;
    private String url;

    public NavigationBean(String text, String url) {
        this.text = text;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
