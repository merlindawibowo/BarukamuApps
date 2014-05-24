package com.merlinda.barukamu;

import android.app.ActionBar;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Merlinda Wibowo on 26/03/14.
 */
public class DashboardActivity extends ListActivity {
    private ProgressDialog pDialog;

    // URL to get contacts JSON
    private static String url = "http://indopacificjayatama-jogja.com/merlinda/index.php/service/dataJson";
//    private static String url = "http://10.0.2.2/barukamu/index.php/service/dataJson";

    // JSON Node names
    private static final String TAG_DATADATA = "datadata";
    private static final String TAG_ID_USER = "id_user";
    private static final String TAG_USERNAME = "username";
    private static final String TAG_PASSWORD = "password";
    private static final String TAG_ID_AKSES = "id_akses";
    private static final String TAG_ID_USAHA = "id_usaha";
    private static final String TAG_NAMA_USAHA = "nama_usaha";
    private static final String TAG_CABANG_INDUSTRI = "cabang_industri";
    private static final String TAG_JENIS_KOMODITI = "jenis_komoditi";
    private static final String TAG_ALAMAT = "alamat";
    private static final String TAG_NAMA_PEMILIK = "nama_pemilik";
    private static final String TAG_LATITUDE = "latitude";
    private static final String TAG_LONGITUDE = "longitude";
    private static final String TAG_ID_PRODUK = "id_produk";
    private static final String TAG_NAMA_PRODUK = "nama_produk";
    private static final String TAG_HARGA_PRODUK = "harga_produk";
    private static final String TAG_DETAIL_PRODUK = "detail_produk";
    private static final String TAG_GAMBAR_PRODUK = "gambar_produk";
    private static final String TAG_PROMO = "promo";

    // contacts JSONArray
    JSONArray contacts = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> contactList;
    private ArrayList<Dashboard> dash = new ArrayList<Dashboard>(); 
    
    //onclick
    final Context context = this;
	private Button button;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setSubtitle("List UMKM");

        contactList = new ArrayList<HashMap<String, String>>();

        final ListView lv = getListView();
        
        //cek batere
        getBatteryPercentage();

        // Calling async task to get json
        new GetContacts().execute();

        // Listview on item click listener
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {        	               

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
            	// custom dialogm
    			Dialog dialog = new Dialog(context);
    			dialog.setContentView(R.layout.dashboard_popup);
    			dialog.setTitle("Menu UMKM");
     
    			// set the custom dialog components - text, image and button
    			TextView peta = (TextView) dialog.findViewById(R.id.peta);
    			TextView produkUsaha = (TextView) dialog.findViewById(R.id.produkUsaha);
    			TextView produkBaru = (TextView) dialog.findViewById(R.id.produkBaru);
    			TextView informasi = (TextView) dialog.findViewById(R.id.informasi);
     
    			// if text is clicked, close the custom dialog
    			peta.setOnClickListener(new View.OnClickListener() {
    	            @Override
    	            public void onClick(View v) {    	            	
    	                Intent intent = new Intent(DashboardActivity.this, SingleMapActivity.class);
    	                intent.putExtra("LNG", contactList.get(position).get(TAG_LONGITUDE));
    	                intent.putExtra("LAT", contactList.get(position).get(TAG_LATITUDE));
    	                intent.putExtra("NAMA", contactList.get(position).get(TAG_NAMA_USAHA));
    	                intent.putExtra("ALAMAT", contactList.get(position).get(TAG_ALAMAT));
//    	                Toast.makeText(getBaseContext(), contactList.get(position).get(TAG_ALAMAT), Toast.LENGTH_SHORT).show();
    	                startActivity(intent);
    	            }
    	        });

    	        produkUsaha.setOnClickListener(new View.OnClickListener() {
    	            @Override
    	            public void onClick(View v) {
    	                Intent intent = new Intent(DashboardActivity.this, SingleProductActivity.class);
    	                intent.putExtra("NAMA", contactList.get(position).get(TAG_NAMA_PRODUK));
    	                intent.putExtra("HARGA", contactList.get(position).get(TAG_HARGA_PRODUK));
    	                intent.putExtra("DETAIL", contactList.get(position).get(TAG_DETAIL_PRODUK));
    	                intent.putExtra("GAMBAR", contactList.get(position).get(TAG_GAMBAR_PRODUK));
    	                intent.putExtra("PROMO", contactList.get(position).get(TAG_PROMO));
    	                intent.putExtra("NAMA", contactList.get(position).get(TAG_NAMA_USAHA));
    	                startActivity(intent);
    	            }
    	        });

    	        produkBaru.setOnClickListener(new View.OnClickListener() {
    	            @Override
    	            public void onClick(View v) {
    	                Intent intent = new Intent(DashboardActivity.this, SingleNewProductActivity.class);
    	                intent.putExtra("NAMA", contactList.get(position).get(TAG_NAMA_PRODUK));
    	                intent.putExtra("HARGA", contactList.get(position).get(TAG_HARGA_PRODUK));
    	                intent.putExtra("DETAIL", contactList.get(position).get(TAG_DETAIL_PRODUK));
    	                intent.putExtra("GAMBAR", contactList.get(position).get(TAG_GAMBAR_PRODUK));
    	                startActivity(intent);
    	            }
    	        });

    	        informasi.setOnClickListener(new View.OnClickListener() {
    	            @Override
    	            public void onClick(View v) {
    	                Intent intent = new Intent(DashboardActivity.this, SingleInforActivity.class);
    	                intent.putExtra("NAMA", contactList.get(position).get(TAG_NAMA_USAHA));
    	                intent.putExtra("PROMO", contactList.get(position).get(TAG_PROMO));
    	                startActivity(intent);
    	            }
    	        });
    			
     
    			dialog.show();

            }
        });


    }

    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(DashboardActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
            
            //cek koneksi
//            try {
//                URL url = new URL("http://indopacificjayatama-jogja.com/merlinda/index.php/service/dataJson");
//
//                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
//                urlc.setRequestProperty("User-Agent", "Android Application:"+Z.APP_VERSION);
//                urlc.setRequestProperty("Connection", "close");
//                urlc.setConnectTimeout(1000 * 30); // mTimeout is in seconds
//                urlc.connect();
//
//                if (urlc.getResponseCode() == 200) {
//                    DashboardActivity.Log("getResponseCode == 200");
//                    return new Boolean(true);
//                }
//            } catch (MalformedURLException e1) {
//                e1.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    contacts = jsonObj.getJSONArray(TAG_DATADATA);

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString(TAG_ID_USER);
                        String username = c.getString(TAG_USERNAME);
                        String password = c.getString(TAG_PASSWORD);
                        String idAkses = c.getString(TAG_ID_AKSES);
                        String idUsaha = c.getString(TAG_ID_USAHA);
                        String namaUsaha = c.getString(TAG_NAMA_USAHA);
                        String cabangIndustri = c.getString(TAG_CABANG_INDUSTRI);
                        String namaPemilik = c.getString(TAG_NAMA_PEMILIK);
                        String latitude = c.getString(TAG_LATITUDE);
                        String longitude = c.getString(TAG_LONGITUDE);
                        String alamat = c.getString(TAG_ALAMAT);
                        String jenisKomoditi = c.getString(TAG_JENIS_KOMODITI);
                        String promo = c.getString(TAG_PROMO);
                        String idProduk = c.getString(TAG_ID_PRODUK);
                        String namaProduk = c.getString(TAG_NAMA_PRODUK);
                        String hargaProduk = c.getString(TAG_HARGA_PRODUK);
                        String detailProduk = c.getString(TAG_DETAIL_PRODUK);
                        String gambarProduk = c.getString(TAG_GAMBAR_PRODUK);

                        // tmp hashmap for single contact
                        HashMap<String, String> contact = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        Dashboard dashboard = new Dashboard();
                        dashboard.setIdUser(Integer.parseInt(id));
                        dashboard.setNamaUsaha(namaUsaha);
                        dashboard.setAlamat(alamat);
                        dashboard.setJenisKomoditi(jenisKomoditi);
                        dashboard.setPromo(promo);
                        dashboard.setCabangIndustri(cabangIndustri);
                        dashboard.setDetailProduk(detailProduk);
                        dashboard.setGambarProduk(gambarProduk);
                        dashboard.setHargaProduk(hargaProduk);
                        dashboard.setIdAkses(Integer.parseInt(idAkses));
                        dashboard.setIdProduk(Integer.parseInt(idProduk));
                        dashboard.setIdUsaha(Integer.parseInt(idUsaha));
                        dashboard.setLat(Double.parseDouble(latitude));
                        dashboard.setLng(Double.parseDouble(longitude));
                        dashboard.setNamaPemilik(namaPemilik);
                        dashboard.setNamaProduk(namaProduk);
                        dashboard.setUsername(username);
                        dashboard.setPassword(password);
                        
                        contact.put(TAG_ID_USER, id);
                        contact.put(TAG_NAMA_USAHA, namaUsaha);
                        contact.put(TAG_ALAMAT, alamat);
                        contact.put(TAG_JENIS_KOMODITI, jenisKomoditi);
                        contact.put(TAG_PROMO, promo);
                        contact.put(TAG_CABANG_INDUSTRI, cabangIndustri);
                        contact.put(TAG_DETAIL_PRODUK, detailProduk);
                        contact.put(TAG_GAMBAR_PRODUK, gambarProduk);
                        contact.put(TAG_HARGA_PRODUK, hargaProduk);
                        contact.put(TAG_ID_AKSES, idAkses);
                        contact.put(TAG_ID_PRODUK, idProduk);
                        contact.put(TAG_ID_USAHA, idUsaha);
                        contact.put(TAG_LATITUDE, latitude);
                        contact.put(TAG_LONGITUDE, longitude);
                        contact.put(TAG_NAMA_PEMILIK, namaPemilik);
                        contact.put(TAG_NAMA_PRODUK, namaProduk);
                        contact.put(TAG_PASSWORD, password);
                        contact.put(TAG_USERNAME, username);
                        
                        // adding contact to contact list
                        contactList.add(contact);
                        dash.add(dashboard);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    DashboardActivity.this, contactList,
                    R.layout.dasboard_detail, new String[] { TAG_NAMA_USAHA, TAG_ALAMAT,
                    TAG_JENIS_KOMODITI, TAG_PROMO }, new int[] { R.id.namaUsaha,
                    R.id.alamat, R.id.jenisKomoditi, R.id.promo });

            setListAdapter(adapter);
        }

    }
    
    //cek batere
    private void getBatteryPercentage() {
   	 BroadcastReceiver batteryLevelReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                context.unregisterReceiver(this);
                int currentLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                int level = -1;
                if (currentLevel >= 0 && scale > 0) {
                    level = (currentLevel * 100) / scale;
                }
//                batteryPercent.setText("Battery Level Remaining: " + level + "%");
                Toast.makeText(getBaseContext(), "Battery Level Remaining: " + level + "%", Toast.LENGTH_SHORT).show();
            }
        };	
        IntentFilter batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryLevelReceiver, batteryLevelFilter);
   	 }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
	        case android.R.id.home:
	        	NavUtils.navigateUpFromSameTask(this);
	            return true;
            case R.id.action_search:
                // search action
                return true;
            
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
