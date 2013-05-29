$(document).ready(function(){

	$('.switch').css('background', 'url("switch.png")');
	$('.on_off').css('display','none');
	$('.on, .off').css('text-indent','-10000px');

    $("input[name=on_off]").change(function() {
      var button = $(this).val();
	  
		if(button == 'off'){ $('.switch').css('background-position', 'right'); }
		if(button == 'on'){ $('.switch').css('background-position', 'left'); }	 
		  	  
		 $('.result span').html(button); 
		 $('.result').fadeIn();

   });

});
