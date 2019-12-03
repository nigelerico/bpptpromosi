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
import latihan.rico.com.bpptpromosi.R;


public class AdapterListSektorTahun  extends RecyclerView.Adapter<AdapterListSektorTahun.ViewHolder>{

    private Context mContext;
    private List<ModelListSektorTahun> sektorstahun;
    onListClickedRowListner listner;

    public AdapterListSektorTahun(Context mContext, ArrayList<ModelListSektorTahun> ModelListSektorTahun,onListClickedRowListner listner){
        this.mContext = mContext;
        this.sektorstahun = ModelListSektorTahun;
        this.listner = listner;

    }

    public interface onListClickedRowListner {
        void onListSelected(int mposition);
    }

    @Override
    public AdapterListSektorTahun.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_laporan_tahun, parent, false);
        AdapterListSektorTahun.ViewHolder vh = new AdapterListSektorTahun.ViewHolder(mView); // pass the view to View Holder


        return vh;
    }


    @Override
    public void onBindViewHolder(final AdapterListSektorTahun.ViewHolder holder, final int position) {
        final ModelListSektorTahun modelListSektorTahun = sektorstahun.get(position);


        holder.tv_tahun.setText("Tahun "+(modelListSektorTahun.getTahun()));
        holder.tv_nominal.setText("Rp."+(modelListSektorTahun.getNominal()));


        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onListSelected(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sektorstahun.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        TextView tv_tahun,tv_nominal;
        RelativeLayout relativeLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            tv_nominal = (TextView) itemView.findViewById(R.id.tv_nominal);
            tv_tahun = (TextView) itemView.findViewById(R.id.tv_tahun);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl_tahun);

        }
    }

    public void clear(){
        sektorstahun.clear();
        notifyDataSetChanged();
    }
}

