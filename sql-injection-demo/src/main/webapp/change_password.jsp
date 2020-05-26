<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/login.css" rel='stylesheet' type='text/css' />
<title>Đăng kí tài khoản</title>
</head>
<body>
	<%
		String err = "";
	if (request.getAttribute("err") != null) {
		err = (String) request.getAttribute("err");
	}
	%>
	<!--/start-login-two-->
	<div class="login-02">
		<div class="two-login  hvr-float-shadow">
			<div class="two-login-head">
				<img src="images/top-note.png" alt="" />
				<h2>Thay đổi mật khẩu</h2>
				<lable></lable>
			</div>
			<form action="ChangePasswordServlet" method="post">
				<li style="color: red"><%=err%></li> Tên người dùng
				<li><input type="text" class="text" value="" name="username"><a
					href="#" class=" icon2 user2"></a>
				</li> Mật khẩu
				<li><input type="password" value="" name="password"><a
					href="#" class=" icon2 lock2"></a>
				</li> Mật khẩu mới
				<li><input type="password" value="" name="newPassword">
				<a href="#" class=" icon2 lock2"></a>
				</li> Nhắc lại mật khẩu
				<li><input type="password" value="" name="resetPassword">
				<a href="#" class=" icon2 lock2"></a>
				</li>
				
				<div class="submit two">
					<input type="submit" value="Thay đổi">
				</div>
			</form>
		</div>
	</div>
</body>
</html>