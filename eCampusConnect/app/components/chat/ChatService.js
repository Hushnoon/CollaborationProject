var ChatModule=angular.module('ChatModule', []); 
ChatModule.service('ChatService',function($q, $timeout,RESTURI) {

	var service = {}, listener = $q.defer(), socket = {
		client: null,
		stomp: null
	}, messageIds = [];

	service.RECONNECT_TIMEOUT = 30000;
	service.SOCKET_URL = RESTURI+"/chat";
	service.CHAT_TOPIC = "/topic/message";
	service.CHAT_BROKER = "/app/chat";

	service.receive = function() {
		console.log('re service');
		return listener.promise;
	};

	service.send = function(message) {
		console.log("Send service");
		console.log(message);
		var id = Math.floor(Math.random() * 1000000);
		socket.stomp.send(service.CHAT_BROKER, {
			priority: 9
      }, JSON.stringify(/*{
        message: message,
        id: id
    }*/message));
		messageIds.push(id);
	};

	var reconnect = function() {
		$timeout(function() {
			initialize();
		}, this.RECONNECT_TIMEOUT);
	};

	var getMessage = function(data) {
		console.log('getMessage');
		var message = JSON.parse(data), out = {};
		out.message = message.message;
		out.userid=message.userid;
		out.time = new Date(message.time);
		if (_.contains(messageIds, message.id)) {
			out.self = true;
			messageIds = _.remove(messageIds, message.id);
		}
		return out;
	};

	var startListener = function() {
		console.log('startListener');
		socket.stomp.subscribe(service.CHAT_TOPIC, function(data) {

			console.log('data from service:'+data.body);
			listener.notify(getMessage(data.body));
		});
	};

	var initialize = function() {
		console.log('initialize');
		socket.client = new SockJS(service.SOCKET_URL);
		socket.stomp = Stomp.over(socket.client);
		socket.stomp.connect({}, startListener);
		socket.stomp.onclose = reconnect;
	};

	initialize();
	return service;
});