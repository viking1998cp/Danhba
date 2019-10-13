package vn.lachongmedia.appnv.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.SharedPrefs;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerDanhSachPhanHoi;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerTaoPhanHoi;
import vn.lachongmedia.appnv.databinding.ActivityTaophanhoiBinding;
import vn.lachongmedia.appnv.databinding.FragmentDanhsachphanhoiBinding;
import vn.lachongmedia.appnv.network.Login.LoginRespon;
import vn.lachongmedia.appnv.network.NetContext;
import vn.lachongmedia.appnv.network.Service;
import vn.lachongmedia.appnv.network.phanhoi.DanhSachPhanHoiRespon;
import vn.lachongmedia.appnv.network.phanhoi.DanhSachThemPhanHoiRespon;
import vn.lachongmedia.appnv.object.CuaHang;
import vn.lachongmedia.appnv.object.PhanHoi.DanhSachPhanHoi;
import vn.lachongmedia.appnv.object.PhanHoi.LichSuNhom;
import vn.lachongmedia.appnv.viewmodel.PhanHoi.DanhSachPhanHoiViewModel;
import vn.lachongmedia.appnv.viewmodel.PhanHoi.DanhSachThemPhanHoiViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tungda .
 */
public class TaoPhanHoiActivity extends AppCompatActivity {
    ActivityTaophanhoiBinding binding;
    AdapterRecyclerTaoPhanHoi adapterRecyclerTaoPhanHoi;
    ArrayList<DanhSachPhanHoi> listDanhSachPhanHois = new ArrayList<>();
    DanhSachThemPhanHoiViewModel danhSachThemPhanHoiViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_taophanhoi);
        back();
        //recyclerViewAlbum.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));
        binding.rvDanhSach.setLayoutManager(new LinearLayoutManager(this));
        danhSachThemPhanHoiViewModel = ViewModelProviders.of(this).get(DanhSachThemPhanHoiViewModel.class);
        adapterRecyclerTaoPhanHoi = new AdapterRecyclerTaoPhanHoi(listDanhSachPhanHois);
        binding.rvDanhSach.setAdapter(adapterRecyclerTaoPhanHoi);
        getData();
        adapterRecyclerTaoPhanHoi.setOnItemClickedListener(new AdapterRecyclerTaoPhanHoi.OnItemClickedListener() {
            @Override
            public void onItemClick(int postion, View v) {
                //startActivity(new Intent(getActivity(), LichHenActivity.class));
            }
        });
    }

    private void getData() {

        Map<String, String> params = new HashMap<>();
        params.put("token", Common.getToken());
        params.put("idnhanvien", "" + SharedPrefs.getInstance().get(Common.iDNhanVien, Integer.class));
        params.put("idkhachhang", "0");
        params.put("type", "danhsach" );
        params.put("trangthaigps", String.valueOf(Common.checkGPS()));
        Log.d("BBB", "getDanhSachMatHang: "+params);
        danhSachThemPhanHoiViewModel.getDanhSachThemPhanHoi(params).observe(this, new Observer<DanhSachThemPhanHoiRespon>() {
            @Override
            public void onChanged(DanhSachThemPhanHoiRespon danhSachThemPhanHoiRespon) {
                try {
                    if (danhSachThemPhanHoiRespon != null) { ;
                        if (danhSachThemPhanHoiRespon.isStatus()) {
                            if (danhSachThemPhanHoiRespon.getThemPhanHoi() != null) {
                                listDanhSachPhanHois.addAll(danhSachThemPhanHoiRespon.getThemPhanHoi());
                                adapterRecyclerTaoPhanHoi.notifyDataSetChanged();
                            } else {
                                Common.ShowToastLong(getString(R.string.message_no_data));
                            }
                        } else {
                            //Thêm do khi set tim kiem không thay đổi kịp

                            //Common.ShowToastLong(danhSachMatHangLoadMoreRespon.getMsg());
                        }
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void back() {
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
