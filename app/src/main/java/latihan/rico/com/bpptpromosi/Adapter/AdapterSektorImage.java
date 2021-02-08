package latihan.rico.com.bpptpromosi.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.jsibbold.zoomage.ZoomageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.RecyclerView;
import latihan.rico.com.bpptpromosi.Activity.ActivityEventDetailImage;
import latihan.rico.com.bpptpromosi.Model.ModelEventImage;
import latihan.rico.com.bpptpromosi.Model.ModelSektorImage;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Server.Server;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

public class AdapterSektorImage  extends RecyclerView.Adapter<AdapterSektorImage.ViewHolder>{

    private Context mContext;
    private List<ModelSektorImage> modelSektorImages;
    private static final String URL_EVENT_IMAGE = Server.URL_API + "ApiEventImageDetail";


    public AdapterSektorImage(Context mContext, ArrayList<ModelSektorImage> modelSektorImages){
        this.mContext = mContext;
        this.modelSektorImages = modelSektorImages;

    }


    @Override
    public AdapterSektorImage.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_sektor_image, parent, false);
        AdapterSektorImage.ViewHolder vh = new AdapterSektorImage.ViewHolder(mView); // pass the view to View Holder


        return vh;
    }


    @Override
    public void onBindViewHolder(final AdapterSektorImage.ViewHolder holder, final int position) {
        final ModelSektorImage modelSektorImage = modelSektorImages.get(position);


        ImageLoader imageLoader = MySingleton.getInstance(mContext).getImageLoader();
        imageLoader.get(modelSektorImages.get(position).getFoto(), new ImageLoader.ImageListener() {
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
        return modelSektorImages.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView logo;




        public ViewHolder(View itemView) {
            super(itemView);

            logo = (ImageView) itemView.findViewById(R.id.iv_gambar_sektor);

        }
    }

    public void clear(){
        modelSektorImages.clear();
        notifyDataSetChanged();
    }
}
