package vn.lachongmedia.appnv.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.KsmartSalesApplication;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.network.NetContext;
import vn.lachongmedia.appnv.network.Service;
import vn.lachongmedia.appnv.network.chupanh.ListAlbumRespon;
import vn.lachongmedia.appnv.network.mathang.DanhMucMatHangRespon;
import vn.lachongmedia.appnv.object.AlbumObject;

/**
 * Created by tungda on 7/27/2019.
 */
public class DanhMucMatHangRepository {
    private static DanhMucMatHangRepository instance;

    public static DanhMucMatHangRepository getInstance() {
        if (instance == null)
            instance = new DanhMucMatHangRepository();
        return instance;
    }

    private MutableLiveData<DanhMucMatHangRespon> mutableLiveData;

    public MutableLiveData<DanhMucMatHangRespon> getMutableLiveData(Map<String, String> params) {
        mutableLiveData = new MutableLiveData<>();
        try {
            Service service = NetContext.instance.create(Service.class);
            service.getDanhMucMatHang(params).enqueue(new Callback<DanhMucMatHangRespon>() {
                @Override
                public void onResponse(Call<DanhMucMatHangRespon> call, Response<DanhMucMatHangRespon> response) {
                    try {
                        DanhMucMatHangRespon danhMucMatHangRespon = response.body();
                        mutableLiveData.setValue(danhMucMatHangRespon);

                    } catch (Exception e) {
                        mutableLiveData.setValue(null);
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<DanhMucMatHangRespon> call, Throwable t) {

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
