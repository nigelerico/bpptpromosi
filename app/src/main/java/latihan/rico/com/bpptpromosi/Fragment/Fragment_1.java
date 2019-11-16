package latihan.rico.com.bpptpromosi.Fragment;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import latihan.rico.com.bpptpromosi.Adapter.AdapterProspect;
import latihan.rico.com.bpptpromosi.Adapter.AdapterSektor;
import latihan.rico.com.bpptpromosi.Model.ModelProspect;
import latihan.rico.com.bpptpromosi.Model.ModelSektor;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Server.Server;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

public class Fragment_1 extends Fragment {


    Toolbar toolbar_bppt;
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    ArrayList <ModelProspect> modelProspectList;
    ModelProspect mModelProspect;
    CollapsingToolbarLayout collapsingToolbarLayout;
    NestedScrollView scroll;

    RecyclerView rv_sektor;
    ArrayList<ModelSektor> mSektor = new ArrayList<>();
    ModelSektor modelSektor;
    AdapterSektor adapterSektor;
    private static final String URL_SEKTOR = Server.URL_API + "ApiSektor.php";




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.fragment_fragment_1, container, false);

        //setHasOptionsMenu(true);

        recyclerView1 = view.findViewById(R.id.recycler_view);
        recyclerView2 = view.findViewById(R.id.recycler_view2);
        rv_sektor = view.findViewById(R.id.recyeview_sektor);
        toolbar_bppt = view.findViewById(R.id.toolbar_bppt);
       // toolbar_bppt.setTitle("Bppt Promosi");
     //   ((AppCompatActivity)getContext()).setSupportActionBar(toolbar_bppt);

        collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Home");

        scroll = view.findViewById(R.id.nested_scroll);

        collapsingToolbarLayout.setCollapsedTitleTextColor(
                ContextCompat.getColor(getContext(), R.color.colorWhite));
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);

        //setMarginNaik();
        getsektor();


        LinearLayoutManager mGridLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(mGridLayoutManager);

        LinearLayoutManager mGridLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView2.setLayoutManager(mGridLayoutManager2);





        modelProspectList = new ArrayList<>();
        mModelProspect = new ModelProspect(1,"tes1","sawah","pertanian",R.drawable.ic_launcher_background);
        modelProspectList.add(mModelProspect);
        mModelProspect = new ModelProspect(1,"tes1","sawah","pertanian",R.drawable.ic_launcher_background);
        modelProspectList.add(mModelProspect);
        mModelProspect = new ModelProspect(1,"tes1","sawah","pertanian",R.drawable.ic_launcher_background);
        modelProspectList.add(mModelProspect);


        AdapterProspect myAdapter = new AdapterProspect(getContext(), modelProspectList);
        recyclerView1.setAdapter(myAdapter);

        AdapterProspect myAdapter2 = new AdapterProspect(getContext(), modelProspectList);
        recyclerView2.setAdapter(myAdapter2);

        return view;

    }


    private void setMarginNaik(){
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) scroll.getLayoutParams();
        layoutParams.setMargins(0,0,0,170);
        scroll.setLayoutParams(layoutParams);

    }

    private void getsektor() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_SEKTOR,
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
                                        Server.URL_GAMBAR +  jsonObject.getString("logo")));

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
