package vn.lachongmedia.appnv.repository;

import androidx.lifecycle.MutableLiveData;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.KsmartSalesApplication;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.network.NetContext;
import vn.lachongmedia.appnv.network.Service;
import vn.lachongmedia.appnv.network.chupanh.SendAlbumRespon;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tungda on 7/23/2019.
 */
public class SendAlbumRepository {
    private static SendAlbumRepository instance;
    public static SendAlbumRepository getInstance(){
        if(instance==null)
            instance=new SendAlbumRepository();
        return instance;
    }
    private MutableLiveData<SendAlbumRespon> mutableLiveData;
    public MutableLiveData<SendAlbumRespon> getMutableLiveData(Map<String, String> params ){
        try {
            mutableLiveData = new MutableLiveData<>();
            Service service = NetContext.instance.create(Service.class);

            service.sendAlbum(params).enqueue(new Callback<SendAlbumRespon>() {
                @Override
                public void onResponse(Call<SendAlbumRespon> call, Response<SendAlbumRespon> response) {

                    try {
                        SendAlbumRespon sendAlbumRespon = response.body();
                        mutableLiveData.setValue(sendAlbumRespon);

                    } catch (Exception e) {
                        mutableLiveData.setValue(null);
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<SendAlbumRespon> call, Throwable t) {

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
