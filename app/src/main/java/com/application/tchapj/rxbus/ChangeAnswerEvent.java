package com.application.tchapj.rxbus;

import java.util.List;

/**
 * Created on 16/7/19.
 *
 * @author ice
 */
public class ChangeAnswerEvent {

    private String mAnswer;

    private List<String> titles;

    private List<String> titlesId;

    public List<String> getTitlesId() {
        return titlesId;
    }

    public void setTitlesId(List<String> titlesId) {
        this.titlesId = titlesId;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public void setAnswer(String answer) {
        mAnswer = answer;
    }

    public String getmAnswer() {
        return mAnswer;
    }

    public void setmAnswer(String mAnswer) {
        this.mAnswer = mAnswer;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }
}
