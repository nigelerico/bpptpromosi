package latihan.rico.com.bpptpromosi.Activity;

import androidx.appcompat.app.AppCompatActivity;
import latihan.rico.com.bpptpromosi.R;

import android.os.Bundle;
import android.view.View;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


    }

    public void back(View view){
        super.onBackPressed();
    }
}
