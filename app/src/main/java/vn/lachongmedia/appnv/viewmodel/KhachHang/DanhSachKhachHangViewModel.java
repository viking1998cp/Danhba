package vn.lachongmedia.appnv.viewmodel.KhachHang;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Map;

import vn.lachongmedia.appnv.network.khachhang.DanhSachKhachHangLoadMoreRespon;
import vn.lachongmedia.appnv.network.khachhang.DanhSachKhachHangRespon;
import vn.lachongmedia.appnv.repository.KhachHang.DanhSachKhachHangRepository;

public class DanhSachKhachHangViewModel extends AndroidViewModel {
    public DanhSachKhachHangViewModel(Application application){
        super(application);
    }
    public LiveData<DanhSachKhachHangLoadMoreRespon> getDanhSachKhachHang(Map<String,String> pagrams) {
        return DanhSachKhachHangRepository.getInstance().getMutableLiveData(pagrams);
    }
}
