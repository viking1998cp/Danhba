package vn.lachongmedia.appnv.repository;

import androidx.lifecycle.MutableLiveData;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.KsmartSalesApplication;
import vn.lachongmedia.appnv.R;

import vn.lachongmedia.appnv.network.NetContext;
import vn.lachongmedia.appnv.network.Service;
import vn.lachongmedia.appnv.network.chupanh.ListAlbumRespon;
import vn.lachongmedia.appnv.object.AlbumObject;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tungda on 7/22/2019.
 */
public class DanhSachAlbumRepository {
    private static DanhSachAlbumRepository instance;

    public static DanhSachAlbumRepository getInstance() {
        if (instance == null)
            instance = new DanhSachAlbumRepository();
        return instance;
    }

    private MutableLiveData<ArrayList<AlbumObject>> mutableLiveData;

    public MutableLiveData<ArrayList<AlbumObject>> getMutableLiveData(Map<String, String> params) {
        mutableLiveData = new MutableLiveData<>();
        try {
            Service service = NetContext.instance.create(Service.class);
            service.getListAlbum(params).enqueue(new Callback<ListAlbumRespon>() {
                @Override
                public void onResponse(Call<ListAlbumRespon> call, Response<ListAlbumRespon> response) {
                    try {
                        ListAlbumRespon listAlbumRespon = response.body();
                        if (listAlbumRespon.isStatus()) {
                            if (listAlbumRespon.getListAlbum() != null) {
                                if (listAlbumRespon.getListAlbum().size() > 0) {
                                    mutableLiveData.setValue(listAlbumRespon.getListAlbum());
                                } else {
                                    mutableLiveData.setValue(null);
                                }
                            } else {
                                mutableLiveData.setValue(null);
                            }
                        } else {
                            mutableLiveData.setValue(null);
                            Common.ShowToastLong(listAlbumRespon.getMsg());
                        }

                    } catch (Exception e) {
                        mutableLiveData.setValue(null);
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<ListAlbumRespon> call, Throwable t) {

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
