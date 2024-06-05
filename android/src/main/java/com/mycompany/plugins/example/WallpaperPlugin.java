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

    // Se crea una instancia de la implementación de Wallpaper
    private Wallpaper implementation = new Wallpaper();

    // Se define el método del plugin para establecer el fondo de pantalla a partir de una imagen en base64
    @PluginMethod
    public void setBase64(PluginCall call) {

        // Se verifica si se proporcionó la imagen en base64
        if (!call.getData().has("base64Image")) {
            call.reject("Must provide an base64 string");
            return;
        } 

        // Se obtiene la imagen en base64
        String base64Image = call.getString("base64Image");

        // Se elimina el prefijo de la imagen en base64
        base64Image = base64Image.replace("data:image/jpeg;base64,", "");

        // Se transforma la imagen en base64 a un Bitmap
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        // Se verifica si la decodificación fue exitosa
        if (decodedByte == null) {
            call.reject("Failed to decode base64 string.");
            return;
        }

        try {
            // Se establece la imagen decodificada como fondo de pantalla llamando al método setWallpaper de la implementación
            implementation.setWallpaper(getContext(), decodedByte);
            // Se crea un objeto JSObject para almacenar el resultado
            JSObject result = new JSObject();
            // Se agrega una propiedad "success" al objeto de resultado y se establece en true
            result.put("success", true);
            // Se resuelve la llamada al plugin con el objeto de resultado
            call.success(result);
        } catch (Exception e) {
            call.reject("Can not define base64 wallpaper.", e);
        }
    }
}
