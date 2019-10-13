package vn.lachongmedia.appnv.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.KsmartSalesApplication;
import vn.lachongmedia.appnv.R;

import vn.lachongmedia.appnv.network.NetContext;
import vn.lachongmedia.appnv.network.Service;
import vn.lachongmedia.appnv.network.mathang.DanhSachMatHangLoadMoreRespon;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tungda on 7/26/2019.
 */
public class DanhSachMatHangRepository {
    private static DanhSachMatHangRepository instance;
    public static DanhSachMatHangRepository getInstance(){
        if (instance==null){
            instance=new DanhSachMatHangRepository();
        }
        return instance;
    }
    private MutableLiveData<DanhSachMatHangLoadMoreRespon> mutableLiveData ;
    public MutableLiveData<DanhSachMatHangLoadMoreRespon> getMutableLiveData(Map<String, String> params){
        mutableLiveData = new MutableLiveData<>();
        try {

            Service service = NetContext.instance.create(Service.class);

            service.getDanhSachMatHang(params).enqueue(new Callback<DanhSachMatHangLoadMoreRespon>() {
                @Override
                public void onResponse(Call<DanhSachMatHangLoadMoreRespon> call, Response<DanhSachMatHangLoadMoreRespon> response) {

                    try {

                        DanhSachMatHangLoadMoreRespon listCuaHangRespon = response.body();
                        if(listCuaHangRespon.isStatus()){
                            if(listCuaHangRespon.getListMatHang()!=null){
                                mutableLiveData.setValue(listCuaHangRespon);
                            }else {
                                mutableLiveData.setValue(null);
                            }
                        }else {
                            mutableLiveData.setValue(null);
                            Common.ShowToastLong(listCuaHangRespon.getMsg());
                        }

                    } catch (Exception e) {
                        mutableLiveData.setValue(null);
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<DanhSachMatHangLoadMoreRespon> call, Throwable t) {

                    try {
                        mutableLiveData.setValue(null);
                        Common.ShowToastShort(KsmartSalesApplication.getInstance().getString(R.string.toast_message_not_network_server));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mutableLiveData.setValue(null);

        }
        return mutableLiveData;
    }
}
