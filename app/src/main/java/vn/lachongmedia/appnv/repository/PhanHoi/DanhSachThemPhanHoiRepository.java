package vn.lachongmedia.appnv.repository.PhanHoi;

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
import vn.lachongmedia.appnv.network.phanhoi.DanhSachThemPhanHoiRespon;

public class DanhSachThemPhanHoiRepository {
    private static DanhSachThemPhanHoiRepository instance;
    public static DanhSachThemPhanHoiRepository getInstance(){
        if (instance==null){
            instance=new DanhSachThemPhanHoiRepository();
        }
        return instance;
    }
    private MutableLiveData<DanhSachThemPhanHoiRespon> mutableLiveData ;
    public MutableLiveData<DanhSachThemPhanHoiRespon> getMutableLiveData(Map<String, String> params){
        mutableLiveData = new MutableLiveData<>();
        try {

            Service service = NetContext.instance.create(Service.class);

            service.getDanhSachThemPhanHoi(params).enqueue(new Callback<DanhSachThemPhanHoiRespon>() {
                @Override
                public void onResponse(Call<DanhSachThemPhanHoiRespon> call, Response<DanhSachThemPhanHoiRespon> response) {
                    Log.d("BBB", "onResponse: "+response.body());
                    try {
                        DanhSachThemPhanHoiRespon danhSachThemPhanHoiRespon= response.body();
                        mutableLiveData.setValue(danhSachThemPhanHoiRespon);

                    } catch (Exception e) {

                        mutableLiveData.setValue(null);
                        e.printStackTrace();
                    }
                }


                @Override
                public void onFailure(Call<DanhSachThemPhanHoiRespon> call, Throwable t) {
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
