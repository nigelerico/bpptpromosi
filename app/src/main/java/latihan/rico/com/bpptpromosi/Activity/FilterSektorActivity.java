package latihan.rico.com.bpptpromosi.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import latihan.rico.com.bpptpromosi.Adapter.AdapterList;
import latihan.rico.com.bpptpromosi.Model.ModelListSektor;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Server.Server;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class FilterSektorActivity extends AppCompatActivity {

    RecyclerView rv_sektor;
    ArrayList<ModelListSektor> modelListSektors = new ArrayList<>();
    AdapterList adapterList;
    private static final String URL_SEKTOR_LIST = Server.URL_API + "ApiListSektorWhereSektor.php";
    String value;
    TextView tv_sektor;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_sektor);

        rv_sektor = (RecyclerView) findViewById(R.id.recycler_view_list);
        tv_sektor = (TextView) findViewById(R.id.tv_sektor);

        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        value = intent.getStringExtra("value");

        getSektorList();

        tv_sektor.setText("Sektor "+value);
    }

    private void getSektorList() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SEKTOR_LIST,
                new Response.Listener<String>() {
                    public void onResponse(String response) {
                        Log.d("json", response.toString());

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                modelListSektors.add(new ModelListSektor(
                                        jsonObject.getInt("id"),
                                        jsonObject.getInt("id_sektor"),
                                        jsonObject.getInt("id_subsektor"),
                                        jsonObject.getString("nama_sektor"),
                                        jsonObject.getString("alamat_sektor"),
                                        jsonObject.getString("telepon_sektor"),
                                        jsonObject.getString("nama_pengelola"),
                                        jsonObject.getString("deskripsifix"),
                                        jsonObject.getString("lokasi"),
                                        jsonObject.getString("latitude"),
                                        jsonObject.getString("longitude"),
                                        jsonObject.getString("statusVerifikasi"),
                                        jsonObject.getString("nama_idsektor"),
                                        jsonObject.getString("nama_idsubsektor"),
                                        jsonObject.getString("directory"),
                                        jsonObject.getString("image")));

                                rv_sektor.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                rv_sektor.setHasFixedSize(true);
                                adapterList = new AdapterList(getApplicationContext(), modelListSektors);
                                rv_sektor.setAdapter(adapterList);
                                adapterList.notifyDataSetChanged();
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
