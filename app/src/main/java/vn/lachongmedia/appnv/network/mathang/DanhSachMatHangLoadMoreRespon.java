package vn.lachongmedia.appnv.network.mathang;

import vn.lachongmedia.appnv.network.CommonRespon;
import vn.lachongmedia.appnv.object.MatHang;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by tungda on 7/26/2019.
 */
public class DanhSachMatHangLoadMoreRespon extends CommonRespon {
    @SerializedName("data")
    ArrayList<MatHang>listMatHang;
    int endlist;
    int lastid;

    public ArrayList<MatHang> getListMatHang() {
        return listMatHang;
    }

    public void setListMatHang(ArrayList<MatHang> listMatHang) {
        this.listMatHang = listMatHang;
    }

    public int getEndlist() {
        return endlist;
    }

    public void setEndlist(int endlist) {
        this.endlist = endlist;
    }

    public int getLastid() {
        return lastid;
    }

    public void setLastid(int lastid) {
        this.lastid = lastid;
    }
}
