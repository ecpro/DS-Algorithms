package com.design.patterns.memeto.states;

public class FontName implements State {

    private String fontName;

    public FontName(String fontType) {
        this.fontName = fontType;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

}
