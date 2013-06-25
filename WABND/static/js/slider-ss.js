$(document).ready(function(){
	//alert(2);
	$(".slider").slider({
		min: 0,
		max:100,
		change: function(event,ui){
			
			var att = $(this).prev().first().text().trim();
			var serv = $(this).parent().parent().prev().first().text().trim();
			//alert(ui.value);
			$.get('changecontrollerservice', {service: serv, attribute: att, value: ui.value}, function(data){
				alert(data);
			});
			/*		
			$.ajax({
				url: 'update',
				type: 'GET',
				datatype: 'json'
			});*/
			//send values
		}
	});
});