package vn.lachongmedia.appnv.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import vn.lachongmedia.appnv.network.chupanh.SendAlbumRespon;
import vn.lachongmedia.appnv.repository.SendAlbumRepository;

import java.util.Map;

/**
 * Created by tungda on 7/23/2019.
 */
public class SendAlbumViewModel extends AndroidViewModel {
    public SendAlbumViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<SendAlbumRespon> sendAlbum(Map<String, String> params){
        return SendAlbumRepository.getInstance().getMutableLiveData(params);
    }
}
