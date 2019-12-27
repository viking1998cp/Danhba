package vn.lachongmedia.appnv.viewmodel.GhiChu;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Map;

import vn.lachongmedia.appnv.network.ghichu.DanhSachTieuChiRespon;
import vn.lachongmedia.appnv.repository.GhiChu.DanhSachTieuChiRepository;

public class DanhSachTieuChiViewModel extends AndroidViewModel {
    public DanhSachTieuChiViewModel(Application application){
        super(application);
    }
    public LiveData<DanhSachTieuChiRespon> getDanhSachTieuChi(Map<String,String> pagrams) {
        return DanhSachTieuChiRepository.getInstance().getMutableLiveData(pagrams);
    }
}
