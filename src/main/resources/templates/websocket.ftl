<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WebSocket demo</title>
<script type="text/javascript" src="${baseUrl}/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${baseUrl}/js/sockjs.min.js"></script>
<script type="text/javascript" src="${baseUrl}/js/stomp.min.js"></script>
</head>
<body>
	<label for="topicGroup">Group: </label><input id="topicGroup" type="text" value="default" style="width:100px;" />
	<button id="connect" onclick="connect();">Connect</button>
	<button id="disconnect" onclick="disconnect();">Disconnect</button>
	<button id="serverTime" onclick="getServerTime();">Get Server Time</button>
	<span>Name: <span id="authorName"></span></span>
	<div style="margin:10px 0;height:1px;background:gray;"></div>
	<div id="messageWrap"></div>
	<script type="text/javascript">
		var stompClient = null;
		var $messageWrap = $('#messageWrap');
		var $topicGroup = $('#topicGroup');
		var $authorName = $('#authorName');
		var randomName = null;
		$(function() {
			setConnect(false);
		});
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