package vn.lachongmedia.appnv.network.chupanh;

import vn.lachongmedia.appnv.network.CommonRespon;
import vn.lachongmedia.appnv.object.AlbumObject;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by tungda on 7/22/2019.
 */
public class ListAlbumRespon extends CommonRespon {
    @SerializedName("data")
    ArrayList<AlbumObject>listAlbum;

    public ArrayList<AlbumObject> getListAlbum() {
        return listAlbum;
    }

    public void setListAlbum(ArrayList<AlbumObject> listAlbum) {
        this.listAlbum = listAlbum;
    }
}
