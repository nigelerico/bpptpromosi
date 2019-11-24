package latihan.rico.com.bpptpromosi.Fragment;


import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import latihan.rico.com.bpptpromosi.Activity.MoreActivity;
import latihan.rico.com.bpptpromosi.Adapter.AdapterProspectHome;
import latihan.rico.com.bpptpromosi.Adapter.AdapterSektor;
import latihan.rico.com.bpptpromosi.Adapter.AdapterSektorHome;
import latihan.rico.com.bpptpromosi.Model.ModelListSektor;
import latihan.rico.com.bpptpromosi.Model.ModelProspect;
import latihan.rico.com.bpptpromosi.Model.ModelSektor;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Server.Server;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

public class HomeFragment extends Fragment implements MoreActivity.BottomSheetListener, AdapterSektor.onListClickedRowListner {


    Toolbar toolbar_bppt;
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    CollapsingToolbarLayout collapsingToolbarLayout;
    NestedScrollView scroll;
    RecyclerView rv_sektor;
    ArrayList<ModelSektor> mSektor = new ArrayList<>();
    ArrayList<ModelListSektor> modelListSektors = new ArrayList<>();
    ArrayList<ModelProspect> modelProspects = new ArrayList<>();
    AdapterSektor adapterSektor;
    private static final String URL_SEKTOR = Server.URL_API + "ApiSektor.php";
    private static final String URL_SEKTOR_LIST = Server.URL_API + "ApiListSektor.php";

    AdapterProspectHome adapterProspectHome;
    AdapterSektorHome adapterSektorHome;


    TextView txt_more;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.fragment_home, container, false);

        txt_more = view.findViewById(R.id.txt_more);



        recyclerView1 = view.findViewById(R.id.recycler_view);
        recyclerView2 = view.findViewById(R.id.recycler_view2);
        rv_sektor = view.findViewById(R.id.recyeview_sektor);
        toolbar_bppt = view.findViewById(R.id.toolbar_bppt);


        collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Home");

        scroll = view.findViewById(R.id.nested_scroll);

        collapsingToolbarLayout.setCollapsedTitleTextColor(
                ContextCompat.getColor(getContext(), R.color.colorWhite));
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);


        getsektor();
        getProspectHome();
        getSektorHome();




        txt_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoreActivity bottomSheet = new MoreActivity();
                bottomSheet.show(getActivity().getSupportFragmentManager(), "More");
            }
        });

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


    private void getProspectHome() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_SEKTOR_LIST,
                new Response.Listener<String>() {
                    public void onResponse(String response){
                        Log.d("json", response.toString());
                        modelListSektors.clear();
                        try {
                            JSONArray jsonArray =  new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                if (jsonObject.getString("statusVerifikasi").equals("Terverifikasi")){
                                    modelProspects.add(new ModelProspect(
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
                                }


                                recyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
                                recyclerView1.setHasFixedSize(true);
                                adapterProspectHome = new AdapterProspectHome(getContext(), modelProspects);
                                recyclerView1.setAdapter(adapterProspectHome);
                                adapterProspectHome.notifyDataSetChanged();
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

    private void getSektorHome() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_SEKTOR_LIST,
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
                                            jsonObject.getString("directory")));


                                recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
                                recyclerView2.setHasFixedSize(true);
                                adapterSektorHome = new AdapterSektorHome(getContext(), modelListSektors);
                                recyclerView2.setAdapter(adapterSektorHome);
                                adapterSektorHome.notifyDataSetChanged();
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
    public void onButtonClicked(String text) {

    }

    @Override
    public void onListSelected(int listposition) {
        Toast.makeText(getContext(),String.valueOf(mSektor.get(listposition).getId()), Toast.LENGTH_SHORT).show();
    }
}
