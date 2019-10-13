package vn.lachongmedia.appnv.viewmodel;

import android.app.Application;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import vn.lachongmedia.appnv.network.CuaHang;
import vn.lachongmedia.appnv.repository.CuaHangGanNhatRepository;

import java.util.ArrayList;

/**
 * Created by tungda on 7/23/2019.
 */
public class CuaHangGanNhatViewModel extends AndroidViewModel {


    public CuaHangGanNhatViewModel(@NonNull Application application) {
        super(application);

    }
    public LiveData<ArrayList<CuaHang>> getCuaHangGanNhat(Location location) {
        return CuaHangGanNhatRepository.getInstance().getMutableLiveData(location);
    }
}
