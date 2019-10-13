package vn.lachongmedia.appnv.network.Login;

import androidx.annotation.NonNull;

/**
 * Created by tungda on 7/16/2019.
 */
public class LoaiAlbum {
    private int ID_LoaiAlbum;//1,
    private String TenLoaiAlbum;//Ảnh sản phẩm

    public int getID_LoaiAlbum() {
        return ID_LoaiAlbum;
    }

    public void setID_LoaiAlbum(int ID_LoaiAlbum) {
        this.ID_LoaiAlbum = ID_LoaiAlbum;
    }

    public String getTenLoaiAlbum() {
        return TenLoaiAlbum;
    }

    public void setTenLoaiAlbum(String tenLoaiAlbum) {
        TenLoaiAlbum = tenLoaiAlbum;
    }

    @NonNull
    @Override
    public String toString() {
        return TenLoaiAlbum;
    }
}
