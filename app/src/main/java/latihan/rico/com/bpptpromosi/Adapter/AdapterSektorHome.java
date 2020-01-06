package latihan.rico.com.bpptpromosi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
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
import latihan.rico.com.bpptpromosi.Model.ModelListSektor;
import latihan.rico.com.bpptpromosi.Model.ModelProspect;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Server.Server;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

public class AdapterSektorHome extends RecyclerView.Adapter<AdapterSektorHome.ViewHolder>{

    private Context mContext;
    private List<ModelListSektor> sektorsHome;


    public AdapterSektorHome(Context mContext, ArrayList<ModelListSektor> ModelListSektor){
        this.mContext = mContext;
        this.sektorsHome = ModelListSektor;
    }

    @Override
    public AdapterSektorHome.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_home, parent, false);
        AdapterSektorHome.ViewHolder vh = new AdapterSektorHome.ViewHolder(mView); // pass the view to View Holder


        return vh;
    }


    @Override
    public void onBindViewHolder(final AdapterSektorHome.ViewHolder holder, final int position) {
        final ModelListSektor modelListSektor = sektorsHome.get(position);


        ImageLoader imageLoader2 = MySingleton.getInstance(mContext).getImageLoader();
        imageLoader2.get(sektorsHome.get(position).getGambar(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                holder.iv_gambar.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });


        holder.tv_nama_sektor.setText(Html.fromHtml(modelListSektor.getNama_sektor()));
        holder.tv_sektor.setText(modelListSektor.getMaster_sektor());
        holder.tv_bidang.setText(modelListSektor.getValue());
        holder.cv_sektor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext.getApplicationContext(), DetailActivity.class);
                intent.putExtra("id", modelListSektor.getId());
                intent.putExtra("id_sektor", modelListSektor.getId_sektor());
                intent.putExtra("id_sub_sektor", modelListSektor.getId_subsektor());
                intent.putExtra("nama_sektor", modelListSektor.getNama_sektor());
                intent.putExtra("sektor", modelListSektor.getMaster_sektor());
                intent.putExtra("bidang", modelListSektor.getValue());
                intent.putExtra("alamat", modelListSektor.getAlamat_sektor());
                intent.putExtra("pemilik", modelListSektor.getNama_pengelola());
                intent.putExtra("deskripsi", modelListSektor.getDeskripsi());
                intent.putExtra("logo", modelListSektor.getLogo());
                intent.putExtra("status", modelListSektor.getStatusVerifikasi());
                intent.putExtra("lokasi", modelListSektor.getLokasi());
                intent.putExtra("latitude", modelListSektor.getLatitude());
                intent.putExtra("longitude", modelListSektor.getLongitude());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sektorsHome.size();
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
        sektorsHome.clear();
        notifyDataSetChanged();
    }
}
