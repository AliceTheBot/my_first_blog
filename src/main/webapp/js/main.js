//随机古诗词
var xhr = new XMLHttpRequest();
xhr.open('get', 'https://v1.jinrishici.com/all.txt');
xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
        var gushici = document.getElementById('gushici');
        gushici.innerText = xhr.responseText;
    }
};
xhr.send();

//随机配色
var color=["#525288","#c04851","#5a191b","#4e2a40","#74759b","#2177b8","#2c9678","#229453","#b78b26","#cd6227","#a7535a","#894e54","#7a7374","#61649f","#134857","#12aa9c","#e2c17c","#481e1c"];
var n=Math.floor(Math.random()*color.length);
$("header").css("background-color",color[n]);
$("i").css("color",color[n]);
