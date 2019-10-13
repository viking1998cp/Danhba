package vn.lachongmedia.appnv.repository;

import androidx.lifecycle.MutableLiveData;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.KsmartSalesApplication;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.network.NetContext;
import vn.lachongmedia.appnv.network.Service;
import vn.lachongmedia.appnv.network.tinnhan.TinNhanConversionRespon;
import vn.lachongmedia.appnv.object.TinNhanGroup;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tungda on 7/23/2019.
 */
public class TinNhanconVersionRepository {
    private static TinNhanconVersionRepository instance;

    public static TinNhanconVersionRepository getInstance() {
        if (instance == null)
            instance = new TinNhanconVersionRepository();
        return instance;
    }

    private MutableLiveData<ArrayList<TinNhanGroup>> mutableLiveData;

    public MutableLiveData<ArrayList<TinNhanGroup>> getMutableLiveData(Map<String, String> params) {
        mutableLiveData = new MutableLiveData<>();
        try {
            Service service = NetContext.instance.create(Service.class);
            service.getDanhSachTinNhanConversion(params).enqueue(new Callback<TinNhanConversionRespon>() {
                @Override
                public void onResponse(Call<TinNhanConversionRespon> call, Response<TinNhanConversionRespon> response) {
                    try {
                        TinNhanConversionRespon tinNhanConversionRespon = response.body();
                        if (tinNhanConversionRespon.isStatus()) {
                            if (tinNhanConversionRespon.getListTinNhanGroup() != null) {
                                if (tinNhanConversionRespon.getListTinNhanGroup().size() > 0) {
                                    mutableLiveData.setValue(tinNhanConversionRespon.getListTinNhanGroup());
                                } else {
                                    mutableLiveData.setValue(null);
                                }
                            } else {
                                mutableLiveData.setValue(null);
                            }
                        } else {
                            mutableLiveData.setValue(null);
                            Common.ShowToastLong(tinNhanConversionRespon.getMsg());
                        }

                    } catch (Exception e) {
                        mutableLiveData.setValue(null);
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<TinNhanConversionRespon> call, Throwable t) {

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
