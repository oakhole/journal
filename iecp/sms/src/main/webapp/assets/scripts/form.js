$(document).ready(function(){
	
	$("form").validate();
	
	$("#showMore").on("click",function(){
		$("#moreInfo").toggle();
	});
});