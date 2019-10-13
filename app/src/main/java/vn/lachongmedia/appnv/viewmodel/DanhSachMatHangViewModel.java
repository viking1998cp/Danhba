package vn.lachongmedia.appnv.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import vn.lachongmedia.appnv.network.mathang.DanhSachMatHangLoadMoreRespon;
import vn.lachongmedia.appnv.repository.DanhSachMatHangRepository;

import java.util.Map;

/**
 * Created by tungda on 7/26/2019.
 */
public class DanhSachMatHangViewModel extends AndroidViewModel {


    public DanhSachMatHangViewModel(@NonNull Application application) {
        super(application);

    }
    public LiveData<DanhSachMatHangLoadMoreRespon> getDanhSachMatHang(Map<String,String> pagrams) {
        return DanhSachMatHangRepository.getInstance().getMutableLiveData(pagrams);
    }
}
