package com.merlinda.barukamu;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SingleNewProductAdapter extends BaseAdapter {
	private List<Dashboard> dashboard;
	private LayoutInflater inflater;
	private Activity activity;
	private TextView namaproduk;
	private TextView harga;
	
	public SingleNewProductAdapter(Activity activity, List<Dashboard> dashboards) {
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
            convertView = inflater.inflate(R.layout.produk_baru_detail, null);
            namaproduk = (TextView) convertView.findViewById(R.id.namaProduk);
            harga = (TextView) convertView.findViewById(R.id.harga);
        }

        namaproduk.setText(dashboard.get(position).getNamaProduk());
        harga.setText(dashboard.get(position).getHargaProduk());
        
        return convertView;
	}

}
