package latihan.rico.com.bpptpromosi.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import latihan.rico.com.bpptpromosi.Adapter.AdapterImageNew;
import latihan.rico.com.bpptpromosi.Model.ModelIipro;
import latihan.rico.com.bpptpromosi.Model.ModelLahan;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Server.Server;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

public class DetailIiproActivity extends AppCompatActivity {

    TextView tv_namasektor, tv_luastanah, tv_estimasi, tv_email, tv_alamat, tv_status, tv_kontak, tv_website, tv_skema, tv_nama_kontak, tv_deskripsi;
    CardView btn_location;
    ModelIipro modelIipro;
    private static final String URL_DETAIL_IIPRO = Server.URL_API_NEW + "iipro/detail/";
    RecyclerView rv_image_sektor;
    AdapterImageNew adapterImageNew;
    final List<String> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_iipro);

        tv_namasektor = (TextView) findViewById(R.id.tv_nama_sektor);
        tv_luastanah = (TextView) findViewById(R.id.tv_luastanah);
        tv_estimasi = (TextView) findViewById(R.id.tv_estimasi);
        tv_email = (TextView) findViewById(R.id.tv_analisa);
        tv_alamat = (TextView) findViewById(R.id.tv_kapasitas);
        tv_status = (TextView) findViewById(R.id.tv_aspek);
        tv_deskripsi = (TextView) findViewById(R.id.tv_desk);
        rv_image_sektor = (RecyclerView) findViewById(R.id.rv_image_sektor);

        modelIipro = getIntent().getParcelableExtra("data");

        tv_namasektor.setText("Nilai Investasi : " + modelIipro.getNilai_investasi());
        tv_luastanah.setText("Total Area : " + modelIipro.getTotal_area());
        tv_estimasi.setText("Skema Kerjasama : " + modelIipro.getSkema_kerjasama());
        tv_email.setText(Html.fromHtml(modelIipro.getAnalisa()));
        tv_alamat.setText(Html.fromHtml(modelIipro.getKapasitas()));
        tv_status.setText(Html.fromHtml(modelIipro.getPendukung()));

        tv_deskripsi.setText(Html.fromHtml(modelIipro.getDeskripsi()));

        detailLahan();
    }

    private void detailLahan() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL_DETAIL_IIPRO + modelIipro.getId() , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = response;
                images.clear();
                try {
                    JSONObject jsonObjects = new JSONObject(response);
                    JSONObject jsonObject = jsonObjects.getJSONObject("data");
                    JSONArray array = jsonObject.getJSONArray("images");

                    for(int i = 0; i < array.length(); i++) {
                        String imagesTemp  = array.getString(i);
                        images.add(imagesTemp);
                    }

                    rv_image_sektor.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false));
                    rv_image_sektor.setHasFixedSize(true);
                    adapterImageNew = new AdapterImageNew(getApplicationContext(), images);
                    rv_image_sektor.setAdapter(adapterImageNew);
                    adapterImageNew.notifyDataSetChanged();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(),"Error Bro",Toast.LENGTH_LONG ).show();
            }
        }){};

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    public void back(View view){

        super.onBackPressed();
    }
}