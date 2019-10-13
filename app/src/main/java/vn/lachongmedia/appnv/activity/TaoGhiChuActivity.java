package vn.lachongmedia.appnv.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerTaoGhiChu;
import vn.lachongmedia.appnv.object.CuaHang;

import java.util.ArrayList;
/**
 * Created by tungda .
 */
public class TaoGhiChuActivity extends AppCompatActivity {
    RecyclerView rvDanhSach;
    ImageView ivBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taoghichu);
        initUI();
        back();

        //recyclerViewAlbum.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));
        rvDanhSach.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<CuaHang> cuaHangs = new ArrayList<>();
        cuaHangs.add(new CuaHang());
        cuaHangs.add(new CuaHang());
        cuaHangs.add(new CuaHang());
        AdapterRecyclerTaoGhiChu adapterRecyclerTaoPhanHoi = new AdapterRecyclerTaoGhiChu(getApplicationContext(), cuaHangs);
        rvDanhSach.setAdapter(adapterRecyclerTaoPhanHoi);
        adapterRecyclerTaoPhanHoi.setOnItemClickedListener(new AdapterRecyclerTaoGhiChu.OnItemClickedListener() {
            @Override
            public void onItemClick(int postion, View v) {
                //startActivity(new Intent(getActivity(), LichHenActivity.class));
            }
        });
    }

    private void initUI() {
        //recyclerViewMatHang = findViewById(R.id.rvDotGiaoHang);
        rvDanhSach = findViewById(R.id.rvDanhSach);
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
