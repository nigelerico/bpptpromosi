package latihan.rico.com.bpptpromosi.Adapter;

import android.content.Context;
import android.content.Intent;
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

import latihan.rico.com.bpptpromosi.Activity.DetailActivity;
import latihan.rico.com.bpptpromosi.Activity.DetailLahanActivity;
import latihan.rico.com.bpptpromosi.Activity.DetailPeluangActivity;
import latihan.rico.com.bpptpromosi.Model.ModelPeluang;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

public class AdapterPeluang extends RecyclerView.Adapter<AdapterPeluang.ViewHolder> {
    private Context mContext;
    private List<ModelPeluang> peluangs;

    public AdapterPeluang(Context mContext, ArrayList<ModelPeluang> modelPeluangs){
        this.mContext = mContext;
        this.peluangs = modelPeluangs;
    }

    @Override
    public AdapterPeluang.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_home, parent, false);
        AdapterPeluang.ViewHolder vh = new AdapterPeluang.ViewHolder(mView); // pass the view to View Holder


        return vh;
    }

    @Override
    public void onBindViewHolder(final AdapterPeluang.ViewHolder holder, final int position) {
        final ModelPeluang modelPeluang = peluangs.get(position);

        ImageLoader imageLoader = MySingleton.getInstance(mContext).getImageLoader();
        imageLoader.get(peluangs.get(position).getImage(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                holder.iv_gambar.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        holder.tv_nama_sektor.setText(modelPeluang.getNama_sektor());

        holder.tv_desc.setText("Peluang Investasi di bidang " + modelPeluang.getNama_sektor() + " di Kabupaten Tulungagung");

        holder.cv_sektor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext.getApplicationContext(), DetailPeluangActivity.class);
                intent.putExtra("data", modelPeluang);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return peluangs.size();
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
        peluangs.clear();
        notifyDataSetChanged();
    }


}
