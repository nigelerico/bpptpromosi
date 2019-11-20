package latihan.rico.com.bpptpromosi.Fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;


import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import latihan.rico.com.bpptpromosi.Activity.DetailActivity;

import latihan.rico.com.bpptpromosi.Adapter.AdapterList;
import latihan.rico.com.bpptpromosi.Adapter.AdapterSektor;
import latihan.rico.com.bpptpromosi.Model.ModelListSektor;
import latihan.rico.com.bpptpromosi.Model.ModelProspect;
import latihan.rico.com.bpptpromosi.Model.ModelSektor;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Server.Server;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;


public class Fragment_2 extends Fragment {



     MaterialSearchView searchView;


    RecyclerView rv_sektor;
    ArrayList<ModelListSektor> modelListSektors = new ArrayList<>();
    AdapterList adapterList;
    private static final String URL_SEKTOR_LIST = Server.URL_API + "ApiListSektor.php";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.fragment_fragment_2, container, false);


        setHasOptionsMenu(true);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getContext()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getContext()).getSupportActionBar().setTitle("List");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        searchView = (MaterialSearchView) view.findViewById(R.id.search_view);
        rv_sektor = (RecyclerView) view.findViewById(R.id.recycler_view_list);

        getSektorList();

        return view;
    }


    private void getSektorList() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_SEKTOR_LIST,
                new Response.Listener<String>() {
                    public void onResponse(String response){
                        Log.d("json", response.toString());

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
                                        jsonObject.getString("directory")));

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
        });
        MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
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
                //method
                return false;
            }

        });

    }


}
