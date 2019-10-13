package vn.lachongmedia.appnv.repository;

import androidx.lifecycle.MutableLiveData;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.KsmartSalesApplication;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.network.NetContext;
import vn.lachongmedia.appnv.network.Service;
import vn.lachongmedia.appnv.network.checkin.CheckOutResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tungda on 7/21/2019.
 */
public class RaDiemRepository {
    private static RaDiemRepository instance;
    public static RaDiemRepository getInstance(){
        if(instance==null)
            instance=new RaDiemRepository();
        return instance;
    }
    private MutableLiveData<CheckOutResponse> mutableLiveData;
    public MutableLiveData<CheckOutResponse> getMutableLiveData(Map<String, String> params ){
        try {
            mutableLiveData = new MutableLiveData<>();
            Service service = NetContext.instance.create(Service.class);

            service.checkOut(params).enqueue(new Callback<CheckOutResponse>() {
                @Override
                public void onResponse(Call<CheckOutResponse> call, Response<CheckOutResponse> response) {

                    try {
                        CheckOutResponse checkOutResponse = response.body();
                        mutableLiveData.setValue(checkOutResponse);

                    } catch (Exception e) {
                        mutableLiveData.setValue(null);
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<CheckOutResponse> call, Throwable t) {

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
