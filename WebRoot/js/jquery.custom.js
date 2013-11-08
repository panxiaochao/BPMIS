/*!
 * Copyright &copy; 2012-2013 All rights reserved.
	jquery.custom.js
   design by panxiaochao
 */

//login
	$(document).ready(function() {			
			//验证初始化
			$('#loginForm').validator({ 
				theme: 'yellow_right',
				fields: {
					"username": {
						rule: "用户名:required;username",
						tip: "用户名"
					},					
					"password": {
						rule: "密码:required;password;strength",
						tip: "密码"
					}
						
				},
				//验证成功
				valid: function(form) {
					//do something...					
					login();
				},
				//验证失败
				invalid: function(form) {

				}
			});
			
		});
		
		function login(){
			var username = $("#username").val();
			var password = $("#password").val();
			var checkcode = $("#checkcode").val();
			jtip("正在审核中......", 'loading');					
			$.ajax({
				type: "post",					
				url: "userController.do?checkuserlogin",
				data: "username="+username+"&pwd="+password+"&checkcode="+checkcode,
				success: function(data){
					if(data.success == true) { 
						jtip(data.msg,'success'); 
						window.location.href = "userController.do?loginbpmis"; 
					} else {						
						$.jBox.tip(data.msg, 'success'); 
						//$("#username").attr("value","");
						$("#password").attr("value","");
						$("#checkcode").attr("value","");
					}	
				},					
				complete: function(XMLHttpRequest, textStatus){
					//do something......							
				},
				error: function(){
					jerror("出错了！");
					jtip('error', 'info'); 
				}
			});				  		
		}
//end login	
	//fankui
	function fankui(){
			var email = $("#email").val();
			var sug = $("#sug").val();
			var submit = function (v, h, f) {
				if (v == 'ok'){
					jtip("正在提交中......", 'loading');		
					$.ajax({
						type: "post",					
						url: "systemController.do?feedback",
						data: "email="+email+"&sug="+sug,
						success: function(data){
							if(data.success == true) { 
								jtip(data.msg, 'info'); 
								$('#fankui').modal('hide');
								$("#email").val("");
								$("#sug").val("");
							} else {
								jtip('错误', 'info'); 
								jerror(data.msg, 'errror');
							}	
						},					
						complete: function(XMLHttpRequest, textStatus){
							//do something......							
						},
						error: function(){
							jerror("出错了！");
							jtip('error', 'info'); 
						}
					});	
				}
				else if (v == 'cancel')
					$.jBox.close();			
				return true; //close
			};
			if(email==""||sug==""){
				jinfo("邮箱不能为空或请填写您的意见再提交","提醒");
				return false;
			}		
			else{
				jconfirm("请确保您的邮箱正确，以至于能收到我们的回信，谢谢！","提醒",submit);
				return true;
			}
		
		}

// login out
	function loginout(){
		$.jBox.tip("正在退出......", 'loading');
		window.location.href = "userController.do?loginout"; 	
	}
		
