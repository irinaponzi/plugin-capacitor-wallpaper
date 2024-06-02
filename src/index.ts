import { registerPlugin } from '@capacitor/core';

export interface WallpaperPlugin {
  setBase64(options: { base64Image: string }): Promise<void>;
}

const Wallpaper = registerPlugin<WallpaperPlugin>('Wallpaper');

export default Wallpaper;