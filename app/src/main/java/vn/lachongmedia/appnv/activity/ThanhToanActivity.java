package vn.lachongmedia.appnv.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import vn.lachongmedia.appnv.R;

/**
 * Created by tungda .
 */
public class ThanhToanActivity extends AppCompatActivity {

    Button btXemLichSu;
    ImageView ivBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoan);
        initUI();
        back();
        btXemLichSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LichSuThanhToanActivity.class));
            }
        });
    }
    private void initUI() {
        //recyclerViewMatHang = findViewById(R.id.rvDotGiaoHang);
        btXemLichSu=findViewById(R.id.btXemLichSu);
        ivBack = findViewById(R.id.ivBack);
    }

    private void back() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
