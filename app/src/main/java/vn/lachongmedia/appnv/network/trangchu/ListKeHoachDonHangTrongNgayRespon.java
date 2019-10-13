package vn.lachongmedia.appnv.network.trangchu;

import vn.lachongmedia.appnv.network.CommonRespon;
import vn.lachongmedia.appnv.object.KeHoachDonHangObject;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by tungda on 7/18/2019.
 */
public class ListKeHoachDonHangTrongNgayRespon extends CommonRespon {
    @SerializedName("data")
    private ArrayList<KeHoachDonHangObject>listKeHoachDonHang;
    public ArrayList<KeHoachDonHangObject> getListKeHoachDonHang() {
        return listKeHoachDonHang;
    }

    public void setData(ArrayList<KeHoachDonHangObject> listKeHoachDonHang) {
        this.listKeHoachDonHang = listKeHoachDonHang;
    }
}
