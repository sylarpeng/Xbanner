package com.pzj.banner;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;


/**
 * Created by  : pzj
 * Created date:2017/12/27
 * description : 轮播banner
 */

public class Xbanner<T extends Object> extends RelativeLayout {
	private Context mContext;
	private ImageLoader mImageLoader;
	private ConfigBuilder mConfig;

	private List<T> mDatas;

	private ViewPager mViewPager;
	private RelativeLayout mIndicatorContainer;
	private LinearLayout mPointContainer;

	private TextView mTvDescription;
	private TextView mTvIndicator;
	private MyPagerAdapter mAdapter;
	private int currentPosition;
	private OnBannerClickListener mClickListener;
	private OnBannerChangeListener mChangeListener;
	EntityAnalysis entityAys;


	public Xbanner(Context context) {
		this(context,null);
	}

	public Xbanner(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext=context;
		initView();
	}

	private void initView() {
		mImageLoader=new GlideImageLoader();
		mConfig=new ConfigBuilder.Builder().build();

		mViewPager = new ViewPager(mContext);
		mIndicatorContainer=new RelativeLayout(mContext);
		this.addView(mViewPager);
		this.addView(mIndicatorContainer);

	}

	private void initIndicatorContainer(){
		RelativeLayout.LayoutParams pointParams = new LayoutParams(LayoutParams.MATCH_PARENT, mConfig.getIndicatorBgHeight());
		pointParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		mIndicatorContainer.setLayoutParams(pointParams);
		mIndicatorContainer.setBackgroundColor(getResources().getColor(mConfig.getIndicatorBgColor()));
	}

	public void init(List<T> mImages,Class cls){
		init(mImages,cls,null);
	}

	public void init(List<T> datas,Class cls,ConfigBuilder config){

		entityAys = EntityAnalysis.init(cls, Title.class, ImagePath.class);
		if(datas==null || datas.size()==0){
			return;
		}

		reset();
		this.mDatas =datas;
		if(config!=null){
			mConfig=config;
		}

		initIndicatorContainer();

		//设置轮播图片
		mAdapter=new MyPagerAdapter();
		mViewPager.setAdapter(mAdapter);

		if(mConfig.isLoopPlay() && mDatas.size()>1){
			mViewPager.setCurrentItem(mDatas.size()*10);
		}

		//设置指示点
		if(mConfig.isShowIndicator()){
			if(mConfig.getmIndicatorType()== ConfigBuilder.IndicatorType.POINTS){
				addDian(mDatas);
			}else{
				addDigitalIndicator();
			}

		}

		//设置文字
		if(mConfig.isShowTitle()){
			addTitle();
		}


		initTouch();
	}

	private void addTitle(){
		mTvDescription=new TextView(mContext);
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, mConfig.getIndicatorBgHeight());
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.setMargins(30, 0, 30, 0);
		mTvDescription.setTextColor(mContext.getResources().getColor(mConfig.getTextColor()));
		mTvDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP, mConfig.getTextSize());
		mTvDescription.setLines(1);
		mTvDescription.setEllipsize(TextUtils.TruncateAt.END);
		mTvDescription.setGravity(Gravity.CENTER_VERTICAL);
		if(mDatas !=null && mDatas.size()>0){
			String title = (String) entityAys.getValue(mDatas.get(0),Title.class);
			mTvDescription.setText(title);
		}
		mIndicatorContainer.addView(mTvDescription,params);
	}

	private void addDigitalIndicator(){
		mTvIndicator=new TextView(mContext);
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		if(mConfig.getIndicatorGravity()== ConfigBuilder.IndicatorGravity.LEFT){
			params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		}else if(mConfig.getIndicatorGravity()== ConfigBuilder.IndicatorGravity.RIGHT){
			params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		}else{
			params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		}
		params.setMargins(30, 0, 30, 30);
		mTvIndicator.setTextColor(mContext.getResources().getColor(mConfig.getTextColor()));
		mTvIndicator.setTextSize(TypedValue.COMPLEX_UNIT_SP, mConfig.getTextSize());
		mTvIndicator.setPadding(24,5,24,5);
		mTvIndicator.setLines(1);
		mTvIndicator.setEllipsize(TextUtils.TruncateAt.END);
		mTvIndicator.setGravity(Gravity.CENTER_VERTICAL);
		if(mDatas !=null && mDatas.size()>0){
			setCurrentPage(0);
		}
		mTvIndicator.setBackgroundResource(R.drawable.common_banner_digital);
		((GradientDrawable)mTvIndicator.getBackground()).setColor(mContext.getResources().getColor(mConfig.getmIndicatorDigitalBgColor()));
		this.addView(mTvIndicator,params);

		mTvIndicator.setVisibility(mDatas !=null && mDatas.size()>1?VISIBLE:View.GONE);
	}

	private void setCurrentPage(int currentPosition){
		mTvIndicator.setText(currentPosition+1+"/"+mDatas.size());
	}

	private void addDian(List<T> images){
		mPointContainer = new LinearLayout(mContext);
		mPointContainer.setId(R.id.banner_point_container);
		mPointContainer.setGravity(Gravity.CENTER_VERTICAL);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, mConfig.getIndicatorBgHeight());
		lp.setMargins(30,0,30,0);
		lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		if(mConfig.getIndicatorGravity()== ConfigBuilder.IndicatorGravity.LEFT){
			lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		}else if(mConfig.getIndicatorGravity()== ConfigBuilder.IndicatorGravity.RIGHT){
			lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		}else{
			lp.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		}


		if(images.size()>1){
			for (int i = 0; i < mDatas.size(); i++) {
				ImageView iiv = new ImageView(mContext);
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
						LinearLayout.LayoutParams.WRAP_CONTENT);
				params.leftMargin = 10;
				if (i == 0) {
					iiv.setBackgroundResource(R.drawable.common_banner_point_selected);
					((GradientDrawable)iiv.getBackground()).setColor(mContext.getResources().getColor(mConfig.getIndicatorSelectedColor()));
				} else {
					iiv.setBackgroundResource(R.drawable.common_banner_point_normal);
					((GradientDrawable)iiv.getBackground()).setColor(mContext.getResources().getColor(mConfig.getIndicatorNormalColor()));
				}

				mPointContainer.addView(iiv, params);
			}
		}

		mIndicatorContainer.addView(mPointContainer,lp);

	}


	private class MyPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			if(mDatas ==null){
				return 0;
			}else if(mDatas.size()<=1 || !mConfig.isLoopPlay()){
				return mDatas.size();
			}else{
				return Integer.MAX_VALUE;
			}

		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			if (mDatas.size() <= 0) {
				return;
			}
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			position = position % mDatas.size();
			ImageView iv = new ImageView(mContext);
			iv.setScaleType(ImageView.ScaleType.FIT_XY);
			// 添加到容器
			container.addView(iv);
			String imagePath = (String) entityAys.getValue(mDatas.get(position),ImagePath.class);
			mImageLoader.displayImage(mContext,iv,imagePath);

			return iv;
		}
	}


	private void initTouch() {
		mViewPager.setOnTouchListener(new OnTouchListener() {
			private float downX, downY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(mDatas==null || mDatas.size()==0){
					return false;
				}
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						currentPosition = mViewPager.getCurrentItem() % mDatas.size();
						handler.removeCallbacksAndMessages(null);
						downX = event.getX();
						downY = event.getY();
						break;
					case MotionEvent.ACTION_UP:
						handler.removeCallbacksAndMessages(null);
						handler.sendEmptyMessageDelayed(1,mConfig.getDelayTime());
						if (Math.abs(event.getX() - downX) < 5 && Math.abs(event.getY() - downY) < 5) {
							if(mClickListener!=null){
								mClickListener.onBannerClick(currentPosition);
							}
						}
						break;
					case MotionEvent.ACTION_MOVE:
						handler.removeCallbacksAndMessages(null);
						break;
					case MotionEvent.ACTION_CANCEL:
						handler.removeCallbacksAndMessages(null);
						handler.sendEmptyMessageDelayed(1,mConfig.getDelayTime());
					default:
						break;
				}
				return false;
			}
		});
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				int position = arg0 % mDatas.size();
				if(mConfig.isShowIndicator()){
					if(mConfig.getmIndicatorType()== ConfigBuilder.IndicatorType.POINTS){
						for (int i = 0; i < mPointContainer.getChildCount(); i++) {
							ImageView childAt = (ImageView) mPointContainer.getChildAt(i);
							if (i == arg0 % mPointContainer.getChildCount()) {
								((GradientDrawable)childAt.getBackground()).setColor(mContext.getResources().getColor(mConfig.getIndicatorSelectedColor()));
							} else {
								((GradientDrawable)childAt.getBackground()).setColor(mContext.getResources().getColor(mConfig.getIndicatorNormalColor()));
							}
						}
					}else{
						if(mDatas !=null && mDatas.size()>position){
							setCurrentPage(position);
						}
					}

				}

				if(mChangeListener!=null){
					mChangeListener.onBannerPageChange(arg0);
				}



				if(mConfig.isShowTitle()){
					if(mDatas !=null && mDatas.size()>position){
						try{
							String title = (String) entityAys.getValue(mDatas.get(position),Title.class);
							if(!TextUtils.isEmpty(title)){
								mTvDescription.setText(title);
							}
						}catch (Exception e){
							e.printStackTrace();
						}

					}
				}

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

		handler.sendEmptyMessageDelayed(1, mConfig.getDelayTime());
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if(!mConfig.isAutoPlay()){
				return;
			}
			switch (msg.what) {
				case 1:
					if(mDatas !=null && mDatas.size()>1){
						mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
						sendEmptyMessageDelayed(1, mConfig.getDelayTime());
					}
					break;
				default:
					break;
			}
		};
	};

	private void reset(){
		mIndicatorContainer.removeAllViews();
		removeAllCallBack();
	}
	private void removeAllCallBack(){
		handler.removeCallbacksAndMessages(null);
	}


	public void setOnBannerClickListener(OnBannerClickListener listener){
		this.mClickListener=listener;
	}
	public void setOnBannerChangeListener(OnBannerChangeListener listener){
		this.mChangeListener=listener;
	}
	public void setCustomerImageLoader(ImageLoader loader){
		if(loader!=null){
			mImageLoader=loader;
		}
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		removeAllCallBack();
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();

	}

	public void notifyDataSetChanged(){
		if(mAdapter!=null){
			mAdapter.notifyDataSetChanged();
		}
	}
}
