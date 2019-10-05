<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文件上传</title>
<style type="text/css">
img{max-width:120px;max-height:120px;margin:2px;border:1px black solid;vertical-align:middle;}
</style>
</head>
<body>
	<form action="${baseUrl}/upload" enctype="multipart/form-data" method="POST">
		<p><label for="file">选择文件：</label><input type="file" name="file" accept=".png,.gif,.jpg,.jpeg,.bmp,.svg" /></p>
		<p><button type="submit">上传</button></p>
		
		<#list files as f>
			<img src="${baseUrl}/upload/${f}" />
		</#list>
	</form>
</body>
</html>