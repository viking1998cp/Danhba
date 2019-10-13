package vn.lachongmedia.appnv.network.phanhoi;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.network.CommonRespon;
import vn.lachongmedia.appnv.object.PhanHoi.LichSuNhom;
import vn.lachongmedia.appnv.object.PhanHoi.PhanHoi;

public class DanhSachPhanHoiRespon extends CommonRespon {
    @SerializedName("lichsunhom")
    private ArrayList<LichSuNhom> phanHoi;

    public ArrayList<LichSuNhom> getPhanHoi() {
        return phanHoi;
    }

    public void setPhanHoi(ArrayList<LichSuNhom> phanHoi) {
        this.phanHoi = phanHoi;
    }
}
