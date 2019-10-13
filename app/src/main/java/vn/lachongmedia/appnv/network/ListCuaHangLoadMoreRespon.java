package vn.lachongmedia.appnv.network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by tungda on 7/26/2019.
 */
public class ListCuaHangLoadMoreRespon extends CommonRespon {
    @SerializedName("data")
    private ArrayList<CuaHang> listCuaHang;

    private int endlist;
    private int lastid;

    public ArrayList<CuaHang> getListCuaHang() {
        return listCuaHang;
    }

    public void setListCuaHang(ArrayList<CuaHang> listCuaHang) {
        this.listCuaHang = listCuaHang;
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
