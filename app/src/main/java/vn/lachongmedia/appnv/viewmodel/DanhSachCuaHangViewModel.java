package vn.lachongmedia.appnv.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import vn.lachongmedia.appnv.network.ListCuaHangLoadMoreRespon;
import vn.lachongmedia.appnv.repository.DanhSachCuaHangRepository;

import java.util.Map;

/**
 * Created by tungda on 7/25/2019.
 */
public class DanhSachCuaHangViewModel extends AndroidViewModel {


    public DanhSachCuaHangViewModel(@NonNull Application application) {
        super(application);

    }
    public LiveData<ListCuaHangLoadMoreRespon> getDanhSachCuaHang(Map<String,String> pagrams) {
        return DanhSachCuaHangRepository.getInstance().getMutableLiveData(pagrams);
    }
}
