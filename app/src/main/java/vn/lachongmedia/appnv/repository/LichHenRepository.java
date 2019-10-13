package vn.lachongmedia.appnv.repository;

import androidx.lifecycle.MutableLiveData;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.KsmartSalesApplication;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.network.NetContext;
import vn.lachongmedia.appnv.network.Service;
import vn.lachongmedia.appnv.network.trangchu.DanhSachlichHenRespon;
import vn.lachongmedia.appnv.object.LichHenObject;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tungda on 7/19/2019.
 */
public class LichHenRepository {
    private MutableLiveData<ArrayList<LichHenObject>> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<ArrayList<LichHenObject>> getMutableLiveData(Map<String, String> params) {
        try {
            Service service = NetContext.instance.create(Service.class);

            service.getLichHen(params).enqueue(new Callback<DanhSachlichHenRespon>() {
                @Override
                public void onResponse(Call<DanhSachlichHenRespon> call, Response<DanhSachlichHenRespon> response) {
                    try {

                        DanhSachlichHenRespon listCuaHangRespon = response.body();
                        if (listCuaHangRespon.isStatus()) {
                            if (listCuaHangRespon.getListLichHen() != null) {
                                if (listCuaHangRespon.getListLichHen().size() > 0) {
                                    //showDialogKeHoachTrongNgay(listCuaHangRespon.getData());

                                    mutableLiveData.setValue(listCuaHangRespon.getListLichHen());
                                    //mutableLiveData.setValue(null);
                                } else {
                                    mutableLiveData.setValue(null);
                                }
                            } else {
                                mutableLiveData.setValue(null);
                            }
                        } else {
                            mutableLiveData.setValue(null);
                            Common.ShowToastLong(listCuaHangRespon.getMsg());
                        }

                    } catch (Exception e) {
                        mutableLiveData.setValue(null);
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<DanhSachlichHenRespon> call, Throwable t) {

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
