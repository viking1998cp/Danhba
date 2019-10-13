package vn.lachongmedia.appnv.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tungda on 7/19/2019.
 */
public class KeHoachDonHangObject extends KeHoachOBJ {
    @SerializedName("TongTien")
    private double tongTien;
    @SerializedName("NgayLap")
    private String ngayLap;

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }
}
