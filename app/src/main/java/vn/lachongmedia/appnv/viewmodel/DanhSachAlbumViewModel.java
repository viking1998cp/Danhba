package vn.lachongmedia.appnv.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import vn.lachongmedia.appnv.object.AlbumObject;
import vn.lachongmedia.appnv.repository.DanhSachAlbumRepository;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by tungda on 7/22/2019.
 */

public class DanhSachAlbumViewModel extends AndroidViewModel {

    public DanhSachAlbumViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<ArrayList<AlbumObject>> getListAlbum(Map<String, String> params) {
        return DanhSachAlbumRepository.getInstance().getMutableLiveData(params);
    }
}
