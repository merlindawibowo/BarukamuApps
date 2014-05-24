package com.merlinda.barukamu;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.support.v4.app.NavUtils;

/**
 * Created by Merlinda Wibowo on 02/04/14.
 */
public class SingleInforActivity extends Activity {
	private List<Dashboard> listInformasi = new ArrayList<Dashboard>();
	ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informasi);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setSubtitle("Informasi");
        
        String promo = getIntent().getExtras().getString("PROMO");
        String namausaha = getIntent().getExtras().getString("NAMA");
        
        Dashboard dashboard = new Dashboard();
        dashboard.setPromo(promo);
        dashboard.setNamaUsaha(namausaha);
        listInformasi.add(dashboard);
        
        SingleInforAdapter adapter = new SingleInforAdapter(SingleInforActivity.this, listInformasi);
        list = (ListView)findViewById(R.id.listInformasi);
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
