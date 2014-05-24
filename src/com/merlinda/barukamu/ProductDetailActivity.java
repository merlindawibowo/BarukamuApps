package com.merlinda.barukamu;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetailActivity extends Activity{
	private TextView namapp,hargapp,detilp,promopp,namaupp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_produk);
		
		ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setSubtitle("Produk Detail");
        
        String namaproduk = getIntent().getExtras().getString("NAMAP");
        String harga = getIntent().getExtras().getString("HARGAP");
        String detail = getIntent().getExtras().getString("DETAILP");
        String gambar = getIntent().getExtras().getString("GAMBARP");
        String promo = getIntent().getExtras().getString("PROMOP");
        String namausaha = getIntent().getExtras().getString("NAMAUP");
        
//        Toast.makeText(getBaseContext(), namaproduk+" "+harga, Toast.LENGTH_SHORT).show();
        
        namapp = (TextView)findViewById(R.id.namaProduk);
        hargapp = (TextView)findViewById(R.id.harga);
        detilp = (TextView)findViewById(R.id.detil);
        promopp = (TextView)findViewById(R.id.promo);
        namaupp = (TextView)findViewById(R.id.namaUsaha);
        
        namapp.setText(""+namaproduk);
        hargapp.setText(""+harga);
        detilp.setText(""+detail);
        promopp.setText(""+promo);
        namaupp.setText(""+namausaha);
        
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
            	NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
	
}
