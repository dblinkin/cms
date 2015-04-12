var ori = document.location.origin;
var projectName = document.location.href.substring(ori.length + 1);
projectName = projectName.substring(0, projectName.indexOf("/"));


var _ajax = $.ajax;
$.ajax = function (s) {
    var old = s.success;
    
    s.success = function (data, textStatus, jqXHR) {
        if (data && data.retCode && data.retCode == 0x101) {
            alert("timeout");
            window.location.href = "/" + projectName + "/manage/login.html";
            return;
        }
        if (old) {
            old(data, textStatus, jqXHR);
        }
    };
    
    _ajax(s);
}
