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
import latihan.rico.com.bpptpromosi.Model.ModelPeluang;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Server.Server;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

public class DetailPeluangActivity extends AppCompatActivity {



    TextView tv_namasektor, tv_luastanah, tv_estimasi, tv_email, tv_alamat, tv_status, tv_kontak, tv_website, tv_skema, tv_nama_kontak, tv_deskripsi;
    CardView btn_location;
    ModelPeluang modelPeluang;
    private static final String URL_DETAIL_PELUANG = Server.URL_API_NEW + "peluang/detail/";
    RecyclerView rv_image_sektor;
    AdapterImageNew adapterImageNew;
    final List<String> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_peluang);

        tv_namasektor = (TextView) findViewById(R.id.tv_nama_sektor);
        tv_luastanah = (TextView) findViewById(R.id.tv_luastanah);
        tv_estimasi = (TextView) findViewById(R.id.tv_estimasi);
        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_alamat = (TextView) findViewById(R.id.tv_alamat);
        tv_status = (TextView) findViewById(R.id.tv_status);
        tv_kontak = (TextView) findViewById(R.id.tv_kontak);
        tv_website = (TextView) findViewById(R.id.tv_website);
        tv_skema = (TextView) findViewById(R.id.tv_skema);
        tv_nama_kontak = (TextView) findViewById(R.id.tv_nama_kontak);
        tv_deskripsi = (TextView) findViewById(R.id.tv_desk);
        rv_image_sektor = (RecyclerView) findViewById(R.id.rv_image_sektor);

        modelPeluang = getIntent().getParcelableExtra("data");

        tv_namasektor.setText("Nama : " + modelPeluang.getNama_sektor());
        tv_luastanah.setText("Luas Tanah : " + modelPeluang.getLuas_tanah());
        tv_estimasi.setText("Estimasti Biaya : " + modelPeluang.getEstimasi_biaya());
        tv_email.setText("Email : " + modelPeluang.getEmail());
        tv_alamat.setText("Alamat : " + modelPeluang.getAlamat_sektor());
        tv_status.setText("Status Kepemilikan : " + modelPeluang.getStatus_kepemilikan());
        tv_kontak.setText("Kontak : " + modelPeluang.getTelepon_sektor());
        tv_website.setText("Website : " + modelPeluang.getWebsite());
        tv_skema.setText("Skema Kerjasama : " + modelPeluang.getSkema_kerjasama());
        tv_nama_kontak.setText("Nama Kontak : " + modelPeluang.getNama_pengelola());
        tv_deskripsi.setText(Html.fromHtml(modelPeluang.getDeskripsi()));

        detailPeluang();

    }

    private void detailPeluang() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL_DETAIL_PELUANG + modelPeluang.getId() , new Response.Listener<String>() {
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