package vn.lachongmedia.appnv.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.KsmartSalesApplication;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.SharedPrefs;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerDanhSachCuaHangCheckIn;

import vn.lachongmedia.appnv.databinding.FragmentCheckinBinding;

import vn.lachongmedia.appnv.network.CuaHang;
import vn.lachongmedia.appnv.network.checkin.CheckInResponse;
import vn.lachongmedia.appnv.object.flagobj;
import vn.lachongmedia.appnv.viewmodel.CuaHangDeVaoDiemViewModel;
import vn.lachongmedia.appnv.viewmodel.VaoDiemViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tungda .
 */
public class CheckInFragment extends Fragment implements OnMapReadyCallback {
    public CheckInFragment() {

    }

    CuaHangDeVaoDiemViewModel cuaHangDeVaoDiemViewModel;
    VaoDiemViewModel vaoDiemViewModel;
    FragmentCheckinBinding binding;
    private FusedLocationProviderClient mFusedLocationClient;
    private GoogleMap googleMap;
    private AdapterRecyclerDanhSachCuaHangCheckIn adapterRecyclerKehoachTrongNgay;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View rootView = inflater.inflate(R.layout.fragment_checkin, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_checkin, container, false);
        setFragmentMap();
        cuaHangDeVaoDiemViewModel = ViewModelProviders.of(this).get(CuaHangDeVaoDiemViewModel.class);
        vaoDiemViewModel=ViewModelProviders.of(this).get(VaoDiemViewModel.class);
        mFusedLocationClient= LocationServices.getFusedLocationProviderClient(KsmartSalesApplication.getInstance());
        binding.rvDanhSachKhachHang.setLayoutManager(new LinearLayoutManager(getActivity()));



        binding.btVaoDiemTuDo.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location!=null){
                            try {
                                CuaHang cuaHang=new CuaHang();
                                cuaHang.setIdcuahang(0);
                                cuaHang.setKinhDo(location.getLongitude());
                                cuaHang.setViDo(location.getLatitude());
                                cuaHang.setDiaChi(Common.getDiaChi(location.getLatitude(),location.getLongitude()));
                                cuaHang.setTenCuaHang("Khách hàng gợi ý");
                                Map<String, String> params=new HashMap<>();
                                params.put("token", Common.getToken());
                                params.put("idnhanvien", ""+ SharedPrefs.getInstance().get(Common.iDNhanVien,Integer.class));
                                params.put("rq","checkin");
                                params.put("thoigian",Common.getCurentTime());
                                params.put("kinhdo",""+location.getLongitude());
                                params.put("vido",""+location.getLatitude());
                                params.put("accuracy",""+location.getLatitude());
                                params.put("idcheckin",idCheckIn);
                                params.put("idcuahang","0");
                                params.put("kinhdocuahang",""+location.getLongitude());
                                params.put("vidocuahang",""+location.getLatitude());
                                params.put("idct",""+SharedPrefs.getInstance().get(Common.KEY_IDQLLH,Integer.class));
                                params.put("diachivaodiem",Common.getDiaChi(location.getLatitude(),location.getLongitude()));
                                vaoDiem(params,cuaHang);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        });

        return binding.getRoot();
    }

    private void setFragmentMap() {
       // FragmentManager fm = getActivity().getSupportFragmentManager();/// getChildFragmentManager();

       /* if (supportMapFragment == null) {
            supportMapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.maps, supportMapFragment).commit();
        }*/
        ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapmap)).getMapAsync(this);

        //getChildFragmentManager().beginTransaction().replace(R.id.maps, supportMapFragment).commit();
    }

    ProgressDialog progressDialog;
    private String idCheckIn="";
    public void getCuaHangGanNhatDeVaoDiem(Location location) {
        //binding.rvViecCanLam.removeAllViews();
        try {
            if(progressDialog==null){
                progressDialog = ProgressDialog.show(getActivity(), "", getString(R.string.dialog_waiting), true, false);
            }else {
                progressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cuaHangDeVaoDiemViewModel.getCuaHangGanNhatDeVaoDiem(location).observe(this, new Observer<ArrayList<CuaHang>>() {
            @Override
            public void onChanged(@Nullable final ArrayList<CuaHang> listCuaHang) {
                try {
                    progressDialog.cancel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (listCuaHang != null) {
                    adapterRecyclerKehoachTrongNgay = new AdapterRecyclerDanhSachCuaHangCheckIn(listCuaHang);
                    binding.rvDanhSachKhachHang.setAdapter(adapterRecyclerKehoachTrongNgay);
                    adapterRecyclerKehoachTrongNgay.setOnItemClickedListener(new AdapterRecyclerDanhSachCuaHangCheckIn.OnItemClickedListener() {
                        @SuppressLint("MissingPermission")
                        @Override
                        public void onItemClick(final int postion, View v) {
                            //startActivityForResult(new Intent(getActivity(), CheckInActivity.class), 1);
                           // startActivity(new Intent(getActivity(), CheckInActivity.class));
                           // vaoDiemViewModel.vaoDiem()
                            mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                                @Override
                                public void onSuccess(Location location) {
                                    if(location!=null){
                                        try {
                                            CuaHang cuaHang=listCuaHang.get(postion);
                                            Map<String, String> params=new HashMap<>();
                                            params.put("token", Common.getToken());
                                            params.put("idnhanvien", ""+ SharedPrefs.getInstance().get(Common.iDNhanVien,Integer.class));
                                            params.put("rq","checkin");
                                            params.put("thoigian",Common.getCurentTime());
                                            params.put("kinhdo",""+location.getLongitude());
                                            params.put("vido",""+location.getLatitude());
                                            params.put("accuracy",""+location.getLatitude());
                                            params.put("idcheckin",idCheckIn);
                                            params.put("idcuahang",""+cuaHang.getIdcuahang());
                                            params.put("kinhdocuahang",""+cuaHang.getKinhDo());
                                            params.put("vidocuahang",""+cuaHang.getViDo());
                                            params.put("idct",""+SharedPrefs.getInstance().get(Common.KEY_IDQLLH,Integer.class));
                                            params.put("diachivaodiem",Common.getDiaChi(location.getLatitude(),location.getLongitude()));
                                            vaoDiem(params,cuaHang);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            });
                        }
                    });
                }else {
                    adapterRecyclerKehoachTrongNgay = new AdapterRecyclerDanhSachCuaHangCheckIn(new ArrayList<CuaHang>());
                    binding.rvDanhSachKhachHang.setAdapter(adapterRecyclerKehoachTrongNgay);
                }
            }
        });

    }
    public void vaoDiem(Map<String, String> params, final CuaHang cuaHang) {
        //binding.rvViecCanLam.removeAllViews();
        try {
            if(progressDialog==null){
                progressDialog = ProgressDialog.show(getActivity(), "", getString(R.string.dialog_waiting), true, false);
            }else {
                progressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        vaoDiemViewModel.vaoDiem(params).observe(this, new Observer<CheckInResponse>() {
            @Override
            public void onChanged(@Nullable CheckInResponse checkInResponse) {
                try {
                    progressDialog.cancel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if(checkInResponse!=null){
                        if(checkInResponse.isStatus()){
                            flagobj.VDOBJ.setIdKeHoach(0);
                            flagobj.VDOBJ.setIdcheckin(checkInResponse.getVaoDiem().getIdCheckIn());
                            flagobj.VDOBJ.setTenkhachhang(cuaHang.getTenCuaHang());
                            flagobj.VDOBJ.setIdkhachhang(cuaHang.getIdcuahang());
                            try {
                                flagobj.VDOBJ.setMakhachhang(cuaHang.getMaCuaHang());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            flagobj.VDOBJ.setDiachi(cuaHang.getDiaChi());
                            flagobj.VDOBJ.setLat(cuaHang.getViDo());
                            flagobj.VDOBJ.setLng(cuaHang.getKinhDo());

                            flagobj.VDOBJ.setThoigianvaodiem(Common.getCurentTimeHMS_DMY());
                            flagobj.VDOBJ.setThoiGianVaoDiemKieuDate(Calendar.getInstance().getTime());
                            flagobj.VDOBJ.setStatus(true);

                            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.fl_contain, new CheckOutFragment())
                                    .commit();
                        }else {
                            Common.ShowToastLong(checkInResponse.getMsg());
                        }
                    }else {
                        Common.ShowToastLong(getString(R.string.message_no_data));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }



    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap map) {
        googleMap=map;
        mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null){
                    zoomTarget(location.getLatitude(), location.getLongitude());
                    getCuaHangGanNhatDeVaoDiem(location);
                }else {

                }
            }
        });
    }
    private void zoomTarget(double lat, double lng) {
        try {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(lat, lng)).zoom(18).build();
            googleMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void killOldMap() {
        try{
            SupportMapFragment mapFragment = ((SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.mapmap));
            if(mapFragment != null) {
                //mapFragment.isRemoving();

                FragmentManager fM = getFragmentManager();
                fM.beginTransaction().remove(mapFragment).commit();
            }
           /* getChildFragmentManager().beginTransaction().remove(this)
                    .commit();*/
           // getActivity().getSupportFragmentManager().beginTransaction().remove(this) .commit();
        }catch (Exception e){
            e.printStackTrace();

        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        //setTargetFragment(null, -1);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onStop() {
        super.onStop();
       // killOldMap();
    }
}
