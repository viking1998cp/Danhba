package vn.lachongmedia.appnv.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import vn.lachongmedia.appnv.object.ImageAlbumObject;
import vn.lachongmedia.appnv.repository.AlbumRepository;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by tungda on 7/22/2019.
 */

public class AlbumViewModel extends AndroidViewModel {

    public AlbumViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<ArrayList<ImageAlbumObject>> getListImage(Map<String, String> params) {
        return AlbumRepository.getInstance().getMutableLiveData(params);
    }
}
