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

import vn.lachongmedia.appnv.network.phanhoi.DanhSachPhanHoiRespon;

public class DanhSachPhanHoiRepository {
    private static DanhSachPhanHoiRepository instance;
    public static DanhSachPhanHoiRepository getInstance(){
        if (instance==null){
            instance=new DanhSachPhanHoiRepository();
        }
        return instance;
    }
    private MutableLiveData<DanhSachPhanHoiRespon> mutableLiveData ;
    public MutableLiveData<DanhSachPhanHoiRespon> getMutableLiveData(Map<String, String> params){
        mutableLiveData = new MutableLiveData<>();
        try {

            Service service = NetContext.instance.create(Service.class);

            service.getDanhSachPhanHoi(params).enqueue(new Callback<DanhSachPhanHoiRespon>() {
                @Override
                public void onResponse(Call<DanhSachPhanHoiRespon> call, Response<DanhSachPhanHoiRespon> response) {

                    try {
                        DanhSachPhanHoiRespon danhSachPhanHoiRespon = response.body();
                        mutableLiveData.setValue(danhSachPhanHoiRespon);

                    } catch (Exception e) {

                        mutableLiveData.setValue(null);
                        e.printStackTrace();
                    }
                }


                @Override
                public void onFailure(Call<DanhSachPhanHoiRespon> call, Throwable t) {
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
