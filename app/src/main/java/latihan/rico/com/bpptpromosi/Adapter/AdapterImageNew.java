package latihan.rico.com.bpptpromosi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import latihan.rico.com.bpptpromosi.Model.ModelSektorImage;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

public class AdapterImageNew extends RecyclerView.Adapter<AdapterImageNew.ViewHolder>{

    private Context mContext;
    private List<String> Images;

    public AdapterImageNew(Context mContext, List<String> images){
        this.mContext = mContext;
        this.Images = images;
    }

    @Override
    public AdapterImageNew.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_sektor_image, parent, false);
        AdapterImageNew.ViewHolder vh = new AdapterImageNew.ViewHolder(mView); // pass the view to View Holder

        return vh;
    }


    @Override
    public void onBindViewHolder(final AdapterImageNew.ViewHolder holder, final int position) {
        ImageLoader imageLoader = MySingleton.getInstance(mContext).getImageLoader();
        imageLoader.get(Images.get(position), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                holder.logo.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });


    }

    @Override
    public int getItemCount() {
        return Images.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView logo;




        public ViewHolder(View itemView) {
            super(itemView);

            logo = (ImageView) itemView.findViewById(R.id.iv_gambar_sektor);

        }
    }

    public void clear(){
        Images.clear();
        notifyDataSetChanged();
    }


}
