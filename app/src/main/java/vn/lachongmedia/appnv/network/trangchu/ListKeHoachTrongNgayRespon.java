package vn.lachongmedia.appnv.network.trangchu;

import vn.lachongmedia.appnv.network.CommonRespon;
import vn.lachongmedia.appnv.object.KeHoachOBJ;

import java.util.ArrayList;

/**
 * Created by tungda on 7/18/2019.
 */
public class ListKeHoachTrongNgayRespon extends CommonRespon {
    private ArrayList<KeHoachOBJ>data;

    public ArrayList<KeHoachOBJ> getData() {
        return data;
    }

    public void setData(ArrayList<KeHoachOBJ> listCuaHang) {
        this.data = listCuaHang;
    }
}
