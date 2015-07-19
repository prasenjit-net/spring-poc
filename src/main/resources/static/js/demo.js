'use strict';

$(document).ready(function() {

	$(document).on("submit", "form", function(event) {
		var target = $(this).attr("data-demo-target");
		if (target) {
			event.preventDefault();
			var method = $(this).attr("method");
			var action = $(this).attr("action");
			var data = $(this).serialize();

			var request = $.ajax({
				url : action,
				method : method,
				data : data,
				dataType : "html"
			});

			request.done(function(msg) {
				$(target).replaceWith(msg);
			});
		}
	});

	$(document).on("click", "a.ajax", function(event) {
		var target = $(this).attr("data-demo-target");
		if (target) {
			event.preventDefault();
			var href = $(this).attr("href");
			$(target).load(href);
		} else {
			var targetForm = $(this).attr("data-demo-form");
			if (targetForm) {
				$(targetForm).submit();
			}
		}
	});

	$(document).on("click", "button.ajax", function(event) {
		var targetForm = $(this).attr("data-demo-form");
		if (targetForm) {
			var event = jQuery.Event("submit");
			$(targetForm).submit();// trigger(event);
		}
	});

});