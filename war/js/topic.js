var Topic = new function () {
	this.create () = function create (options) {
		var defaults = {
			topic: null,		
			callback: function (){}
			fail: function (){}
		};
		
		var config = $.extend({}, defaults, options);
		
		if (config.topic == null)
			return;
		else {			
			$.ajax {
				type: 'POST',
				url: '/topic',
				data: JSON.stringify(config.topic),
				contentType: 'application/json',
				success: config.callback,
				fail: config.fail
			}	
		}
	}
	
	this.get () = function get (options) {
		var defaults = {	
			topicId: null,
			callback: function (){}
			fail: function (){}
		};
		
		var config = $.extend({}, defaults, options);
				
		var url = (config.topicId) ? '/topic/' + config.topicId : '/topic/';
		
		if (config.expanded)
			url += '?expanded=true';
			
		$.ajax {				
			url: url,
			dataType: 'json',				
			success: config.callback,
			fail: config.fail
		}
				
	}
}