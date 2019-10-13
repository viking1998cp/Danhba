package vn.lachongmedia.appnv.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import vn.lachongmedia.appnv.network.checkin.CheckOutResponse;
import vn.lachongmedia.appnv.repository.RaDiemRepository;

import java.util.Map;

/**
 * Created by tungda on 7/21/2019.
 */
public class RaDiemViewModel extends AndroidViewModel {

    public RaDiemViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<CheckOutResponse> raDiem(Map<String, String> params){
        return RaDiemRepository.getInstance().getMutableLiveData(params);
    }
}
