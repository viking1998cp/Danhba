package vn.lachongmedia.appnv.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import vn.lachongmedia.appnv.object.LichHenObject;
import vn.lachongmedia.appnv.repository.LichHenRepository;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by tungda on 7/19/2019.
 */
/*
* Thiết kế theo chuẩn MVVM tận dụng ViewModel và liveData*/
public class LichHenViewModel extends AndroidViewModel {
    private LichHenRepository lichHenRepository;
    public LichHenViewModel(@NonNull Application application) {
        super(application);
        lichHenRepository=new LichHenRepository();
    }
    public LiveData<ArrayList<LichHenObject>> getKeHoachTrongNgay(Map<String,String> params) {
        return lichHenRepository.getMutableLiveData(params);
    }
}
