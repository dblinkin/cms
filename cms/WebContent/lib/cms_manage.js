var ori = document.location.origin;
var projectName = document.location.href.substring(ori.length + 1);
projectName = projectName.substring(0, projectName.indexOf("/"));


var _ajax = $.ajax;
$.ajax = function (s) {
    var old = s.success;
    
    s.success = function (data, textStatus, jqXHR) {
        if (data && data.retCode) {
        	if(data.retCode == 0x101){
        		BUI.Message.Alert('您长时间未操作，请重新登录','error');
        		
        		var w = window;
        		while(w.parent != w){
        			w = w.parent;
        		}
        		
                w.location.href = "/manage/login.html";
                return;
        	}else if(data.retCode != 0){
        		BUI.Message.Alert(data.retMsg,'error');
        		return;
        	}
        	
        }
        if (old) {
            old(data, textStatus, jqXHR);
        }
    };
    
    if(!s.type){
    	$.extend(s, {type : "post"})    	
    }
    if(!s.dataType){
    	$.extend(s, {dataType : "json"})
    }
    _ajax(s);
}

/**
 * 取得url链接中的参数
 * @param argName
 * @returns
 */
function getHrefParam(argName){
	var url = document.location.href;
	var i = url.indexOf("?");
	if( i == -1)
		return null;
	var attr = url.substring(i + 1).split("&");
	for(var j = 0; j < attr.length; i++){
		var ai = attr[j].indexOf(argName + "=");
		if(ai != -1){
			return attr[j].replace(argName + "=","");
		}
	}
	return null;
}