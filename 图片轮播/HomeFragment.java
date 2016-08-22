import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.tangram.nazi.app.R;
import com.tangram.nazi.app.bean.ADInfo;
import com.tangram.nazi.app.cycleviewpager.CycleViewPager;
import com.tangram.nazi.app.utils.ViewFactory;


import java.util.ArrayList;
import java.util.List;

/**
 * 作者：anti-nazi梁文硕
 * 时间：2016/8/15 15:15
 */
public class HomeFragment extends Fragment {

	private String TAG = "HomeFragment";
	private List<ImageView> views = new ArrayList<ImageView>();
	private List<ADInfo> infos = new ArrayList<ADInfo>();
	private CycleViewPager cycleViewPager;
	private Context context;
	private View view;
	private String[] imageUrls = {"http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
			"http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
			"http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
			"http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg",
			"http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg"};

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view == null) {
			view = inflater.inflate(R.layout.home_tab_home,container,false);
			configImageLoader();
			initialize();
		}
		return view;
	}

	@SuppressLint("NewApi")
	private void initialize() {
			cycleViewPager = (CycleViewPager) getActivity().getFragmentManager()
					.findFragmentById(R.id.fragment_cycle_viewpager_content);

		for(int i = 0; i < imageUrls.length; i ++){
			ADInfo info = new ADInfo();
			info.setUrl(imageUrls[i]);
			info.setContent("图片-->" + i );
			infos.add(info);
		}

		// 将最后一个ImageView添加进来
		views.add(ViewFactory.getImageView(context, infos.get(infos.size() - 1).getUrl()));
		for (int i = 0; i < infos.size(); i++) {
			views.add(ViewFactory.getImageView(context, infos.get(i).getUrl()));
		}
		// 将第一个ImageView添加进来
		views.add(ViewFactory.getImageView(context, infos.get(0).getUrl()));

		// 设置循环，在调用setData方法前调用
		cycleViewPager.setCycle(true);

		// 在加载数据前设置是否循环
		cycleViewPager.setData(views, infos, mAdCycleViewListener);
		//设置轮播
		cycleViewPager.setWheel(true);

		// 设置轮播时间，默认5000ms
		cycleViewPager.setTime(4000);
		//设置圆点指示图标组居中显示，默认靠右
		cycleViewPager.setIndicatorCenter();
	}

	private CycleViewPager.ImageCycleViewListener mAdCycleViewListener = new CycleViewPager.ImageCycleViewListener() {

		@Override
		public void onImageClick(ADInfo info, int position, View imageView) {
			if (cycleViewPager.isCycle()) {
				position = position - 1;
				Toast.makeText(getActivity(),
						"position-->" + info.getContent(), Toast.LENGTH_SHORT)
						.show();
			}
		}
	};

	 * 配置ImageLoder
	 */
	private void configImageLoader() {
		// 初始化ImageLoader
		@SuppressWarnings("deprecation")
		DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.icon_stub) // 设置图片下载期间显示的图片
				.showImageForEmptyUri(R.drawable.icon_empty) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.icon_error) // 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
				// .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
				.build(); // 创建配置过得DisplayImageOption对象

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity().getApplicationContext()).defaultDisplayImageOptions(options)
				.threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).build();
		ImageLoader.getInstance().init(config);
	}

}
