package latihan.rico.com.bpptpromosi.AdapterLaporanSektor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.recyclerview.widget.RecyclerView;
import latihan.rico.com.bpptpromosi.ModelLaporanSektor.ModelListSektorBulan;
import latihan.rico.com.bpptpromosi.R;

public class AdapterListSektorBulan extends RecyclerView.Adapter<AdapterListSektorBulan.ViewHolder>{

    private Context mContext;
    private List<ModelListSektorBulan> sektorBulans;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);


    public AdapterListSektorBulan(Context mContext, ArrayList<ModelListSektorBulan> ModelListSektorBulan){
        this.mContext = mContext;
        this.sektorBulans = ModelListSektorBulan;


    }

    @Override
    public AdapterListSektorBulan.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_laporan_bulan, parent, false);
        AdapterListSektorBulan.ViewHolder vh = new AdapterListSektorBulan.ViewHolder(mView); // pass the view to View Holder


        return vh;
    }


    @Override
    public void onBindViewHolder(final AdapterListSektorBulan.ViewHolder holder, final int position) {
        final ModelListSektorBulan modelListSektorBulan = sektorBulans.get(position);


        holder.tv_bulan.setText(modelListSektorBulan.getBulan());
        holder.tv_nominal.setText(formatRupiah.format((double)modelListSektorBulan.getNominal()));


    }

    @Override
    public int getItemCount() {
        return sektorBulans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        TextView tv_bulan,tv_nominal;
        RelativeLayout relativeLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            tv_nominal = (TextView) itemView.findViewById(R.id.tv_nominal_bulan);
            tv_bulan = (TextView) itemView.findViewById(R.id.tv_bulan);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl_tahun);

        }
    }

    public void clear(){
        sektorBulans.clear();
        notifyDataSetChanged();
    }
}



