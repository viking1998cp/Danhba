package vn.lachongmedia.appnv.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import vn.lachongmedia.appnv.object.KeHoachDonHangObject;
import vn.lachongmedia.appnv.repository.KeHoachDonHangRepository;


import java.util.ArrayList;

/**
 * Created by tungda on 7/19/2019.
 */
/*
* Thiết kế theo chuẩn MVVM tận dụng ViewModel và liveData*/
public class KeHoachDonHangViewModel extends AndroidViewModel {
    private KeHoachDonHangRepository keHoachDonHangRepository;

    public KeHoachDonHangViewModel(@NonNull Application application) {
        super(application);
        keHoachDonHangRepository=new KeHoachDonHangRepository();
    }
    public LiveData<ArrayList<KeHoachDonHangObject>> getKeHoachDonHangTrongNgay(String tuNgay, String denNgay) {
        return keHoachDonHangRepository.getMutableLiveData(tuNgay,denNgay);
    }
}
