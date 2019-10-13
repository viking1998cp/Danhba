package vn.lachongmedia.appnv.object;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Hoangdh on 24/09/2016.
 * edit tungda
 */
public class ImageAlbumObject implements Serializable {

    @SerializedName("imagesid")
    private int idimage;
    @SerializedName("idnhanvien")
    private int idnhanvien;
    @SerializedName("idcongty")
    private int idct;
    @SerializedName("idalbum")
    private int idalbum;
    private int idalbum_server;
    private Bitmap img_bitmap;
    @SerializedName("path")
    private String path;
    @SerializedName("path_thumbnail_small")
    private String path_thumbnail_small;
    @SerializedName("path_thumbnail_medium")
    private String path_thumbnail_medium;
    @SerializedName("tendaily")
    private String tendaily;
    private String thoigiangui;
    @SerializedName("diachi")
    private String diachi;
    @SerializedName("thoigianchup")
    private String thoigianchup;
    @SerializedName("ghichu")
    private String ghichu;
    @SerializedName("kinhdo")
    private double kinhdo;
    @SerializedName("vido")
    private double vido;
    private double acc;
    private int type;// 0 la chua gui, 1 la xoa , 2 la gui
    private int isUpload; // 0 la chua up, 1 la dang up , 2 la da up
    private int percent;

    public boolean isLocalFile() {

        //return LocalFile;
        return getType() == 0 || getType() == 1 || getType() == 3;
    }

    private void setLocalFile() {
        boolean localFile = true;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getIsUpload() {
        return isUpload;
    }

    public void setIsUpload() {
        this.isUpload = 1;
    }

    public int getIdalbum_server() {
        return idalbum_server;
    }

    public void setIdalbum_server(int idalbum_server) {
        this.idalbum_server = idalbum_server;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public double getAcc() {
        return acc;
    }

    public void setAcc(double acc) {
        this.acc = acc;
    }

    public int getIdalbum() {
        return idalbum;
    }

    public void setIdalbum(int idalbum) {
        this.idalbum = idalbum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        if (type == 0 || type == 1 || type == 3) {
            setLocalFile();
        }
    }

    public int getIdimage() {
        return idimage;
    }

    public void setIdimage(int idimage) {
        this.idimage = idimage;
    }

    public int getIdnhanvien() {
        return idnhanvien;
    }

    public void setIdnhanvien(int idnhanvien) {
        this.idnhanvien = idnhanvien;
    }

    public void setIdct(int idct) {
        this.idct = idct;
    }

    public void setImg_bitmap(Bitmap img_bitmap) {
        this.img_bitmap = img_bitmap;
    }

    public String getPath() {
        if (path == null) {
            return "";
        }
       /* if(type==2){
        return NetContext.getInstance().getBASE_URL()+path;
        }else {
            return path;
        }*/
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath_thumbnail_small() {
        if(path_thumbnail_small == null){
            return "";
        }
        return path_thumbnail_small;
    }

    public void setPath_thumbnail_small(String path_thumbnail_small) {
        this.path_thumbnail_small = path_thumbnail_small;
    }

    public void setPath_thumbnail_medium(String path_thumbnail_medium) {
        this.path_thumbnail_medium = path_thumbnail_medium;
    }

    public String getTendaily() {
        return tendaily;
    }

    public void setTendaily(String tendaily) {
        this.tendaily = tendaily;
    }

    public String getThoigiangui() {
        return thoigiangui;
    }

    public void setThoigiangui(String thoigiangui) {
        this.thoigiangui = thoigiangui;
    }

    public String getThoigianchup() {
        return thoigianchup;
    }

    public void setThoigianchup(String thoigianchup) {
        this.thoigianchup = thoigianchup;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public double getKinhdo() {
        return kinhdo;
    }

    public void setKinhdo(double kinhdo) {
        this.kinhdo = kinhdo;
    }

    public double getVido() {
        return vido;
    }

    public void setVido(double vido) {
        this.vido = vido;
    }
}


