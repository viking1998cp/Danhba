package vn.lachongmedia.appnv.repository;

import androidx.lifecycle.MutableLiveData;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.KsmartSalesApplication;

import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.SharedPrefs;
import vn.lachongmedia.appnv.network.NetContext;
import vn.lachongmedia.appnv.network.Service;
import vn.lachongmedia.appnv.network.trangchu.ListKeHoachTrongNgayRespon;
import vn.lachongmedia.appnv.object.KeHoachOBJ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tungda on 7/19/2019.
 */
public class KeHoachRepository {
    private MutableLiveData<ArrayList<KeHoachOBJ>> mutableLiveData = new MutableLiveData<>();
    public MutableLiveData<ArrayList<KeHoachOBJ>> getMutableLiveData(){
        try {

            Service service = NetContext.instance.create(Service.class);
            Map<String, String> params = new HashMap<>();
            params.put("token", Common.getToken());
            params.put("idnhanvien", ""+ SharedPrefs.getInstance().get(Common.iDNhanVien,Integer.class));
            params.put("thoigian", Common.getNgayHienTaiHai());

            service.getKeHoachTrongNgay(params).enqueue(new Callback<ListKeHoachTrongNgayRespon>() {
                @Override
                public void onResponse(Call<ListKeHoachTrongNgayRespon> call, Response<ListKeHoachTrongNgayRespon> response) {

                    try {

                        ListKeHoachTrongNgayRespon listCuaHangRespon = response.body();
                        if(listCuaHangRespon.isStatus()){
                            if(listCuaHangRespon.getData()!=null){
                                if(listCuaHangRespon.getData().size()>0){
                                    //showDialogKeHoachTrongNgay(listCuaHangRespon.getData());

                                    mutableLiveData.setValue(listCuaHangRespon.getData());
                                    //mutableLiveData.setValue(null);
                                }else {
                                    Common.ShowToastLong(KsmartSalesApplication.getInstance().getString(R.string.message_no_data));
                                    mutableLiveData.setValue(null);
                                }
                            }else {
                                Common.ShowToastLong(KsmartSalesApplication.getInstance().getString(R.string.message_no_data));
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
                public void onFailure(Call<ListKeHoachTrongNgayRespon> call, Throwable t) {

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
