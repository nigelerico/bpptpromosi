package latihan.rico.com.bpptpromosi.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import latihan.rico.com.bpptpromosi.AdapterLaporanSektor.AdapterListSektorBulan;
import latihan.rico.com.bpptpromosi.AdapterLaporanSektor.AdapterListSektorTahun;
import latihan.rico.com.bpptpromosi.AdapterLaporanSektor.AdapterListSektorTriwulan;
import latihan.rico.com.bpptpromosi.ModelLaporanSektor.ModelListSektorBulan;
import latihan.rico.com.bpptpromosi.ModelLaporanSektor.ModelListSektorTahun;
import latihan.rico.com.bpptpromosi.ModelLaporanSektor.ModelListSektorTriwulan;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Server.Server;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity  implements AdapterListSektorTahun.onListClickedRowListner{


    private static final int PERMISSION_STORAGE_CODE = 1000;
    String nama_sektor, sekor, bidang, alamat, pemilik, deskripsi, logo, status;
    TextView tv_namasektor, tv_sektor, tv_bidang, tv_alamat, tv_pemilik, tv_deskripsi;
    ImageView gambar, iv_icon, iv_centang;
    private static final String URL_FOTOLISTSEKTOR = Server.URL_API + "ApiListSektorImage.php";
    private static final String URL_PDF = "https://dpmptsp.tulungagung.go.id/api/android/ApiListSektorPdf.php";
    int id_sektor;
    EditText tv_link_pdf;
    CardView btn_pdf;
    String link_download;
    RecyclerView rv_tahun,rv_triwulan,rv_bulan;

    private static final String URL_LAPORAN_TAHUN = Server.URL_API + "ApiListSektorTahun.php";
    ArrayList<ModelListSektorTahun> modelListSektorTahuns = new ArrayList<>();
    AdapterListSektorTahun adapterListSektorTahun;

    private static final String URL_LAPORAN_TRIWULAN = Server.URL_API + "ApiListSektorTriwulan.php";
    ArrayList<ModelListSektorTriwulan> modelListSektorTriwulans = new ArrayList<>();
    AdapterListSektorTriwulan adapterListSektorTriwulan;

    private static final String URL_LAPORAN_BULAN = Server.URL_API + "ApiListSektorBulan.php";
    ArrayList<ModelListSektorBulan> modelListSektorBulans = new ArrayList<>();
    AdapterListSektorBulan adapterListSektorBulan;

    int tahun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btn_pdf = (CardView) findViewById(R.id.btn_pdf);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        iv_centang = (ImageView) findViewById(R.id.iv_centang);
        tv_namasektor = (TextView) findViewById(R.id.tv_nama_sektor);
        tv_sektor = (TextView) findViewById(R.id.tv_sektor);
        tv_bidang = (TextView) findViewById(R.id.tv_bidang);
        tv_alamat = (TextView) findViewById(R.id.tv_alamat);
        tv_pemilik = (TextView) findViewById(R.id.tv_pemilik);
        tv_deskripsi = (TextView) findViewById(R.id.tv_desk);
        gambar = (ImageView) findViewById(R.id.iv_gambar);
        tv_link_pdf = (EditText) findViewById(R.id.tv_link_pdf);
        rv_tahun = (RecyclerView) findViewById(R.id.rv_tahun);
        rv_triwulan = (RecyclerView) findViewById(R.id.rv_triwulan);
        rv_bulan = (RecyclerView) findViewById(R.id.rv_bulan);


        Intent intent = getIntent();
        id_sektor = intent.getIntExtra("id", 0);
        nama_sektor = intent.getStringExtra("nama_sektor");
        sekor = intent.getStringExtra("sektor");
        bidang = intent.getStringExtra("bidang");
        alamat = intent.getStringExtra("alamat");
        pemilik = intent.getStringExtra("pemilik");
        deskripsi = intent.getStringExtra("deskripsi");
        logo = intent.getStringExtra("logo");
        status = intent.getStringExtra("status");

        ambildata();

        tv_namasektor.setText("Nama Sektor : " + nama_sektor);
        tv_sektor.setText("Sektor : " + sekor);
        tv_bidang.setText("Bidang : " + bidang);
        tv_alamat.setText(alamat);
        tv_pemilik.setText("Pemilik : " + pemilik);
        tv_deskripsi.setText(Html.fromHtml(deskripsi));
        tv_link_pdf.setVisibility(View.GONE);


        gambarSektor();
        getLaporanTahun();




        showIcon(logo);

        if (status.equals("Belum Terverifikasi")) {
            iv_centang.setVisibility(View.GONE);
        }

        btn_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_link_pdf.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Data PDF Tidak Ada", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                            requestPermissions(permissions, PERMISSION_STORAGE_CODE);
                        } else {
                            startDownload();
                        }

                    } else {
                        startDownload();
                    }
                }
            }
        });


    }



    private void getLaporanTahun() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LAPORAN_TAHUN,
                new Response.Listener<String>() {
                    public void onResponse(String response){
                        Log.d("json", response.toString());
                        try {
                            JSONArray jsonArray =  new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                modelListSektorTahuns.add(new ModelListSektorTahun(
                                        jsonObject.getInt("nama"),
                                        jsonObject.getString("nominal")));

                                rv_tahun.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                rv_tahun.setHasFixedSize(true);
                                adapterListSektorTahun = new AdapterListSektorTahun(getApplicationContext(), modelListSektorTahuns,DetailActivity.this);
                                rv_tahun.setAdapter(adapterListSektorTahun);
                                adapterListSektorTahun.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("id",(String.valueOf(id_sektor)));
                return params;
            }

        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }



    private void ambildata() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PDF,
                new Response.Listener<String>() {
                    public void onResponse(String response){
                        Log.d("json", response.toString());
                        try {
                            JSONArray jsonArray =  new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                               link_download = jsonObject.getString("directory");
                                tv_link_pdf.setText(link_download);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(id_sektor));
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }

    private void startDownload() {
        String url = tv_link_pdf.getText().toString().trim();

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle(nama_sektor+".pdf");
        request.setDescription("Downloading file.....");


        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"" + nama_sektor+".pdf");


        DownloadManager manager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_STORAGE_CODE: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startDownload();
                }else {
                    Toast.makeText(getApplicationContext(),"Permission Denied...!", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }




    private void  gambarSektor() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FOTOLISTSEKTOR , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = response.toString();
                try {
                    JSONArray jsonArray = new JSONArray(json);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        showImage( jsonObject.getString("directory"));


                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_LONG ).show();
            }
        }){


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(id_sektor));
                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }



    public void showImage(String linkImage){
        ImageLoader imageLoader = MySingleton.getInstance(this.getApplicationContext()).getImageLoader();
        imageLoader.get(linkImage, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                gambar.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
    }


    public void showIcon(String linkImage){
        ImageLoader imageLoader = MySingleton.getInstance(this.getApplicationContext()).getImageLoader();
        imageLoader.get(linkImage, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                iv_icon.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
    }






    public void back(View view){
        super.onBackPressed();
    }

    @Override
    public void onListSelected(int mposition) {
//        tahun = modelListSektorTahuns.get(mposition).getTahun();
//        Toast.makeText(getApplicationContext(),""+tahun,Toast.LENGTH_LONG ).show();
        getLaporanTriwulan(mposition);
        getLaporanBulan(mposition);
    }

    private void getLaporanTriwulan(final int mposition) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LAPORAN_TRIWULAN,
                new Response.Listener<String>() {
                    public void onResponse(String response){
                        Log.d("json", response.toString());
                        modelListSektorTriwulans.clear();
                        try {
                            JSONArray jsonArray =  new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                modelListSektorTriwulans.add(new ModelListSektorTriwulan(
                                        jsonObject.getString("nama"),
                                        jsonObject.getString("nominal")));

                                rv_triwulan.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                rv_triwulan.setHasFixedSize(true);
                                adapterListSektorTriwulan = new AdapterListSektorTriwulan(getApplicationContext(), modelListSektorTriwulans);
                                rv_triwulan.setAdapter(adapterListSektorTriwulan);
                                adapterListSektorTriwulan.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("id",(String.valueOf(id_sektor)));
                params.put("tahun",String.valueOf(modelListSektorTahuns.get(mposition).getTahun()));
                return params;
            }

        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    private void getLaporanBulan(final int mposition) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LAPORAN_BULAN,
                new Response.Listener<String>() {
                    public void onResponse(String response){
                        Log.d("json", response.toString());
                        modelListSektorBulans.clear();
                        try {
                            JSONArray jsonArray =  new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                modelListSektorBulans.add(new ModelListSektorBulan(
                                        jsonObject.getString("nama"),
                                        jsonObject.getString("nominal")));

                                rv_bulan.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                rv_bulan.setHasFixedSize(true);
                                adapterListSektorBulan = new AdapterListSektorBulan(getApplicationContext(), modelListSektorBulans);
                                rv_bulan.setAdapter(adapterListSektorBulan);
                                adapterListSektorBulan.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("id",(String.valueOf(id_sektor)));
                params.put("tahun",String.valueOf(modelListSektorTahuns.get(mposition).getTahun()));
                return params;
            }

        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }


}
