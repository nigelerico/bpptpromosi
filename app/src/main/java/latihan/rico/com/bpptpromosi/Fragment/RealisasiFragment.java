package latihan.rico.com.bpptpromosi.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import latihan.rico.com.bpptpromosi.Adapter.AdapterList;
import latihan.rico.com.bpptpromosi.AdapterRealisasi.AdapterRealisasiTahun;
import latihan.rico.com.bpptpromosi.Model.ModelListSektor;
import latihan.rico.com.bpptpromosi.ModelRealisasi.ModelRealisasiTahun;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Server.Server;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;


public class RealisasiFragment extends Fragment {

    RecyclerView rv_tahun;
    ArrayList<ModelRealisasiTahun> modelRealisasiTahuns = new ArrayList<>();
    AdapterRealisasiTahun adapterRealisasiTahun;
    private static final String URL_REALISASI_TAHUN = Server.URL_API + "ApiRealisasiList.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.fragment_realisasi, container, false);

        rv_tahun = (RecyclerView) view.findViewById(R.id.rv_realisasi);


        getTahun();

        return view;
    }

    private void getTahun() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_REALISASI_TAHUN,
                new Response.Listener<String>() {
                    public void onResponse(String response){
                        Log.d("json", response.toString());
                     modelRealisasiTahuns.clear();
                        try {
                            JSONArray jsonArray =  new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                modelRealisasiTahuns.add(new ModelRealisasiTahun(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("tahun")));

                                rv_tahun.setLayoutManager(new LinearLayoutManager(getContext()));
                                rv_tahun.setHasFixedSize(true);
                                adapterRealisasiTahun = new AdapterRealisasiTahun(getContext(), modelRealisasiTahuns);
                                rv_tahun.setAdapter(adapterRealisasiTahun);
                                adapterRealisasiTahun.notifyDataSetChanged();
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
