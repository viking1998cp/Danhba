package vn.lachongmedia.appnv.evenbus;

/**
 * Created by tungda on 7/24/2019.
 */
public class GuiAnhEvent {
   private int state;
    private int idAlbum;

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public GuiAnhEvent(int state,int idAlbum){
        this.state=state;
        this.idAlbum=idAlbum;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
