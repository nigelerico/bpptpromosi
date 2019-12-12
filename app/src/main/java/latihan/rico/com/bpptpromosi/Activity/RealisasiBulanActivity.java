package latihan.rico.com.bpptpromosi.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import latihan.rico.com.bpptpromosi.Adapter.AdapterList;
import latihan.rico.com.bpptpromosi.AdapterLaporanSektor.AdapterListSektorBulan;
import latihan.rico.com.bpptpromosi.AdapterRealisasi.AdapterRealisasiBulan;
import latihan.rico.com.bpptpromosi.Model.ModelListSektor;
import latihan.rico.com.bpptpromosi.ModelLaporanSektor.ModelListSektorBulan;
import latihan.rico.com.bpptpromosi.ModelRealisasi.ModelRealisasiBulan;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Server.Server;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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


public class RealisasiBulanActivity extends AppCompatActivity {

    RecyclerView rv_realisasi_bulan;
    ArrayList<ModelRealisasiBulan> modelRealisasiBulans = new ArrayList<>();
    AdapterRealisasiBulan adapterRealisasiBulan;
    private static final String URL_REALISASI_BULAN = Server.URL_API + "ApiRealisasiListBulan.php";

    int id;
    String tahun;
    TextView tv_tahun;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realisasi_bulan);

        rv_realisasi_bulan = (RecyclerView) findViewById(R.id.rv_realisasi_bulan);
        tv_tahun = (TextView) findViewById(R.id.tv_tahun);


        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        tahun = intent.getStringExtra("tahun");


        tv_tahun.setText("Data Realisasi "+tahun);

        getRealisaiBulan();
    }

    private void getRealisaiBulan() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REALISASI_BULAN,
                new Response.Listener<String>() {
                    public void onResponse(String response) {
                        Log.d("json", response.toString());

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                modelRealisasiBulans.add(new ModelRealisasiBulan(
                                        jsonObject.getInt("id"),
                                        jsonObject.getInt("id_tahun"),
                                        jsonObject.getString("bulan"),
                                        jsonObject.getDouble("value"),
                                        jsonObject.getString("bulanfix")));

                                rv_realisasi_bulan.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                                rv_realisasi_bulan.setHasFixedSize(true);
                                adapterRealisasiBulan = new AdapterRealisasiBulan(getApplicationContext(), modelRealisasiBulans);
                                rv_realisasi_bulan.setAdapter(adapterRealisasiBulan);
                                adapterRealisasiBulan.notifyDataSetChanged();
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
    public void back(View view){
        super.onBackPressed();
    }
}
