package latihan.rico.com.bpptpromosi.AdapterRealisasi;

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
import latihan.rico.com.bpptpromosi.Activity.RealisasiBulanActivity;
import latihan.rico.com.bpptpromosi.Activity.RealisasiSektorActivity;
import latihan.rico.com.bpptpromosi.ModelRealisasi.ModelRealisasiBulan;
import latihan.rico.com.bpptpromosi.ModelRealisasi.ModelRealisasiTahun;
import latihan.rico.com.bpptpromosi.R;

public class AdapterRealisasiBulan  extends RecyclerView.Adapter<AdapterRealisasiBulan.ViewHolder>{

    private Context mContext;
    private List<ModelRealisasiBulan> modelRealisasiBulans;


    public AdapterRealisasiBulan(Context mContext, ArrayList<ModelRealisasiBulan> ModelRealisasiBulan){
        this.mContext = mContext;
        this.modelRealisasiBulans = ModelRealisasiBulan;


    }



    @Override
    public AdapterRealisasiBulan.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_realisasi_bulan, parent, false);
        AdapterRealisasiBulan.ViewHolder vh = new AdapterRealisasiBulan.ViewHolder(mView); // pass the view to View Holder


        return vh;
    }


    @Override
    public void onBindViewHolder(final AdapterRealisasiBulan.ViewHolder holder, final int position) {
        final ModelRealisasiBulan modelRealisasiBulan = modelRealisasiBulans.get(position);




        holder.tv_bulan.setText(modelRealisasiBulan.getBulanfix());
        holder.tv_value.setText("Rp."+modelRealisasiBulan.getValue());


        holder.cv_bulan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext.getApplicationContext(), RealisasiSektorActivity.class);
                intent.putExtra("id", modelRealisasiBulan.getId());
                intent.putExtra("bulan", modelRealisasiBulan.getBulanfix());
                view.getContext().startActivity(intent);

            }
        });

    }



    @Override
    public int getItemCount() {
        return modelRealisasiBulans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv_bulan;
        TextView tv_bulan,tv_value;


        public ViewHolder(View itemView) {
            super(itemView);
            cv_bulan = (CardView) itemView.findViewById(R.id.cv_bulan);
            tv_value = (TextView) itemView.findViewById(R.id.tv_value);
            tv_bulan = (TextView) itemView.findViewById(R.id.tv_bulan);


        }
    }

    public void clear(){
        modelRealisasiBulans.clear();
        notifyDataSetChanged();
    }
}

