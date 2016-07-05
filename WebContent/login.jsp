<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body style="background-image: url(image/overlay.png);background-repeat: repeat;">
<div class="container">
      <div style="margin-top:10%;">
          <div class="container"  >
              <div class="row">
                  <div class="col-md-4 col-sm-6 col-xs-10 col-sm-offset-3 col-xs-offset-1 col-md-offset-3 well" >
                  <h3>登录</h3><hr>
                      <form action="login" role='form' class="form-horizontal" method="post">
                          <div class="form-group">
                              <div class="col-md-2 col-xs-2">
                              <label for="number" class="control-label"><span class="glyphicon glyphicon-user" style="font-size:25px"></span></label>
                              </div>
                              <div class="col-md-10 col-xs-10">
                                  <input type="text" class="form-control" name="number" 

id="StuId" placeholder='请输入您的账号'>
                              </div>
                          </div>
                          <div class="form-group">
                              <div class="col-md-2 col-xs-2">
                              <label for="password" class="control-label"><span class="glyphicon glyphicon-lock" style="font-size:25px"></span></label>
                              </div>
                              <div class="col-md-10 col-xs-10">
                                  <input type="password" class="form-control" name="password" 

id="password" placeholder='请输入您的密码'>
                              </div>
                          </div>
                        <div class="form-group">
                            <div class="col-md-9 col-md-offset-3 col-xs-9 col-xs-offset-3">
                                
                            </div>
                        </div>
                      
                          <div class="form-group">
                          <div class="col-md-2 col-xs-2">
                          </div>
                          <div class="col-md-10  col-xs-10 ">
                                  <button class="btn btn-info btn-block" type="submit">登录</button>
                                  </div>
                          </div>
                      </form>
                      <div class="bottom" id="bottom-web" style="display:block;">
                      <a href="forget.html" class="link" id="forgetpwd">忘记密码？</a>
                      
                      </div>
                  </div>
              </div>
          </div>
      </div>
      </div>
  </body>
  <script src="js/bootstrap.js"></script>
    <script src="js/jQuery.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
</html>
