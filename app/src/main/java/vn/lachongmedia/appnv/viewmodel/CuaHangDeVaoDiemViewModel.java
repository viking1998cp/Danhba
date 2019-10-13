package vn.lachongmedia.appnv.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import android.location.Location;
import androidx.annotation.NonNull;

import vn.lachongmedia.appnv.network.CuaHang;
import vn.lachongmedia.appnv.repository.CuaHangDeVaoDiemRepository;


import java.util.ArrayList;

/**
 * Created by tungda on 7/20/2019.
 */
public class CuaHangDeVaoDiemViewModel extends AndroidViewModel {
    private CuaHangDeVaoDiemRepository cuaHangDeVaoDiemRepository;

    public CuaHangDeVaoDiemViewModel(@NonNull Application application) {
        super(application);
        cuaHangDeVaoDiemRepository=new CuaHangDeVaoDiemRepository();
    }
    public LiveData<ArrayList<CuaHang>> getCuaHangGanNhatDeVaoDiem(Location location) {
        return cuaHangDeVaoDiemRepository.getMutableLiveData(location);
    }
}
