package latihan.rico.com.bpptpromosi.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import latihan.rico.com.bpptpromosi.Adapter.AdapterPeluang;
import latihan.rico.com.bpptpromosi.Model.ModelPeluang;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Server.Server;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

public class PeluangActivity extends AppCompatActivity {

    private ShimmerFrameLayout mShimmerViewContainer;
    RecyclerView rv_peluang;
    AdapterPeluang adapterPeluang;
    ArrayList<ModelPeluang> modelPeluangs = new ArrayList<>();
    SwipeRefreshLayout swipe;
    private static final String URL_PELUANG = Server.URL_API_NEW + "peluang";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peluang);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setTitle("Peluang Investasi");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        rv_peluang = (RecyclerView) findViewById(R.id.recycler_view_list);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);


        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe.setRefreshing(false);
                        getPeluang();
                    }
                }, 1000);
            }
        });


        getPeluang();
    }


    private void getPeluang() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PELUANG,
                new Response.Listener<String>() {
                    public void onResponse(String response){
                        Log.d("json", response.toString());
                        modelPeluangs.clear();
                        try {
                            JSONObject jsonObjects = new JSONObject(response);
                            JSONArray jsonArray = jsonObjects.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    modelPeluangs.add(new ModelPeluang(
                                            jsonObject.getString("id"),
                                            jsonObject.getString("nama_sektor"),
                                            jsonObject.getString("alamat_sektor"),
                                            jsonObject.getString("telepon_sektor"),
                                            jsonObject.getString("nama_pengelola"),
                                            jsonObject.getString("deskripsi"),
                                            jsonObject.getString("lokasi"),
                                            jsonObject.getString("latitude"),
                                            jsonObject.getString("longitude"),
                                            jsonObject.getString("luas_tanah"),
                                            jsonObject.getString("status_kepemilikan"),
                                            jsonObject.getString("estimasi_biaya"),
                                            jsonObject.getString("skema_kerjasama"),
                                            jsonObject.getString("email"),
                                            jsonObject.getString("website"),
                                            jsonObject.getString("status"),
                                            jsonObject.getString("created_at"),
                                            jsonObject.getString("updated_at"),
                                            jsonObject.getString("created_by"),
                                            jsonObject.getString("image")
                                    ));


                                rv_peluang.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));
                                rv_peluang.setHasFixedSize(true);
                                adapterPeluang = new AdapterPeluang(getApplicationContext(), modelPeluangs);
                                rv_peluang.setAdapter(adapterPeluang);
                                adapterPeluang.notifyDataSetChanged();

                                mShimmerViewContainer.stopShimmer();
                                mShimmerViewContainer.setVisibility(View.GONE);
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
        });
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        mShimmerViewContainer.startShimmer();
        super.onResume();
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmer();
        super.onPause();
    }

}