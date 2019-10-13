package vn.lachongmedia.appnv.network.phanhoi;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import vn.lachongmedia.appnv.network.CommonRespon;
import vn.lachongmedia.appnv.object.PhanHoi.DanhSachPhanHoi;
import vn.lachongmedia.appnv.object.PhanHoi.LichSuNhom;

public class DanhSachThemPhanHoiRespon extends CommonRespon {
    @SerializedName("data")
    private ArrayList<DanhSachPhanHoi> themPhanHoi;

    public ArrayList<DanhSachPhanHoi> getThemPhanHoi() {
        return themPhanHoi;
    }

    public void setThemPhanHoi(ArrayList<DanhSachPhanHoi> phanHoi) {
        this.themPhanHoi = phanHoi;
    }
}
