package vn.lachongmedia.appnv.network;

/**
 * Created by tungda on 7/16/2019.
 */
public class CommonRespon {
    private boolean status;
    private String msg;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
