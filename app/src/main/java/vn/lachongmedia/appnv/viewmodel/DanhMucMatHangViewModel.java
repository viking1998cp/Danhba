package vn.lachongmedia.appnv.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Map;

import vn.lachongmedia.appnv.network.mathang.DanhMucMatHangRespon;
import vn.lachongmedia.appnv.repository.DanhMucMatHangRepository;

/**
 * Created by tungda on 7/27/2019.
 */
public class DanhMucMatHangViewModel extends AndroidViewModel {
    public DanhMucMatHangViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<DanhMucMatHangRespon> getDanhMucMatHang(Map<String,String> params) {
        return DanhMucMatHangRepository.getInstance().getMutableLiveData(params);
    }
}
