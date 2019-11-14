package latihan.rico.com.bpptpromosi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import latihan.rico.com.bpptpromosi.Activity.DetailActivity;
import latihan.rico.com.bpptpromosi.Model.ModelProspect;
import latihan.rico.com.bpptpromosi.R;

public class AdapterList  extends RecyclerView.Adapter<AdapterList.ViewHolder>{

    private Context mContext;
    private List<ModelProspect> prospects;

    public AdapterList(Context mContext, ArrayList<ModelProspect> modelProspects){
        this.mContext = mContext;
        this.prospects = modelProspects;
    }

    @Override
    public AdapterList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list, parent, false);
        AdapterList.ViewHolder vh = new AdapterList.ViewHolder(mView); // pass the view to View Holder


        return vh;
    }


    @Override
    public void onBindViewHolder(final AdapterList.ViewHolder holder, final int position) {
        final ModelProspect modelProspect = prospects.get(position);
        holder.tv_nama.setText(modelProspect.getNama());
        holder.tv_tempat.setText(modelProspect.getTempat());
        holder.tv_bidang.setText(modelProspect.getBidang());

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, DetailActivity.class);
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return prospects.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView tv_nama, tv_tempat, tv_bidang;


        public ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardview);
            tv_nama = (TextView) itemView.findViewById(R.id.txt_nama);
            tv_tempat = (TextView) itemView.findViewById(R.id.txt_tempat);
            tv_bidang = (TextView) itemView.findViewById(R.id.txt_bidang);


        }
    }

    public void clear(){
        prospects.clear();
        notifyDataSetChanged();
    }
}
