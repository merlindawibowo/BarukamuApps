package com.merlinda.barukamu;


import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Merlinda Wibowo on 02/04/14.
 */
public class SingleProductActivity extends Activity {
	private List<Dashboard> listProduk = new ArrayList<Dashboard>();
	private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.produk_utama);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setSubtitle("Produk UMKM");
        
        final String namaproduk = getIntent().getExtras().getString("NAMA");
        final String harga = getIntent().getExtras().getString("HARGA");
        final String detail = getIntent().getExtras().getString("DETAIL");
        final String gambar = getIntent().getExtras().getString("GAMBAR");
        final String promo = getIntent().getExtras().getString("PROMO");
        final String namausaha = getIntent().getExtras().getString("NAMAU");
        
        
        Dashboard dashboard = new Dashboard();
        dashboard.setNamaProduk(namaproduk);
        dashboard.setHargaProduk(harga);
        listProduk.add(dashboard);
        
        SingleProdukAdapter adapter = new SingleProdukAdapter(SingleProductActivity.this, listProduk);
        list = (ListView)findViewById(R.id.listProduk);
        list.setAdapter(adapter);
        
        // Listview on item click listener
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {        	               

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
            	Intent intent = new Intent(SingleProductActivity.this, ProductDetailActivity.class);
            	intent.putExtra("NAMAP", namaproduk);
                intent.putExtra("HARGAP", harga);
                intent.putExtra("DETAILP", detail);
                intent.putExtra("GAMBARP", gambar);
                intent.putExtra("PROMOP", promo);
                intent.putExtra("NAMAUP", namausaha);
//                Toast.makeText(getBaseContext(), namaproduk, Toast.LENGTH_SHORT).show();
            	startActivity(intent);            	
            }
        });
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
