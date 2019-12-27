package vn.lachongmedia.appnv.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.SharedPrefs;
import vn.lachongmedia.appnv.activity.ChonDanhMucActivity;
import vn.lachongmedia.appnv.activity.ChonKhachHangActivity;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerDanhSachPhanHoi;
import vn.lachongmedia.appnv.databinding.FragmentDanhsachphanhoiBinding;
import vn.lachongmedia.appnv.network.CuaHang;
import vn.lachongmedia.appnv.network.phanhoi.DanhSachPhanHoiRespon;
import vn.lachongmedia.appnv.object.DanhmucOBJ;
import vn.lachongmedia.appnv.object.PhanHoi.LichSuNhom;
import vn.lachongmedia.appnv.object.PhanHoi.PhanHoi;
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
public class DanhSachPhanHoiFragment extends Fragment {

    private boolean isLoading = false;
    FragmentDanhsachphanhoiBinding binding;
    AdapterRecyclerDanhSachPhanHoi adapter;
    ArrayList<LichSuNhom> listLichSuNhoms = new ArrayList<>();
    DanhSachPhanHoiViewModel danhSachPhanHoiViewModel;
    public DanhSachPhanHoiFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_danhsachphanhoi, container, false);
        danhSachPhanHoiViewModel = ViewModelProviders.of(this).get(DanhSachPhanHoiViewModel.class);
        binding.rvDanhSachKhachHang.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.tvDenNgay.setText(Common.getNgayHienTaiHai());
        binding.tvTuNgay.setText(Common.getNgayCachMotThangHai());
        setUpSearhTime();
        initChonKhachHang();
        adapter = new AdapterRecyclerDanhSachPhanHoi(listLichSuNhoms);
        cuaHang.setIdcuahang(0);
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

        binding.rvDanhSachKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        refreshLoad();
        return binding.getRoot();
    }
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
        getDanhSachPhanHoi();
    }

    private void getDanhSachPhanHoi() {
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
        danhSachPhanHoiViewModel.getDanhSachPhanHoi(params).observe(this, new Observer<DanhSachPhanHoiRespon>() {
            @Override
            public void onChanged(DanhSachPhanHoiRespon danhSachPhanHoiRespon) {
                binding.pullToRefresh.setRefreshing(false);
                try {
                    isLoading = false;
                    if (danhSachPhanHoiRespon != null) { ;
                        if (danhSachPhanHoiRespon.isStatus()) {
                            if (danhSachPhanHoiRespon.getPhanHoi() != null) {
                                listLichSuNhoms.clear();
                                listLichSuNhoms.addAll(danhSachPhanHoiRespon.getPhanHoi());
                                Collections.sort(listLichSuNhoms, new Comparator<LichSuNhom>() {
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

    private CuaHang cuaHang = new CuaHang();

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            cuaHang = (CuaHang) data.getSerializableExtra("cuahang");
            Log.d("BBB", "onActivityResult: "+cuaHang.getTenCuaHang());
            if (cuaHang == null) {
                binding.tvChonKhachHang.setText(getResources().getString(R.string.tatca));
            } else { ;
                binding.tvChonKhachHang.setText(cuaHang.getTenCuaHang());
            }

        }
    }

    @Override
    public void onResume() {
        refreshLoad();
        super.onResume();
    }
}
