package latihan.rico.com.bpptpromosi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import latihan.rico.com.bpptpromosi.Activity.DetailIiproActivity;
import latihan.rico.com.bpptpromosi.Activity.DetailLahanActivity;
import latihan.rico.com.bpptpromosi.Model.ModelIipro;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

public class AdapterIipro  extends RecyclerView.Adapter<AdapterIipro.ViewHolder> {

    private Context mContext;
    private List<ModelIipro> iipros;

    public AdapterIipro(Context mContext, ArrayList<ModelIipro> modelIipros){
        this.mContext = mContext;
        this.iipros = modelIipros;
    }

    @Override
    public AdapterIipro.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_home, parent, false);
        AdapterIipro.ViewHolder vh = new AdapterIipro.ViewHolder(mView); // pass the view to View Holder


        return vh;
    }

    @Override
    public void onBindViewHolder(final AdapterIipro.ViewHolder holder, final int position) {
        final ModelIipro modelIipro = iipros.get(position);

        ImageLoader imageLoader = MySingleton.getInstance(mContext).getImageLoader();
        imageLoader.get(iipros.get(position).getImage(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                holder.iv_gambar.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        holder.tv_nama_sektor.setText(modelIipro.getNama_sektor());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.tv_desc.setText(Html.fromHtml(modelIipro.getDeskripsi()));
        } else {
            holder.tv_desc.setText(Html.fromHtml(modelIipro.getDeskripsi()));
        }

        holder.cv_sektor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext.getApplicationContext(), DetailIiproActivity.class);
                intent.putExtra("data", modelIipro);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return iipros.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv_sektor;
        ImageView iv_gambar;
        TextView tv_nama_sektor, tv_desc;


        public ViewHolder(View itemView) {
            super(itemView);
            cv_sektor = (CardView) itemView.findViewById(R.id.cv_sektor);
            tv_nama_sektor = (TextView) itemView.findViewById(R.id.tv_nama_sektor);
            tv_desc = (TextView) itemView.findViewById(R.id.tv_desc);
            iv_gambar = (ImageView) itemView.findViewById(R.id.iv_gambar);

        }
    }
    public void clear(){
        iipros.clear();
        notifyDataSetChanged();
    }



}
