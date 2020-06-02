package com.design.patterns.memeto.states;

public class ContentState implements State {

    private String content;

    public ContentState(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
