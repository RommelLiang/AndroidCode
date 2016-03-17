 public class MyAdapter extends BaseAdapter {
    	List<DB> db;//将从数据库里查询出来的数据传递给适配器
    	Context context;//接收上下文
    	private LayoutInflater layoutInflater;//加载布局的准备工作
    	public MyAdapter(List<DB> db,Context context){
    		//构造函数，获取传递过来的数据和上下文，以及准备加载自定义的布局文件
    		this.db = db;
    		this.context=context;
    		layoutInflater = LayoutInflater.from(this.context);
    	}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return db.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return db.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder;
			DB oneOfDb = db.get(position);
			final String englishString = oneOfDb.getSPELLING();
			String englishYinbiao = oneOfDb.getPHONETIC_ALPHABET();
			String Chinese = oneOfDb.getMEANNING();
			int shoucang = oneOfDb.getFavorite();
			int shengci = oneOfDb.getLIST();
			//如果是第一次加载布局文件，则执行以下代码，加载布局和绑定控件
			if(convertView == null) {
				//适配器加载自定义布局，同时使用viewHolder优化适配器
				holder = new ViewHolder();
				//绑定UI控件
				convertView = layoutInflater.inflate(R.layout.activity_listview_layout, null);
				holder.english_ListView = (TextView) convertView.findViewById(R.id.English_ListView);
				holder.english_yinbiaoTextView = (TextView) convertView.findViewById(R.id.English_yinbian);
				holder.chineseTextView = (TextView) convertView.findViewById(R.id.Chinese_Listview);
				holder.imageButton = (ImageButton) convertView.findViewById(R.id.fayin);
				holder.shoucangButton = (ImageButton) convertView.findViewById(R.id.shoucang);
				holder.tianjiButton = (ImageButton) convertView.findViewById(R.id.tianjiashengci);
				convertView.setTag(holder);
			}else{//不是第一次加载布局的时候，直接使用已经加载好的布局和绑定好的控件，节省内存
				holder = (ViewHolder) convertView.getTag();
			}
			//将数据库返回的集合里的元素的内容显示在指定的控件之中
			
			return convertView;
		}
	}
    //使用静态类的方式，定义控件
    static class ViewHolder {
        TextView english_ListView;
        TextView english_yinbiaoTextView;
        TextView chineseTextView;
        ImageButton imageButton;
        ImageButton shoucangButton;
        ImageButton tianjiButton;
    }
	//将数据加载进适配器的方法
	adapter = new MyAdapter(db, MainActivity.this);//实例化适配器
	listView.setAdapter(adapter);//向listview中加载适配器