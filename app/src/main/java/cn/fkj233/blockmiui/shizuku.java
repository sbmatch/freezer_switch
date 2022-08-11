package cn.fkj233.blockmiui;

import android.content.pm.PackageManager;
import android.util.Log;

import rikka.shizuku.Shizuku;

public class shizuku {

    public static int grantResult_global = 5201314;

    public shizuku() {
    }

    public static int add_req_listener(){
        Shizuku.addRequestPermissionResultListener((requestCode, grantResult) -> grantResult_global = grantResult);
        return grantResult_global;
    }

    public static void apply_shizuku(int RequestCode){
        Shizuku.requestPermission(RequestCode);
    }

    public static int check_permission() {
        try {
            if (Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED) {
                return 0;
            }
        } catch (ClassCastException e) {
            Log.e("ClassCastException", e.getMessage());
        }
        return 1;
    }

}
