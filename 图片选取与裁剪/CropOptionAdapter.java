import java.util.ArrayList;

/**
 * 作者：anti-nazi梁文硕
 * 时间：2016/8/22 09:33
 */
public class CropOptionAdapter extends ArrayAdapter {
	private ArrayList<CropOption> mOptions;
	private LayoutInflater mInflater;

	public CropOptionAdapter(Context context, ArrayList<CropOption> options) {
		super(context, R.layout.crop_selector, options);
		mOptions = options;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup group) {
		if (convertView == null)
			convertView = mInflater.inflate(R.layout.crop_selector, null);

		CropOption item = mOptions.get(position);

		if (item != null) {
			((ImageView) convertView.findViewById(R.id.iv_icon))
					.setImageDrawable(item.icon);
			((TextView) convertView.findViewById(R.id.tv_name))
					.setText(item.title);

			return convertView;
		}

		return null;
	}
}
