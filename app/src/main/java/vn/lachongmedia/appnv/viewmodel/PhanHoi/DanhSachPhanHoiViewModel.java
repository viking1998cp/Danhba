package vn.lachongmedia.appnv.viewmodel.PhanHoi;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Map;

import vn.lachongmedia.appnv.network.phanhoi.DanhSachPhanHoiRespon;
import vn.lachongmedia.appnv.repository.PhanHoi.DanhSachPhanHoiRepository;

public class DanhSachPhanHoiViewModel extends AndroidViewModel {
    public DanhSachPhanHoiViewModel(Application application){
        super(application);
    }
    public LiveData<DanhSachPhanHoiRespon> getDanhSachPhanHoi(Map<String,String> pagrams) {
        return DanhSachPhanHoiRepository.getInstance().getMutableLiveData(pagrams);
    }
}
