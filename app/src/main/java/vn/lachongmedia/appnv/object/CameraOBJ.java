package vn.lachongmedia.appnv.object;

import java.util.ArrayList;

/**
 * Created by Administrator on 2/14/2017.
 */

public class CameraOBJ extends ArrayList<CameraOBJ> {
    private int chuyencamera = 0;
    private String chonFlash = "auto";

    public int getChuyencamera() {
        return chuyencamera;
    }

    public void setChuyencamera(int chuyencamera) {
        this.chuyencamera = chuyencamera;
    }

    public String getChonFlash() {
        return chonFlash;
    }

    public void setChonFlash(String chonFlash) {
        this.chonFlash = chonFlash;
    }
}
