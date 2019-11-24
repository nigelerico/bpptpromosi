package latihan.rico.com.bpptpromosi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import latihan.rico.com.bpptpromosi.Activity.DetailActivity;
import latihan.rico.com.bpptpromosi.Model.ModelProspect;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Server.Server;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

public class AdapterProspectHome extends RecyclerView.Adapter<AdapterProspectHome.ViewHolder>{

    private Context mContext;
    private List<ModelProspect> prospects;
    private static final String URL_FOTOLISTSEKTOR =  Server.URL_API + "ApiListSektorImage.php";

    public AdapterProspectHome(Context mContext, ArrayList<ModelProspect> ModelProspect){
        this.mContext = mContext;
        this.prospects = ModelProspect;
    }

    @Override
    public AdapterProspectHome.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_home, parent, false);
        AdapterProspectHome.ViewHolder vh = new AdapterProspectHome.ViewHolder(mView); // pass the view to View Holder


        return vh;
    }


    @Override
    public void onBindViewHolder(final AdapterProspectHome.ViewHolder holder, final int position) {
        final ModelProspect modelProspect = prospects.get(position);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FOTOLISTSEKTOR,
                new Response.Listener<String>() {
                    public void onResponse(String response){
                        Log.d("json", response.toString());


                        try {
                            JSONArray jsonArray =  new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);


                                ImageLoader imageLoader = MySingleton.getInstance(mContext).getImageLoader();
                                imageLoader.get( jsonObject.getString("directory"), new ImageLoader.ImageListener() {
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
                params.put("id", String.valueOf(modelProspect.getId()));
                return params;
            }
        };

        MySingleton.getInstance(mContext).addToRequestQueue(stringRequest);


        holder.tv_nama_sektor.setText(modelProspect.getNama_sektor());
        holder.tv_sektor.setText(modelProspect.getMaster_sektor());
        holder.tv_bidang.setText(modelProspect.getValue());

        holder.cv_sektor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext.getApplicationContext(), DetailActivity.class);
                intent.putExtra("id", modelProspect.getId());
                intent.putExtra("id_sektor", modelProspect.getId_sektor());
                intent.putExtra("id_sub_sektor", modelProspect.getId_subsektor());
                intent.putExtra("nama_sektor", modelProspect.getNama_sektor());
                intent.putExtra("sektor", modelProspect.getMaster_sektor());
                intent.putExtra("bidang", modelProspect.getValue());
                intent.putExtra("alamat", modelProspect.getAlamat_sektor());
                intent.putExtra("pemilik", modelProspect.getNama_pengelola());
                intent.putExtra("deskripsi", modelProspect.getDeskripsi());
                intent.putExtra("logo", modelProspect.getLogo());
                intent.putExtra("status", modelProspect.getStatusVerifikasi());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return prospects.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv_sektor;
        ImageView iv_gambar;
        TextView tv_nama_sektor, tv_sektor, tv_bidang;


        public ViewHolder(View itemView) {
            super(itemView);
            cv_sektor = (CardView) itemView.findViewById(R.id.cv_sektor);
            tv_nama_sektor = (TextView) itemView.findViewById(R.id.tv_nama_sektor);
            tv_sektor = (TextView) itemView.findViewById(R.id.tv_sektor);
            tv_bidang = (TextView) itemView.findViewById(R.id.tv_bidang);
            iv_gambar = (ImageView) itemView.findViewById(R.id.iv_gambar);

        }
    }
    public void clear(){
        prospects.clear();
        notifyDataSetChanged();
    }
}
