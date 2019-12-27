package vn.lachongmedia.appnv.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.SharedPrefs;
import vn.lachongmedia.appnv.activity.ChiTietKhachHangActivity;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerDanhSachKhachHang;
import vn.lachongmedia.appnv.databinding.FragmentDanhsachkhachhangBinding;
import vn.lachongmedia.appnv.network.khachhang.DanhSachKhachHangLoadMoreRespon;
import vn.lachongmedia.appnv.network.khachhang.DanhSachKhachHangRespon;
import vn.lachongmedia.appnv.object.CuaHang;
import vn.lachongmedia.appnv.object.Khachhang.Data;
import vn.lachongmedia.appnv.object.Khachhang.DataLoaiKhachHang;
import vn.lachongmedia.appnv.viewmodel.KhachHang.DanhSachKhachHangViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tungda .
 */
public class DanhSachKhachHangFragment extends Fragment {
    private boolean isLoading = false;
    FragmentDanhsachkhachhangBinding binding;
    AdapterRecyclerDanhSachKhachHang adapter;
    ArrayList<Data> listDatas = new ArrayList<>();
    DanhSachKhachHangViewModel danhSachKhachHangViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_danhsachkhachhang, container, false);
        danhSachKhachHangViewModel = ViewModelProviders.of(this).get(DanhSachKhachHangViewModel.class);
        binding.rvDanhSachKhachHang.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new AdapterRecyclerDanhSachKhachHang(listDatas);
     /*   MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager()
                .findFragmentById(R.id.maps);
*/

        binding.rvDanhSachKhachHang.setAdapter(adapter);
        binding.pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLoad();
            }
        });
        adapter.setOnItemClickedListener(new AdapterRecyclerDanhSachKhachHang.OnItemClickedListener() {
            @Override
            public void onItemClick(int postion, View v) {
              /*  if (cuaHangs.get(postion).getType() == 0) {
                    cuaHangs.get(postion).setType(1);
                }else {
                    cuaHangs.get(postion).setType(0);
                }
                adapterRecyclerDanhSachPhanHoi.notifyDataSetChanged();*/
            }
        });
        refreshLoad();
        return binding.getRoot();
    }
    private int lastID = 5;
    private int endList = 1;
    private void refreshLoad() {
        lastID = 0;
        endList = 1;
        isLoading = false;
        listDatas.clear();
        loadMore();
        getDanhSachKhachHang();
    }

    private void getDanhSachKhachHang() {
        binding.pullToRefresh.setRefreshing(true);
        Map<String, String> params = new HashMap<>();
        params.put("token", Common.getToken());
        params.put("idnhanvien", "" + SharedPrefs.getInstance().get(Common.iDNhanVien, Integer.class));
        params.put("lastid", "" + lastID);
        params.put("loctatca", "" + binding.editSearch.getText().toString());
        params.put("idnhomkh", "");

        danhSachKhachHangViewModel.getDanhSachKhachHang(params).observe(this, new Observer<DanhSachKhachHangLoadMoreRespon>() {
            @Override
            public void onChanged(DanhSachKhachHangLoadMoreRespon danhSachKhachHangLoadMoreRespon) {
                binding.pullToRefresh.setRefreshing(false);
                try {
                    isLoading = false;
                    if (danhSachKhachHangLoadMoreRespon != null) {
                        if (danhSachKhachHangLoadMoreRespon.isStatus()) {

                            lastID = danhSachKhachHangLoadMoreRespon.getLastid();
                            endList = danhSachKhachHangLoadMoreRespon.getEndlist();
                            if (danhSachKhachHangLoadMoreRespon.getListDatas() != null && danhSachKhachHangLoadMoreRespon.getListDatas().size() > 0) {

                                listDatas.addAll(danhSachKhachHangLoadMoreRespon.getListDatas());
                                adapter.notifyDataSetChanged();

                            } else {
                                Common.ShowToastLong(getString(R.string.message_no_data));
                            }
                        } else {
                            //Thêm do khi set tim kiem không thay đổi kịp
                            if (lastID == 0) {
                                listDatas.clear();
                                adapter.notifyDataSetChanged();
                                ;
                            }
                            //Common.ShowToastLong(danhSachMatHangLoadMoreRespon.getMsg());
                        }
                    } else {

                    }
                } catch (Exception e) {
                    Log.d("CCC", "onChanged: "+e.getMessage());
                    isLoading = false;
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

                if (!isLoading && endList == 0) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == listDatas.size() - 1) {
                        //bottom of list!
                        getDanhSachKhachHang();
                        isLoading = true;
                    }
                }
            }
        });
    }


}
