package latihan.rico.com.bpptpromosi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import latihan.rico.com.bpptpromosi.Activity.DetailActivity;
import latihan.rico.com.bpptpromosi.Model.ModelListSektor;
import latihan.rico.com.bpptpromosi.Model.ModelProspect;
import latihan.rico.com.bpptpromosi.R;

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

        holder.tv_nama_sektor.setText(modelListSektor.getNama_sektor());
        holder.tv_sektor.setText(modelListSektor.getAlamat_sektor());
        holder.tv_bidang.setText(modelListSektor.getValue());

        holder.cv_sektor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, DetailActivity.class);
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sektors.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

//        CardView cv;
//        TextView tv_nama, tv_tempat, tv_bidang;
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
