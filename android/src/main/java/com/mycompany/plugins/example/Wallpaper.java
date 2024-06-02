package com.mycompany.plugins.example;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;

import java.io.IOException;

public class Wallpaper {

    void setWallpaper(Context context, Bitmap tempBitMap) throws IOException {
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        int height = Resources.getSystem().getDisplayMetrics().heightPixels;

        Bitmap bitmap = Bitmap.createScaledBitmap(tempBitMap, width, height, true);

        WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
        wallpaperManager.setWallpaperOffsetSteps(1, 1);
        wallpaperManager.suggestDesiredDimensions(width, height);

        // Define wallpaper
        wallpaperManager.setBitmap(bitmap);
    }
}
