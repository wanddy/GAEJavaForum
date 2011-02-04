var Post = new function () {
	this.create () = function create (options) {
		var defaults = {
			post: null,
			topicId: '',
			callback: function (){}
			fail: function (){}
		};
		
		var config = $.extend({}, defaults, options);
		
		if (config.post == null)
			return;
		else {			
			$.ajax {
				type: 'POST',
				url: '/topic/' + config.topicId + '/post',
				data: JSON.stringify(config.post),
				contentType: 'application/json',
				success: config.callback,
				fail: config.fail
			}	
		}
	}
	
	this.get () = function get (options) {
		var defaults = {
			postId: null,
			topicId: null,
			callback: function (){}
			fail: function (){}
		};
		
		var config = $.extend({}, defaults, options);
		
		if (config.topicId == null)
			return;
		else {
			var url = (config.postId) ? '/topic/' + config.topicId + '/post/' + config.postId :
				'/topic/' + config.topicId + '?expanded=true';
			$.ajax {				
				url: url,
				dataType: 'json',				
				success: config.callback,
				fail: config.fail
			}
		}		
	}
	
	this.update () = function update (options) {
		var defaults = {
			post: null,
			topicId: '',
			callback: function (){}
			fail: function (){}
		};
		
		var config = $.extend({}, defaults, options);
		
		if (config.post == null)
			return;
		else {
			$.ajax {
				type: 'PUT',
				url: '/topic/' + config.topicId + '/post/' + config.post.id,
				data: JSON.stringify(config.post),
				contentType: 'application/json',
				success: config.callback,
				fail: config.fail
			}
		}		
	}
}