package latihan.rico.com.bpptpromosi.Adapter;

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
import androidx.recyclerview.widget.RecyclerView;
import latihan.rico.com.bpptpromosi.Activity.DetailActivity;
import latihan.rico.com.bpptpromosi.Activity.FilterSektorActivity;
import latihan.rico.com.bpptpromosi.Model.ModelSektor;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

public class AdapterSektor  extends RecyclerView.Adapter<AdapterSektor.ViewHolder>{

    private Context mContext;
    private List<ModelSektor> sektors;

    public AdapterSektor(Context mContext, ArrayList<ModelSektor> modelSektors){
        this.mContext = mContext;
        this.sektors = modelSektors;
    }

    @Override
    public AdapterSektor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_sector, parent, false);
        AdapterSektor.ViewHolder vh = new AdapterSektor.ViewHolder(mView); // pass the view to View Holder


        return vh;
    }


    @Override
    public void onBindViewHolder(final AdapterSektor.ViewHolder holder, final int position) {
        final ModelSektor modelSektor = sektors.get(position);


        ImageLoader imageLoader = MySingleton.getInstance(mContext).getImageLoader();
        imageLoader.get(sektors.get(position).getLogo(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                holder.logo.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });


        holder.tv_sektor.setText(modelSektor.getValue());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext.getApplicationContext(), FilterSektorActivity.class);
                intent.putExtra("id", modelSektor.getId());
                view.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return sektors.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView logo;
        TextView tv_sektor;
        RelativeLayout relativeLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            tv_sektor = (TextView) itemView.findViewById(R.id.txt_sektor);
            logo = (ImageView) itemView.findViewById(R.id.iv_logo);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative_icon_sektor);

        }
    }

    public void clear(){
        sektors.clear();
        notifyDataSetChanged();
    }
}

