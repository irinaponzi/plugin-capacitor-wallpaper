import { Wallpaper } from 'wall';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    Wallpaper.echo({ value: inputValue })
}
