package vn.lachongmedia.appnv.network.trangchu;

import vn.lachongmedia.appnv.network.CommonRespon;
import vn.lachongmedia.appnv.object.LichHenObject;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by tungda on 7/19/2019.
 */
public class DanhSachlichHenRespon extends CommonRespon {
    @SerializedName("data")
    private ArrayList<LichHenObject> listLichHen;

    public ArrayList<LichHenObject> getListLichHen() {
        return listLichHen;
    }

    public void setListLichHen(ArrayList<LichHenObject> listLichHen) {
        this.listLichHen = listLichHen;
    }
}
