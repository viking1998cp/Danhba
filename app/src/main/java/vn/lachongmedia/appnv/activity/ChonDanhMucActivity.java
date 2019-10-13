package vn.lachongmedia.appnv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.SharedPrefs;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerDanhMucMatHang;
import vn.lachongmedia.appnv.databinding.ActivityChondanhmucBinding;
import vn.lachongmedia.appnv.network.mathang.DanhMucMatHangRespon;
import vn.lachongmedia.appnv.object.DanhmucOBJ;
import vn.lachongmedia.appnv.viewmodel.DanhMucMatHangViewModel;

/**
 * Created by tungda on 7/27/2019.
 */
public class ChonDanhMucActivity  extends AppCompatActivity {
    ActivityChondanhmucBinding binding;
    DanhMucMatHangViewModel danhMucMatHangViewModel;
    private ArrayList<DanhmucOBJ> listDanhMuc=new ArrayList<>();
    private DanhmucOBJ danhmucOBJ;
    AdapterRecyclerDanhMucMatHang adapter;
    private String tendanhmuccha = "";
    private int KEY_ID_PARENT = 0;
    private int id_preParent = -2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_chondanhmuc);
        danhMucMatHangViewModel= ViewModelProviders.of(this).get(DanhMucMatHangViewModel.class);
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.rvDanhMucMatHang.setLayoutManager(new LinearLayoutManager(this));
        adapter=new AdapterRecyclerDanhMucMatHang(listDanhMuc);
        binding.rvDanhMucMatHang.setAdapter(adapter);
        adapter.setOnItemClickedListener((postion, v) -> {
            danhmucOBJ =listDanhMuc.get(postion);
            if (danhmucOBJ.getSoLuongDanhMucCon() == 0 || danhmucOBJ.getTenDanhMuc().equals("Tất cả")) {
                Intent it = new Intent();
                it.putExtra("danhmuc", danhmucOBJ);
                it.putExtra("ten_danhmuccha", tendanhmuccha);
                setResult(1, it);
                finish();
            }else {
                listDanhMuc.clear();
                tendanhmuccha=danhmucOBJ.getTenDanhMuc();
                danhmucOBJ.setTenDanhMuc(getResources().getString(R.string.tatca));
                listDanhMuc.add(0, danhmucOBJ);
                KEY_ID_PARENT = danhmucOBJ.getiDDanhMuc();
                getDanhMuc();
            }

        });
        getDanhMuc();


    }

    private void getDanhMuc(){
        binding.pullToRefresh.setRefreshing(true);
        Map<String,String> params=new HashMap<>();
        params.put("token", Common.getToken());
        params.put("idnhanvien", "" + SharedPrefs.getInstance().get(Common.iDNhanVien, Integer.class));
        if(danhmucOBJ!=null){
            params.put("iddanhmuc", "" + danhmucOBJ.getiDDanhMuc());
        }
        danhMucMatHangViewModel.getDanhMucMatHang(params).observe(this, new Observer<DanhMucMatHangRespon>() {
            @Override
            public void onChanged(DanhMucMatHangRespon danhMucMatHangRespon) {
                binding.pullToRefresh.setRefreshing(false);
                if(danhMucMatHangRespon!=null){
                    if(danhMucMatHangRespon.isStatus()){
                        if(danhMucMatHangRespon.getListDanhMuc()!=null&&danhMucMatHangRespon.getListDanhMuc().size()>0){
                            int dem = 0;
                            DanhmucOBJ tatca = new DanhmucOBJ();
                            tatca.setiDQLLH(0);
                            tatca.setTenDanhMuc(getResources().getString(R.string.tatca));
                            tatca.setiDDanhMucCha(id_preParent);
                            tatca.setiDDanhMuc(KEY_ID_PARENT);
                            tatca.setSoLuongDanhMucCon(0);
                            tatca.setiDDanhMuc(-1);
                            for(DanhmucOBJ temp:danhMucMatHangRespon.getListDanhMuc()){
                                if (temp.getiDDanhMuc() == id_preParent) {
                                    id_preParent = temp.getiDDanhMucCha();
                                }
                                if (KEY_ID_PARENT == 0) {
                                    if (temp.getiDDanhMucCha() == KEY_ID_PARENT) {
                                        {
                                            dem += temp.getSoLuongMatHang();
                                        }


                                    }
                                }

                                if (temp.getiDDanhMucCha() == KEY_ID_PARENT) {
                                    {
                                        tatca.setSoLuongMatHang(dem);
                                        listDanhMuc.add(temp);
                                    }
                                }
                            }
                            if (KEY_ID_PARENT == 0) {
                                listDanhMuc.add(0, tatca);
                            }

                          //  listDanhMuc.addAll(danhMucMatHangRespon.getListDanhMuc());
                            adapter.notifyDataSetChanged();
                        }else{
                            listDanhMuc.clear();
                            adapter.notifyDataSetChanged();
                        }
                    }else {
                        Common.ShowToastLong(danhMucMatHangRespon.getMsg());
                    }
                }
            }
        });

    }
}
