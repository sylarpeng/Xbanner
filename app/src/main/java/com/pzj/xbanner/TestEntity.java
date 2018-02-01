package com.pzj.xbanner;

import com.pzj.banner.ImagePath;
import com.pzj.banner.Title;


/**
 * Created by  : pzj
 * Created date:2017/12/28
 * description : des
 */
public class TestEntity {

	@ImagePath
	public String image;
	@Title
	public String title;

	public TestEntity(String image, String title) {
		this.image = image;
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
