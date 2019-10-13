package vn.lachongmedia.appnv.network.Login;

/**
 * Created by tungda on 7/16/2019.
 */
public class VersionApp {
    String Version;// "1.0.3.1",
    String Description;// "- Cải thiện độ ổn định - Bổ sung tính năng mới",
    String EffectFrom;// "2018-10-13T00;//00;//00",
    String EffectTo;// "1900-01-01T00;//00;//00",
    int Status;// 1,
    int ChoPhepDangNhapPhienBanCuHon;// 0

    public int getThongBaoPhienBanMoi() {
        return ThongBaoPhienBanMoi;
    }

    public void setThongBaoPhienBanMoi(int thongBaoPhienBanMoi) {
        ThongBaoPhienBanMoi = thongBaoPhienBanMoi;
    }

    private int ThongBaoPhienBanMoi;
    private   String LinkUpdateAndroid;

    public String getLinkUpdateAndroid() {
        return LinkUpdateAndroid;
    }

    public void setLinkUpdateAndroid(String linkUpdateAndroid) {
        LinkUpdateAndroid = linkUpdateAndroid;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getEffectFrom() {
        return EffectFrom;
    }

    public void setEffectFrom(String effectFrom) {
        EffectFrom = effectFrom;
    }

    public String getEffectTo() {
        return EffectTo;
    }

    public void setEffectTo(String effectTo) {
        EffectTo = effectTo;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getChoPhepDangNhapPhienBanCuHon() {
        return ChoPhepDangNhapPhienBanCuHon;
    }

    public void setChoPhepDangNhapPhienBanCuHon(int choPhepDangNhapPhienBanCuHon) {
        ChoPhepDangNhapPhienBanCuHon = choPhepDangNhapPhienBanCuHon;
    }
}
