package vn.lachongmedia.appnv.network.Login;

import vn.lachongmedia.appnv.network.CommonRespon;
import vn.lachongmedia.appnv.object.CongTy;

/**
 * Created by tungda on 7/17/2019.
 */
public class CongTyRespon extends CommonRespon {
    private CongTy congTy;

    public CongTy getCongTy() {
        return congTy;
    }

    public void setCongTy(CongTy congTy) {
        this.congTy = congTy;
    }
}
