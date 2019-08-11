package com.application.tchapj.task.common;

/**
 * @author cenxiaozhong
 * @date 2018/7/15
 * @since 1.0.0
 */
public class GuideItemEntity {

	private String guideTitle;
	private int guideDictionary;
	private int extra;

	public GuideItemEntity(String guideTitle, int guideDictionary) {
		this.guideTitle = guideTitle;
		this.guideDictionary = guideDictionary;
	}


	public String getGuideTitle() {
		return guideTitle;
	}

	public void setGuideTitle(String guideTitle) {
		this.guideTitle = guideTitle;
	}

	public int getGuideDictionary() {
		return guideDictionary;
	}

	public void setGuideDictionary(int guideDictionary) {
		this.guideDictionary = guideDictionary;
	}
}
