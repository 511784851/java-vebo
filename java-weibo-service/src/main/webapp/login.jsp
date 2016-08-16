<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>迷雾科技 - 微博发布系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript" src="/weibo/html/assets/js/jquery.min.js"></script>

<link rel="stylesheet" href="/weibo/html/assets/css/loader-style.css">
<link rel="stylesheet" href="/weibo/html/assets/css/bootstrap.css">
<link rel="stylesheet" href="/weibo/html/assets/css/signin.css">

<link rel="shortcut icon" href="/weibo/html/assets/ico/minus.png">
</head>

<body>
	<%
		Object msgObject = request.getAttribute("msgCode");
		String msgCode = "";
		if(msgObject!=null){
			msgCode = msgObject.toString();
		}
	%>
	<div class="container">
		<div class="" id="login-wrapper">
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<div id="logo-login">
						<h1>微博管理系统</h1>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<div class="account-box">
						<form role="form" action="/weibo/login.do" method="post">
							<div class="form-group">
								<label for="inputUsernameEmail">用户名</label> <input type="text"
									name="username" id="inputUsernameEmail" class="form-control"
									value="">
							</div>
							<div class="form-group">
								<label for="inputPassword">密码</label> <input type="password"
									name="password" id="inputPassword" class="form-control"
									value="">
							</div>
							<div class="checkbox pull-left">
								
							</div>
							<button class="btn btn btn-primary pull-right" type="submit">
								登 录</button>
						</form>
						<a class="forgotLnk" href="index.html"></a>
						<div class="row-block">
							<div class="row"></div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<p>&nbsp;</p>
		<div style="text-align:center;margin:0 auto;">
			<h6 style="color:#fff;">
				<br />
				深圳迷雾科技有限公司 版权所有 
			</h6>
		</div>

	</div>

	<script type="text/javascript">
	var msgCode = '<%=msgCode%>';
	if(msgCode=="10000"){
		alert("用户名或密码错误");
	} else if(msgCode=="10001"){
		alert("已退出登录");
	}
	</script>
</body>
</html>

