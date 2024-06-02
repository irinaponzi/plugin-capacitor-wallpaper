package com.mycompany.plugins.example;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;

@CapacitorPlugin(name = "Wallpaper", permissions = {
    @Permission(strings = {Manifest.permission.SET_WALLPAPER_HINTS}, alias = "setWallpaperHints"),
    @Permission(strings = {Manifest.permission.SET_WALLPAPER}, alias = "setWallpaper")
})
public class WallpaperPlugin extends Plugin {

    private Wallpaper implementation = new Wallpaper();

    @PluginMethod
    public void setBase64(PluginCall call) {

        if (!call.getData().has("base64Image")) {
            call.reject("Must provide an base64 string");
            return;
        } 

        String base64Image = call.getString("base64Image");

        // Remove base64 prefix
        base64Image = base64Image.replace("data:image/jpeg;base64,", "");

        // Transform base64 to Bitmap
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        if (decodedByte == null) {
            call.reject("Failed to decode base64 string.");
            return;
        }

        try {
            implementation.setWallpaper(getContext(), decodedByte);
            JSObject result = new JSObject();
            result.put("success", true);
            call.success(result);
        } catch (Exception e) {
            call.reject("Can not define base64 wallpaper.", e);
        }
    }
}
