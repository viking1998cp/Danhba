package vn.lachongmedia.appnv.viewmodel.PhanHoi;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Map;

import vn.lachongmedia.appnv.network.phanhoi.DanhSachThemPhanHoiRespon;
import vn.lachongmedia.appnv.repository.PhanHoi.DanhSachThemPhanHoiRepository;

public class DanhSachThemPhanHoiViewModel extends AndroidViewModel {
    public DanhSachThemPhanHoiViewModel(Application application){
        super(application);
    }
    public LiveData<DanhSachThemPhanHoiRespon> getDanhSachThemPhanHoi(Map<String,String> pagrams) {
        return DanhSachThemPhanHoiRepository.getInstance().getMutableLiveData(pagrams);
    }
}
