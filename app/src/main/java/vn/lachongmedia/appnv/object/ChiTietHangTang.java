
package vn.lachongmedia.appnv.object;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by tungda on 7/26/2019.
 */
public class ChiTietHangTang implements Serializable {

    @SerializedName("idchitiethangtang")
    @Expose
    private int idChiTietHangTang;
    @SerializedName("idchitietctkm")
    @Expose
    private int iDChiTietCTKM;
    @SerializedName("idhanghoa")
    @Expose
    private int iDHangHoa;
    @SerializedName("tenhang")
    @Expose
    private String tenHang;
    @SerializedName("mahang")
    @Expose
    private String maHang;
    @SerializedName("giabanbuon")
    @Expose
    private String giaBanBuon;
    @SerializedName("giabanle")
    @Expose
    private String giaBanLe;
    @SerializedName("soluong")
    @Expose
    private int soLuong;
    @SerializedName("tongtien")
    @Expose
    private String tongTien;
}
