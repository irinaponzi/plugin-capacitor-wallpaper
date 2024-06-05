package com.mycompany.plugins.example;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;

import java.io.IOException;

public class Wallpaper {

    // Método para establecer el fondo de pantalla
    void setWallpaper(Context context, Bitmap tempBitMap) throws IOException {
        
        // Se obtienen las dimensiones de la pantalla
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        int height = Resources.getSystem().getDisplayMetrics().heightPixels;

        // Se crea un bitmap escalado para que se ajuste a las dimensiones de la pantalla
        Bitmap bitmap = Bitmap.createScaledBitmap(tempBitMap, width, height, true);

        // Se obtiene una instancia del administrador de fondos de pantalla
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
        // Se establecen los pasos de desplazamiento del fondo de pantalla
        wallpaperManager.setWallpaperOffsetSteps(1, 1);
        // Se establecen las dimensiones deseadas del fondo de pantalla
        wallpaperManager.suggestDesiredDimensions(width, height);
        // Se define el bitmap como fondo de pantalla
        wallpaperManager.setBitmap(bitmap);
    }
}
