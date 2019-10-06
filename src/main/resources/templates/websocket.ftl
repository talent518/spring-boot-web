<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WebSocket demo</title>
<script type="text/javascript" src="${baseUrl}/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${baseUrl}/js/sockjs.min.js"></script>
<script type="text/javascript" src="${baseUrl}/js/stomp.min.js"></script>
</head>
<body style="margin:0;padding:0;">
	<div style="position:fixed;left:0px;top:0px;width:100%;height:40px;line-height:40px;border-bottom:1px gray solid;background:white;padding:0 10px;">
		<label for="topicGroup">Group: </label><input id="topicGroup" type="text" value="default" style="width:100px;" />
		<button id="connect" onclick="connect();">Connect</button>
		<button id="disconnect" onclick="disconnect();">Disconnect</button>
		<button id="serverTime" onclick="getServerTime();">Get Server Time</button>
		<span>Name: <span id="authorName"></span></span>
		<label><input type="checkbox" onclick="if($(this).is(':checked')){setAutoScrollEvent();}else{clearAutoScrollEvent()}" checked="checked" /> Auto Scroll</label>
	</div>
	<div id="messageWrap" style="margin-top:40px;padding:10px;"></div>
	<script type="text/javascript">
		var stompClient = null;
		var $messageWrap = $('#messageWrap');
		var $topicGroup = $('#topicGroup');
		var $authorName = $('#authorName');
		var randomName = null;
		var timer = false;
		$(function() {
			setConnect(false);
		});
		function clearAutoScrollEvent() {
			clearInterval(timer);
			timer = false;
		}
		function setAutoScrollEvent() {
			clearInterval(timer);
			timer = setInterval(function() {
				$(window).scrollTop(document.body.scrollHeight);
			}, 20);
		}
		
		setAutoScrollEvent();
		
		function setConnect(connected) {
			$('#connect').attr('disabled', connected);
			$('#disconnect').attr('disabled', !connected);
			if(connected)
				$authorName.parent().show();
			else
				$authorName.parent().hide();
		}
		function connect() {
			var socket = new SockJS('/endpoint');
			stompClient = Stomp.over(socket);
			stompClient.debug = function(str) {
				$messageWrap.append('<pre>' + $.trim(str.replace('\u0000','')) + '</pre>');
			}
			stompClient.connect({}, function(frame) {
				setConnect(true);
				randomName = 'N' + parseInt(Math.random() * 100000);
				$authorName.text(randomName);
				stompClient.subscribe('/topic/' + $topicGroup.val(), function(response) {
					var json = $.parseJSON(response.body);
					$messageWrap.append('<p><b>' + json.name + '</b> &nbsp; <span>' + json.message + '</span></p>');
				});
			});
		}
		function disconnect() {
			if(stompClient) {
				stompClient.disconnect();
				stompClient = false;
			}
			setConnect(false);
		}
		function getServerTime() {
			var message = "The server time is ";
			stompClient.send('/getServerTime', {}, JSON.stringify({group:$topicGroup.val(),name:randomName,message:message}));
		}
	</script>
</body>
</html>