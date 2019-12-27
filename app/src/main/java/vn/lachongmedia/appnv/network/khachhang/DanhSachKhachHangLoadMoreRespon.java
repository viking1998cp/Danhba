package vn.lachongmedia.appnv.network.khachhang;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import vn.lachongmedia.appnv.network.CommonRespon;
import vn.lachongmedia.appnv.object.Khachhang.Data;
import vn.lachongmedia.appnv.object.Khachhang.Khachhang;

public class DanhSachKhachHangLoadMoreRespon extends CommonRespon {
    @SerializedName("data")
    ArrayList<Data> listDatas;
    int endlist;
    int lastid;

    public ArrayList<Data> getListDatas() {
        return listDatas;
    }

    public void setListDatas(ArrayList<Data> listDatas) {
        this.listDatas = listDatas;
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
