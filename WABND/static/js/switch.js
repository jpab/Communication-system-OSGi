$(document).ready(function(){

	$('.switch').css('background', 'url("img/switch.jpg")');
	$('.on_off').css('display','none');
	$('.on, .off').css('text-indent','-10000px');
    $("input[name=on_off]").change(function() {
      var button = $(this).val();
      var serv = $(this).parent().parent().parent().parent().prev().prev().first().text().trim();
      var att = $(this).parent().parent().parent().parent().prev().text().trim();
     
		if(button == 'off'){ $('.switch').css('background-position', 'right');}
		if(button == 'on'){ $('.switch').css('background-position', 'left'); }	 
		  	 
		 $('.result span').html(button); 
		 $('.result').fadeIn();
		 $.get('changeswitchservice', {service: serv, attribute: att, value: button}, function(data){
				alert(data);
			});
		

   });

});