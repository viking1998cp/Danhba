package vn.lachongmedia.appnv.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerLichSuThanhToan;
import vn.lachongmedia.appnv.object.CuaHang;

import java.util.ArrayList;
/**
 * Created by tungda .
 */
public class LichSuThanhToanActivity extends AppCompatActivity {
    RecyclerView recyclerViewMatHang;
    ImageView ivBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichsuthanhtoan);
        initUI();
        back();

        //recyclerViewAlbum.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));
        recyclerViewMatHang.setLayoutManager(new LinearLayoutManager(LichSuThanhToanActivity.this));
        ArrayList<CuaHang> cuaHangs = new ArrayList<>();
        cuaHangs.add(new CuaHang());
        cuaHangs.add(new CuaHang());
        cuaHangs.add(new CuaHang());
        AdapterRecyclerLichSuThanhToan adapterRecyclerDanhSachCuaHangCheckIn = new AdapterRecyclerLichSuThanhToan(getApplicationContext(), cuaHangs);
        recyclerViewMatHang.setAdapter(adapterRecyclerDanhSachCuaHangCheckIn);
        adapterRecyclerDanhSachCuaHangCheckIn.setOnItemClickedListener(new AdapterRecyclerLichSuThanhToan.OnItemClickedListener() {
            @Override
            public void onItemClick(int postion, View v) {
               // startActivity(new Intent(getApplicationContext(), AlbumActivity.class));
            }
        });

    }
    private void initUI() {
        recyclerViewMatHang = findViewById(R.id.rvDotGiaoHang);
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
