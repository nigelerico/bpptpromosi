package latihan.rico.com.bpptpromosi.AdapterRealisasi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import latihan.rico.com.bpptpromosi.ModelRealisasi.ModelRealisasiBulan;
import latihan.rico.com.bpptpromosi.ModelRealisasi.ModelRealisasiSektor;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

public class AdapterRealisasiSektor  extends RecyclerView.Adapter<AdapterRealisasiSektor.ViewHolder>{

    private Context mContext;
    private List<ModelRealisasiSektor> modelRealisasiSektors;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);


    public AdapterRealisasiSektor(Context mContext, ArrayList<ModelRealisasiSektor> ModelRealisasiSektor){
        this.mContext = mContext;
        this.modelRealisasiSektors = ModelRealisasiSektor;



    }



    @Override
    public AdapterRealisasiSektor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_realisasi_sektor, parent, false);
        AdapterRealisasiSektor.ViewHolder vh = new AdapterRealisasiSektor.ViewHolder(mView); // pass the view to View Holder


        return vh;
    }


    @Override
    public void onBindViewHolder(final AdapterRealisasiSektor.ViewHolder holder, final int position) {
        final ModelRealisasiSektor modelRealisasiSektor = modelRealisasiSektors.get(position);



        ImageLoader imageLoader = MySingleton.getInstance(mContext).getImageLoader();
        imageLoader.get(modelRealisasiSektors.get(position).getLogo(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                holder.logo.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });


        holder.tv_nama_sektor.setText(modelRealisasiSektor.getNama_sektor());
        holder.tv_unit_usaha.setText(modelRealisasiSektor.getUnitusaha());
        holder.tv_investasi.setText(formatRupiah.format((double)modelRealisasiSektor.getInvestasi()));
        holder.tv_tenaga_kerja.setText(modelRealisasiSektor.getTenaga_kerja());



    }



    @Override
    public int getItemCount() {
        return modelRealisasiSektors.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nama_sektor,tv_unit_usaha,tv_investasi,tv_tenaga_kerja;
        ImageView logo;


        public ViewHolder(View itemView) {
            super(itemView);
            tv_nama_sektor = (TextView) itemView.findViewById(R.id.textView_barang);
            tv_unit_usaha = (TextView) itemView.findViewById(R.id.txt_bidang);
            tv_investasi = (TextView) itemView.findViewById(R.id.textView_status);
            tv_tenaga_kerja = (TextView) itemView.findViewById(R.id.txt_pemilik);

            logo = (ImageView) itemView.findViewById(R.id.textView_kirim);




        }
    }

    public void clear(){
        modelRealisasiSektors.clear();
        notifyDataSetChanged();
    }
}

