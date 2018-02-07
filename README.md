# Xbanner
 android 广告轮播控件，支持自动轮播和切换时间、位置、图片加载框架，文字显示等
 先上效果图
 ![image](https://github.com/sylarpeng/xbanner/tree/master/images/banner.gif)
 
# 使用步骤
### step1添加依赖  
   	allprojects {
		    repositories {
			...
		    	maven { url 'https://jitpack.io' }
		}
	}  
	
	dependencies {
    		compile 'com.github.sylarpeng:xbanner:1.0'
		}

### step2 xml布局
    <com.pzj.banner.Xbanner
       android:id="@+id/banner"
       android:layout_width="match_parent"
       android:layout_height="自定义高度"/>

### step3 代码中使用
    xbanner= (Xbanner) findViewById(R.id.banner);
    List<TestEntity> datas = new ArrayList<>();
	datas.add(new TestEntity("http://img5.imgtn.bdimg.com/it/u=1245745137,1322364824&fm=27&gp=0.jpg","测试001"));
    datas.add(new TestEntity("http://img3.imgtn.bdimg.com/it/u=3773737273,3162414253&fm=214&gp=0.jpg","测试002"));
	datas.add(new TestEntity("http://a3.topitme.com/6/9a/f9/112653021665cf99a6l.jpg","测试003"));
    xbanner.init(datas,TestEntity.class);

#### 其中TestEntity为接口返回封装的bean。在对应的字段加上注解 @ImagePath @Title 表示要获取展示的轮播图片和文字
     public class TestEntity {

        @ImagePath
        public String image;
        @Title
        public String name;

        public int xxxx;
       }

### 提供config进行个性化设置
    		ConfigBuilder config=new ConfigBuilder.Builder()
    				.setAutoPlay(true) //是否自动轮播 默认true
    				.setLoopPlay(true) //是否无限循环轮播 默认true
    				.setDelayTime(1000) //轮播时间间隔,默认3000 单位毫秒
    				.setTextColor(R.color.white) //文字颜色
    				.setTextSize(14)       //文字大小 sp
    				.setShowIndicator(true) //是否显示指示器
    				.setShowTitle(true) //是否显示文字
    				.setIndicatorBgColor(R.color.bannerindicatorbgcolor)//指示器底部栏颜色
    				.setIndicatorNormalColor(R.color.indictor_normal)//指示器未选中颜色
    				.setIndicatorSelectedColor(R.color.indictor_selected)//指示器选中颜色
    				.setIndicatorGravity(gravity)//指示器显示位置,左中右
    				.build();

    		xbanner.init(datas,TestEntity.class,config);

### 若需要自定义图片加载框架, 则只需实现ImageLoader接口
  		public class CustomLoader implements ImageLoader {
            @Override
            public void displayImage(Context context, ImageView iv, Object path) {

            }
        }
        //设置banner自定义图片加载
        xbanner.setCustomerImageLoader(new CustomLoader());
