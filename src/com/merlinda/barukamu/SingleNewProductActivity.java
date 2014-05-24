package com.merlinda.barukamu;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ListView;

/**
 * Created by Merlinda Wibowo on 02/04/14.
 */
public class SingleNewProductActivity extends Activity {
	private List<Dashboard> listProdukBaru = new ArrayList<Dashboard>();
	private ListView list;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.produk_baru);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setSubtitle("Produk Baru");
        
        String namaproduk = getIntent().getExtras().getString("NAMA");
        String harga = getIntent().getExtras().getString("HARGA");
        String detail = getIntent().getExtras().getString("DETAIL");
        String gambar = getIntent().getExtras().getString("GAMBAR");
        
        Dashboard dashboard = new Dashboard();
        dashboard.setNamaProduk(namaproduk);
        dashboard.setHargaProduk(harga);
        listProdukBaru.add(dashboard);
        
        SingleProdukAdapter adapter = new SingleProdukAdapter(SingleNewProductActivity.this, listProdukBaru);
        list = (ListView)findViewById(R.id.listProdukBaru);
        list.setAdapter(adapter);
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
