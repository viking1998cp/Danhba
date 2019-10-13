package vn.lachongmedia.appnv.repository;

import androidx.lifecycle.MutableLiveData;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.KsmartSalesApplication;
import vn.lachongmedia.appnv.R;

import vn.lachongmedia.appnv.network.NetContext;
import vn.lachongmedia.appnv.network.Service;
import vn.lachongmedia.appnv.network.checkin.CheckInResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tungda on 7/21/2019.
 */
public class VaoDiemRepository {
    private static VaoDiemRepository instance;
    public static VaoDiemRepository getInstance(){
        if(instance==null)
            instance=new VaoDiemRepository();
        return instance;
    }
    private MutableLiveData<CheckInResponse> mutableLiveData = new MutableLiveData<>();
    public MutableLiveData<CheckInResponse> getMutableLiveData(Map<String, String> params ){
        try {

            Service service = NetContext.instance.create(Service.class);

            service.checkIn(params).enqueue(new Callback<CheckInResponse>() {
                @Override
                public void onResponse(Call<CheckInResponse> call, Response<CheckInResponse> response) {

                    try {
                        CheckInResponse checkInResponse = response.body();
                        mutableLiveData.setValue(checkInResponse);

                    } catch (Exception e) {
                        mutableLiveData.setValue(null);
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<CheckInResponse> call, Throwable t) {

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
