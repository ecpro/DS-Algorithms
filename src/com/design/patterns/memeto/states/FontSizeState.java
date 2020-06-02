package com.design.patterns.memeto.states;

public class FontSizeState implements State {

    private Integer fontSize;

    public FontSizeState(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }


}
