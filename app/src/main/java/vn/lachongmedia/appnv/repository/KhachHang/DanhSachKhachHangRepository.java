package vn.lachongmedia.appnv.repository.KhachHang;

import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
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
import vn.lachongmedia.appnv.network.khachhang.DanhSachKhachHangLoadMoreRespon;
import vn.lachongmedia.appnv.network.khachhang.DanhSachKhachHangRespon;

public class DanhSachKhachHangRepository {
    private static DanhSachKhachHangRepository instance;
    public static DanhSachKhachHangRepository getInstance(){
        if (instance==null){
            instance=new DanhSachKhachHangRepository();
        }
        return instance;
    }
    private MutableLiveData<DanhSachKhachHangLoadMoreRespon> mutableLiveData ;
    public MutableLiveData<DanhSachKhachHangLoadMoreRespon> getMutableLiveData(Map<String, String> params){
        mutableLiveData = new MutableLiveData<>();
        try {

            Service service = NetContext.instance.create(Service.class);
            Log.d("CCC", "getMutableLiveData:ttttttttt ");
            service.getDanhSachKhachHang(params).enqueue(new Callback<DanhSachKhachHangLoadMoreRespon>() {

                @Override
                public void onResponse(Call<DanhSachKhachHangLoadMoreRespon> call, Response<DanhSachKhachHangLoadMoreRespon> response) {

                    try {

                        DanhSachKhachHangLoadMoreRespon danhSachKhachHangLoadMoreRespon = response.body();
                        mutableLiveData.setValue(danhSachKhachHangLoadMoreRespon);

                    } catch (Exception e) {

                        mutableLiveData.setValue(null);
                        e.printStackTrace();
                    }
                }


                @Override
                public void onFailure(Call<DanhSachKhachHangLoadMoreRespon> call, Throwable t) {
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
