package vn.lachongmedia.appnv.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import vn.lachongmedia.appnv.object.TinNhanGroup;
import vn.lachongmedia.appnv.repository.TinNhanconVersionRepository;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by tungda on 7/25/2019.
 */
public class TinNhanConversionViewModel extends AndroidViewModel {
    public TinNhanConversionViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<ArrayList<TinNhanGroup>> getTinNhanConversion(Map<String, String> params){
        return TinNhanconVersionRepository.getInstance().getMutableLiveData(params);
    }
}
