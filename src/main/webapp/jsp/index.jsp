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
p{margin:0;padding:0;}
label{font-weight:bold;}
input{border:1px #999 solid;border-radius:5px;padding:0 2px;line-height:26px;width:200px;height:26px;margin-left:10px;}
button{border:1px #999 solid;background:#eee;border-radius:5px;padding:0 1em;line-height:26px;height:26px;margin-top:10px;}

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
	<button type="submit">OK</button>
</form>
<c:forEach items="${urls}" var="url">
	<a href="${baseUrl}${url}">${url}</a><br/>
</c:forEach>
<script type="text/javascript">
$('a').filter(function() {
	var url = $(this).attr('href');
	
	if(url.indexOf('{') < 0) return false;
	
	$(this).html($(this).text().replace(/(\{([^\}]+)\})/g, '<span>$1</span>'));
	
	return this;
}).click(function() {
	var url = $(this).attr('href');
	
	var args = url.match(/\{([^\}]+)\}/g);
	var form = $('#input-param');
	
	$('p', form).remove();
	$.each(args, function(k,v) {
		form.prepend('<p><label for="'+v+'">'+v+'</label><input name="'+v+'" type="text" value="" /></p>');
	});
	
	form.show().unbind('submit').submit(function() {
		var u = url;
		$('input', this).each(function() {
			var $this = $(this);
			if($this.val().length == 0) return false;
			
			u = u.replace($this.attr('name'), encodeURIComponent($this.val()));
		});
		
		if(u.indexOf('{') > -1) return false;
		
		location.href = u;
		
		return false;
	});
	
	return false;
});
</script>
</body>
</html>