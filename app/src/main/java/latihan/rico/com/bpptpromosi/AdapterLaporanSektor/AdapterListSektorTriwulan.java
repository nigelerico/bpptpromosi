package latihan.rico.com.bpptpromosi.AdapterLaporanSektor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import latihan.rico.com.bpptpromosi.ModelLaporanSektor.ModelListSektorTahun;
import latihan.rico.com.bpptpromosi.ModelLaporanSektor.ModelListSektorTriwulan;
import latihan.rico.com.bpptpromosi.R;

public class AdapterListSektorTriwulan extends RecyclerView.Adapter<AdapterListSektorTriwulan.ViewHolder>{

    private Context mContext;
    private List<ModelListSektorTriwulan> sektorTriwulans;


    public AdapterListSektorTriwulan(Context mContext, ArrayList<ModelListSektorTriwulan> ModelListSektorTriwulan){
        this.mContext = mContext;
        this.sektorTriwulans = ModelListSektorTriwulan;

    }

    @Override
    public AdapterListSektorTriwulan.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_laporan_triwulan, parent, false);
        AdapterListSektorTriwulan.ViewHolder vh = new AdapterListSektorTriwulan.ViewHolder(mView); // pass the view to View Holder


        return vh;
    }


    @Override
    public void onBindViewHolder(final AdapterListSektorTriwulan.ViewHolder holder, final int position) {
        final ModelListSektorTriwulan modelListSektorTriwulan = sektorTriwulans.get(position);


        holder.tv_triwulan.setText(modelListSektorTriwulan.getTriwulan());
        holder.tv_nominal.setText("Rp."+(modelListSektorTriwulan.getNominaltriwulan()));


    }

    @Override
    public int getItemCount() {
        return sektorTriwulans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        TextView tv_triwulan,tv_nominal;
        RelativeLayout relativeLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            tv_nominal = (TextView) itemView.findViewById(R.id.tv_nominal_triwulan);
            tv_triwulan = (TextView) itemView.findViewById(R.id.tv_triwulan);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl_tahun);

        }
    }

    public void clear(){
        sektorTriwulans.clear();
        notifyDataSetChanged();
    }
}


