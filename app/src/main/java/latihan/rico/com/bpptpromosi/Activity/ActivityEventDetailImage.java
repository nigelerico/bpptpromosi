package latihan.rico.com.bpptpromosi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.jsibbold.zoomage.ZoomageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Server.Server;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

public class ActivityEventDetailImage extends AppCompatActivity {

    private static final String URL_EVENT_IMAGE = Server.URL_API + "ApiEventImageDetail.php";
    private ZoomageView iv_image;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail_image);

        iv_image = (ZoomageView) findViewById(R.id.iv_image);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        getImageDetail();
    }

    private void getImageDetail() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_EVENT_IMAGE,
                new Response.Listener<String>() {
                    public void onResponse(String response){
                        Log.d("json", response.toString());


                        try {
                            JSONArray jsonArray =  new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);




                                ImageLoader imageLoader = MySingleton.getInstance(getApplicationContext()).getImageLoader();
                                imageLoader.get( jsonObject.getString("image"), new ImageLoader.ImageListener() {
                                    @Override
                                    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                                        iv_image.setImageBitmap(response.getBitmap());

                                    }

                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        error.printStackTrace();
                                    }
                                });


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

    public void back(View view){
        super.onBackPressed();
    }
}
