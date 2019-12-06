package latihan.rico.com.bpptpromosi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import latihan.rico.com.bpptpromosi.Activity.ActivityDetailEvent;
import latihan.rico.com.bpptpromosi.Model.ModelEvent;
import latihan.rico.com.bpptpromosi.Model.ModelListSektor;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Server.Server;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

public class AdapterEvent extends RecyclerView.Adapter<AdapterEvent.ViewHolder> {

    private Context mContext;
    private List<ModelEvent> events;
    private static final String URL_IMAGE_EVENT =  Server.URL_API + "ApiEventImage.php";

    public AdapterEvent(Context mContext, ArrayList<ModelEvent> modelEvents){
        this.mContext = mContext;
        this.events = modelEvents;
    }

    @Override
    public AdapterEvent.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_event, parent, false);
        AdapterEvent.ViewHolder vh = new AdapterEvent.ViewHolder(mView); // pass the view to View Holder

        return vh;
    }

    @Override
    public void onBindViewHolder(final AdapterEvent.ViewHolder holder, final int position) {
        final ModelEvent modelEvent = events.get(position);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_IMAGE_EVENT,
                new Response.Listener<String>() {
                    public void onResponse(String response){
                        Log.d("json", response.toString());


                        try {
                            JSONArray jsonArray =  new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);


                                ImageLoader imageLoader = MySingleton.getInstance(mContext).getImageLoader();
                                imageLoader.get( jsonObject.getString("image"), new ImageLoader.ImageListener() {
                                    @Override
                                    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                                        holder.iv_gambar.setImageBitmap(response.getBitmap());
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
                params.put("id", String.valueOf(modelEvent.getId()));
                return params;
            }
        };
        MySingleton.getInstance(mContext).addToRequestQueue(stringRequest);

        holder.tv_nama_event.setText(modelEvent.getJudul_event());
        holder.cv_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ActivityDetailEvent.class);
                intent.putExtra("video", modelEvent.getVideo());
                intent.putExtra("judul_event", modelEvent.getJudul_event());
                intent.putExtra("deskripsi", modelEvent.getDeskripsi());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_gambar;
        TextView tv_nama_event;
        CardView cv_event;

        public ViewHolder(View itemView) {
            super(itemView);

            iv_gambar = itemView.findViewById(R.id.iv_gambar);
            tv_nama_event = itemView.findViewById(R.id.tv_nama_event);
            cv_event = itemView.findViewById(R.id.cv_event);

        }
    }
}
