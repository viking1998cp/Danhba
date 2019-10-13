package vn.lachongmedia.appnv.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import vn.lachongmedia.appnv.object.KeHoachOBJ;
import vn.lachongmedia.appnv.repository.KeHoachRepository;

import java.util.ArrayList;

/**
 * Created by tungda on 7/19/2019.
 */
/*
* Thiết kế theo chuẩn MVVM tận dụng ViewModel và liveData*/
public class KeHoachViewModel extends AndroidViewModel {
    private KeHoachRepository keHoachRepository;
    public KeHoachViewModel(@NonNull Application application) {
        super(application);
        keHoachRepository=new KeHoachRepository();
    }
    public LiveData<ArrayList<KeHoachOBJ>> getKeHoachTrongNgay() {
        return keHoachRepository.getMutableLiveData();
    }
}
