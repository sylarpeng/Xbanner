package com.pzj.banner;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;

/**
 * Created by  : pzj
 * Created date:2017/12/27
 * description : banner配置
 */

public class ConfigBuilder {
	/**
	 * 是否自动轮播,默认true
	 */
	private boolean mIsAutoPlay;
	/**
	 * 是否循环轮播
	 */
	private boolean mLoopPlay;

	/**
	 * 文字大小(sp)
	 */
	private int mTextSize;
	/**
	 * 文字颜色
	 */
	private int mTextColor;

	/**
	 * 是否显示标题
	 */
	private boolean mShowTitle;
	/**
	 * 是否显示指示器
	 */
	private boolean mShowIndicator;


	/**
	 * 指示器选中颜色
	 */
	private int mIndicatorSelectedColor;

	/**
	 * 指示器默认颜色
	 */
	private int mIndicatorNormalColor;

	/**
	 * 指示器背景颜色(点)
	 */
	private int mIndicatorBgColor;
	/**
	 * 指示器背景颜色（数字）
	 */
	private int mIndicatorDigitalBgColor;

	private int mIndicatorBgHeight;


	/**
	 * 指示器显示位置
	 */
	private IndicatorGravity mIndicatorGravity;

	/**
	 * 指示器显示样式（数字或者点）
	 */
	private IndicatorType mIndicatorType;

	/**
	 * 轮播间隔时间
	 */
	private int mDelayTime;


	public enum IndicatorGravity {
		LEFT,RIGHT,CENTER

	}

	//指示器显示（点,数字）
	public enum IndicatorType {
		POINTS,DIGITAL

	}


	public ConfigBuilder(Builder builder) {
		this.mIsAutoPlay = builder.isAutoPlay;
		this.mLoopPlay=builder.isLoopPlay;
		this.mTextSize=builder.textSize;
		this.mTextColor=builder.textColor;
		this.mShowIndicator=builder.showIndicator;
		this.mIndicatorNormalColor=builder.indicatorNormalColor;
		this.mIndicatorSelectedColor=builder.indicatorSelectedColor;
		this.mIndicatorBgColor=builder.indicatorBgColor;
		this.mIndicatorDigitalBgColor=builder.indicatorDigitalBgColor;
		this.mIndicatorGravity=builder.indicatorGravity;
		this.mIndicatorType=builder.indicatorType;
		this.mIndicatorBgHeight=builder.indicatorBgHeight;
		this.mDelayTime=builder.delayTime;
		this.mShowTitle=builder.showTitle;
	}

	public static class Builder{
		private boolean isAutoPlay=true;
		private boolean isLoopPlay=true;
		private int textSize=14;
		private int textColor= R.color.white;
		private boolean showIndicator=true;
		private boolean showTitle=true;
		private int indicatorNormalColor=R.color.indictor_normal;
		private int indicatorSelectedColor=R.color.indictor_selected;
		private int indicatorBgColor=R.color.bannerindicatorbgcolor;
		private int indicatorDigitalBgColor=R.color.indictor_digital_bg;
		private int indicatorBgHeight=65;
		private IndicatorGravity indicatorGravity=IndicatorGravity.RIGHT;
		private IndicatorType indicatorType=IndicatorType.POINTS;
		private int delayTime=3000;

		public  Builder setAutoPlay(boolean isAutoPlay){
			this.isAutoPlay=isAutoPlay;
			return this;
		}
		public  Builder setLoopPlay(boolean isLoopPlay){
			this.isLoopPlay=isLoopPlay;
			return this;
		}

		public Builder setTextSize(int textSize){
			this.textSize=textSize;
			return this;
		}
		public Builder setTextColor(@ColorRes int color){
			this.textColor=color;
			return this;
		}

		public  Builder setShowIndicator(boolean isShow){
			this.showIndicator=isShow;
			return this;
		}
		public  Builder setShowTitle(boolean isShow){
			this.showTitle=isShow;
			return this;
		}

		public Builder setIndicatorNormalColor(@ColorRes int color){
			this.indicatorNormalColor=color;
			return this;
		}
		public Builder setIndicatorSelectedColor(@ColorRes int color){
			this.indicatorSelectedColor=color;
			return this;
		}

		public Builder setIndicatorGravity(IndicatorGravity gravity){
			this.indicatorGravity=gravity;
			return this;
		}
		public Builder setIndicatorType(IndicatorType type){
			this.indicatorType=type;
			return this;
		}
		public Builder setIndicatorBgHeight(int indicatorHeight){
			this.indicatorBgHeight=indicatorHeight;
			return this;
		}
		public Builder setDelayTime(int time){
			if(time>=1000){
				this.delayTime=time;
			}
			return this;
		}
		public Builder setIndicatorBgColor(@ColorRes int color){
			this.indicatorBgColor=color;
			return this;
		}
		public Builder setIndicatorDigitalBgColor(@ColorRes int color){
			this.indicatorDigitalBgColor=color;
			return this;
		}



		public ConfigBuilder build(){
			return new ConfigBuilder(this);
		}
	}


	public boolean isAutoPlay() {
		return mIsAutoPlay;
	}

	public boolean isLoopPlay() {
		return mLoopPlay;
	}

	public int getTextSize() {
		return mTextSize;
	}

	public int getTextColor() {
		return mTextColor;
	}

	public boolean isShowIndicator() {
		return mShowIndicator;
	}

	public int getIndicatorSelectedColor() {
		return mIndicatorSelectedColor;
	}

	public int getIndicatorNormalColor() {
		return mIndicatorNormalColor;
	}

	public int getIndicatorBgColor() {
		return mIndicatorBgColor;
	}

    public int getmIndicatorDigitalBgColor() {
        return mIndicatorDigitalBgColor;
    }

    public IndicatorGravity getIndicatorGravity() {
		return mIndicatorGravity;
	}

	public IndicatorType getmIndicatorType() {
		return mIndicatorType;
	}

	public int getIndicatorBgHeight() {
		return mIndicatorBgHeight;
	}

	public int getDelayTime() {
		return mDelayTime;
	}



	public boolean isShowTitle() {
		return mShowTitle;
	}
}
