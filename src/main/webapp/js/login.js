$(document).ready(function() {
	$('#submit').click(function() {
		var username = $('#username').val();
		var userpwd = $('#userpwd').val();
		if(username === null || username == '' || userpwd === null || userpwd == '') {
			$('.login-msg').show().text('用户名或密码不能为空！');
			return false;
		} else {
			$.ajax({
				type: "post",
				url: "/user/login",
				async: false,
				dataType:"json",
				scriptCharset: 'utf-8',
				data:{
					"username":username,
					"userpwd":userpwd
				},
				success: function(date) {
					$('.login-msg').hide();
					window.location.href="./index.html";
				},
				error: function(date) {
					console.log(date);
					$('.login-msg').show().text(date.msg);
				}
			});
		}
	});
	document.onkeydown=function(event){
		   var e = event || window.event || arguments.callee.caller.arguments[0];
		   if(e && e.keyCode==13){
			   $('#submit').click();
		   }
	}
});