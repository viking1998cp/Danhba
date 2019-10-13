package vn.lachongmedia.appnv.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerDanhSachMatHang;
import vn.lachongmedia.appnv.object.CuaHang;

import java.util.ArrayList;
/**
 * Created by tungda .
 */
public class DanhSachMatHangActivity extends AppCompatActivity {
    RecyclerView recyclerViewMatHang;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathang);
        recyclerViewMatHang = findViewById(R.id.rvDanhSachMatHang);
        //recyclerViewAlbum.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));
        recyclerViewMatHang.setLayoutManager(new LinearLayoutManager(DanhSachMatHangActivity.this));
        ArrayList<CuaHang> cuaHangs = new ArrayList<>();
        cuaHangs.add(new CuaHang());
        cuaHangs.add(new CuaHang());
        cuaHangs.add(new CuaHang());
        AdapterRecyclerDanhSachMatHang adapterRecyclerDanhSachCuaHangCheckIn = new AdapterRecyclerDanhSachMatHang(getApplicationContext(), cuaHangs);
        recyclerViewMatHang.setAdapter(adapterRecyclerDanhSachCuaHangCheckIn);
        adapterRecyclerDanhSachCuaHangCheckIn.setOnItemClickedListener(new AdapterRecyclerDanhSachMatHang.OnItemClickedListener() {
            @Override
            public void onItemClick(int postion, View v) {
               // startActivity(new Intent(getApplicationContext(), AlbumActivity.class));
            }
        });
        Log.d("BBB", "onCreate: mathang");

    }
}
