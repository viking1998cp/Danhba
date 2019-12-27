package vn.lachongmedia.appnv.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;

import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import vn.lachongmedia.appnv.KsmartSalesApplication;
import vn.lachongmedia.appnv.R;

import vn.lachongmedia.appnv.activity.DanhSachViecCanLamActivity;
import vn.lachongmedia.appnv.databinding.DialogKehoachtrongngayBinding;
import vn.lachongmedia.appnv.databinding.FragmentHomeBinding;
import vn.lachongmedia.appnv.network.CuaHang;
import vn.lachongmedia.appnv.object.KeHoachOBJ;
import vn.lachongmedia.appnv.viewmodel.CuaHangGanNhatViewModel;
import vn.lachongmedia.appnv.viewmodel.KeHoachViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

/**
 * Created by tungda .
 */
public class TrangchuFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {
    public TrangchuFragment() {

    }

    private GoogleMap googleMap;
    private FragmentHomeBinding binding;
    private CuaHangGanNhatViewModel cuaHangGanNhatViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        setFragmentMap();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        keHoachViewModel = ViewModelProviders.of(this).get(KeHoachViewModel.class);
        cuaHangGanNhatViewModel = ViewModelProviders.of(this).get(CuaHangGanNhatViewModel.class);
        getKeHoachTrongNgay();
        binding.fbviecCanLam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KsmartSalesApplication.getInstance(), DanhSachViecCanLamActivity.class));
            }
        });
        binding.pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getKeHoachTrongNgay();
            }
        });
        return binding.getRoot();
    }


   // ProgressDialog progressDialog;
    private FusedLocationProviderClient mFusedLocationClient;
    private SupportMapFragment supportMapFragment;

    private void setFragmentMap() {
        FragmentManager fm = getActivity().getSupportFragmentManager();/// getChildFragmentManager();
        supportMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.maps);
        if (supportMapFragment == null) {
            supportMapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.maps, supportMapFragment).commit();
        }
        supportMapFragment.getMapAsync(this);
        getChildFragmentManager().beginTransaction().replace(R.id.maps, supportMapFragment).commit();
    }

    /**
     * @param listKeHoach target: display listKeHoach on dialog
     */
    private void showDialogKeHoachTrongNgay(ArrayList<KeHoachOBJ> listKeHoach) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog.setContentView(R.layout.dialog_kehoachtrongngay);
        DialogKehoachtrongngayBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_kehoachtrongngay, null, false);
        dialog.setContentView(mBinding.getRoot());
        kehoachAdapter adapter = new kehoachAdapter(listKeHoach);
        mBinding.lvKehoachtrongngay.setAdapter(adapter);
        mBinding.btThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * target: create a adapter is kehoachAdapter for display on listview
     */
    private class kehoachAdapter extends BaseAdapter {
        private final ArrayList<KeHoachOBJ> LKHOBJ;

        public kehoachAdapter(ArrayList<KeHoachOBJ> lkehoach) {
            // TODO Auto-generated constructor stub
            this.LKHOBJ = lkehoach;

        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return LKHOBJ.size();
        }

        @Override
        public KeHoachOBJ getItem(int position) {
            // TODO Auto-generated method stub
            return LKHOBJ.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.list_item_kehoach, parent, false);
                holder.tvDiaChi = (TextView) convertView.findViewById(R.id.tv_DiaChi);
                holder.tvThoigian = (TextView) convertView.findViewById(R.id.tv_ThoiGian);
                holder.tvThoiGianThucTe = (TextView) convertView.findViewById(R.id.tv_ThoiGianthucte);
                holder.tv_motakehoach = (TextView) convertView.findViewById(R.id.tv_motakehoach);
                holder.ll_kehoach = (LinearLayout) convertView.findViewById(R.id.ll_kehoach);
                holder.tv_vieccanlam = (TextView) convertView.findViewById(R.id.tv_vieccanlam);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final KeHoachOBJ khitem = getItem(position);
            try {
                holder.ll_kehoach.setBackgroundColor(Color.parseColor(khitem.getMaMau()));
                holder.tv_motakehoach.setText(getResources().getString(R.string.title_note_kehoach) + " " + khitem.getMoTakehoach());
                holder.tv_vieccanlam.setText(getResources().getString(R.string.vieccanlamhaicham) + " " + khitem.getVieccanlam());
            } catch (Exception e) {
                e.printStackTrace();
            }
            holder.tvDiaChi.setText(khitem.getTencuahang());
            try {
                if (khitem.getThoigianradiemdukien() != null) {
                    holder.tvThoigian.setText(getResources().getString(R.string.title_expected_kehoach) + " " + khitem.getThoigianvaodiemdukien().substring(11, 16) + " - " + khitem.getThoigianradiemdukien().substring(11, 16));

                } else {
                    holder.tvThoigian.setText(getResources().getString(R.string.title_expected_kehoach) + " " + khitem.getThoigianvaodiemdukien().substring(11, 16));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                String thoiGianVaoDiemThucTe = khitem.getThoigianvaodiem();
                if (thoiGianVaoDiemThucTe.equals("1900-01-01T00:00:00")) {
                    thoiGianVaoDiemThucTe = "";
                }
                if (!thoiGianVaoDiemThucTe.equals("")) {
                    thoiGianVaoDiemThucTe = thoiGianVaoDiemThucTe.substring(11, 16);
                }

                String thoiGianRaDiemThucTe = "";
                try {
                    thoiGianRaDiemThucTe = khitem.getThoigianradiem();
                    if (thoiGianRaDiemThucTe.equals("1900-01-01T00:00:00")) {
                        thoiGianRaDiemThucTe = "";
                    }
                    if (!thoiGianRaDiemThucTe.equals("")) {
                        thoiGianRaDiemThucTe = thoiGianRaDiemThucTe.substring(11, 16);
                    }
                } catch (Exception e) {
                    thoiGianRaDiemThucTe = "";
                    e.printStackTrace();
                }
                if (thoiGianRaDiemThucTe.equals("")) {
                    holder.tvThoiGianThucTe.setText(getResources().getString(R.string.title_real_kehoach) + " " + thoiGianVaoDiemThucTe);
                } else {
                    holder.tvThoiGianThucTe.setText(getResources().getString(R.string.title_real_kehoach) + " " + thoiGianVaoDiemThucTe + " - " + thoiGianRaDiemThucTe);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return convertView;
        }

        public class ViewHolder {
            TextView tvDiaChi, tvThoigian, tvThoiGianThucTe, tv_motakehoach, tv_vieccanlam;
            LinearLayout ll_kehoach;
        }
    }

    private KeHoachViewModel keHoachViewModel;

    public void getKeHoachTrongNgay() {

        binding.pullToRefresh.setRefreshing(true);
        keHoachViewModel.getKeHoachTrongNgay().observe(this, new Observer<ArrayList<KeHoachOBJ>>() {
            @Override
            public void onChanged(@Nullable ArrayList<KeHoachOBJ> keHoachTrongNgay) {
                binding.pullToRefresh.setRefreshing(false);
                if (keHoachTrongNgay != null) {
                    if (keHoachTrongNgay.size() > 0) {
                        showDialogKeHoachTrongNgay(keHoachTrongNgay);
                    }
                }
            }
        });


    }

    private void getCuaHangGanNhat(Location location) {
        try {

            binding.pullToRefresh.setRefreshing(true);
            cuaHangGanNhatViewModel.getCuaHangGanNhat(location).observe(getActivity(), new Observer<ArrayList<CuaHang>>() {
                @Override
                public void onChanged(ArrayList<CuaHang> cuaHangs) {
                    binding.pullToRefresh.setRefreshing(false);
                    try {
                        if(cuaHangs!=null){
                            for(CuaHang cuaHang:cuaHangs){
                                addCuaHang(cuaHang);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            binding.pullToRefresh.setRefreshing(false);
        }

    }

    private final ArrayList<PoinCuaHang> ALCuaHang = new ArrayList<>();

    private void addCuaHang(CuaHang obj) {
        Marker myMarkeri;
        googleMap.setOnMarkerClickListener(this);
        myMarkeri = googleMap.addMarker(new MarkerOptions().position(
                new LatLng(obj.getViDo(), obj.getKinhDo()))
                .title(obj.getTenCuaHang())
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.cuahang1)));


        PoinCuaHang poin = new PoinCuaHang();
        poin.marker = myMarkeri;
        poin.cuahang = obj;
        ALCuaHang.add(poin);
    }

    public class PoinCuaHang {
        public Marker marker;
        public CuaHang cuahang;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onDestroy() {
        try {
            if (googleMap != null) {
                googleMap.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            binding.pullToRefresh.setRefreshing(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //killOldMap();
        super.onDestroy();

    }

    @Override
    public void onStop() {
        super.onStop();
        try {
            binding.pullToRefresh.setRefreshing(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        //killOldMap();
        super.onDestroyView();
        killOldMap();
        /*Fragment f =  getFragmentManager()
                .findFragmentById(R.id.maps);
        if (f != null)
            getFragmentManager().beginTransaction().remove(f).commit();*/
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.getUiSettings().setRotateGesturesEnabled(false);
        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnMapClickListener(this);
        if (googleMap == null) {
            Toast.makeText(KsmartSalesApplication.getInstance(),
                    "Không tạo được bản đồ", Toast.LENGTH_SHORT)
                    .show();
        }
      /*  mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
             getCuaHangGanNhat(location.getLongitude(),location.getLatitude());
            }
        });*/
        mFusedLocationClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
            /*     CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(20.994495, 105.831015)).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));*/
                if (location != null) {
                    zoomTarget(location.getLatitude(), location.getLongitude());
                    addMarker(location.getLatitude(), location.getLongitude());
                    getCuaHangGanNhat(location);
                }
            }
        });
    }

    private void addMarker(double lat, double lng) {
        try {
            googleMap.clear();
           /* googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng))
                    .icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.ic_location_on_blue_24dp)));*/
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    private void killOldMap() {
        try {
            SupportMapFragment mapFragment = ((SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.maps));
            if (mapFragment != null) {
                FragmentManager fM = getFragmentManager();
                fM.beginTransaction().remove(mapFragment).commit();
            }
           /* getActivity().getSupportFragmentManager().beginTransaction().remove(this)
                    .commit();*/
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}
