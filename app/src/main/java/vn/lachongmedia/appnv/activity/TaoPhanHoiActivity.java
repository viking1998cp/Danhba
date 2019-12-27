package vn.lachongmedia.appnv.activity;

import android.content.Intent;
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
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.SharedPrefs;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerDanhSachPhanHoi;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerTaoPhanHoi;
import vn.lachongmedia.appnv.databinding.ActivityTaophanhoiBinding;
import vn.lachongmedia.appnv.databinding.FragmentDanhsachphanhoiBinding;

import vn.lachongmedia.appnv.network.CuaHang;
import vn.lachongmedia.appnv.network.Login.LoginRespon;
import vn.lachongmedia.appnv.network.NetContext;
import vn.lachongmedia.appnv.network.Service;
import vn.lachongmedia.appnv.network.phanhoi.DanhSachPhanHoiRespon;
import vn.lachongmedia.appnv.network.phanhoi.DanhSachThemPhanHoiRespon;
import vn.lachongmedia.appnv.object.PhanHoi.DanhSachPhanHoi;
import vn.lachongmedia.appnv.object.PhanHoi.LichSuNhom;
import vn.lachongmedia.appnv.object.PhanHoi.PhanHoi;
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

        initChondanhmuc();
        adapterRecyclerTaoPhanHoi.setOnItemClickedListener(new AdapterRecyclerTaoPhanHoi.OnItemClickedListener() {
            @Override
            public void onItemClick(int postion, View v) {
                //startActivity(new Intent(getActivity(), LichHenActivity.class));
            }
        });
        save();
        getData();
        cuaHang.setIdcuahang(0);
    }

    private void save() {
        binding.imvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Service service = NetContext.instance.create(Service.class);
                    Map<String , String> params = new HashMap<>();
                    params.put("token", Common.getToken());
                    params.put("idkhachhang", String.valueOf(cuaHang.getIdcuahang()));
                    params.put("type", "them" );
                    params.put("trangthaigps", String.valueOf(Common.checkGPS()));
                    JSONArray jsonArray = new JSONArray();
                    ArrayList<DanhSachPhanHoi> duLieuPhanHoi = new ArrayList<>();
                    duLieuPhanHoi.addAll(adapterRecyclerTaoPhanHoi.getDuLieuPhanHoi());
                    Log.d("CCC", "onClick: "+duLieuPhanHoi.size());
                    boolean checkEmpty = true;
                    for(int i=0;i<duLieuPhanHoi.size();i++){
                        if(duLieuPhanHoi.get(i).getChiTiet().equals("")){
                            Common.ShowToastShort("Không được để trống nội dung phản hồi");
                            checkEmpty = false;
                            break;
                        }
                        JSONObject jsonObject = new JSONObject();
                        Log.d("CCC", "onClick: "+duLieuPhanHoi.get(i).getChiTiet());
                        try {
                            Log.d("CCC", "onClick: "+duLieuPhanHoi.get(i).getChiTiet().toString());
                            jsonObject.put("ChiTiet",duLieuPhanHoi.get(i).getChiTiet().toString());
                            jsonObject.put("ID_QLLH",duLieuPhanHoi.get(i).getIDQLLH());
                            jsonObject.put("ID_KhachHang",cuaHang.getIdcuahang());
                            jsonObject.put("ID_PhanHoi",duLieuPhanHoi.get(i).getIDPhanHoi());
                            jsonObject.put("ID_NhanVien",SharedPrefs.getInstance().get(Common.iDNhanVien, Integer.class));
                            jsonArray.put(jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    Log.d("CCC", "onClick: "+jsonArray.toString());
                    params.put("dulieuphanhoi","{danhsachphanhoi:"+jsonArray.toString()+"}");
                    if(checkEmpty){
                        service.themPhanHoi(params).enqueue(new Callback<PhanHoi>() {
                            @Override
                            public void onResponse(Call<PhanHoi> call, Response<PhanHoi> response) {
                                if(response.body().isStatus()){
                                    Common.ShowToastLong("Thêm thành công phản hồi");
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<PhanHoi> call, Throwable t) {

                            }
                        });
                    }

            }
        });
    }

    private void initChondanhmuc() {
        binding.tvKhachhang.setOnClickListener(v -> {
            Intent intent = new Intent(this, ChonKhachHangActivity.class);
            intent.putExtra("parentFormId",2);
            this.startActivityForResult(intent, 1);
        });
    }
    private void getData() {

        Map<String, String> params = new HashMap<>();
        params.put("token", Common.getToken());
        params.put("idnhanvien", "" + SharedPrefs.getInstance().get(Common.iDNhanVien, Integer.class));
        params.put("idkhachhang", String.valueOf(cuaHang.getIdcuahang()));
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
    CuaHang cuaHang = new CuaHang();
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            cuaHang = (CuaHang) data.getSerializableExtra("cuahang");
            Log.d("BBB", "onActivityResult: "+cuaHang.getTenCuaHang());
            if (cuaHang == null) {
                binding.tvKhachhang.setText(getResources().getString(R.string.khachhanggoiy));
            } else {
                binding.tvKhachhang.setText(cuaHang.getTenCuaHang());
            }

        }
    }

}
