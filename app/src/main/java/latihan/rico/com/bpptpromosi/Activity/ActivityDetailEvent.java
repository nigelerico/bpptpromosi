package latihan.rico.com.bpptpromosi.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import latihan.rico.com.bpptpromosi.Adapter.AdapterEvent;
import latihan.rico.com.bpptpromosi.Adapter.AdapterImageEvent;
import latihan.rico.com.bpptpromosi.Adapter.AdapterSektor;
import latihan.rico.com.bpptpromosi.Model.ModelEventImage;
import latihan.rico.com.bpptpromosi.Model.ModelSektor;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Server.Server;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

public class ActivityDetailEvent extends AppCompatActivity implements AdapterImageEvent.onListClickedRowListner{


    private VideoView vv;
    private MediaController mediacontroller;
    private Uri uri;
    private boolean isContinuously = false;
    private ProgressBar progressBar;
    TextView tv_desk, tv_judul;
    String video, judul_event, deskripsi;
    int id;
    RecyclerView rv_image;
    ArrayList<ModelEventImage> modelEventImages = new ArrayList<>();
    AdapterImageEvent adapterImageEvent;
    private static final String URL_EVENT_IMAGE = Server.URL_API + "ApiEventImage.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        video = intent.getStringExtra("video");
        judul_event = intent.getStringExtra("judul_event");
        deskripsi = intent.getStringExtra("deskripsi");

        progressBar = (ProgressBar) findViewById(R.id.progrss);
        vv = (VideoView) findViewById(R.id.vv);
        tv_desk = (TextView) findViewById(R.id.tv_desk);
        tv_judul = (TextView) findViewById(R.id.tv_judul);
        rv_image = (RecyclerView) findViewById(R.id.rv_image);



        tv_desk.setText(Html.fromHtml(deskripsi));
        tv_judul.setText(judul_event);

        mediacontroller = new MediaController(this);
        mediacontroller.setAnchorView(vv);
        String uriPath = video; //update package name
        uri = Uri.parse(uriPath);

        isContinuously = false;
        progressBar.setVisibility(View.VISIBLE);
        vv.setMediaController(mediacontroller);
        vv.setVideoURI(uri);
        vv.requestFocus();

        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(isContinuously){
                    vv.start();
                }
            }
        });

        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                progressBar.setVisibility(View.GONE);
            }
        });

        getImage();
    }

    public void back(View view){
        super.onBackPressed();
    }

    @Override
    public void onListSelected(int mposition) {

    }



    private void getImage() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_EVENT_IMAGE,
                new Response.Listener<String>() {
                    public void onResponse(String response){
                        Log.d("json", response.toString());


                        try {
                            JSONArray jsonArray =  new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);


                                modelEventImages.add(new ModelEventImage(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("image")));

                                rv_image.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false));
                                rv_image.setHasFixedSize(true);
                                adapterImageEvent = new AdapterImageEvent(getApplicationContext(), modelEventImages, ActivityDetailEvent.this);
                                rv_image.setAdapter(adapterImageEvent);
                                adapterImageEvent.notifyDataSetChanged();
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


}
