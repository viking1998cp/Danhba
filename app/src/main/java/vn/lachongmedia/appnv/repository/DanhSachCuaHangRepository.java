package vn.lachongmedia.appnv.repository;

import androidx.lifecycle.MutableLiveData;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.KsmartSalesApplication;
import vn.lachongmedia.appnv.R;

import vn.lachongmedia.appnv.network.ListCuaHangLoadMoreRespon;
import vn.lachongmedia.appnv.network.NetContext;
import vn.lachongmedia.appnv.network.Service;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tungda on 7/25/2019.
 */
public class DanhSachCuaHangRepository {
    private static DanhSachCuaHangRepository instance;
    public static DanhSachCuaHangRepository getInstance(){
        if (instance==null){
            instance=new DanhSachCuaHangRepository();
        }
        return instance;
    }
    private MutableLiveData<ListCuaHangLoadMoreRespon> mutableLiveData ;
    public MutableLiveData<ListCuaHangLoadMoreRespon> getMutableLiveData(Map<String, String> params){
        mutableLiveData = new MutableLiveData<>();
        try {

            Service service = NetContext.instance.create(Service.class);

            service.getDanhSachCuaHang(params).enqueue(new Callback<ListCuaHangLoadMoreRespon>() {
                @Override
                public void onResponse(Call<ListCuaHangLoadMoreRespon> call, Response<ListCuaHangLoadMoreRespon> response) {

                    try {

                        ListCuaHangLoadMoreRespon listCuaHangRespon = response.body();
                        if(listCuaHangRespon.isStatus()){
                            if(listCuaHangRespon.getListCuaHang()!=null){
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
                public void onFailure(Call<ListCuaHangLoadMoreRespon> call, Throwable t) {

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
