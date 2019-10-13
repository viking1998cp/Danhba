package vn.lachongmedia.appnv.network.tinnhan;

import vn.lachongmedia.appnv.network.CommonRespon;
import vn.lachongmedia.appnv.object.TinNhanGroup;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by tungda on 7/25/2019.
 */
public class TinNhanConversionRespon extends CommonRespon {
    @SerializedName("data")
    private ArrayList<TinNhanGroup> listTinNhanGroup;

    public ArrayList<TinNhanGroup> getListTinNhanGroup() {
        return listTinNhanGroup;
    }

    public void setListTinNhanGroup(ArrayList<TinNhanGroup> listTinNhanGroup) {
        this.listTinNhanGroup = listTinNhanGroup;
    }
}
