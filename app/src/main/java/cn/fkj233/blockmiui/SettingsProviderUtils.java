package cn.fkj233.blockmiui;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.Settings;

import org.jetbrains.annotations.Nullable;

public class SettingsProviderUtils {

    public SettingsProviderUtils(){

    }

    public static String get(Context context, String skey){

        ContentResolver cr = new ContentResolver(context) {
            @Nullable
            public String[] getStreamTypes(Uri url,String mimeTypeFilter) {
                return super.getStreamTypes(url, mimeTypeFilter);
            }
        };
       return Settings.Global.getString(cr,skey);
    }

}
