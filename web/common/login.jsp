<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8"/>
    <script type="text/javascript" src="/public/jquery.easyui-1.4.1/jquery.min.js"></script>
    <script type="text/javascript" src="/public/jquery.easyui-1.4.1/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/public/jquery.easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css" href="/public/jquery.easyui-1.4.1/themes/black/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="/public/jquery.easyui-1.4.1/themes/icon.css"/>
    <style type="text/css">
        #login {
            padding: 6px 0 0 0;
        }

        p {
            height: 22px;
            line-height: 22px;
            padding: 4px 0 0 25px;
        }

        .textbox {
            height: 22;
            padding: 0 2px;
        }

        #submit {
            text-align: center;
        }

        .easyui-linkButton {
            padding: 0 10px;
        }
    </style>
    <script type="text/javascript" language="JavaScript">
        $(function () {
            //弹出登录窗口
            $("#login").dialog({
                title: '登录后台',
                width: 250,
                height: 180,
                modal: true,
                buttons: "#submit",
                left: 0,
                top: 0
            });
            //用户名验证
            $("#userName").validatebox({
                required: true,
                missingMessage: "请输入用账号",
                invalidMessage: "账号不能包括空格"
            });
            //密码验证
            $("#passWord").validatebox({
                required: true,
                validType: "length[6,30]",
                missingMessage: "请输入密码",
                invalidMessage: "密码长度为6-30个字符"
            });
            //点击登录按钮
            $("#btn").click(function () {
                if (!$("#userName").validatebox("isValid")) {
                    $("#userName").focus();
                } else if (!$("#passWord").validatebox("isValid")) {
                    $("#passWord").focus();
                } else {
                    $.ajax({
                        url: "/common/login.do",
                        type: "post",
                        data: {
                            userName:$("#userName").val(),
                            passWord:$("#passWord").val()
                        },
                        beforeSend: function(){
                            $.messager.progress({
                                text:"正在登录验证，请稍候..."
                            });
                        },
                        success: function(data){
                            $.messager.progress("close");
                            if(data != "1"){
                                $.messager.alert("登录失败","账号不存在或密码错误","warning",function(){
                                    $("#passWord").select();
                                });
                            }else{
                                location.href = "./home.jsp";
                            }
                        }
                    });
                }
            });
            //页面加载时就进行验证
            if (!$("#userName").validatebox("isValid")) {
                $("#userName").focus();
            } else if (!$("#passWord").validatebox("isValid")) {
                $("#passWord").focus();
            }
        });
    </script>
</head>
<body>
        <div id="login">
            <p>账号&nbsp;<input id="userName" type="input" class="textbox"></p>
            <p>密码&nbsp;<input id="passWord" type="password" class="textbox"></p>
        </div>
        <div id="submit">
            <button id="btn" class="easyui-linkButton">登录</button>
        </div>
</body>
</html>