package vn.lachongmedia.appnv.network.Login;

import vn.lachongmedia.appnv.network.CommonRespon;

/**
 * Created by tungda on 7/16/2019.
 */
public class VersionAppRespon extends CommonRespon {

    private VersionApp data;
    public VersionApp getData() {
        return data;
    }

    public void setData(VersionApp data) {
        this.data = data;
    }
}
