package latihan.rico.com.bpptpromosi.Fragment;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.snackbar.Snackbar;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import latihan.rico.com.bpptpromosi.Adapter.AdapterList;
import latihan.rico.com.bpptpromosi.Adapter.AdapterSektorList;
import latihan.rico.com.bpptpromosi.Model.ModelListSektor;
import latihan.rico.com.bpptpromosi.Model.ModelSektor;
import latihan.rico.com.bpptpromosi.R;
import latihan.rico.com.bpptpromosi.Server.Server;
import latihan.rico.com.bpptpromosi.Volley.MySingleton;


public class ListFragment extends Fragment implements  AdapterSektorList.onListClickedRowListner{

    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;

    MaterialSearchView searchView;

    RecyclerView rv_sektor;
    ArrayList<ModelListSektor> modelListSektors = new ArrayList<>();
    AdapterList adapterList;

    AdapterSektorList adapterSektorList;
    RecyclerView recyeview_sektor;
    private static final String URL_SEKTOR_LIST = Server.URL_API + "ApiListSektor";
    private static final String URL_SEKTOR_LIST_WHERE = Server.URL_API + "ApiListSektorWhereSektor";
    private static final String URL_SEKTOR_FULL = Server.URL_API + "ApiSektorFull";

    ArrayList<ModelSektor> mSektor = new ArrayList<>();
        SwipeRefreshLayout swipe;
    int id, temp;
    Boolean status = true;
    Snackbar snackbar;
    private ShimmerFrameLayout mShimmerViewContainer;

  

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.fragment_list, container, false);


        setHasOptionsMenu(true);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getContext()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getContext()).getSupportActionBar().setTitle("List");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        searchView = (MaterialSearchView) view.findViewById(R.id.search_view);
        rv_sektor = (RecyclerView) view.findViewById(R.id.recycler_view_list);
        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);



        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe.setRefreshing(false);
                        if (status){
                            getSektorList();
                        } else {
                            selectAPI(id-1);
                        }
                    }
                }, 1000);
            }
        });

        snackbar = Snackbar
                .make(container, "Pencarian Tidak Ditemukan", Snackbar.LENGTH_LONG);

        getSektorList();

        return view;
    }


    private void getSektorList() {
        status = true;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_SEKTOR_LIST,
                new Response.Listener<String>() {
                    public void onResponse(String response){
                        Log.d("json", response.toString());
                        modelListSektors.clear();
                        try {
                            JSONObject jsonObjects = new JSONObject(response);
                            JSONArray jsonArray = jsonObjects.getJSONArray("data");

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
                                        jsonObject.getString("lokasi"),
                                        jsonObject.getString("latitude"),
                                        jsonObject.getString("longitude"),
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

                                mShimmerViewContainer.stopShimmer();
                                mShimmerViewContainer.setVisibility(View.GONE);
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
        inflater.inflate(R.menu.filter, menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_filter) {

            openDialog(R.layout.dialog_filter);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openDialog(int layout){
        dialogBuilder = new AlertDialog.Builder(getContext());
        View layoutView = getLayoutInflater().inflate(layout, null);
        recyeview_sektor = layoutView.findViewById(R.id.recyeview_sektor);
        ImageView iv_close = layoutView.findViewById(R.id.iv_close);
        Button btn_showall = layoutView.findViewById(R.id.btn_showall);
        dialogBuilder.setView(layoutView);
        alertDialog = dialogBuilder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btn_showall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSektorList();
                alertDialog.dismiss();
            }
        });



        getSektorFull();


    }

    private void getSektorFull() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_SEKTOR_FULL,
                new Response.Listener<String>() {
                    public void onResponse(String response){
                        Log.d("json", response.toString());
                        mSektor.clear();
                        try {
                            JSONObject jsonObjects = new JSONObject(response);
                            JSONArray jsonArray = jsonObjects.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                mSektor.add(new ModelSektor(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("value"),
                                        jsonObject.getString("logo")));

                                recyeview_sektor.setLayoutManager(new GridLayoutManager(getContext(),4));
                                recyeview_sektor.setHasFixedSize(true);
                                adapterSektorList = new AdapterSektorList(getContext(), mSektor, ListFragment.this);
                                recyeview_sektor.setAdapter(adapterSektorList);
                                adapterSektorList.notifyDataSetChanged();

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
    public void onListSelected(final int mposition) {
//        Toast.makeText(getContext(),String.valueOf(mSektor.get(mposition).getId()), Toast.LENGTH_SHORT).show();
        id=mSektor.get(mposition).getId();
        selectAPI(mposition);
    }

    public void selectAPI(final int mposition) {
        status=false;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SEKTOR_LIST_WHERE,
                new Response.Listener<String>() {
                    public void onResponse(String response) {
                        Log.d("json", response.toString());
                        temp=0;
                        modelListSektors.clear();
                        try {
                            JSONObject jsonObjects = new JSONObject(response);
                            JSONArray jsonArray = jsonObjects.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                temp++;
                                modelListSektors.add(new ModelListSektor(
                                        jsonObject.getInt("id"),
                                        jsonObject.getInt("id_sektor"),
                                        jsonObject.getInt("id_subsektor"),
                                        jsonObject.getString("nama_sektor"),
                                        jsonObject.getString("alamat_sektor"),
                                        jsonObject.getString("telepon_sektor"),
                                        jsonObject.getString("nama_pengelola"),
                                        jsonObject.getString("deskripsifix"),
                                        jsonObject.getString("lokasi"),
                                        jsonObject.getString("latitude"),
                                        jsonObject.getString("longitude"),
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
                            if (temp == 0){
                                snackbar.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            snackbar.show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                snackbar.show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(mSektor.get(mposition).getId()));
                return params;
            }

        };
        MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
        alertDialog.dismiss();
    }


    @Override
    public void onResume() {
        mShimmerViewContainer.startShimmer();
        super.onResume();
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmer();
        super.onPause();
    }


}
