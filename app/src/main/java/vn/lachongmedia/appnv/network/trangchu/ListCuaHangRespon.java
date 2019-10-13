package vn.lachongmedia.appnv.network.trangchu;

import vn.lachongmedia.appnv.network.CommonRespon;
import vn.lachongmedia.appnv.network.CuaHang;

import java.util.ArrayList;

/**
 * Created by tungda on 7/18/2019.
 */
public class ListCuaHangRespon extends CommonRespon {
    private ArrayList<CuaHang>data;

    public ArrayList<CuaHang> getListCuaHang() {
        return data;
    }

    public void setListCuaHang(ArrayList<CuaHang> listCuaHang) {
        this.data = listCuaHang;
    }
}
