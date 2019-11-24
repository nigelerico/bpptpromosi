package latihan.rico.com.bpptpromosi.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import latihan.rico.com.bpptpromosi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RealisasiFragment extends Fragment {


    public RealisasiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_realisasi, container, false);
    }

}
