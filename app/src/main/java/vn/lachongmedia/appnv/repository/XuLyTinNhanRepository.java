package vn.lachongmedia.appnv.repository;

import androidx.lifecycle.MutableLiveData;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.KsmartSalesApplication;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.network.CommonRespon;
import vn.lachongmedia.appnv.network.NetContext;
import vn.lachongmedia.appnv.network.Service;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tungda on 7/25/2019.
 */
public class XuLyTinNhanRepository {
    private static XuLyTinNhanRepository instance;

    public static XuLyTinNhanRepository getInstance() {
        if (instance == null)
            instance = new XuLyTinNhanRepository();
        return instance;
    }

    private MutableLiveData<CommonRespon> mutableLiveData;

    public MutableLiveData<CommonRespon> getMutableLiveData(Map<String, String> params) {
        mutableLiveData = new MutableLiveData<>();
        try {
            Service service = NetContext.instance.create(Service.class);
            service.xuLyTinNhan(params).enqueue(new Callback<CommonRespon>() {
                @Override
                public void onResponse(Call<CommonRespon> call, Response<CommonRespon> response) {
                    try {
                        CommonRespon commonRespon = response.body();
                        if (commonRespon.isStatus()) {
                            mutableLiveData.setValue(commonRespon);
                        } else {
                            mutableLiveData.setValue(null);
                            Common.ShowToastLong(commonRespon.getMsg());
                        }

                    } catch (Exception e) {
                        mutableLiveData.setValue(null);
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<CommonRespon> call, Throwable t) {

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
