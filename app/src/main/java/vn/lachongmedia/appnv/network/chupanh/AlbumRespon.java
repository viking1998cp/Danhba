package vn.lachongmedia.appnv.network.chupanh;

import vn.lachongmedia.appnv.network.CommonRespon;
import vn.lachongmedia.appnv.object.AlbumObject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tungda on 7/22/2019.
 */
public class AlbumRespon extends CommonRespon {
    @SerializedName("obj")
    AlbumObject albumObject;

    public AlbumObject getAlbumObject() {
        return albumObject;
    }

    public void setAlbumObject(AlbumObject albumObject) {
        this.albumObject = albumObject;
    }
}
