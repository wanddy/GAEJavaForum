$(document).ready(function(){
	$.ajax({
		url: '/rest/channel',
		dataType: 'json',
		success: function(data, status, req) {
			var channel = new goog.appengine.Channel(data.token);

			var socket = channel.open();
			
			socket.onopen = function () {
				alert('Socket opened...');
			};
			
			socket.onmessage = function(message) {
				if (message.data == 'getPosts') {
					if (getPosts)
						getPosts();
				}				
				else if (message.data == 'getTopics') {
					if (getTopics)
						getTopics();
				}
				else if (message.data.toLowerCase().indexOf('lurker') > -1) {
					alert(message.data);
				}
				
			};
			
			socket.onerror(error)  {
				alert('The socket experienced an error.  You should refresh your page.');
			}
			
			socket.onclose() {
				alert('The socket closed unexpectedly.  You should refresh your page.');
			}
		}		
	});
});