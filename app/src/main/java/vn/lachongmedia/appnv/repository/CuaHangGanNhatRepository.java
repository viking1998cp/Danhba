package vn.lachongmedia.appnv.repository;

import android.location.Location;

import androidx.lifecycle.MutableLiveData;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.KsmartSalesApplication;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.SharedPrefs;
import vn.lachongmedia.appnv.network.CuaHang;
import vn.lachongmedia.appnv.network.NetContext;
import vn.lachongmedia.appnv.network.Service;
import vn.lachongmedia.appnv.network.trangchu.ListCuaHangRespon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tungda on 7/23/2019.
 */
public class CuaHangGanNhatRepository {
    private static CuaHangGanNhatRepository instance;
    public static CuaHangGanNhatRepository getInstance(){
        if (instance==null){
            instance=new CuaHangGanNhatRepository();
        }
        return instance;
    }
    private MutableLiveData<ArrayList<CuaHang>> mutableLiveData ;
    public MutableLiveData<ArrayList<CuaHang>> getMutableLiveData(Location location){
        mutableLiveData = new MutableLiveData<>();
        try {

            Service service = NetContext.instance.create(Service.class);
            Map<String, String> params = new HashMap<>();
            params.put("token", Common.getToken());
            params.put("idnhanvien", ""+ SharedPrefs.getInstance().get(Common.iDNhanVien,Integer.class));
            params.put("kinhdo",""+ location.getLongitude());
            params.put("vido",""+location.getLatitude());
            params.put("accuracy",""+location.getAccuracy());

            service.getCuaHangGanNhat(params).enqueue(new Callback<ListCuaHangRespon>() {
                @Override
                public void onResponse(Call<ListCuaHangRespon> call, Response<ListCuaHangRespon> response) {

                    try {

                        ListCuaHangRespon listCuaHangRespon = response.body();
                        if(listCuaHangRespon.isStatus()){
                            if(listCuaHangRespon.getListCuaHang()!=null){
                                if(listCuaHangRespon.getListCuaHang().size()>0){
                                    //showDialogKeHoachTrongNgay(listCuaHangRespon.getData());
                                    mutableLiveData.setValue(listCuaHangRespon.getListCuaHang());
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
                public void onFailure(Call<ListCuaHangRespon> call, Throwable t) {

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
