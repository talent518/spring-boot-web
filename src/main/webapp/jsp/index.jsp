<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>spring-boot-web(jsp模板引擎)</title>
<script type="text/javascript" src="${baseUrl}/js/jquery-3.4.1.min.js"></script>
<style type="text/css">
*{font-size:16px;line-height:26px;}
a{display:inline-block;text-decoration:none;color:blue;}
a>span{display:inline-block;vertical-align:middle;color:black;-webkit-animation: hue 3s infinite linear;}
a:hover{color:#F20;}
form{position:fixed;top:10px;right:10px;display:none;line-height:26px;text-align:center;}
p{margin:0;padding:5px;text-align:right;}
label{font-weight:bold;}
input{border:1px #999 solid;border-radius:5px;padding:0 2px;line-height:26px;width:200px;height:26px;margin-left:10px;}
button{border:1px #999 solid;background:#eee;border-radius:5px;padding:0 1em;line-height:26px;height:26px;}

@-webkit-keyframes hue {
    0%{
        color: #0000ff;
        font-size: 16px;
    }
    50% {
        color: #ff2200;
        font-size: 12px;
    }
    100%{
        color: #0000ff;
        font-size: 16px;
    }
}
</style>
</head>
<body>
<form id="input-param" action="." method="get">
	<button type="submit" method="get">GET</button>
	<button type="button" method="post">POST</button>
	<button type="button" method="put">PUT</button>
	<button type="button" method="delete">DELETE</button>
</form>
<c:forEach items="${urls}" var="url">
	<a href="${baseUrl}${url}" params="${paramMaps.get(url)}">${url}</a><br/>
</c:forEach>
<script type="text/javascript">
$('a').filter(function() {
	var url = $(this).attr('href');
	
	if(url.indexOf('{') < 0) return $(this).is('[params!=""]');
	
	$(this).html($(this).text().replace(/(\{([^\}]+)\})/g, '<span>$1</span>'));
	
	return this;
}).click(function() {
	var url = $(this).attr('href');
	var params = $(this).attr('params');
	
	var args = url.match(/\{([^\}]+)\}/g);
	var form = $('#input-param');
	var btn = $('button[type=submit]', form);
	$('p', form).remove();
	
	$.each(args, function(k,v) {
		$('<p><label for="'+v+'">'+v+'</label><input name="'+v+'" type="text" value="" /></p>').insertBefore(btn);
	});
	
	params.replace(/([^\:]*)\:([^\;]*)[\;]?/g, function($0,$1,$2) {
		$('<p><label for="'+$1+'">'+$1+'</label><input class="param" name="'+$1+'" type="text" value="'+$2+'" /></p>').insertBefore(btn);
		return $0;
	});
	
	form.find('button[method]').unbind('click').click(function() {
		form.attr('method', $(this).attr('method'));
		
		form.submit();
	});
	form.show().unbind('submit').submit(function() {
		var a = {};
		var u = url;
		var b = false;
		var $input = $('input', this).each(function() {
			var $this = $(this);
			var k = $this.attr('name');
			var v = $this.val();
			if(v.length == 0) {
				b = true;
				return false;
			}
			
			if($this.is('.param'))
				a[k] = v;
			else
				u = u.replace(k, encodeURIComponent(v));
		});
		
		if(b || u.indexOf('{') > -1) return false;
		
		$input.filter(':not(.param)').remove();
		$(this).attr('action', u);
	});
	
	return false;
});
</script>
</body>
</html>