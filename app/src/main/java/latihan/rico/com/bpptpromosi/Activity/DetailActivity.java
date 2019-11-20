package latihan.rico.com.bpptpromosi.Activity;

import androidx.appcompat.app.AppCompatActivity;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Server.Server;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
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
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {


    String nama_sektor,sekor,bidang,alamat,pemilik,deskripsi;

    TextView tv_namasektor, tv_sektor, tv_bidang, tv_alamat, tv_pemilik, tv_deskripsi;

    ImageView gambar;

    private static final String URL_FOTOLISTSEKTOR =  Server.URL_API + "ApiListSektorImage.php";

    int id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tv_namasektor = (TextView) findViewById(R.id.tv_nama_sektor);
        tv_sektor  = (TextView) findViewById(R.id.tv_sektor);
        tv_bidang  = (TextView) findViewById(R.id.tv_bidang);
        tv_alamat  = (TextView) findViewById(R.id.tv_alamat);
        tv_pemilik  = (TextView) findViewById(R.id.tv_pemilik);
        tv_deskripsi  = (TextView) findViewById(R.id.tv_desk);
        gambar = (ImageView) findViewById(R.id.iv_gambar);

        Intent intent = getIntent();
        // 2. get message value from intent
        id = intent.getIntExtra("id",0);
        nama_sektor = intent.getStringExtra("nama_sektor");
        sekor = intent.getStringExtra("sektor");
        bidang = intent.getStringExtra("bidang");
        alamat = intent.getStringExtra("alamat");
        pemilik = intent.getStringExtra("pemilik");
        deskripsi = intent.getStringExtra("deskripsi");


        tv_namasektor.setText("Nama Sektor : "+nama_sektor);
        tv_sektor.setText("Sektor : "+sekor);
        tv_bidang.setText("Bidang : "+bidang);
        tv_alamat.setText(alamat);
        tv_pemilik.setText("Pemilik : "+pemilik);
        tv_deskripsi.setText(Html.fromHtml(deskripsi));

        detailsektor();


    }



    private void detailsektor() {
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
                Toast.makeText(getApplicationContext(),"Error Bro",Toast.LENGTH_LONG ).show();
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


    public void back(View view){
        super.onBackPressed();
    }
}
