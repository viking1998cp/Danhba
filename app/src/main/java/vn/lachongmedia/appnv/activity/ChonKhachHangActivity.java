package vn.lachongmedia.appnv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.SharedPrefs;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerDanhSachCuaHang;
import vn.lachongmedia.appnv.databinding.ActivityChonkhachhangBinding;
import vn.lachongmedia.appnv.network.CuaHang;
import vn.lachongmedia.appnv.network.ListCuaHangLoadMoreRespon;
import vn.lachongmedia.appnv.viewmodel.DanhSachCuaHangViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tungda on 7/26/2019.
 */

/**
 * Dùng cho tất cả các chức năng có chọn khách hàng
 */
public class ChonKhachHangActivity extends AppCompatActivity {
    ActivityChonkhachhangBinding binding;
    DanhSachCuaHangViewModel danhSachCuaHangViewModel;
    private boolean isLoading = false;
    private int lastID = 0;
    private int endList = 1;
    private ArrayList<CuaHang> listCuaHang = new ArrayList<>();
    AdapterRecyclerDanhSachCuaHang adapterRecyclerDanhSachCuaHang;
    private int parentFormId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(ChonKhachHangActivity.this, R.layout.activity_chonkhachhang);
        if(this.getIntent().getExtras() != null){
            parentFormId = this.getIntent().getExtras().getInt("parentFormId");
        }
        danhSachCuaHangViewModel = ViewModelProviders.of(this).get(DanhSachCuaHangViewModel.class);
        binding.rvDanhSachKhachHang.setLayoutManager(new LinearLayoutManager(this));
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adapterRecyclerDanhSachCuaHang=new AdapterRecyclerDanhSachCuaHang(listCuaHang);
        binding.rvDanhSachKhachHang.setAdapter(adapterRecyclerDanhSachCuaHang);
        adapterRecyclerDanhSachCuaHang.setOnItemClickedListener(new AdapterRecyclerDanhSachCuaHang.OnItemClickedListener() {
            @Override
            public void onItemClick(int postion, View v) {
                Intent intent=new Intent();
                intent.putExtra("cuahang",listCuaHang.get(postion));
                setResult(1,intent);
                finish();
            }
        });


        binding.pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                lastID=0;
                endList=1;
                isLoading=false;
                listCuaHang.clear();
                getDanhSachKhachHang();
            }
        });
        loadMore();
        getDanhSachKhachHang();
    }

    private void hideLoadMore(){
        if(lastID!=0){
            listCuaHang.remove(listCuaHang.size() - 1);
            int scrollPosition = listCuaHang.size();
            adapterRecyclerDanhSachCuaHang.notifyItemRemoved(scrollPosition);
        }
        binding.pullToRefresh.setRefreshing(false);
    }
    private void getDanhSachKhachHang() {

        if(lastID!=0){
        listCuaHang.add(null);
        adapterRecyclerDanhSachCuaHang.notifyItemInserted(listCuaHang.size() - 1);
        }else {
            binding.pullToRefresh.setRefreshing(false);
        }
        Map<String, String> params = new HashMap<>();
        params.put("token", Common.getToken());
        params.put("idnhanvien", "" + SharedPrefs.getInstance().get(Common.iDNhanVien, Integer.class));
        params.put("lastid", "" + lastID);

        danhSachCuaHangViewModel.getDanhSachCuaHang(params).observe(this, new Observer<ListCuaHangLoadMoreRespon>() {
            @Override
            public void onChanged(ListCuaHangLoadMoreRespon listCuaHangLoadMoreRespon) {
                try {
                    isLoading=false;
                    if (listCuaHangLoadMoreRespon != null) {
                        if (listCuaHangLoadMoreRespon.isStatus()) {
                            // start form see
                            if(parentFormId==1){
                                CuaHang cuaHang = new CuaHang();
                                cuaHang.setTenCuaHang("Tất cả");
                                cuaHang.setIdcuahang(0);
                                listCuaHang.add(cuaHang);
                            }
                            // start form create
                            if(parentFormId==2){
                                CuaHang cuaHang = new CuaHang();
                                cuaHang.setTenCuaHang("Khách hàng gợi ý");
                                cuaHang.setIdcuahang(0);
                                listCuaHang.add(cuaHang);
                            }
                            if(lastID!=0){
                                listCuaHang.remove(listCuaHang.size() - 1);
                                int scrollPosition = listCuaHang.size();
                                adapterRecyclerDanhSachCuaHang.notifyItemRemoved(scrollPosition);
                            }
                            lastID=listCuaHangLoadMoreRespon.getLastid();
                            endList=listCuaHangLoadMoreRespon.getEndlist();
                            if (listCuaHangLoadMoreRespon.getListCuaHang() != null && listCuaHangLoadMoreRespon.getListCuaHang().size() > 0) {

                                listCuaHang.addAll(listCuaHangLoadMoreRespon.getListCuaHang());
                                adapterRecyclerDanhSachCuaHang.notifyDataSetChanged();;
                            }else {
                                hideLoadMore();
                                Common.ShowToastLong(getString(R.string.message_no_data));
                            }
                        }else {
                            hideLoadMore();
                            Common.ShowToastLong(listCuaHangLoadMoreRespon.getMsg());
                        }
                    }else {
                        hideLoadMore();
                    }
                } catch (Exception e) {
                    isLoading=false;
                    hideLoadMore();
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadMore() {
        binding.rvDanhSachKhachHang.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading&&endList == 0) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == listCuaHang.size() - 1) {
                        //bottom of list!
                        getDanhSachKhachHang();
                        isLoading = true;
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.pullToRefresh.setVisibility(View.GONE);
    }

}
