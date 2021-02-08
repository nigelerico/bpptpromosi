package latihan.rico.com.bpptpromosi.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

public class DetailProspectActivity extends AppCompatActivity {

    String nama, foto , url;
    ImageView gambar_sekor;
    TextView tv_nama_sektor;
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_prospect);

        youTubePlayerView = findViewById(R.id.youtube_player_view_detail);
        getLifecycle().addObserver(youTubePlayerView);
        tv_nama_sektor = (TextView) findViewById(R.id.tv_nama_sektor);
        gambar_sekor = (ImageView) findViewById(R.id.iv_gambar_sektor);


        Intent intent = getIntent();
        nama = intent.getStringExtra("nama");
        foto = intent.getStringExtra("foto");
        url = intent.getStringExtra("url").substring(30, 41);


        tv_nama_sektor.setText(nama);
        showImage(foto);


        //Toast.makeText(this, url, Toast.LENGTH_SHORT).show();


        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = url;
                youTubePlayer.loadVideo(videoId, 0);


            }
        });


    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();
    }

    public void showImage(String linkImage){
        ImageLoader imageLoader = MySingleton.getInstance(this.getApplicationContext()).getImageLoader();
        imageLoader.get(linkImage, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                gambar_sekor.setImageBitmap(response.getBitmap());
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