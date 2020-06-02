package com.design.patterns.memeto;

import com.design.patterns.memeto.states.ContentState;
import com.design.patterns.memeto.states.FontSizeState;
import com.design.patterns.memeto.states.FontName;
import com.design.patterns.memeto.states.State;

public class Document {

    private String content;
    private Integer fontSize;
    private String fontName;

    public Document() {
    }

    public Document(String content, Integer fontSize, String fontName) {
        this.content = content;
        this.fontSize = fontSize;
        this.fontName = fontName;
    }

    public State createFontSizeState() {
        return new FontSizeState(this.fontSize);
    }

    public State createFontNameState() {
        return new FontName(this.fontName);
    }

    public State createContentState() {
        return new ContentState(this.content);
    }

    public void restoreState(State state) {
        if(state.getClass() == FontName.class) {
            this.fontName = ((FontName) state).getFontName();
        }

        if(state.getClass() == FontSizeState.class) {
            this.fontSize = ((FontSizeState) state).getFontSize();
        }

        if(state.getClass() == ContentState.class) {
            this.content = ((ContentState) state).getContent();
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    @Override
    public String toString() {
        return "Document{" +
                "content='" + content + '\'' +
                ", fontSize=" + fontSize +
                ", fontName='" + fontName + '\'' +
                '}';
    }
}
