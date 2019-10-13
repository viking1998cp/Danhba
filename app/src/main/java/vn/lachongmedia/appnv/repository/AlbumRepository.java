package vn.lachongmedia.appnv.repository;

import androidx.lifecycle.MutableLiveData;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.KsmartSalesApplication;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.network.NetContext;
import vn.lachongmedia.appnv.network.Service;
import vn.lachongmedia.appnv.network.chupanh.AlbumRespon;
import vn.lachongmedia.appnv.object.ImageAlbumObject;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tungda on 7/22/2019.
 */
public class AlbumRepository {
    private static AlbumRepository instance;
    public static AlbumRepository getInstance(){
        if(instance==null)
            instance=new AlbumRepository();
        return instance;
    }
    private MutableLiveData<ArrayList<ImageAlbumObject>> mutableLiveData ;
    public MutableLiveData<ArrayList<ImageAlbumObject>> getMutableLiveData(Map<String, String> params) {
        mutableLiveData = new MutableLiveData<>();
        try {
            Service service = NetContext.instance.create(Service.class);

            service.getAlbum(params).enqueue(new Callback<AlbumRespon>() {
                @Override
                public void onResponse(Call<AlbumRespon> call, Response<AlbumRespon> response) {
                    try {
                        AlbumRespon albumRespon = response.body();
                        if(albumRespon.isStatus()){
                            if(albumRespon.getAlbumObject().getListImage()!=null){
                                if(albumRespon.getAlbumObject().getListImage().size()>0){
                                    mutableLiveData.setValue(albumRespon.getAlbumObject().getListImage());
                                }else {
                                    mutableLiveData.setValue(null);
                                }
                            }else {
                                mutableLiveData.setValue(null);
                            }
                        }else {
                            mutableLiveData.setValue(null);
                            Common.ShowToastLong(albumRespon.getMsg());
                        }

                    } catch (Exception e) {
                        mutableLiveData.setValue(null);
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<AlbumRespon> call, Throwable t) {

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
