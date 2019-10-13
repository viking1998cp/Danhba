package vn.lachongmedia.appnv.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import vn.lachongmedia.appnv.R;
/**
 * Created by tungda .
 */
public class ChiTietKhachHangActivity extends AppCompatActivity {

    ImageView ivBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietkhachhang);
        initUI();
        back();

    }

    private void initUI() {
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
