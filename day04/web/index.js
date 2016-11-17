$(function() {
	//jQuery application
	var socket = null;

	$("#connectBtn").on("click", function() {
		socket = new WebSocket("ws://localhost:8080/day04/chat/" + $("#rmName").val());
		socket.onopen = function() {
			$("#chatarea").val("Connected\n" + $("#chatarea").val());
		}
		socket.onmessage = function(evt) {
			var msg = JSON.parse(evt.data);
			$("#chatarea").val("[" + msg.timestamp + "] " + msg.message + "\n" + 
					$("#chatarea").val());
		}

	})

	$("#sendBtn").on("click", function() {
		var msg = $("#msg").val();
		socket.send(msg);
		$("#msg").val("");
	});


});

