var socket, sercret;
var sended = 0;
var player = null;
var pk_player_me_id = "";
var pk_player_target_id = "";
var auth_url = "http://localhost:18080";

function initSocket(rs) {
	sended = 0;
	sercret = rs.result.sercret;
	if (!window.WebSocket) {
		window.WebSocket = window.MozWebSocket;
	}
	if (window.WebSocket) {
		socket = $.websocket(rs.result.address, {
			open : function(event) {
				$("#responseText").val("");
				socket.send("init", {
					sercret : sercret
				});
			},
			close : function(event) {
				sended = 0;
				player = null;
				$("#login_area").show();
				$("#regist_area").show();
				$("#msg_area").hide();
				$("#pk_area").hide();
				$("#wait_info").hide();
				$("#responseText").val("");
			},
			events : {
				"test.test" : function(event) {
					$("#responseText").val($("#responseText").val() + "\n" + event.result);
				},
				init : function(event) {
					$("#wait_info").hide();
					player = event.result;
					initAfterLogin(player);
				},
				duel : function(event) {
					var msg = event.result;
					if (msg instanceof Object) {
						if (msg.action == "init") {
							pk_player_me_id = msg.player_me.id;
							pk_player_target_id = msg.player_target.id;
							$("#player_me").attr("player_id", pk_player_me_id);
							$("#player_target").attr("player_id", pk_player_target_id);
							initPkPlayer(msg);
							$("#msg_area").hide();
							$("#pk_area").show();
							$("#pk_responseText").val("战斗开始\n");
						} else if (msg.action == "end") {
							initPkPlayer(msg);
							$("#pk_responseText").val($("#pk_responseText").val() + msg.msg + "\n");
							alert(msg.msg);
							$("#player_me").attr("player_id", "");
							$("#player_target").attr("player_id", "");
							$("#pk_area").hide();
							$("#msg_area").show();
						} else if (msg.action == "msg") {
							initPkPlayer(msg);
							$("#pk_responseText").val($("#pk_responseText").val() + msg.msg + "\n");
						} else {
							initPlayer(msg, "player_info");
						}
					}
				},
				sendmsg : function(event) {
					var rs = event.result;
					var msg = rs.msg;
					var sender = rs.player;
					if (sender.id == player.id) {
						$("#responseText").val($("#responseText").val() + "我：" + msg + "\n");
					} else {
						$("#responseText").val($("#responseText").val() + sender.name + "：" + msg + "\n");
					}
				},
				onlineuser : function(event) {
					var selector = $("#online_user");
					var list = event.result;
					// 先清空第二个
					selector.empty();
					// 实际的应用中，这里的option一般都是用循环生成多个了
					for ( var i in list) {
						if (player.id != list[i].id) {
							var option = $("<option>").val(list[i].id).text(list[i].name);
							selector.append(option);
						}
					}
				},
				_wait_ : function(event) {
					$("#login_area").hide();
					$("#regist_area").hide();
					var index = event.result;
					if(index > 0){
						$("#wait_info").show();
						$("#wait_no").html(index);
					}else if(index == 0){
						$("#wait_info").hide();
						socket.send("init","");
					}else{
						alert("系统错误");
					}
				}
			}
		});
	} else {
		alert("Your browser does not support Web Socket.");
	}
}

function initAfterLogin(player){
	initPlayer(player, "player_info");
	socket.send("onlineuser", "");
	$("#login_area").hide();
	$("#regist_area").hide();
	$("#msg_area").show();
}

function initPkPlayer(msg) {
	if (msg.player_me) {
		if (msg.player_me.id == $("#player_me").attr("player_id")) {
			initPlayer(msg.player_me, "player_me");
			initPlayer(msg.player_target, "player_target");
		} else {
			initPlayer(msg.player_target, "player_me");
			initPlayer(msg.player_me, "player_target");
		}
	}
}

function initPlayer(player, id) {
	var actions = "";
	for ( var i in player.actions) {
		var action = player.actions[i];
		actions += action.name + " (" + action.level + "级)" + action.minDamage + "-" + action.maxDamage + "<br/>";
	}
	$("#" + id).html(player.name);
	$("#" + id + "_actions").html(actions);
	$("#" + id + "_level").html(player.level);
	$("#" + id + "_experience").html(player.experience);
	$("#" + id + "_totalHp").html(player.hp);
}

function send(action, message) {
	if (!window.WebSocket) {
		return;
	}
	if (!socket) {
		alert("websocket 还没有连接");
		return;
	}
	if (socket.readyState == WebSocket.OPEN) {
		socket.send(action, {
			msg : message
		});
	} else {
		alert("The socket is not open.");
	}
}

function closeSocket() {
	if (!window.WebSocket) {
		return;
	}
	if (!socket) {
		alert("websocket 还没有连接");
		return;
	}
	if (socket.readyState == WebSocket.OPEN) {
		socket.close();
	} else {
		alert("The socket is not open.");
	}
}

$("#logout").click(function() {
	closeSocket();
});

$("#login").click(function() {
	$.post(auth_url + "/login", $("#loginform").serialize(), function(rs) {
		if (rs.code == 0) {
			initSocket(rs);
		} else {
			alert(rs.result);
		}
	}, "json");
});

$("#regist").click(function() {
	$.post(auth_url + "/regist", $("#registform").serialize(), function(data) {
		alert(data.result);
	}, 'json');
});

$("#duel").click(function() {
	$("#pk_responseText").val("");
	var selector = $("#online_user");
	var uid = selector.val();
	socket.send("duel", {
		uid : uid
	});
});