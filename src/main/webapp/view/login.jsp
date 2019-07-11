<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="../css/login.css" rel="stylesheet" type="text/css" />
<script src="../jquery-1.11.3/jquery.js"></script>
<script src="../js/login.js"></script>
<title>登录</title>
</head>
<body onload="changeImg1()">
    <div id="bg">
    	<span id="sp1"></span>
    </div>
    <form action="oauth" method="post" onsubmit="return check1()">
        <h2>选 课 系 统</h2>
            <table id="d1">
                <tr class="tr1">
                    <td><img class="im" src="../img/uwhite.png"></td>
                    <td><input class="inp" id="f1"  type="text" name="username"  placeholder="账号"></td>
                </tr>
                <tr class="tr1">
                    <td><img class="im" src="../img/pwd.png"></td>
                    <td><input class="inp" id="f2" type="password" name="password" placeholder="密码"></td>
                </tr>
                <tr class="tr1">
                    <td><input id="inp11" id="f3" type="text" name="yzm" placeholder="验证码"></td>
                    <td><img title="点击更换验证码" id="yzm" alt="加载中..." src="" onclick="changeImg1()" ></td>
                </tr>
                <tr>
                    <td><input id="sub1" type="submit" value="登录"></td>
                </tr>
            </table>
        <a href="javascript:tobeBig();changeImg2();">忘记密码？</a>
    </form>
    <div id="reset">
        <div id="nav">
            <h3>重置密码</h3>
            <img id="close" src="../img/close.png" onclick="tobeSmall()" >
        </div>
        <form action="oauth" method="post"  onsubmit="return check2()">
            <div id="rtb1">
            <span id="sp2"></span>
                <table>
                    <tr class="tr2">
                        <td><img class="im2" src="../img/userblack.png"></td>
                        <td><input class="inp2" id="ff11" type="text" name="username2"  placeholder="找回账号"></td>
                    </tr>
                    <tr class="tr2">
                        <td><img class="im2" src="../img/tel1.png"></td>
                        <td><input class="inp2" id="ff22" type="text" name="telphone2"  placeholder="密保手机"></td>
                    </tr>
                    <tr class="tr2">
                        <td><img class="im2" src="../img/pwdblack.png"></td>
                        <td><input class="inp2" id="ff33"  type="password" name="newpassword2"  placeholder="修改密码"></td>
                    </tr>
                    <tr class="tr2">
                        <td><img class="im2" src="../img/pwdblack.png"></td>
                        <td><input class="inp2" id="ff44" type="password" name="againpassword2"  placeholder="确认密码"></td>
                    </tr>
                     <tr class="tr2">
                        <td><input id="inp22"  type="text" name="yzm22" placeholder="验证码"></td>
                        <td><img id="yzm2" title="点击更换验证码" src="" alt="加载中..." onclick="changeImg2()" ></td>
                    </tr>
                    <tr>
                        <td><input type="submit" id="sub2" value="确认修改"></td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
</body>
</html>