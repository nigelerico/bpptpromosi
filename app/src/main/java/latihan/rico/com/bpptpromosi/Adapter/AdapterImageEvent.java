package latihan.rico.com.bpptpromosi.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

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

import latihan.rico.com.bpptpromosi.Model.ModelEvent;
import latihan.rico.com.bpptpromosi.Model.ModelEventImage;
import latihan.rico.com.bpptpromosi.Model.ModelSektor;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Server.Server;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class AdapterImageEvent  extends RecyclerView.Adapter<AdapterImageEvent.ViewHolder>{

    private Context mContext;
    private List<ModelEventImage> modelEventImages;
    onListClickedRowListner listner;
    ZoomageView iv_image;
    private static final String URL_EVENT_IMAGE = Server.URL_API + "ApiEventImageDetail.php";

    public AdapterImageEvent(Context mContext, ArrayList<ModelEventImage> modelEventImages, onListClickedRowListner listner){
        this.mContext = mContext;
        this.modelEventImages = modelEventImages;
        this.listner = listner;

    }

    public interface onListClickedRowListner {
        void onListSelected(int mposition);
    }

    @Override
    public AdapterImageEvent.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_event_image, parent, false);
        AdapterImageEvent.ViewHolder vh = new AdapterImageEvent.ViewHolder(mView); // pass the view to View Holder


        return vh;
    }


    @Override
    public void onBindViewHolder(final AdapterImageEvent.ViewHolder holder, final int position) {
        final ModelEventImage modelEventImage = modelEventImages.get(position);


        ImageLoader imageLoader = MySingleton.getInstance(mContext).getImageLoader();
        imageLoader.get(modelEventImages.get(position).getGambar(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                holder.logo.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });





        holder.logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//
//                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(mContext);
//                View mView = layoutInflaterAndroid.inflate(R.layout.dialog_image_event, null);
//                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(mContext, R.style.myDialog));
//                alertDialogBuilder.setView(mView);
//
//                iv_image = (ZoomageView) mView.findViewById(R.id.iv_image);
//
//                getImageDetail(position);
//                final AlertDialog alertDialog = alertDialogBuilder.create();
//                final Window dialogWindow = alertDialog.getWindow();
//                final WindowManager.LayoutParams dialogWindowAttributes = dialogWindow.getAttributes();
//
//
//
//                // Set fixed width (280dp) and WRAP_CONTENT height
//                final WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//                lp.copyFrom(dialogWindowAttributes);
//                lp.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 280, mContext.getApplicationContext().getResources().getDisplayMetrics());
//                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//                dialogWindow.setAttributes(lp);
//                alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    dialogWindow.setType(WindowManager.LayoutParams.TYPE_TOAST);
//                }
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    dialogWindow.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
//                }
//                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
//                {
//                    dialogWindow.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//                }
//                alertDialog.show();
//

            }
        });
    }


    private void getImageDetail(final int position) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_EVENT_IMAGE,
                new Response.Listener<String>() {
                    public void onResponse(String response){
                        Log.d("json", response.toString());


                        try {
                            JSONArray jsonArray =  new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);


                                ImageLoader imageLoader = MySingleton.getInstance(mContext).getImageLoader();
                                imageLoader.get( jsonObject.getString("image"), new ImageLoader.ImageListener() {
                                    @Override
                                    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                                       iv_image.setImageBitmap(response.getBitmap());
                                    }

                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        error.printStackTrace();
                                    }
                                });


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(modelEventImages.get(position).getId()));
                return params;
            }
        };
        MySingleton.getInstance(mContext).addToRequestQueue(stringRequest);
    }

    @Override
    public int getItemCount() {
        return modelEventImages.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView logo;




        public ViewHolder(View itemView) {
            super(itemView);

            logo = (ImageView) itemView.findViewById(R.id.iv_gambar);

        }
    }

    public void clear(){
        modelEventImages.clear();
        notifyDataSetChanged();
    }
}
