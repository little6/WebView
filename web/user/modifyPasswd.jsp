<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    .textbox {
        height: 22px;
        padding: 0 2px;
    }
    #userForm {
        padding: 6px 0 0 0;
    }
    p {
        height: 22px;
        line-height: 22px;
        padding: 4px 0 0 25px;
    }
</style>
<script type="text/javascript" language="javascript">
    $(function(){
        //旧密码验证
        $("#oldPassWord").validatebox({
            required: true,
            validType: "length[6,30]",
            missingMessage: "请输入旧密码",
            invalidMessage: "密码长度为6-30个字符"
        });
        //新密码验证
        $("#passWord").validatebox({
            required: true,
            validType: "length[6,30]",
            missingMessage: "请输入新密码",
            invalidMessage: "密码长度为6-30个字符"
        });
        //确认新密码验证
        $("#confirmPass").validatebox({
            required: true,
            validType: "length[6,30]",
            missingMessage: "请确认新密码",
            invalidMessage: "密码长度为6-30个字符"
        });
        //检查密码是否输入
        function checkUser(){
            var boolean = false;
            if (!$("#oldPassWord").validatebox("isValid")) {
                $("#oldPassWord").focus();
            }else if (!$("#passWord").validatebox("isValid")) {
                $("#passWord").focus();
            }else if (!$("#confirmPass").validatebox("isValid")) {
                $("#confirmPass").focus();
            }else if($("#passWord").val() != $("#confirmPass").val()){
                $.messager.alert("ERROR","两次密码输入不一致","warning",function(){
                    $("#confirmPass").select();
                });
            }else{
                boolean = true;
            }
            return boolean;
        }

        //修改密码弹窗
        $("#passForm").dialog({
            width:250,
            modal:true,
            closed:false,
            title:"修改密码",
            buttons:[{
                text:"确定",
                iconCls:"icon-edit",
                handler:function(){
                    if (checkUser()){
                        modifyPassword();
                    }
                }
            },{
                text:"取消",
                iconCls:"icon-redo",
                handler:function(){
                    $("#passForm").dialog("close").form("reset");
                }
            }]
        });
        //修改用户请求
        function modifyPassword(){
            $.ajax({
                url: "/user/modifyPassword.do",
                type: "post",
                data: {
                    oldPassword:$("#oldPassWord").val(),
                    password:$("#passWord").val()
                },
                beforeSend: function(){
                    $.messager.progress({
                        text:"正在修改密码，请稍候..."
                    });
                },
                success: function(data){
                    $.messager.progress("close");
                    if(data == "1"){
                        location.href = "./login.jsp";
                    }else{
                        $.messager.alert("修改失败","旧密码错误","warning",function(){
                            $("#oldPassWord").select();
                        });
                    }
                }
            });
        }
    });
</script>
<form id="passForm">
    <p>旧密码&nbsp;&nbsp;<input name="oldPassWord" id="oldPassWord" type="passWord" class="textbox"></p>
    <p>新密码&nbsp;&nbsp;<input name="passWord" id="passWord" type="password" class="textbox"></p>
    <p>新密码&nbsp;&nbsp;<input name="confirmPass"id="confirmPass" type="password" class="textbox"></p>
</form>
