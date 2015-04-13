package cn.believeus.main;

import java.util.ArrayList;
import java.util.HashMap;

import cn.believeus.main.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterMusicItem extends BaseAdapter {
	private LayoutInflater inflater;
	private ArrayList<HashMap<String, Object>> listItem;
	public AdapterMusicItem(Context context,ArrayList<HashMap<String, Object>> listItem){
		inflater = LayoutInflater.from(context);
		this.listItem=listItem;
	}
	@Override
	public int getCount() {
		return listItem.size();
	}

	//
	@Override
	public Object getItem(int position) {
		// Get the data item associated with the specified position in the data
		// set.(获取数据集中与指定索引对应的数据项)
		return position;
	}

	//当前显示的view在所有View中的ID
	@Override
	public long getItemId(int position) {
		// Get the row id associated with the specified position in the
		// list.(取在列表中与指定索引对应的行id)
		return position;
	}
	//每次只要屏幕上显示一个Item就会调用这个方法,屏幕第一次可以显示十个item,所以创建10个view,convertView=null
	//当屏幕往下划的时候,出现的第11个item,convertView就是曾经的第1次设置的view,
	//第12次出现的item,convertView就是曾经的第2次设置的view,
	//但是不会创建view,会用原来的view填充数据而已
	//这就减少了创建view的内存
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			//新建一个布局
			convertView=inflater.inflate(R.layout.music_items, null);
			holder = new ViewHolder();
			holder.vedioName = (TextView)convertView.findViewById(R.id.vedioName);
			holder.vediodesc = (TextView)convertView.findViewById(R.id.vediodesc);
			holder.vediourl = (TextView)convertView.findViewById(R.id.vedioUrl);
			//Tags can also be used to store data within a view without resorting to another data structure.
			//会自动填充对应的属性中的值
 			convertView.setTag(holder);
		}else {
			 holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
		}
		holder.vedioName.setText(listItem.get(position).get(Variables.VIDEO_NAME).toString());
		holder.vediodesc.setText(listItem.get(position).get(Variables.VIDEO_DESC).toString());
		holder.vediourl.setText(listItem.get(position).get(Variables.VIDEO_URL).toString());
		return convertView;
	}
	//解决复杂布局的时候findViewById查找id慢的问题
	public final class ViewHolder {
		public ImageView imageView;
		public TextView vedioName;
		public TextView vediodesc;
		public TextView vediourl;
	}
}