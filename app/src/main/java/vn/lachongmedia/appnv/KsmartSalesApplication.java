package vn.lachongmedia.appnv;

import android.app.Application;

import com.google.gson.Gson;

/**
 * Created by tungda on 7/16/2019.
 */
public class KsmartSalesApplication extends Application {
    private static KsmartSalesApplication instance;
    private Gson mGSon;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        mGSon=new Gson();
    }
    public static KsmartSalesApplication getInstance(){
        return instance;
    }
    public Gson getGSon() {
        return mGSon;
    }
}
