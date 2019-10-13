package vn.lachongmedia.appnv.network.checkin;

import vn.lachongmedia.appnv.network.CommonRespon;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tungda on 7/21/2019.
 */
public class CheckInResponse extends CommonRespon {
    @SerializedName("data")
    private VaoDiem vaoDiem;

    public VaoDiem getVaoDiem() {
        return vaoDiem;
    }

    public void setVaoDiem(VaoDiem vaoDiem) {
        this.vaoDiem = vaoDiem;
    }

    public class VaoDiem{
        @SerializedName("idcheckin")
        private int idCheckIn;

        public int getIdCheckIn() {
            return idCheckIn;
        }

        public void setIdCheckIn(int idCheckIn) {
            this.idCheckIn = idCheckIn;
        }
    }
}
