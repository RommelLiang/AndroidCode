 public class MyAdapter extends BaseAdapter {
    	List<DB> db;//�������ݿ����ѯ���������ݴ��ݸ�������
    	Context context;//����������
    	private LayoutInflater layoutInflater;//���ز��ֵ�׼������
    	public MyAdapter(List<DB> db,Context context){
    		//���캯������ȡ���ݹ��������ݺ������ģ��Լ�׼�������Զ���Ĳ����ļ�
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
			//����ǵ�һ�μ��ز����ļ�����ִ�����´��룬���ز��ֺͰ󶨿ؼ�
			if(convertView == null) {
				//�����������Զ��岼�֣�ͬʱʹ��viewHolder�Ż�������
				holder = new ViewHolder();
				//��UI�ؼ�
				convertView = layoutInflater.inflate(R.layout.activity_listview_layout, null);
				holder.english_ListView = (TextView) convertView.findViewById(R.id.English_ListView);
				holder.english_yinbiaoTextView = (TextView) convertView.findViewById(R.id.English_yinbian);
				holder.chineseTextView = (TextView) convertView.findViewById(R.id.Chinese_Listview);
				holder.imageButton = (ImageButton) convertView.findViewById(R.id.fayin);
				holder.shoucangButton = (ImageButton) convertView.findViewById(R.id.shoucang);
				holder.tianjiButton = (ImageButton) convertView.findViewById(R.id.tianjiashengci);
				convertView.setTag(holder);
			}else{//���ǵ�һ�μ��ز��ֵ�ʱ��ֱ��ʹ���Ѿ����غõĲ��ֺͰ󶨺õĿؼ�����ʡ�ڴ�
				holder = (ViewHolder) convertView.getTag();
			}
			//�����ݿⷵ�صļ������Ԫ�ص�������ʾ��ָ���Ŀؼ�֮��
			
			return convertView;
		}
	}
    //ʹ�þ�̬��ķ�ʽ������ؼ�
    static class ViewHolder {
        TextView english_ListView;
        TextView english_yinbiaoTextView;
        TextView chineseTextView;
        ImageButton imageButton;
        ImageButton shoucangButton;
        ImageButton tianjiButton;
    }
	//�����ݼ��ؽ��������ķ���
	adapter = new MyAdapter(db, MainActivity.this);//ʵ����������
	listView.setAdapter(adapter);//��listview�м���������