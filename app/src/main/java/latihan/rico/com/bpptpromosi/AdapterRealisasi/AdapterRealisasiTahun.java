package latihan.rico.com.bpptpromosi.AdapterRealisasi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import latihan.rico.com.bpptpromosi.Activity.FilterSektorActivity;
import latihan.rico.com.bpptpromosi.Activity.RealisasiBulanActivity;
import latihan.rico.com.bpptpromosi.Model.ModelSektor;
import latihan.rico.com.bpptpromosi.ModelRealisasi.ModelRealisasiTahun;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

public class AdapterRealisasiTahun  extends RecyclerView.Adapter<AdapterRealisasiTahun.ViewHolder>{

    private Context mContext;
    private List<ModelRealisasiTahun> modelRealisasiTahuns;


    public AdapterRealisasiTahun(Context mContext, ArrayList<ModelRealisasiTahun> ModelRealisasiTahun){
        this.mContext = mContext;
        this.modelRealisasiTahuns = ModelRealisasiTahun;


    }



    @Override
    public AdapterRealisasiTahun.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_realisasi_tahun, parent, false);
        AdapterRealisasiTahun.ViewHolder vh = new AdapterRealisasiTahun.ViewHolder(mView); // pass the view to View Holder


        return vh;
    }


    @Override
    public void onBindViewHolder(final AdapterRealisasiTahun.ViewHolder holder, final int position) {
        final ModelRealisasiTahun modelRealisasiTahun = modelRealisasiTahuns.get(position);




        holder.tv_tahun.setText("DATA REALISASI INVESTASI DAERAH PMA/PMDN MELALUI OSS TAHUN "+modelRealisasiTahun.getTahun());


        holder.cv_tahun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext.getApplicationContext(), RealisasiBulanActivity.class);
                intent.putExtra("id", modelRealisasiTahun.getId());
                intent.putExtra("tahun", modelRealisasiTahun.getTahun());
                view.getContext().startActivity(intent);

            }
        });

    }



    @Override
    public int getItemCount() {
        return modelRealisasiTahuns.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv_tahun;
        TextView tv_tahun;


        public ViewHolder(View itemView) {
            super(itemView);
            cv_tahun = (CardView) itemView.findViewById(R.id.cv_tahun);
            tv_tahun = (TextView) itemView.findViewById(R.id.tv_tahun);


        }
    }

    public void clear(){
        modelRealisasiTahuns.clear();
        notifyDataSetChanged();
    }
}

