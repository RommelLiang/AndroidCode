package com.main.apadter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.main.R;

public class MainListExpandableListAdapter extends BaseExpandableListAdapter {

	//单元类
	class ExpandableListHolder {
		TextView nickName;
		TextView phone;
		ImageView ioc;
	}

	//父单元
	class ExpandableGroupHolder {
		TextView title;
	}

	private List<Map<String, Object>> groupData;//组显示
	private List<List<Map<String, Object>>> childData;//子列表

	private LayoutInflater mGroupInflater; //用于加载group的布局xml
	private LayoutInflater mChildInflater; //用于加载listitem的布局xml

	//自宝义构造
	public MainListExpandableListAdapter(Context context, List<Map<String, Object>> groupData, List<List<Map<String, Object>>> childData) {
		this.childData=childData;
		this.groupData=groupData;

		mChildInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//记载子单元view
		mGroupInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//加载父单元view
	}

	//必须实现 得到子数据
	@Override
	public Object getChild(int groupPosition, int j) {
		return childData.get(groupPosition).get(j);
	}

	@Override
	public long getChildId(int groupPosition, int j) {
		return groupPosition;
	}

	@Override
	public int getChildrenCount(int i) {
		return childData.get(i).size();
	}

	@Override
	public Object getGroup(int i) {
		return groupData.get(i);
	}

	@Override
	public int getGroupCount() {
		return groupData.size();
	}

	@Override
	public long getGroupId(int i) {
		return i;
	}

	@Override
	public boolean hasStableIds() {//行是否具有唯一id
		return false;
	}

	@Override
	public boolean isChildSelectable(int i, int j) {//行是否可选
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean flag, View convertView, ViewGroup viewgroup) {
		ExpandableGroupHolder holder = null; //清空临时变量holder
		if (convertView == null) { //判断view（即view是否已构建好）是否为空

			convertView = mGroupInflater.inflate(R.layout.main_tree_group, null);
			holder = new ExpandableGroupHolder();
			holder.title=(TextView) convertView.findViewById(R.id.main_tree_title_id);
			convertView.setTag(holder);
		} else { //若view不为空，直接从view的tag属性中获得各子视图的引用
			holder = (ExpandableGroupHolder) convertView.getTag();
		}
		String title=(String)this.groupData.get(groupPosition).get("title");
		holder.title.setText(title);
		notifyDataSetChanged();
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup viewgroup) {
		ExpandableListHolder holder = null;
		if (convertView == null) {
			convertView = mChildInflater.inflate(R.layout.main_tree_child, null);

			holder = new ExpandableListHolder();
			holder.nickName = (TextView) convertView.findViewById(R.id.mainChildText1);
			holder.ioc = (ImageView) convertView.findViewById(R.id.mainChildIcoId);
			holder.phone = (TextView) convertView.findViewById(R.id.mainChildText2);
			convertView.setTag(holder);
		} else {//若行已初始化，直接从tag属性获得子视图的引用
			holder = (ExpandableListHolder) convertView.getTag();
		}
		Map<String,Object> unitData=this.childData.get(groupPosition).get(childPosition);
		holder.nickName.setText((String)unitData.get("nickName"));
		holder.ioc.setImageResource((Integer) unitData.get("ico"));
		holder.phone.setText((String)unitData.get("phone"));
		return convertView;
	}
}
