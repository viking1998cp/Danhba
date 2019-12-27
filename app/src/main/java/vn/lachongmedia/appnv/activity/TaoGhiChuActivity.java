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
import android.view.View;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.SharedPrefs;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerTaoGhiChu;
import vn.lachongmedia.appnv.databinding.ActivityTaoghichuBinding;
import vn.lachongmedia.appnv.network.CuaHang;
import vn.lachongmedia.appnv.network.NetContext;
import vn.lachongmedia.appnv.network.Service;
import vn.lachongmedia.appnv.network.ghichu.DanhSachTieuChiRespon;
import vn.lachongmedia.appnv.object.GhiChu.DanhSachGhiChu;
import vn.lachongmedia.appnv.object.GhiChu.GhiChu;
import vn.lachongmedia.appnv.viewmodel.GhiChu.DanhSachTieuChiViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tungda .
 */
public class TaoGhiChuActivity extends AppCompatActivity {
    ActivityTaoghichuBinding binding;
    AdapterRecyclerTaoGhiChu adapterRecyclerTaoGhiChu;
    ArrayList<DanhSachGhiChu> listDanhSachGhiChus = new ArrayList<>();
    DanhSachTieuChiViewModel danhSachTieuChiViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_taoghichu);
        back();
        //recyclerViewAlbum.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));
        binding.rvDanhSach.setLayoutManager(new LinearLayoutManager(this));
        danhSachTieuChiViewModel = ViewModelProviders.of(this).get(DanhSachTieuChiViewModel.class);
        adapterRecyclerTaoGhiChu = new AdapterRecyclerTaoGhiChu(listDanhSachGhiChus);
        binding.rvDanhSach.setAdapter(adapterRecyclerTaoGhiChu);

        initChondanhmuc();
        adapterRecyclerTaoGhiChu.setOnItemClickedListener(new AdapterRecyclerTaoGhiChu.OnItemClickedListener() {
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
                params.put("idnhanvien", "" + SharedPrefs.getInstance().get(Common.iDNhanVien, Integer.class));
                params.put("type", "them" );
                params.put("trangthaigps", String.valueOf(Common.checkGPS()));
                JSONArray jsonArray = new JSONArray();
                ArrayList<DanhSachGhiChu> duLieuGhiChus = new ArrayList<>();
                duLieuGhiChus.addAll(adapterRecyclerTaoGhiChu.getDuLieuPhanHoi());
                Log.d("CCC", "onClick: "+duLieuGhiChus.size());
                boolean checkEmpty = true;
                for(int i=0;i<duLieuGhiChus.size();i++){
                    if(duLieuGhiChus.get(i).getChiTiet().equals("")){
                        Common.ShowToastShort("Không được để trống nội dung phản hồi");
                        checkEmpty = false;
                        break;
                    }
                    JSONObject jsonObject = new JSONObject();
                    Log.d("CCC", "onClick: "+duLieuGhiChus.get(i).getChiTiet());
                    try {
                        Log.d("CCC", "onClick: "+duLieuGhiChus.get(i).getChiTiet().toString());
                        jsonObject.put("ChiTiet",duLieuGhiChus.get(i).getChiTiet().toString());
                        jsonObject.put("ID_QLLH",duLieuGhiChus.get(i).getIDQLLH());
                        jsonObject.put("ID_KhachHang",cuaHang.getIdcuahang());
                        jsonObject.put("ID_CheckList",duLieuGhiChus.get(i).getIDCheckList());
                        jsonObject.put("idcheckin",duLieuGhiChus.get(i).getIdcheckin());
                        jsonObject.put("ID_NhanVien",SharedPrefs.getInstance().get(Common.iDNhanVien, Integer.class));
                        jsonArray.put(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                Log.d("CCC", "onClick: "+jsonArray.toString());
                params.put("dulieuchecklist","{danhsachchecklist:"+jsonArray.toString()+"}");
                if(checkEmpty){
                    service.themGhiChu(params).enqueue(new Callback<GhiChu>() {
                        @Override
                        public void onResponse(Call<GhiChu> call, Response<GhiChu> response) {
                            if(response.body().isStatus()){
                                Common.ShowToastLong("Thêm thành công phản hồi");
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<GhiChu> call, Throwable t) {

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
        danhSachTieuChiViewModel.getDanhSachTieuChi(params).observe(this, new Observer<DanhSachTieuChiRespon>() {
            @Override
            public void onChanged(DanhSachTieuChiRespon danhSachTieuChiRespon) {
                try {
                    if (danhSachTieuChiRespon != null) { ;
                        if (danhSachTieuChiRespon.isStatus()) {
                            if (danhSachTieuChiRespon.getThemGhiChu() != null) {
                                listDanhSachGhiChus.addAll(danhSachTieuChiRespon.getThemGhiChu());
                                adapterRecyclerTaoGhiChu.notifyDataSetChanged();
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
