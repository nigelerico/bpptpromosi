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
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Server.Server;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

public class AdapterList  extends RecyclerView.Adapter<AdapterList.ViewHolder>{

    private Context mContext;
    private List<ModelListSektor> sektors;


    public AdapterList(Context mContext, ArrayList<ModelListSektor> modelListSektors){
        this.mContext = mContext;
        this.sektors = modelListSektors;
    }

    @Override
    public AdapterList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list, parent, false);
        AdapterList.ViewHolder vh = new AdapterList.ViewHolder(mView); // pass the view to View Holder


        return vh;
    }


    @Override
    public void onBindViewHolder(final AdapterList.ViewHolder holder, final int position) {
        final ModelListSektor modelListSektor = sektors.get(position);



        ImageLoader imageLoader2 = MySingleton.getInstance(mContext).getImageLoader();
        imageLoader2.get(sektors.get(position).getGambar(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                holder.iv_gambar.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        ImageLoader imageLoader = MySingleton.getInstance(mContext).getImageLoader();
        imageLoader.get(sektors.get(position).getLogo(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                holder.iv_bidang.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        if(modelListSektor.getStatusVerifikasi().equals("Belum Terverifikasi")){
            holder.iv_centang.setVisibility(View.GONE);
        }


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
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sektors.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        CardView cv_sektor;
        ImageView iv_gambar, iv_bidang, iv_centang;
        TextView tv_nama_sektor, tv_sektor, tv_bidang;


        public ViewHolder(View itemView) {
            super(itemView);
            cv_sektor = (CardView) itemView.findViewById(R.id.cv_sektor);
            tv_nama_sektor = (TextView) itemView.findViewById(R.id.tv_nama_sektor);
            tv_sektor = (TextView) itemView.findViewById(R.id.tv_sektor);
            tv_bidang = (TextView) itemView.findViewById(R.id.tv_bidang);
            iv_gambar = (ImageView) itemView.findViewById(R.id.iv_gambar);
            iv_bidang = (ImageView) itemView.findViewById(R.id.iv_bidang);
            iv_centang = (ImageView) itemView.findViewById(R.id.iv_centang);
        }
    }

    public void clear(){
        sektors.clear();
        notifyDataSetChanged();
    }
}
