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
import latihan.rico.com.bpptpromosi.Adapter.AdapterEvent;
import latihan.rico.com.bpptpromosi.Adapter.AdapterProspectHome;
import latihan.rico.com.bpptpromosi.Adapter.AdapterSektor;
import latihan.rico.com.bpptpromosi.Adapter.AdapterSektorHome;
import latihan.rico.com.bpptpromosi.AdapterRealisasi.AdapterRealisasiTahun;
import latihan.rico.com.bpptpromosi.Model.ModelEvent;
import latihan.rico.com.bpptpromosi.Model.ModelListSektor;
import latihan.rico.com.bpptpromosi.Model.ModelProspect;
import latihan.rico.com.bpptpromosi.Model.ModelSektor;
import latihan.rico.com.bpptpromosi.ModelRealisasi.ModelRealisasiTahun;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Server.Server;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;

public class HomeFragment extends Fragment implements MoreActivity.BottomSheetListener, AdapterSektor.onListClickedRowListner {


    Toolbar toolbar_bppt;
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    RecyclerView recyclerView3;
    CollapsingToolbarLayout collapsingToolbarLayout;
    NestedScrollView scroll;
    RecyclerView rv_sektor;
    ArrayList<ModelSektor> mSektor = new ArrayList<>();
    ArrayList<ModelListSektor> modelListSektors = new ArrayList<>();
    ArrayList<ModelProspect> modelProspects = new ArrayList<>();
    ArrayList<ModelEvent> modelEvents = new ArrayList<>();
    AdapterSektor adapterSektor;
    private static final String URL_SEKTOR = Server.URL_API + "ApiSektor";
    private static final String URL_SEKTOR_PROSPECT_LIST = Server.URL_API + "ApiProspectus";

    ArrayList<ModelRealisasiTahun> modelRealisasiTahuns = new ArrayList<>();
    AdapterRealisasiTahun adapterRealisasiTahun;
    private static final String URL_REALISASI_TAHUN = Server.URL_API + "ApiRealisasiList";

    AdapterProspectHome adapterProspectHome;
    AdapterSektorHome adapterSektorHome;
    AdapterEvent adapterEvent;


    TextView txt_more;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.fragment_home, container, false);

        txt_more = view.findViewById(R.id.txt_more);



        recyclerView1 = view.findViewById(R.id.recycler_view);
        recyclerView2 = view.findViewById(R.id.recycler_view2);
        recyclerView3 = view.findViewById(R.id.recycler_view3);
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
        getTahun();
//        getSektorHome();
//        getEvent();



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
                            JSONObject jsonObjects = new JSONObject(response);
                            JSONArray jsonArray = jsonObjects.getJSONArray("data");
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
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_SEKTOR_PROSPECT_LIST,
                new Response.Listener<String>() {
                    public void onResponse(String response){
                        Log.d("json", response.toString());
                        modelListSektors.clear();
                        try {
                            JSONObject jsonObjects = new JSONObject(response);
                            JSONArray jsonArray = jsonObjects.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);


                                    modelProspects.add(new ModelProspect(
                                            jsonObject.getString("id"),
                                            jsonObject.getString("nama"),
                                            jsonObject.getString("foto"),
                                            jsonObject.getString("deskripsi"),
                                            jsonObject.getString("url"),
                                            jsonObject.getString("status"),
                                            jsonObject.getString("created_at"),
                                            jsonObject.getString("updated_at"),
                                            jsonObject.getString("created_by")));


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

//    private void getSektorHome() {
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_SEKTOR_LIST,
//                new Response.Listener<String>() {
//                    public void onResponse(String response){
//                        Log.d("json", response.toString());
//                        modelListSektors.clear();
//                        try {
//                            JSONObject jsonObjects = new JSONObject(response);
//                            JSONArray jsonArray = jsonObjects.getJSONArray("data");
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                                    modelListSektors.add(new ModelListSektor(
//                                            jsonObject.getInt("id"),
//                                            jsonObject.getInt("id_sektor"),
//                                            jsonObject.getInt("id_subsektor"),
//                                            jsonObject.getString("nama_sektor"),
//                                            jsonObject.getString("alamat_sektor"),
//                                            jsonObject.getString("telepon_sektor"),
//                                            jsonObject.getString("nama_pengelola"),
//                                            jsonObject.getString("deskripsifix"),
//                                            jsonObject.getString("lokasi"),
//                                            jsonObject.getString("latitude"),
//                                            jsonObject.getString("longitude"),
//                                            jsonObject.getString("statusVerifikasi"),
//                                            jsonObject.getString("nama_idsektor"),
//                                            jsonObject.getString("nama_idsubsektor"),
//                                            jsonObject.getString("directory"),
//                                            jsonObject.getString("image")));
//
//
//                                recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
//                                recyclerView2.setHasFixedSize(true);
//                                adapterSektorHome = new AdapterSektorHome(getContext(), modelListSektors);
//                                recyclerView2.setAdapter(adapterSektorHome);
//                                adapterSektorHome.notifyDataSetChanged();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
//    }

//    private void getEvent() {
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_EVENT,
//                new Response.Listener<String>() {
//                    public void onResponse(String response){
//                        Log.d("json", response.toString());
//                        modelEvents.clear();
//                        try {
//                            JSONObject jsonObjects = new JSONObject(response);
//                            JSONArray jsonArray = jsonObjects.getJSONArray("data");
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                                modelEvents.add(new ModelEvent(
//                                        jsonObject.getInt("id"),
//                                        jsonObject.getString("judul_event"),
//                                        jsonObject.getString("deskripsifix"),
//                                        jsonObject.getString("video")));
//
//                                recyclerView3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
//                                recyclerView3.setHasFixedSize(true);
//                                adapterEvent = new AdapterEvent(getContext(), modelEvents);
//                                recyclerView3.setAdapter(adapterEvent);
//                                adapterEvent.notifyDataSetChanged();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
//    }


    private void getTahun() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_REALISASI_TAHUN,
                new Response.Listener<String>() {
                    public void onResponse(String response){
                        Log.d("json", response.toString());
                        modelRealisasiTahuns.clear();
                        try {
                            JSONObject jsonObjects = new JSONObject(response);
                            JSONArray jsonArray = jsonObjects.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                modelRealisasiTahuns.add(new ModelRealisasiTahun(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("tahun")));


                                recyclerView3.setLayoutManager(new GridLayoutManager(getContext(),4));
                                recyclerView3.setHasFixedSize(true);
                                adapterRealisasiTahun = new AdapterRealisasiTahun(getContext(), modelRealisasiTahuns);
                                recyclerView3.setAdapter(adapterRealisasiTahun);
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


    @Override
    public void onButtonClicked(String text) {

    }

    @Override
    public void onListSelected(int listposition) {
        Toast.makeText(getContext(),String.valueOf(mSektor.get(listposition).getId()), Toast.LENGTH_SHORT).show();
    }
}
