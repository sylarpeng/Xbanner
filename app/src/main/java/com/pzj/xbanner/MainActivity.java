package com.pzj.xbanner;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.pzj.banner.ConfigBuilder;
import com.pzj.banner.Xbanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	private Xbanner xbanner;

	private RadioGroup gp_auto_play,gp_loop_play,gp_show_txt,gp_show_indictor,gp_indictor_pos,gp_indictor_type;
	private SeekBar seek_delay_time;

//	List<String> images = Arrays.asList("https://vpclub-img.oss-cn-shenzhen.aliyuncs.com/upload/100000058/201710/20/201710200949213515.jpg", "https://vpclub-img.oss-cn-shenzhen.aliyuncs.com/upload/100000058/201605/12/201605121644501497.jpg", "https://vpclub-img.oss-cn-shenzhen.aliyuncs.com/upload/100000058/201712/05/201712051441457849.jpg");
//	List<String> titles = Arrays.asList("测试111","测试222","测试33333333333333333333333");

	List<TestEntity> datas = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		xbanner= (Xbanner) findViewById(R.id.banner);

		gp_auto_play= (RadioGroup) findViewById(R.id.gp_auto_play);
		gp_loop_play= (RadioGroup) findViewById(R.id.gp_loop_play);
		gp_show_txt= (RadioGroup) findViewById(R.id.gp_show_txt);
		gp_show_indictor= (RadioGroup) findViewById(R.id.gp_show_indictor);
		gp_indictor_pos= (RadioGroup) findViewById(R.id.gp_indictor_pos);
		gp_indictor_type= (RadioGroup) findViewById(R.id.gp_indictor_type);

		seek_delay_time= (SeekBar) findViewById(R.id.seek_delay_time);

		createCheckBoxListener(gp_auto_play);
		createCheckBoxListener(gp_loop_play);
		createCheckBoxListener(gp_show_txt);
		createCheckBoxListener(gp_show_indictor);
		createCheckBoxListener(gp_indictor_pos);
		createCheckBoxListener(gp_indictor_type);

		createSeekListener();

		datas.add(new TestEntity("http://img5.imgtn.bdimg.com/it/u=1245745137,1322364824&fm=27&gp=0.jpg","测试001"));
		datas.add(new TestEntity("http://img3.imgtn.bdimg.com/it/u=3773737273,3162414253&fm=214&gp=0.jpg","测试002"));
		datas.add(new TestEntity("http://a3.topitme.com/6/9a/f9/112653021665cf99a6l.jpg","测试003"));

		initBanner();

	}

	private void createSeekListener() {
		seek_delay_time.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				initBanner();
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
		});
	}

	private void createCheckBoxListener(RadioGroup gp) {
		gp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				initBanner();
			}
		});

	}


	private void initBanner(){
		ConfigBuilder.IndicatorGravity gravity;
		if(gp_indictor_pos.getCheckedRadioButtonId()==R.id.indictor_pos_left){
			gravity= ConfigBuilder.IndicatorGravity.LEFT;
		}else if(gp_indictor_pos.getCheckedRadioButtonId()==R.id.indictor_pos_center){
			gravity= ConfigBuilder.IndicatorGravity.CENTER;
		}else{
			gravity= ConfigBuilder.IndicatorGravity.RIGHT;
		}
		ConfigBuilder config=new ConfigBuilder.Builder()
				.setAutoPlay(gp_auto_play.getCheckedRadioButtonId()==R.id.auto_y)
				.setLoopPlay(gp_loop_play.getCheckedRadioButtonId()==R.id.loop_y)
				.setDelayTime(seek_delay_time.getProgress()*1000)
				.setTextColor(R.color.white)
				.setTextSize(14)
				.setShowIndicator(gp_show_indictor.getCheckedRadioButtonId()==R.id.show_indictor_y)
				.setShowTitle(gp_show_txt.getCheckedRadioButtonId()==R.id.show_txt_y)
				.setIndicatorBgColor(R.color.bannerindicatorbgcolor)
				.setIndicatorNormalColor(R.color.indictor_normal)
				.setIndicatorSelectedColor(R.color.indictor_selected)
				.setIndicatorGravity(gravity)
				.setIndicatorBgHeight(100)
				.setIndicatorType(gp_indictor_type.getCheckedRadioButtonId()==R.id.indictor_type_p?ConfigBuilder.IndicatorType.POINTS:ConfigBuilder.IndicatorType.DIGITAL)
				.build();


//		xbanner.init(datas,TestEntity.class);

		xbanner.init(datas,TestEntity.class,config);


	}
}
