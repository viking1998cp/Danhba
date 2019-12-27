package vn.lachongmedia.appnv.network.khachhang;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import vn.lachongmedia.appnv.network.CommonRespon;
import vn.lachongmedia.appnv.object.Khachhang.Data;

public class DanhSachKhachHangRespon extends CommonRespon {
    @SerializedName("data")
    private ArrayList<Data> data;

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }
}
