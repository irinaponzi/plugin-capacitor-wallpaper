// Se importa la función registerPlugin de la biblioteca Capacitor core
import { registerPlugin } from '@capacitor/core';

// Se define la interfaz para el plugin Wallpaper
export interface WallpaperPlugin {
  // Se declara un método setBase64 que toma un objeto con una propiedad base64Image de tipo string
  // y devuelve una promesa que se resuelve sin valor (void)
  setBase64(options: { base64Image: string }): Promise<void>;
}

// Se registra el plugin con Capacitor
// La función registerPlugin toma el nombre del plugin como primer argumento
// y un objeto con la implementación del plugin como segundo argumento
// Es decir, se registra el plugin con el nombre 'Wallpaper' y la implementación definida por la interfaz WallpaperPlugin
const Wallpaper = registerPlugin<WallpaperPlugin>('Wallpaper');

// Se exporta el plugin para que pueda ser importado y utilizado por otros módulos
export default Wallpaper;