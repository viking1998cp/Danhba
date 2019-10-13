package vn.lachongmedia.appnv.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import vn.lachongmedia.appnv.R;
/**
 * Created by tungda .
 */
public class CheckInActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_checkout);
        //getSupportActionBar().setTitle("Vào điểm thành công");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
