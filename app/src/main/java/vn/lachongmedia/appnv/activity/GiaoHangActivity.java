package vn.lachongmedia.appnv.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerGiaoHang;
import vn.lachongmedia.appnv.object.CuaHang;

import java.util.ArrayList;
/**
 * Created by tungda .
 */
public class GiaoHangActivity extends AppCompatActivity {
    RecyclerView recyclerViewMatHang;
    Button btLichSu;
    ImageView ivBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaohang);
        initUI();
        back();
        recyclerViewMatHang = findViewById(R.id.rvMatHang);
        //recyclerViewAlbum.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));
        recyclerViewMatHang.setLayoutManager(new LinearLayoutManager(GiaoHangActivity.this));
        ArrayList<CuaHang> cuaHangs = new ArrayList<>();
        cuaHangs.add(new CuaHang());
        cuaHangs.add(new CuaHang());
        cuaHangs.add(new CuaHang());
        AdapterRecyclerGiaoHang adapterRecyclerDanhSachCuaHangCheckIn = new AdapterRecyclerGiaoHang(getApplicationContext(), cuaHangs);
        recyclerViewMatHang.setAdapter(adapterRecyclerDanhSachCuaHangCheckIn);
        adapterRecyclerDanhSachCuaHangCheckIn.setOnItemClickedListener(new AdapterRecyclerGiaoHang.OnItemClickedListener() {
            @Override
            public void onItemClick(int postion, View v) {
               // startActivity(new Intent(getApplicationContext(), AlbumActivity.class));
            }
        });
        btLichSu=findViewById(R.id.btLichSu);
        btLichSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LichSuGiaoHangActivity.class));
            }
        });
    }
    private void initUI() {
        recyclerViewMatHang = findViewById(R.id.rvMatHang);
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
