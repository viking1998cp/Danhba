package vn.lachongmedia.appnv.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.databinding.DataBindingUtil;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.MaChucNang;
import vn.lachongmedia.appnv.MainActivity;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.SharedPrefs;
import vn.lachongmedia.appnv.databinding.ActivityLogin2Binding;
import vn.lachongmedia.appnv.network.CommonRespon;
import vn.lachongmedia.appnv.network.Login.CongTyRespon;
import vn.lachongmedia.appnv.network.Login.DieuHuongRespon;
import vn.lachongmedia.appnv.network.Login.ListAppFakeGPSResponse;
import vn.lachongmedia.appnv.network.Login.LoaiAlbum;
import vn.lachongmedia.appnv.network.Login.PhanQuyen;
import vn.lachongmedia.appnv.network.Login.VersionAppRespon;
import vn.lachongmedia.appnv.network.Login.LoginRespon;
import vn.lachongmedia.appnv.network.NetContext;
import vn.lachongmedia.appnv.network.Service;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by tungda .
 */
public class LoginActivity extends AppCompatActivity {
    private String doiMay, osVersion, deviceName, imeiMay = "";
    ActivityLogin2Binding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        NetContext.getInstance().setBASE_URL(Common.UrlServer);
        NetContext.getInstance().init();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login2);
        setThongTinLuu();
        binding.btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                luuThongTinDangNhap();
                if (!Common.isNetworkAvailable()) {
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.toast_message_not_network),
                            Toast.LENGTH_SHORT).show();

                } else if (binding.etID.getText().toString().trim().equals("")
                        || binding.etPass.getText().toString().trim().equals("")
                        || binding.etQL.getText().toString().trim().equals("")) {
                    Toast.makeText(getBaseContext(),
                            getResources().getString(R.string.toast_message_input_info),
                            Toast.LENGTH_SHORT).show();
                    if (binding.etID.getText().toString().trim().equals("")) {
                        Common.focusTextInputEditText(binding.etID);
                    } else if (binding.etPass.getText().toString().trim().equals("")) {
                        Common.focusTextInputEditText(binding.etPass);
                    } else if (binding.etQL.getText().toString().trim().equals("")) {
                        Common.focusTextInputEditText(binding.etQL);
                    }
                } else {

                    dieuHuong();

                    //new ImageDownloadAndSave().execute("http://jav.ksmart.vn/DATA_UPLOADS/Icon/1_LACHONG.png");

                }

            }
        });


        getDeviceSuperInfo();
    }

    private void luuThongTinDangNhap() {
        if (binding.cbCheck.isChecked()) {
            SharedPrefs.getInstance().put(Common.MaCongTy, binding.etQL.getText().toString());
            SharedPrefs.getInstance().put(Common.UserName, binding.etID.getText().toString());
            SharedPrefs.getInstance().put(Common.PassWord, binding.etPass.getText().toString());
            SharedPrefs.getInstance().put(Common.TrangThaiNutCheckLogin, true);
        } else {
            SharedPrefs.getInstance().remove(Common.MaCongTy);
            SharedPrefs.getInstance().remove(Common.UserName);
            SharedPrefs.getInstance().remove(Common.PassWord);
            SharedPrefs.getInstance().remove(Common.TrangThaiNutCheckLogin);
        }
    }

    private void setThongTinLuu() {
        binding.cbCheck.setChecked(SharedPrefs.getInstance().get(Common.TrangThaiNutCheckLogin, Boolean.class));
        if (binding.cbCheck.isChecked()) {
            binding.etQL.setText(SharedPrefs.getInstance().get(Common.MaCongTy, String.class));
            binding.etID.setText(SharedPrefs.getInstance().get(Common.UserName, String.class));
            binding.etPass.setText(SharedPrefs.getInstance().get(Common.PassWord, String.class));
        }
    }

    // lay thong tin phan cung cua thiet bi
    @SuppressLint("HardwareIds")
    private void getDeviceSuperInfo() {
        // Log.i(TAG, "getDeviceSuperInfo");
        try {
            TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            doiMay += "" + Build.MODEL;
            osVersion += "" + android.os.Build.VERSION.RELEASE;
            deviceName += "" + android.os.Build.BRAND;

            try {
                @SuppressLint("MissingPermission") String imei = tm.getDeviceId();
                imeiMay += "" + imei;
            } catch (Exception e) {
                e.printStackTrace();
            }

            //  Log.i(TAG + " | Device Info > ", infoDivice);

        } catch (Exception e) {
            //Log.e(TAG, "Error getting Device INFO");
        }

    }

    ProgressDialog progressDialog;

    private void login() {
        try {
            try {
                progressDialog = ProgressDialog.show(LoginActivity.this, "", getString(R.string.dialog_waiting), true, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Service service = NetContext.instance.create(Service.class);
            Map<String, String> params = new HashMap<>();
            params.put("token", Common.getToken());
            params.put("idct", binding.etQL.getText().toString());
            params.put("taikhoan", binding.etID.getText().toString());
            params.put("matkhau", Common.getMd5Hash(binding.etPass.getText().toString()));
            params.put("os", "2");
            params.put("ver", Common.VersionCode_Login);
            params.put("device", imeiMay);
            params.put("osversion", osVersion);
            params.put("dongmay", doiMay);
            params.put("devicename", deviceName);
            params.put("imei", imeiMay);
            params.put("thoigian", Common.getCurentTime());

            params.put("trangthaigps", "" + Common.checkGPS());
            params.put("isCheDoTietKiemPin", "" + Common.checkPowerSaveMode());
            params.put("idpush", "aaaaaaaa");
            params.put("isFakeGPS", "" + isFakeGPS);
            params.put("appfakegps", paketNameFakes);
            params.put("kinhdo", "0.111");
            params.put("vido", "0.111");
            params.put("accuracy", "0.111");
            service.login(params).enqueue(new Callback<LoginRespon>() {
                @Override
                public void onResponse(Call<LoginRespon> call, Response<LoginRespon> response) {
                    try {
                        progressDialog.cancel();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        LoginRespon loginRespon = response.body();
                        if (loginRespon.isStatus()) {
                            SharedPrefs.getInstance().put(Common.iDNhanVien, loginRespon.getData().getIdnhanvien());
                            String maChucNang = "";
                            for (PhanQuyen phanQuyen : loginRespon.getDanhsachphanquyen()) {
                                switch (phanQuyen.getMaChucNang()) {
                                    case "APP_001":
                                        MaChucNang.getInstance().setTrangChu(true);
                                        break;
                                    case "APP_002":
                                        MaChucNang.getInstance().setTinNhan(true);
                                        break;
                                    case "APP_003":
                                        MaChucNang.getInstance().setVaoDiem(true);
                                        break;
                                    case "APP_004":
                                        MaChucNang.getInstance().setRaDiem(true);
                                        break;
                                    case "APP_005":
                                        MaChucNang.getInstance().setTaoDonHang(true);
                                        break;
                                    case "APP_006":
                                        MaChucNang.getInstance().setXuLyDonHang(true);
                                        break;
                                    case "APP_007":
                                        MaChucNang.getInstance().setKhuyenMai(true);
                                        break;
                                    case "APP_008":
                                        MaChucNang.getInstance().setKhachHang(true);
                                        break;
                                    case "APP_009":
                                        MaChucNang.getInstance().setXemKeHoach(true);
                                        break;
                                    case "APP_010":
                                        MaChucNang.getInstance().setLapKeHoach(true);
                                        break;
                                    case "APP_011":
                                        MaChucNang.getInstance().setChupVaGuiAnh(true);
                                        break;
                                    case "APP_012":
                                        MaChucNang.getInstance().setBaoCaoAnhChup(true);
                                        break;
                                    case "APP_013":
                                        MaChucNang.getInstance().setPhienLamViec(true);
                                        break;
                                    case "APP_014":
                                        MaChucNang.getInstance().setLichSuDiChuyen(true);
                                        break;
                                    case "APP_015":
                                        MaChucNang.getInstance().setLichSuVaoRaDiem(true);
                                        break;
                                    case "APP_016":
                                        MaChucNang.getInstance().setBaoCaoDonHang(true);
                                        break;
                                    case "APP_017":
                                        MaChucNang.getInstance().setBaoCaoDoanhThu(true);
                                        break;
                                    case "APP_018":
                                        MaChucNang.getInstance().setBaoCaoMatHang(true);
                                        break;
                                    case "APP_019":
                                        MaChucNang.getInstance().setLienHe(true);
                                        break;
                                    case "APP_020":
                                        MaChucNang.getInstance().setDoiMatKhau(true);
                                        break;
                                    case "APP_021":
                                        MaChucNang.getInstance().setCaiDat(true);
                                        break;
                                    case "APP_022":
                                        MaChucNang.getInstance().setDangXuat(true);
                                        break;
                                    case "APP_023":
                                        MaChucNang.getInstance().setThuHoiCongNo(true);
                                        break;
                                    case "APP_024":
                                        MaChucNang.getInstance().setPhanHoi(true);
                                        break;
                                    case "APP_025":
                                        MaChucNang.getInstance().setChecklist(true);
                                        break;
                                    case "APP_026":
                                        MaChucNang.getInstance().setLichHen(true);
                                        break;
                                    case "APP_027":
                                        MaChucNang.getInstance().setChupAnhbienBang(true);
                                        break;
                                    case "APP_028":
                                        MaChucNang.getInstance().setChupAnhSanPham(true);
                                        break;
                                    case "APP_029":
                                        MaChucNang.getInstance().setDanhSachMatHang(true);
                                        break;
                                    case "APP_030":
                                        MaChucNang.getInstance().setLichSuGiaoHang(true);
                                        break;
                                    case "APP_031":
                                        MaChucNang.getInstance().setLichSuThanhToan(true);
                                        break;
                                    case "APP_032":
                                        MaChucNang.getInstance().setKeHoachBaoDuong(true);
                                        break;
                                    case "APP_033":
                                        MaChucNang.getInstance().setLichSuBaoDuong(true);
                                        break;
                                    case "APP_034":
                                        MaChucNang.getInstance().setGiaoViec(true);
                                        break;
                                    case "APP_035":
                                        MaChucNang.getInstance().setBaoCaoKPI(true);
                                        break;
                                    case "APP_036":
                                        MaChucNang.getInstance().setDonHangCoBan(true);
                                        break;
                                    case "APP_037":
                                        MaChucNang.getInstance().setGuiBanMatHang(true);
                                        break;
                                    case "APP_038":
                                        MaChucNang.getInstance().setDonHangNhieuKhuyenMai(true);
                                        break;

                                }
                            }
                            SharedPrefs.getInstance().put(Common.KEY_IDQLLH, loginRespon.getData().getIdct());
                            setLoaiAbumSharedPre(loginRespon.getDataLoaiAlbum());
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //Log.e("danhsachcongviec", congViecGiamDinhVienRespon.toString());
                }

                @Override
                public void onFailure(Call<LoginRespon> call, Throwable t) {
                    try {

                        progressDialog.cancel();
                        Toast.makeText(getBaseContext(), getResources().getString(R.string.toast_message_not_network_server),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Log.d("BBB", "onFailure: "+e.toString());
                        e.printStackTrace();
                    }
                    Log.d("BBB", "onFailure: "+t.getMessage());

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setLoaiAbumSharedPre(ArrayList<LoaiAlbum> listLoaiAlbum) {
        try {
            Gson gson = new Gson();
            String searchContactResponseStr = gson.toJson(listLoaiAlbum);
            SharedPrefs.getInstance().put(Common.KEY_LOAIALBUM, searchContactResponseStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String paketNameFakes;
    private int isFakeGPS = 0;

    private void getListAppFake() {
        try {
            try {
                progressDialog = ProgressDialog.show(LoginActivity.this, "", getString(R.string.dialog_waiting), true, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Service service = NetContext.instance.create(Service.class);
            Map<String, String> params = new HashMap<>();
            params.put("token", Common.getToken());
            service.listAppFake(params).enqueue(new Callback<ListAppFakeGPSResponse>() {
                @Override
                public void onResponse(Call<ListAppFakeGPSResponse> call, Response<ListAppFakeGPSResponse> response) {
                    try {
                        progressDialog.cancel();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        ListAppFakeGPSResponse listAppFakeGPSResponse = response.body();
                        try {
                            if (listAppFakeGPSResponse.isStatus()) {
                                SharedPrefs.getInstance().put(Common.ListAppFake, listAppFakeGPSResponse.getListAppFakeGPS());
                                try {
                                    paketNameFakes = Common.checkFakeGPS();
                                    if (paketNameFakes.equals("")) {
                                        isFakeGPS = 0;
                                    } else {
                                        isFakeGPS = 1;
                                    }
                                } catch (Exception e) {
                                    paketNameFakes = "";
                                    isFakeGPS = 0;

                                }
                            } else {

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    checkVersion();
                    thongTinCongTy();
                    //Log.e("danhsachcongviec", congViecGiamDinhVienRespon.toString());
                }

                @Override
                public void onFailure(Call<ListAppFakeGPSResponse> call, Throwable t) {
                    try {
                        progressDialog.cancel();
                        Toast.makeText(getBaseContext(), getResources().getString(R.string.toast_message_not_network_server),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void thongTinCongTy() {
        try {
            try {
                progressDialog = ProgressDialog.show(LoginActivity.this, "", getString(R.string.dialog_waiting), true, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Service service = NetContext.instance.create(Service.class);
            Map<String, String> params = new HashMap<>();
            params.put("token", Common.getToken());
            params.put("idct", binding.etQL.getText().toString());
            service.thongTinCongTy(params).enqueue(new Callback<CongTyRespon>() {
                @Override
                public void onResponse(Call<CongTyRespon> call, Response<CongTyRespon> response) {
                    try {
                        progressDialog.cancel();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        CongTyRespon congTyRespon = response.body();
                        if (congTyRespon.isStatus()) {
                            SharedPrefs.getInstance().put(Common.ThongTinCongTy, congTyRespon.getCongTy());
                        } else {

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //Log.e("danhsachcongviec", congViecGiamDinhVienRespon.toString());
                }

                @Override
                public void onFailure(Call<CongTyRespon> call, Throwable t) {
                    try {
                        progressDialog.cancel();
                        Toast.makeText(getBaseContext(), getResources().getString(R.string.toast_message_not_network_server),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void dieuHuong() {
        try {
            try {
                progressDialog = ProgressDialog.show(LoginActivity.this, "", getString(R.string.dialog_waiting), true, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Service service = NetContext.instance.create(Service.class);
            Map<String, String> params = new HashMap<>();
            params.put("token", Common.getToken());
            params.put("idct", binding.etQL.getText().toString());
            service.dieuHuong(params).enqueue(new Callback<DieuHuongRespon>() {
                @Override
                public void onResponse(Call<DieuHuongRespon> call, Response<DieuHuongRespon> response) {
                    try {
                        progressDialog.cancel();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        DieuHuongRespon commonRespon = response.body();
                        if (commonRespon.isStatus()) {
                            NetContext.getInstance().setBASE_URL(commonRespon.getData());
                            NetContext.getInstance().init();
                            //checkVersion();
                            //getListAppFake();


                            try {
                                long thoiGianTruoc = SharedPrefs.getInstance().get(Common.ThoiGianGoiApiFakeGps, Long.class);
                                long thoiGianHienTai = System.currentTimeMillis();
                                //Gọi lại api hiển thị danh sách packet name có chứa permisson change location mà không phải là app fake
                                // khi 2 lần đăng nhập cách nhau lớn hơn 1 ngày
                                if (thoiGianHienTai > thoiGianTruoc + 24 * 60 * 60 * 1000) {
                                    SharedPrefs.getInstance().put(Common.ThoiGianGoiApiFakeGps, thoiGianHienTai);
                                    getListAppFake();
                                } else {
                                    try {
                                        paketNameFakes = Common.checkFakeGPS();
                                        if (paketNameFakes.equals("")) {
                                            isFakeGPS = 0;
                                        } else {
                                            isFakeGPS = 1;
                                        }
                                    } catch (Exception e) {
                                        paketNameFakes = "";
                                        isFakeGPS = 0;

                                    }
                                    checkVersion();
                                    //new LoginTask().execute();
                                    // new taskVeChungToi().execute();
                                }
                            } catch (Exception e) {
                                //  e.printStackTrace();
                                getListAppFake();
                            }


                        } else {
                            dieuHuongCheckMayChu();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //Log.e("danhsachcongviec", congViecGiamDinhVienRespon.toString());
                }

                @Override
                public void onFailure(Call<DieuHuongRespon> call, Throwable t) {
                    try {
                        Log.d("BBB", "onFailure: "+t.getMessage());
                        progressDialog.cancel();
                        Toast.makeText(getBaseContext(), getResources().getString(R.string.toast_message_not_network_server),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void dieuHuongCheckMayChu() {
        try {
            try {
                progressDialog = ProgressDialog.show(LoginActivity.this, "", getString(R.string.dialog_waiting), true, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Service service = NetContext.instance.create(Service.class);

            service.checkDieuHuongMayChu("http://routesvksmart.lachongmedia.vn/AppCheckRouterAccess.aspx").enqueue(new Callback<CommonRespon>() {
                @Override
                public void onResponse(Call<CommonRespon> call, Response<CommonRespon> response) {
                    try {
                        progressDialog.cancel();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    try {
                        // Log.e("testtest", taoDiemRespon.toString());
                        CommonRespon commonRespon = response.body();
                        if (commonRespon.isStatus()) {
                            dieuHuong();
                        } else {
                            if (commonRespon.getMsg() != null) {
                                Toast.makeText(getBaseContext(), commonRespon.getMsg(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getBaseContext(), getResources().getString(R.string.toast_message_not_balance_network),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //Log.e("danhsachcongviec", congViecGiamDinhVienRespon.toString());
                }

                @Override
                public void onFailure(Call<CommonRespon> call, Throwable t) {
                    try {
                        Log.d("BBB", "onFailure: "+t.getMessage());
                        progressDialog.cancel();
                        Toast.makeText(getBaseContext(), getResources().getString(R.string.toast_message_not_network_server),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void checkVersion() {
        try {
            try {
                progressDialog = ProgressDialog.show(LoginActivity.this, "", getString(R.string.dialog_waiting), true, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Service service = NetContext.instance.create(Service.class);
            Map<String, String> params = new HashMap<>();
            params.put("token", Common.getToken());
            params.put("idct", binding.etQL.getText().toString());
            params.put("ver", Common.VersionCode_Login);

            service.checkPhienBanMoi(params).enqueue(new Callback<VersionAppRespon>() {
                @Override
                public void onResponse(Call<VersionAppRespon> call, Response<VersionAppRespon> response) {
                    try {
                        progressDialog.cancel();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    try {
                        // Log.e("testtest", taoDiemRespon.toString());
                        final VersionAppRespon commonRespon = response.body();
                        if (commonRespon.isStatus()) {
                            if (commonRespon.getData().getThongBaoPhienBanMoi() == 1) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                //Cài đặt các thuộc tính
                                builder.setTitle("Cập nhật phiên bản app");
                                builder.setMessage(commonRespon.getData().getDescription());

                                // Cài đặt button Cancel- Hiển thị Toast
                                builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // btnLogIn.setEnabled(true);
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setData(Uri.parse(
                                                commonRespon.getData().getLinkUpdateAndroid()));
                                        startActivity(intent);
                                        dialog.cancel();
                                    }
                                });
                                builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (commonRespon.getData().getChoPhepDangNhapPhienBanCuHon() == 1) {
                                            login();
                                        } else {
                                            //btnLogIn.setEnabled(true);
                                        }
                                        dialog.cancel();
                                    }
                                });
                                builder.create();
                                builder.show();
                            } else {
                                login();
                            }

                        } else {
                            login();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //Log.e("danhsachcongviec", congViecGiamDinhVienRespon.toString());
                }

                @Override
                public void onFailure(Call<VersionAppRespon> call, Throwable t) {
                    try {
                        progressDialog.cancel();
                        Toast.makeText(getBaseContext(), getResources().getString(R.string.toast_message_not_network_server),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
