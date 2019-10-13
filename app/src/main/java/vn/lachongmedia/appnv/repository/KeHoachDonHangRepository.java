package vn.lachongmedia.appnv.repository;

import androidx.lifecycle.MutableLiveData;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.KsmartSalesApplication;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.SharedPrefs;
import vn.lachongmedia.appnv.network.NetContext;
import vn.lachongmedia.appnv.network.Service;
import vn.lachongmedia.appnv.network.trangchu.ListKeHoachDonHangTrongNgayRespon;
import vn.lachongmedia.appnv.object.KeHoachDonHangObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tungda on 7/19/2019.
 */
public class KeHoachDonHangRepository {
    private MutableLiveData<ArrayList<KeHoachDonHangObject>> mutableLiveData = new MutableLiveData<>();
    public MutableLiveData<ArrayList<KeHoachDonHangObject>> getMutableLiveData(String tuNgay,String denNgay){
        try {

            Service service = NetContext.instance.create(Service.class);
            Map<String, String> params = new HashMap<>();
            params.put("token", Common.getToken());
            params.put("idnhanvien", ""+ SharedPrefs.getInstance().get(Common.iDNhanVien,Integer.class));
            params.put("from", tuNgay);
            params.put("to",denNgay);

            service.getKeHoachDonHang(params).enqueue(new Callback<ListKeHoachDonHangTrongNgayRespon>() {
                @Override
                public void onResponse(Call<ListKeHoachDonHangTrongNgayRespon> call, Response<ListKeHoachDonHangTrongNgayRespon> response) {

                    try {

                        ListKeHoachDonHangTrongNgayRespon listCuaHangRespon = response.body();
                        if(listCuaHangRespon.isStatus()){
                            if(listCuaHangRespon.getListKeHoachDonHang()!=null){
                                if(listCuaHangRespon.getListKeHoachDonHang().size()>0){
                                    //showDialogKeHoachTrongNgay(listCuaHangRespon.getData());
                                    mutableLiveData.setValue(listCuaHangRespon.getListKeHoachDonHang());
                                    //mutableLiveData.setValue(null);
                                }else {
                                    mutableLiveData.setValue(null);
                                }
                            }else {
                                mutableLiveData.setValue(null);
                            }
                        }else {
                            mutableLiveData.setValue(null);
                            Common.ShowToastLong(listCuaHangRespon.getMsg());
                        }

                    } catch (Exception e) {
                        mutableLiveData.setValue(null);
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<ListKeHoachDonHangTrongNgayRespon> call, Throwable t) {

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
