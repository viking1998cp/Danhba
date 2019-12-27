package vn.lachongmedia.appnv.network.ghichu;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import vn.lachongmedia.appnv.network.CommonRespon;
import vn.lachongmedia.appnv.object.GhiChu.LichSuNhom;

public class DanhSachGhiChuRespon extends CommonRespon {
    @SerializedName("lichsunhom")
    private ArrayList<LichSuNhom> ghiChu;

    public ArrayList<LichSuNhom> getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(ArrayList<LichSuNhom> phanHoi) {
        this.ghiChu = phanHoi;
    }
}
