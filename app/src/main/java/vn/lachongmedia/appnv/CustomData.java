package vn.lachongmedia.appnv;

import android.content.res.Resources;
import android.graphics.Color;

import vn.lachongmedia.appnv.R;

import vn.lachongmedia.appnv.object.AlbumObject;

/**
 * Created by tungda on 7/19/2019.
 */
public class CustomData {
    public static  int setColor(String color){
        try {
            return Color.parseColor(color);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static String thoiGianDuKienKeHoach(String vaoDuKien,String raDuKien){
        try {
            if (raDuKien != null) {
              return KsmartSalesApplication.getInstance().getResources().getString(R.string.title_expected_kehoach) + " " + vaoDuKien.substring(11, 16) + " - " + raDuKien.substring(11, 16);

            } else {
               return KsmartSalesApplication.getInstance().getString(R.string.title_expected_kehoach) + " " + vaoDuKien.substring(11, 16);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return KsmartSalesApplication.getInstance().getString(R.string.title_expected_kehoach) ;
    }
    public static String thoiGianThucTe(String vaoThucTe,String raThucTe){

        String thoiGianVaoDiemThucTe =vaoThucTe;
        if (thoiGianVaoDiemThucTe.equals("1900-01-01T00:00:00")) {
            thoiGianVaoDiemThucTe = "";
        }
        if (!thoiGianVaoDiemThucTe.equals("")) {
            thoiGianVaoDiemThucTe = thoiGianVaoDiemThucTe.substring(11, 16);
        }

        String thoiGianRaDiemThucTe = "";
        try {
            thoiGianRaDiemThucTe = raThucTe;
            if (thoiGianRaDiemThucTe.equals("1900-01-01T00:00:00")) {
                thoiGianRaDiemThucTe = "";
            }
            if (!thoiGianRaDiemThucTe.equals("")) {
                thoiGianRaDiemThucTe = thoiGianRaDiemThucTe.substring(11, 16);
            }
        } catch (Exception e) {
            thoiGianRaDiemThucTe = "";
            e.printStackTrace();
        }
        try {
            if (thoiGianRaDiemThucTe.equals("")) {
                return  (KsmartSalesApplication.getInstance().getString(R.string.title_real_kehoach) + " " + thoiGianVaoDiemThucTe);
            } else {
               return KsmartSalesApplication.getInstance().getString(R.string.title_real_kehoach) + " " + thoiGianVaoDiemThucTe + " - " + thoiGianRaDiemThucTe;
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return KsmartSalesApplication.getInstance().getString(R.string.title_real_kehoach) ;
    }
    public static String viecCanLam(String viecCanLam){
        return KsmartSalesApplication.getInstance().getString(R.string.vieccanlamhaicham) + " " + viecCanLam;
    }
    public static String diaChi(String diaChi){
        return KsmartSalesApplication.getInstance().getString(R.string.diachihaicham) + " " + diaChi;
    }
    public static String thoiGianVaoRaDiem(String vaoDiem,String raDiem){
        try {
            if(vaoDiem!=null){
                if(raDiem!=null){
                    return ("Vào/Ra Điểm:") + " " + vaoDiem+"-"+raDiem;
                }else {
                    return ("Vào/Ra Điểm:") + " " + vaoDiem;
                }
            }else {
                return ("Vào/Ra Điểm:");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ("Vào/Ra Điểm:");
    }

    /**
     *
     * @param albumObject
     * Để hiển thị tên cửa hàng trong thong tin Album của danh sách album
     * @return
     */
    public static String thongTinAlbum(AlbumObject albumObject){
        if(albumObject.getTencuahang()!=null&&!albumObject.getTencuahang().equals("null")&&!albumObject.getTencuahang().equals("")){
        return albumObject.getTencuahang();
        }else {
            return  KsmartSalesApplication.getInstance().getString(R.string.chupanhtaidiemtudohaicham);
        }
    }
    public static String tenQuanLy(String tenQuanLy ){
        try {
            String name = tenQuanLy.trim();
            String name_simplify = String.valueOf(name.charAt(0)).toUpperCase();
            for (int i = 0; i < name.length(); i++) {
                if (String.valueOf(name.charAt(i)).compareTo(" ") == 0) {

                    if (i + 1 < name.length()) {
                        name_simplify = name_simplify + String.valueOf(name.charAt(i + 1)).toUpperCase();
                    }
                    break;
                }
            }
            return name_simplify;

        } catch (Exception e) {
            // e.printStackTrace();
        }
        return "";
    }
    public static String hienThiGia(String giabuon,String giale){
        return giabuon+" - "+giale;
    }

    public static String convertToString(int temp){
        return String.valueOf(temp);
    }
    public static String convertToString(double temp){
        return String.valueOf(temp);
    }
}
