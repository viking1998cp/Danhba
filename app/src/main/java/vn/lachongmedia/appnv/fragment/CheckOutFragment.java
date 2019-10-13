package vn.lachongmedia.appnv.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.KsmartSalesApplication;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.databinding.FragmentCheckoutBinding;

import vn.lachongmedia.appnv.network.checkin.CheckOutResponse;
import vn.lachongmedia.appnv.object.VaoDiemOBJ;
import vn.lachongmedia.appnv.object.flagobj;
import vn.lachongmedia.appnv.viewmodel.RaDiemViewModel;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by tungda .
 */
public class CheckOutFragment extends Fragment implements OnMapReadyCallback {
    public CheckOutFragment() {

    }


    RaDiemViewModel raDiemViewModel;
    FragmentCheckoutBinding binding;
    private FusedLocationProviderClient mFusedLocationClient;
    private GoogleMap googleMap;
    private Thread ThoiGianTaiDiemThread;
    private boolean isRunningThoiGianTaiDiem = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View rootView = inflater.inflate(R.layout.fragment_checkin, container, false);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_checkout, container, false);
        // getActivity().getActionBar().setTitle(getString(R.string.radiem));
        setFragmentMap();
        raDiemViewModel = ViewModelProviders.of(this).get(RaDiemViewModel.class);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(KsmartSalesApplication.getInstance());
        binding.tvTenCuaHang.setText(flagobj.VDOBJ.getTenkhachhang());
        if (flagobj.VDOBJ.getDiachi() != null)
            binding.tvDiaDiem.setText(flagobj.VDOBJ.getDiachi());
        binding.tvThoiGianVaoDiem.setText(flagobj.VDOBJ.getThoigianvaodiem());
        binding.btRaDiem.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            try {

                                Map<String, String> params = new HashMap<>();
                                params.put("token", Common.getToken());
                             //   params.put("idnhanvien", "" + SharedPrefs.getInstance().get(Common.iDNhanVien, Integer.class));
                                params.put("rq", "checkout");
                                params.put("thoigian", Common.getCurentTime());
                                params.put("kinhdo", "" + location.getLongitude());
                                params.put("vido", "" + location.getLatitude());
                                params.put("accuracy", "" + location.getLatitude());
                                params.put("idcheckin", "" + flagobj.VDOBJ.getIdcheckin());
                                params.put("diachiradiem", Common.getDiaChi(location.getLatitude(), location.getLongitude()));
                              //  params.put("ghichu", flagobj.VDOBJ.getGhichu());

                                raDiem(params);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        });
        ChayThreadCapNhatThoiGianTaiDiem();
        return binding.getRoot();
    }

    /**
     * target: Cập nhật thời gian tại điểm của khách hàng
     */
    private void ChayThreadCapNhatThoiGianTaiDiem() {
        /////////////////////THREAD Thoi Gian Tai Diem///////////////////////////////
        isRunningThoiGianTaiDiem = true;
        ThoiGianTaiDiemThread = new Thread(
                new Runnable() {
                    public void run() {
                        while (isRunningThoiGianTaiDiem) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        long duration = Calendar.getInstance().getTime().getTime()
                                                - flagobj.VDOBJ.getThoiGianVaoDiemKieuDate().getTime();
                                        String khoangTG = elapsed(duration);
                                        binding.tvThoiGianTaiDiem.setText("" + khoangTG);
                                    } catch (Exception e) {
//                                e.printStackTrace();
                                    }
                                }
                            });

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                //e.printStackTrace();
                            }
                        }
                    }
                });

        ThoiGianTaiDiemThread.start();
    }

    private static String elapsed(long duration) {

        long diffInSec = TimeUnit.MILLISECONDS.toSeconds(duration);
        long seconds = diffInSec % 60;
        diffInSec /= 60;
        String ss = seconds < 10 ? ("0" + seconds) : ("" + seconds);
        long minutes = diffInSec % 60;
        diffInSec /= 60;
        String mm = minutes < 10 ? ("0" + minutes) : ("" + minutes);
        long hours = diffInSec;// % 24;
        String HH = hours < 10 ? ("0" + hours) : ("" + hours);
        //diffInSec /= 24;
        //long days = diffInSec;

        return HH + ":" + mm + ":" + ss;
    }

    /**
     * target: Hủy Thread cập nhật thời gian tại điểm của khách hàng
     */
    private void DungThreadCapNhatThoiGianTaiDiem() {
        isRunningThoiGianTaiDiem = false;
        try {
            if (ThoiGianTaiDiemThread != null) {
                ThoiGianTaiDiemThread.interrupt();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    private String idCheckIn = "";


    public void raDiem(Map<String, String> params) {
        //binding.rvViecCanLam.removeAllViews();
        try {
            if (progressDialog == null) {
                progressDialog = ProgressDialog.show(getActivity(), "", getString(R.string.dialog_waiting), true, false);
            } else {
                progressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        raDiemViewModel.raDiem(params).observe(this, new Observer<CheckOutResponse>() {
            @Override
            public void onChanged(@Nullable CheckOutResponse checkOutResponse) {
                try {
                    progressDialog.cancel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (checkOutResponse != null) {
                        if (checkOutResponse.isStatus()) {
                            flagobj.VDOBJ = new VaoDiemOBJ();
                            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.fl_contain, new CheckInFragment())
                                    .commit();
                        } else {
                            Common.ShowToastLong(checkOutResponse.getMsg());
                        }
                    } else {
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
        googleMap = map;
        mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    zoomTarget(location.getLatitude(), location.getLongitude());

                } else {

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
        try {
            SupportMapFragment mapFragment = ((SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.mapmap));
            if (mapFragment != null) {
                //mapFragment.isRemoving();

                FragmentManager fM = getFragmentManager();
                fM.beginTransaction().remove(mapFragment).commit();
            }
           /* getChildFragmentManager().beginTransaction().remove(this)
                    .commit();*/
            // getActivity().getSupportFragmentManager().beginTransaction().remove(this) .commit();
        } catch (Exception e) {
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
       // DungThreadCapNhatThoiGianTaiDiem();
         killOldMap();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DungThreadCapNhatThoiGianTaiDiem();
    }
}
