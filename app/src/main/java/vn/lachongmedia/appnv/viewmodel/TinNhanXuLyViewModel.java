package vn.lachongmedia.appnv.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import vn.lachongmedia.appnv.network.CommonRespon;
import vn.lachongmedia.appnv.repository.XuLyTinNhanRepository;

import java.util.Map;

/**
 * Created by tungda on 7/25/2019.
 */
public class TinNhanXuLyViewModel extends AndroidViewModel {
    public TinNhanXuLyViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<CommonRespon> getTinNhanConversion(Map<String, String> params){
        return XuLyTinNhanRepository.getInstance().getMutableLiveData(params);
    }

}
