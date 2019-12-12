package latihan.rico.com.bpptpromosi.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import latihan.rico.com.bpptpromosi.AdapterRealisasi.AdapterRealisasiBulan;
import latihan.rico.com.bpptpromosi.AdapterRealisasi.AdapterRealisasiSektor;
import latihan.rico.com.bpptpromosi.ModelRealisasi.ModelRealisasiBulan;
import latihan.rico.com.bpptpromosi.ModelRealisasi.ModelRealisasiSektor;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Server.Server;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

public class RealisasiSektorActivity extends AppCompatActivity {

    RecyclerView rv_realisasi_sektor;
    private static final int PERMISSION_STORAGE_CODE = 1000;
    ArrayList<ModelRealisasiSektor> modelRealisasiSektors = new ArrayList<>();
    AdapterRealisasiSektor adapterRealisasiSektor;
    private static final String URL_REALISASI_SEKTOR= Server.URL_API + "ApiRealisasiListSektor.php";
    private static final String URL_REALISASI_PDF= Server.URL_API + "ApiRealisasiPdf.php";


    int id;
    String bulan;
    TextView tv_bulan;

    Button btn_pdf;
    EditText et_link;

    String link_download;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realisasi_sektor);

        rv_realisasi_sektor = (RecyclerView) findViewById(R.id.rv_realisasi_sektor);
        tv_bulan = (TextView) findViewById(R.id.tv_bulan);

        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        bulan = intent.getStringExtra("bulan");

        btn_pdf = (Button) findViewById(R.id.btn_pdf);
        et_link = (EditText) findViewById(R.id.txt_download);

        tv_bulan.setText("Data Realisasi "+bulan);


        getRealisasiSektor();
        getPDF();


        btn_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_link.getText().toString().equals("https://dpmptsp.tulungagung.go.id/promosi/")){
                    Toast.makeText(getApplicationContext(),"Data PDF TIDAK ADA", Toast.LENGTH_SHORT).show();
                }
                else { if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_STORAGE_CODE);
                    } else {
                        startDownload();
                    }

                } else {
                    startDownload();
                }}

            }
        });

    }

    private void getPDF() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REALISASI_PDF,
                new Response.Listener<String>() {
                    public void onResponse(String response){
                        Log.d("json", response.toString());
                        try {
                            JSONArray jsonArray =  new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                link_download = jsonObject.getString("directory");
                                et_link.setText(link_download);

                               // Toast.makeText(getApplicationContext(),link_download, Toast.LENGTH_SHORT).show();

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
                params.put("id", String.valueOf(id));
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }

    private void getRealisasiSektor() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REALISASI_SEKTOR,
                new Response.Listener<String>() {
                    public void onResponse(String response) {
                        Log.d("json", response.toString());

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                modelRealisasiSektors.add(new ModelRealisasiSektor(
                                        jsonObject.getInt("id"),
                                        jsonObject.getInt("id_bulan"),
                                        jsonObject.getInt("id_sektor"),
                                        jsonObject.getString("unit_usaha"),
                                        jsonObject.getDouble("investasi"),
                                        jsonObject.getString("tk"),
                                        jsonObject.getString("nama_sektor"),
                                        jsonObject.getString("directory")));

                                rv_realisasi_sektor.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                rv_realisasi_sektor.setHasFixedSize(true);
                                adapterRealisasiSektor = new AdapterRealisasiSektor(getApplicationContext(), modelRealisasiSektors);
                                rv_realisasi_sektor.setAdapter(adapterRealisasiSektor);
                                adapterRealisasiSektor.notifyDataSetChanged();
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
                params.put("id", String.valueOf(id));
                return params;
            }

        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    private void startDownload() {
        String url = et_link.getText().toString().trim();

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle(bulan+".pdf");
        request.setDescription("Downloading file.....");


        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"" + bulan+".pdf");


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

    public void back(View view){
        super.onBackPressed();
    }
}
