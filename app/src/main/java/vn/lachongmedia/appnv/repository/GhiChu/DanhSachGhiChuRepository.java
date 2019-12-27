package vn.lachongmedia.appnv.repository.GhiChu;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.KsmartSalesApplication;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.network.NetContext;
import vn.lachongmedia.appnv.network.Service;
import vn.lachongmedia.appnv.network.ghichu.DanhSachGhiChuRespon;

public class DanhSachGhiChuRepository {
    private static DanhSachGhiChuRepository instance;
    public static DanhSachGhiChuRepository getInstance(){
        if (instance==null){
            instance=new DanhSachGhiChuRepository();
        }
        return instance;
    }
    private MutableLiveData<DanhSachGhiChuRespon> mutableLiveData ;
    public MutableLiveData<DanhSachGhiChuRespon> getMutableLiveData(Map<String, String> params){
        mutableLiveData = new MutableLiveData<>();
        try {

            Service service = NetContext.instance.create(Service.class);

            service.getDanhSachGhiChu(params).enqueue(new Callback<DanhSachGhiChuRespon>() {
                    @Override
                public void onResponse(Call<DanhSachGhiChuRespon> call, Response<DanhSachGhiChuRespon> response) {

                    try {
                        DanhSachGhiChuRespon danhSachPhanHoiRespon = response.body();
                        mutableLiveData.setValue(danhSachPhanHoiRespon);

                    } catch (Exception e) {

                        mutableLiveData.setValue(null);
                        e.printStackTrace();
                    }
                }


                @Override
                public void onFailure(Call<DanhSachGhiChuRespon> call, Throwable t) {
                    Log.d("BBB", "onFailure: "+t.getMessage());
                    try {
                        mutableLiveData.setValue(null);
                        Common.ShowToastShort(KsmartSalesApplication.getInstance().getString(R.string.toast_message_not_network_server));
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("BBB", "onFailure: "+e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("BBB", "getMutableLiveData: "+e.toString());
            mutableLiveData.setValue(null);
        }
        return mutableLiveData;
    }
}
