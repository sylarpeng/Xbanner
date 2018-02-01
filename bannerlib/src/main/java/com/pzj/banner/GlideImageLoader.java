package com.pzj.banner;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by  : pzj
 * Created date:2017/12/27
 * description : glide加载图片
 */

public class GlideImageLoader implements ImageLoader {
	@Override
	public void displayImage(Context context, ImageView iv, Object path) {
			if(path instanceof Integer) {
				Integer resId = (Integer) path;
				iv.setBackgroundResource(resId);
			}else{
				String url=path.toString();
				Glide.with(context)
						.load(url)
						.crossFade()
						.into(iv);
			}

	}
}
