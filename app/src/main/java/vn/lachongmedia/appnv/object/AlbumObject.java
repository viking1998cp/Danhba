package vn.lachongmedia.appnv.object;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Hoangdh on 24/09/2016.
 * edit tungda
 */
public class AlbumObject implements Serializable {

    private int id;
    @SerializedName("tenkhachhang")
    private String tencuahang;
    @SerializedName("idalbum")
    private int id_server;
    @SerializedName("idnhanvien")
    private int idnv;
    @SerializedName("idkhachhang")
    private int id_khachhang;
    private int type; // 0 là chưa gửi , 1 là đã gửi, 2 là đang gửi; Type = 0 cũng như Type = 2

    @SerializedName("thoigiantao")
    private String thoigiantao;
    @SerializedName("diachi")
    private String diachi;
    @SerializedName("ghichu")
    private String ghichu;
    @SerializedName("kinhdo")
    private double kinhdo;
    @SerializedName("vido")
    private double vido;
    private double accuracy;
    @SerializedName("diachikhachhang")
    private String diachicuahang;
    @SerializedName("idloaialbum")
    private int idloaialbum;
    @SerializedName("loai")
    private int idchucnangalbum;
    @SerializedName("danhsachanh")
    private ArrayList<ImageAlbumObject> listImage;

    public int getIdloaialbum() {
        return idloaialbum;
    }

    public void setIdloaialbum(int idloaialbum) {
        this.idloaialbum = idloaialbum;
    }

    public int getIdchucnangalbum() {
        return idchucnangalbum;
    }

    public void setIdchucnangalbum(int idchucnangalbum) {
        this.idchucnangalbum = idchucnangalbum;
    }

    public String getDiachicuahang() {
        return diachicuahang;
    }

    public void setDiachicuahang(String diachicuahang) {
        this.diachicuahang = diachicuahang;
    }

    public ArrayList<ImageAlbumObject> getListImage() {
        return listImage;
    }

    public void setListImage(ArrayList<ImageAlbumObject> listImage) {
        this.listImage = listImage;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public int getIdnv() {
        return idnv;
    }

    public void setIdnv(int idnv) {
        this.idnv = idnv;
    }

    public double getVido() {
        return vido;
    }

    public void setVido(double vido) {
        this.vido = vido;
    }

    public double getKinhdo() {
        return kinhdo;
    }

    public void setKinhdo(double kinhdo) {
        this.kinhdo = kinhdo;
    }

    public int getType() {
        //type = 0 cũng như type = 2
        /*if (type == 2) {
            return 0;
        }*/
        return type;
    }

    //get type thật sự
    public int getRealType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTencuahang() {
        return tencuahang;
    }

    public void setTencuahang(String tencuahang) {
        this.tencuahang = tencuahang;
    }

    public int getId_server() {
        return id_server;
    }

    public void setId_server(int id_server) {
        this.id_server = id_server;
    }

    public int getId_khachhang() {
        return id_khachhang;
    }

    public void setId_khachhang(int id_khachhang) {
        this.id_khachhang = id_khachhang;
    }

    public String getThoigiantao() {
        return thoigiantao;
    }

    public void setThoigiantao(String thoigiantao) {
        this.thoigiantao = thoigiantao;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
}
