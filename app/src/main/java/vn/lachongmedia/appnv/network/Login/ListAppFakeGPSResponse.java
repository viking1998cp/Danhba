package vn.lachongmedia.appnv.network.Login;

import vn.lachongmedia.appnv.network.CommonRespon;

import java.util.ArrayList;

/**
 * Created by tungda on 7/17/2019.
 */
public class ListAppFakeGPSResponse extends CommonRespon {
    private ArrayList<AppFakeGPS>listAppFakeGPS;

    public ArrayList<AppFakeGPS> getListAppFakeGPS() {
        return listAppFakeGPS;
    }

    public void setListAppFakeGPS(ArrayList<AppFakeGPS> listAppFakeGPS) {
        this.listAppFakeGPS = listAppFakeGPS;
    }
}
