package latihan.rico.com.bpptpromosi.Activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import latihan.rico.com.bpptpromosi.Adapter.AdapterSektor;
import latihan.rico.com.bpptpromosi.Model.ModelSektor;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Server.Server;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

public class MoreActivity extends BottomSheetDialogFragment{

    private BottomSheetListener mListener;
    RecyclerView rv_sektor;
    ArrayList<ModelSektor> mSektor = new ArrayList<>();
    AdapterSektor adapterSektor;
    private static final String URL_SEKTOR_FULL = Server.URL_API + "ApiSektorFull.php";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_more, container, false);
        rv_sektor = v.findViewById(R.id.recyeview_sektor);

       getSektorFull();

        return v;
    }



    public interface BottomSheetListener {
        void onButtonClicked(String text);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement BottomSheetListener");
        }
    }


    private void getSektorFull() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_SEKTOR_FULL,
                new Response.Listener<String>() {
                    public void onResponse(String response){
                        Log.d("json", response.toString());

                        try {
                            JSONArray jsonArray =  new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                mSektor.add(new ModelSektor(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("value"),
                                        jsonObject.getString("logo")));

                                rv_sektor.setLayoutManager(new GridLayoutManager(getContext(),4));
                                rv_sektor.setHasFixedSize(true);
                                adapterSektor = new AdapterSektor(getContext(), mSektor);
                                rv_sektor.setAdapter(adapterSektor);
                                adapterSektor.notifyDataSetChanged();
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
        });
        MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }


}
