package vn.lachongmedia.appnv.activity;

import android.app.ProgressDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;

import vn.lachongmedia.appnv.SharedPrefs;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.View;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.adapter.AdapterReCyclerSwipeLichHen;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerKehoachDonHang;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerKehoachTrongNgay;
import vn.lachongmedia.appnv.databinding.ActivityDanhsachvieccanlamBinding;
import vn.lachongmedia.appnv.object.KeHoachDonHangObject;
import vn.lachongmedia.appnv.object.KeHoachOBJ;
import vn.lachongmedia.appnv.object.LichHenObject;
import vn.lachongmedia.appnv.viewmodel.KeHoachDonHangViewModel;
import vn.lachongmedia.appnv.viewmodel.KeHoachViewModel;
import vn.lachongmedia.appnv.viewmodel.LichHenViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tungda on 7/18/2019.
 */
public class DanhSachViecCanLamActivity extends AppCompatActivity {
    ActivityDanhsachvieccanlamBinding binding;
    KeHoachViewModel keHoachViewModel;
    LichHenViewModel lichHenViewModel;
    KeHoachDonHangViewModel keHoachDonHangViewModel;
    private int tabFlag=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_danhsachvieccanlam);
        binding.rvViecCanLam.setLayoutManager(new LinearLayoutManager(this));
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        keHoachViewModel = ViewModelProviders.of(this).get(KeHoachViewModel.class);
        lichHenViewModel = ViewModelProviders.of(this).get(LichHenViewModel.class);
        keHoachDonHangViewModel=ViewModelProviders.of(this).get(KeHoachDonHangViewModel.class);
        getKeHoachTrongNgay();

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tabFlag=0;
                        getKeHoachTrongNgay();
                        break;
                    case 1:
                        tabFlag=1;
                        getLichHen();
                        break;
                    case 2:
                        tabFlag=2;
                        getKeHoachDonHang();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
         binding.pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
             @Override
             public void onRefresh() {
                 switch (tabFlag) {
                     case 0:
                         getKeHoachTrongNgay();
                         break;
                     case 1:

                         getLichHen();
                         break;
                     case 2:
                         getKeHoachDonHang();
                         break;
                 }
             }
         });
    }

    //ProgressDialog progressDialog;

    public void getKeHoachTrongNgay() {
        //binding.rvViecCanLam.removeAllViews();
        binding.pullToRefresh.setRefreshing(true);
        keHoachViewModel.getKeHoachTrongNgay().observe(this, new Observer<ArrayList<KeHoachOBJ>>() {
            @Override
            public void onChanged(@Nullable ArrayList<KeHoachOBJ> keHoachTrongNgay) {
                binding.pullToRefresh.setRefreshing(false);
                if (keHoachTrongNgay != null) {
                    AdapterRecyclerKehoachTrongNgay adapterRecyclerKehoachTrongNgay = new AdapterRecyclerKehoachTrongNgay(keHoachTrongNgay);
                    binding.rvViecCanLam.setAdapter(adapterRecyclerKehoachTrongNgay);
                }else {
                    AdapterRecyclerKehoachTrongNgay adapterRecyclerKehoachTrongNgay = new AdapterRecyclerKehoachTrongNgay(new ArrayList<KeHoachOBJ>());
                    binding.rvViecCanLam.setAdapter(adapterRecyclerKehoachTrongNgay);
                }
            }
        });

    }

    public void getLichHen() {
       // binding.rvViecCanLam.removeAllViews();
        binding.pullToRefresh.setRefreshing(true);
        Map<String, String> params = new HashMap<>();
        params.put("token", Common.getToken());
        params.put("idnhanvien", ""+ SharedPrefs.getInstance().get(Common.iDNhanVien,Integer.class));
        params.put("type","danhsach");
        params.put("tungay", Common.getNgayHienTaiHai());
        params.put("denngay",Common.getNgayHienTaiHai());
        lichHenViewModel.getKeHoachTrongNgay(params).observe(this, new Observer<ArrayList<LichHenObject>>() {
            @Override
            public void onChanged(@Nullable ArrayList<LichHenObject> lichHenTrongNgay) {
                binding.pullToRefresh.setRefreshing(false);
                if (lichHenTrongNgay != null) {
                    AdapterReCyclerSwipeLichHen adapter = new AdapterReCyclerSwipeLichHen(lichHenTrongNgay);
                    binding.rvViecCanLam.setAdapter(adapter);
                }else {
                    AdapterReCyclerSwipeLichHen adapter = new AdapterReCyclerSwipeLichHen(new ArrayList<LichHenObject>());
                    binding.rvViecCanLam.setAdapter(adapter);
                }
            }
        });

    }
    public void getKeHoachDonHang() {
        //binding.rvViecCanLam.removeAllViews();
        binding.pullToRefresh.setRefreshing(true);
        keHoachDonHangViewModel.getKeHoachDonHangTrongNgay(Common.getNgayHienTaiHai(), Common.getNgayHienTaiHai()).observe(this, new Observer<ArrayList<KeHoachDonHangObject>>() {
            @Override
            public void onChanged(@Nullable ArrayList<KeHoachDonHangObject> keHoachDonHangObjects) {
                binding.pullToRefresh.setRefreshing(false);
                if (keHoachDonHangObjects != null) {
                    AdapterRecyclerKehoachDonHang adapter = new AdapterRecyclerKehoachDonHang(keHoachDonHangObjects);
                    binding.rvViecCanLam.setAdapter(adapter);
                }else {

                    AdapterRecyclerKehoachDonHang adapter = new AdapterRecyclerKehoachDonHang(new ArrayList<KeHoachDonHangObject>());
                    binding.rvViecCanLam.setAdapter(adapter);
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.pullToRefresh.setRefreshing(false);
    }
}
