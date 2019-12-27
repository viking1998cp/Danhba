package vn.lachongmedia.appnv.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

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
import android.widget.DatePicker;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.SharedPrefs;
import vn.lachongmedia.appnv.activity.ChonKhachHangActivity;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerDanhSachGhiChu;
import vn.lachongmedia.appnv.databinding.FragmentDanhsachghichuBinding;
import vn.lachongmedia.appnv.databinding.FragmentDanhsachphanhoiBinding;
import vn.lachongmedia.appnv.network.CuaHang;
import vn.lachongmedia.appnv.network.ghichu.DanhSachGhiChuRespon;
import vn.lachongmedia.appnv.object.GhiChu.DanhSachGhiChu;
import vn.lachongmedia.appnv.object.GhiChu.LichSuNhom;
import vn.lachongmedia.appnv.viewmodel.GhiChu.DanhSachGhiChuViewModel;
import vn.lachongmedia.appnv.viewmodel.PhanHoi.DanhSachPhanHoiViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tungda .
 */
public class DanhSachGhiChuFragment extends Fragment {

    FragmentDanhsachghichuBinding binding;
    ArrayList<LichSuNhom> listlLichSuNhoms = new ArrayList<>();
    AdapterRecyclerDanhSachGhiChu adapter;
    DanhSachGhiChuViewModel danhSachGhiChuViewModel;
    private boolean isLoading = false;
    public DanhSachGhiChuFragment() {

    }

    RecyclerView recyclerViewAlbum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_danhsachghichu, container, false);
        danhSachGhiChuViewModel = ViewModelProviders.of(this).get(DanhSachGhiChuViewModel.class);
        binding.rvDanhSachPhanHoi.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.tvDenNgay.setText(Common.getNgayHienTaiHai());
        binding.tvTuNgay.setText(Common.getNgayCachMotThangHai());
     /*   MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager()
                .findFragmentById(R.id.maps);
*/

        //recyclerViewCongNo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));
        binding.rvDanhSachPhanHoi.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AdapterRecyclerDanhSachGhiChu(listlLichSuNhoms);

        setUpSearhTime();
        initChonKhachHang();
        binding.rvDanhSachPhanHoi.setAdapter(adapter);
        adapter.setOnItemClickedListener(new AdapterRecyclerDanhSachGhiChu.OnItemClickedListener() {
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
        binding.pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLoad();
            }
        });
        return binding.getRoot();
    }
    //Select date time note
    Date dateStart, dateEnd;
    private void setUpSearhTime() {
        Log.d("BBB", "setUpSearhTime: "+binding.tvTuNgay.getText().toString());
        dateStart = Common.convertStringToDate3(binding.tvTuNgay.getText().toString()+" 00:00:00.000)");
        dateEnd = Common.convertStringToDate3(binding.tvDenNgay.getText().toString()+" 00:00:00.000)");
        binding.tvTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // month 0-11 ==> +1 display
                                // if display month or day <= 1 charaters ==> X ==> 0X
                                if(monthOfYear+1<10){
                                    if(dayOfMonth<10){
                                        binding.tvTuNgay.setText("0"+dayOfMonth+"-0"+(monthOfYear+1)+"-"+(year));
                                    }else {
                                        binding.tvTuNgay.setText(dayOfMonth+"-0"+(monthOfYear+1)+"-"+(year));
                                    }
                                }else {
                                    if(dayOfMonth<10){
                                        binding.tvTuNgay.setText("0"+dayOfMonth+"-"+(monthOfYear+1)+"-"+(year));
                                    }else {
                                        binding.tvTuNgay.setText(dayOfMonth+"-"+(monthOfYear+1)+"-"+(year));
                                    }
                                }
                                dateStart = Common.convertStringToDate3(binding.tvTuNgay.getText().toString()+" 00:00:00.000)");
                                refreshLoad();
                            }
                        }, 1900+dateStart.getYear(),dateStart.getMonth(),dateStart.getDate());
                datePickerDialog.show();
            }
        });
        binding.tvDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                if(monthOfYear+1<10){
                                    if(dayOfMonth<10){
                                        binding.tvDenNgay.setText("0"+dayOfMonth+"-0"+(monthOfYear+1)+"-"+(year));
                                    }else {
                                        binding.tvDenNgay.setText(dayOfMonth+"-0"+(monthOfYear+1)+"-"+(year));
                                    }
                                }else {
                                    if(dayOfMonth<10){
                                        binding.tvDenNgay.setText("0"+dayOfMonth+"-"+(monthOfYear+1)+"-"+(year));
                                    }else {
                                        binding.tvDenNgay.setText(dayOfMonth+"-"+(monthOfYear+1)+"-"+(year));
                                    }
                                }
                                dateEnd = Common.convertStringToDate3(binding.tvDenNgay.getText().toString()+" 00:00:00.000)");
                                refreshLoad();
                            }
                        }, 1900+dateEnd.getYear(),dateEnd.getMonth(),dateEnd.getDate());
                datePickerDialog.show();
            }
        });

    }

    private void refreshLoad() {
        isLoading = false;
        getDanhSachGhiChu();
    }
    //getData list note
    private void getDanhSachGhiChu() {
        binding.pullToRefresh.setRefreshing(true);
        Map<String, String> params = new HashMap<>();
        params.put("token", Common.getToken());
        params.put("idnhanvien", "" + SharedPrefs.getInstance().get(Common.iDNhanVien, Integer.class));
        params.put("idkhachhang", String.valueOf(cuaHang.getIdcuahang()));
        params.put("type", "lichsutheonhom" );
        params.put("tungay", binding.tvTuNgay.getText().toString().trim() );
        params.put("denngayngay", binding.tvDenNgay.getText().toString().trim() );
        Log.d("BBB", "getDanhSachPhanHoi: "+binding.tvTuNgay.getText());
        params.put("trangthaigps", String.valueOf(Common.checkGPS()));
        Log.d("BBB", "getDanhSachMatHang: "+params);
        danhSachGhiChuViewModel.getDanhSachPhanHoi(params).observe(this, new Observer<DanhSachGhiChuRespon>() {
            @Override
            public void onChanged(DanhSachGhiChuRespon danhSachGhiChuRespon) {
                binding.pullToRefresh.setRefreshing(false);
                try {
                    isLoading = false;
                    if (danhSachGhiChuRespon != null) { ;
                        if (danhSachGhiChuRespon.isStatus()) {
                            if (danhSachGhiChuRespon.getGhiChu() != null) {
                                listlLichSuNhoms.clear();
                                listlLichSuNhoms.addAll(danhSachGhiChuRespon.getGhiChu());
                                Collections.sort(listlLichSuNhoms, new Comparator<LichSuNhom>() {
                                    @Override
                                    public int compare(LichSuNhom o1, LichSuNhom o2) {
                                        return (Common.convertStringToDate2(o1.getThoiGian())
                                                .after(Common.convertStringToDate2(o2.getThoiGian())))?-1
                                                :Common.convertStringToDate2(o1.getThoiGian())
                                                .equals(Common.convertStringToDate2(o2.getThoiGian()))?0:1 ;
                                    }
                                });

                                adapter.notifyDataSetChanged();


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
                    isLoading = false;
                    e.printStackTrace();
                }
            }
        });
    }
    private void initChonKhachHang() {
        binding.tvChonKhachHang.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ChonKhachHangActivity.class);
            intent.putExtra("parentFormId",1);
            getActivity().startActivityForResult(intent, 1);
        });
    }
    //get id store
    CuaHang cuaHang = new vn.lachongmedia.appnv.network.CuaHang();
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            cuaHang = (CuaHang) data.getSerializableExtra("cuahang");
            Log.d("BBB", "onActivityResult: "+cuaHang.getTenCuaHang());
            if (cuaHang == null) {
                binding.tvChonKhachHang.setText(getResources().getString(R.string.tatca));
            } else {
                binding.tvChonKhachHang.setText(cuaHang.getTenCuaHang());
            }

        }
    }
    //if activity resum reload
    @Override
    public void onResume() {
        refreshLoad();
        super.onResume();
    }
}
