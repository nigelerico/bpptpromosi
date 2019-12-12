package latihan.rico.com.bpptpromosi.Fragment;


import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import latihan.rico.com.bpptpromosi.Adapter.AdapterList;
import latihan.rico.com.bpptpromosi.Model.ModelListSektor;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Server.Server;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

public class SearchFragment extends Fragment {


    MaterialSearchView searchView;
    RecyclerView rv_sektor;
    ArrayList<ModelListSektor> modelListSektors = new ArrayList<>();
    AdapterList adapterList;
    private static final String URL_SEARCH = Server.URL_API + "ApiSearch.php";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.fragment_search, container, false);
        setHasOptionsMenu(true);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getContext()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getContext()).getSupportActionBar().setTitle("Search Sektor");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        searchView = (MaterialSearchView) view.findViewById(R.id.search_view);
        rv_sektor = (RecyclerView) view.findViewById(R.id.recycler_view_list);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView.setMenuItem(searchItem);

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!newText.equals("")){
                    searchSektor(newText);
                }
                return false;
            }

        });

    }

    private void searchSektor(final String keyword){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SEARCH,
                new Response.Listener<String>() {
                    public void onResponse(String response){
                        Log.d("json", response.toString());
                        modelListSektors.clear();
                        try {
                            JSONArray jsonArray =  new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                modelListSektors.add(new ModelListSektor(
                                        jsonObject.getInt("id"),
                                        jsonObject.getInt("id_sektor"),
                                        jsonObject.getInt("id_subsektor"),
                                        jsonObject.getString("nama_sektor"),
                                        jsonObject.getString("alamat_sektor"),
                                        jsonObject.getString("telepon_sektor"),
                                        jsonObject.getString("nama_pengelola"),
                                        jsonObject.getString("deskripsifix"),
                                        jsonObject.getString("statusVerifikasi"),
                                        jsonObject.getString("nama_idsektor"),
                                        jsonObject.getString("nama_idsubsektor"),
                                        jsonObject.getString("directory"),
                                        jsonObject.getString("image")));

                                rv_sektor.setLayoutManager(new LinearLayoutManager(getContext()));
                                rv_sektor.setHasFixedSize(true);
                                adapterList = new AdapterList(getContext(), modelListSektors);
                                rv_sektor.setAdapter(adapterList);
                                adapterList.notifyDataSetChanged();
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
                Map<String,String> map = new HashMap<>();
                map.put("keyword",keyword);
                return map;
            }
        };
        MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

}
