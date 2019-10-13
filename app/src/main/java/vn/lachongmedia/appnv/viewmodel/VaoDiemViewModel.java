package vn.lachongmedia.appnv.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import vn.lachongmedia.appnv.network.checkin.CheckInResponse;
import vn.lachongmedia.appnv.repository.VaoDiemRepository;

import java.util.Map;

/**
 * Created by tungda on 7/21/2019.
 */
public class VaoDiemViewModel extends AndroidViewModel {

    public VaoDiemViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<CheckInResponse> vaoDiem(Map<String, String> params){
        return VaoDiemRepository.getInstance().getMutableLiveData(params);
    }
}
