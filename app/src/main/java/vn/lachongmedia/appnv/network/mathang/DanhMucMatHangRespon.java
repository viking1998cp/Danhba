package vn.lachongmedia.appnv.network.mathang;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import vn.lachongmedia.appnv.network.CommonRespon;
import vn.lachongmedia.appnv.object.DanhmucOBJ;

/**
 * Created by tungda on 7/27/2019.
 */
public class DanhMucMatHangRespon extends CommonRespon {
    @SerializedName("data")
    ArrayList<DanhmucOBJ> listDanhMuc;

    public ArrayList<DanhmucOBJ> getListDanhMuc() {
        return listDanhMuc;
    }

    public void setListDanhMuc(ArrayList<DanhmucOBJ> listDanhMuc) {
        this.listDanhMuc = listDanhMuc;
    }
}
