package com.merlinda.barukamu;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SingleInforAdapter extends BaseAdapter {
	private List<Dashboard> dashboard;
	private LayoutInflater inflater;
	private Activity activity;
	private TextView promo;
	private TextView namausaha;

	public SingleInforAdapter(Activity activity, List<Dashboard> dashboards) {
		// TODO Auto-generated constructor stub
		dashboard = dashboards;
        activity = activity;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dashboard.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return dashboard.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null){
	            convertView = inflater.inflate(R.layout.informasi_detail, null);
	            promo = (TextView) convertView.findViewById(R.id.promo);
	            namausaha = (TextView) convertView.findViewById(R.id.namaUsaha);
	        }

	        promo.setText(dashboard.get(position).getPromo());
	        namausaha.setText(dashboard.get(position).getNamaUsaha());
	        
		return convertView;
	}

}
