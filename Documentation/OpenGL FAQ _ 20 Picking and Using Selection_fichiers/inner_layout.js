window.onload = function () {
    if (!NiftyCheck())
        return;
    Rounded("div#header-frame", "all", "#004276", "#fff", "smooth");
    Rounded("div#footer", "all", "transparent", "#fff");
    Rounded("div#main", "top", "#004276", "#FFF", "smooth");
    Rounded("div#main", "bottom", "transparent", "#FFF");
    Rounded("div#right_column", "top", "#004276", "#C7D1D2");
    Rounded("div#right_column", "bottom", "transparent", "#678285");

}