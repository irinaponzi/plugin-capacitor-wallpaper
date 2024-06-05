import { Wallpaper } from 'wallpaper-project';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    Wallpaper.echo({ value: inputValue })
}
