import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;

//需要使用universal-image-loader.jar
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tangram.nazi.app.R;

/**
 * ImageView创建工厂
 */
public class ViewFactory {

	public static ImageView getImageView(Context context, String url) {
		ImageView imageView = (ImageView)LayoutInflater.from(context).inflate(
				R.layout.view_banner, null);
		ImageLoader.getInstance().displayImage(url, imageView);
		return imageView;
	}
}
