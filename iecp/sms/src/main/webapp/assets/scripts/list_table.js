$(document).ready(function(){
	
	//通过checkbox的all类型控制该列所有的checkbox是否选中
	$(".list-table #check-all").on("click",function(){
		$(".list-table :checkbox").prop("checked",this.checked);
	});
	
	//当选中checkbox大于1时显示隐藏的toolkit
	var checkedItems;
	$(".list-table :checkbox").on("click",function(){
		checkedItems = $(".list-table td :checked");
		if(checkedItems.length > 0){
			$(".toolkit").show();
		}else{
			$(".toolkit").hide();
		}
	});
	
	$("*[data-dismiss|='modal']").on("click",function(e){
		$("*[data-toggle|='modal']").prop("focus",null);
	});
	
});