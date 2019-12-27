package vn.lachongmedia.appnv.network.ghichu;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import vn.lachongmedia.appnv.network.CommonRespon;
import vn.lachongmedia.appnv.object.GhiChu.DanhSachGhiChu;

public class DanhSachTieuChiRespon extends CommonRespon {
    @SerializedName("data")
    private ArrayList<DanhSachGhiChu> themGhiChu;

    public ArrayList<DanhSachGhiChu> getThemGhiChu() {
        return themGhiChu;
    }

    public void setThemGhiChu(ArrayList<DanhSachGhiChu> themGhiChu) {
        this.themGhiChu = themGhiChu;
    }
}
