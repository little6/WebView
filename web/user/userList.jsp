<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    .textbox {
        height: 22px;
        padding: 0 2px;
    }
    #userForm {
        padding: 6px 0 0 0;
    }
    #userForm1 {
        padding: 6px 0 0 0;
    }
    p {
        height: 22px;
        line-height: 22px;
        padding: 4px 0 0 25px;
    }
</style>
<script type="text/javascript" language="javascript">
    //用户信息列表
    $(function(){
        $("#userList").datagrid({
            url:"/user/userList.do",
            fit:true,
            fitColumns:true,
            striped:true,
            rownumbers:true,
            border:false,
            pagination:true,
            pageList:[15,30,45,60,75],
            pageSize:15,
            pageNumber:1,
            toolbar:"#userListTooBar",
            columns:[[
                {field:'userId',title:'userId',width:20,checkbox:true},
                {field:'name',title:'用户名',width:50},
                {field:'nickName',title:'昵称',width:50,align:'left'},
                {field:'createDate',title:'创建日期',width:50,align:'right'}
            ]]
        });
        //用户名验证
        $("#userName").validatebox({
            required: true,
            missingMessage: "请输入用账号",
            invalidMessage: "账号不能包括空格"
        });
        $("#userName1").validatebox({
            required: true,
            missingMessage: "请输入用账号",
            invalidMessage: "账号不能包括空格"
        });
        //昵称验证
        $("#nickName").validatebox({
            required: true,
            missingMessage: "请输入用昵称",
            invalidMessage: "昵称不能包括空格"
        });
        $("#nickName1").validatebox({
            required: true,
            missingMessage: "请输入用昵称",
            invalidMessage: "昵称不能包括空格"
        });
        //密码验证
        $("#passWord").validatebox({
            required: true,
            validType: "length[6,30]",
            missingMessage: "请输入密码",
            invalidMessage: "密码长度为6-30个字符"
        });
        //确认密码验证
        $("#confirmPass").validatebox({
            required: true,
            validType: "length[6,30]",
            missingMessage: "请确认密码",
            invalidMessage: "密码长度为6-30个字符"
        });
        //新增用户弹窗
        $("#userForm").dialog({
            width:250,
            modal:true,
            closed:true,
            buttons:[{
                text:"确定",
                iconCls:"icon-add",
                handler:function(){
                    if (checkUser()){
                        addUserRequest();
                    }
                }
            },{
                text:"取消",
                iconCls:"icon-redo",
                handler:function(){
                    $("#userForm").dialog("close").form("reset");
                }
            }]
        });
        //修改用户弹窗
        $("#userForm1").dialog({
            width:250,
            modal:true,
            closed:true,
            buttons:[{
                text:"确定",
                iconCls:"icon-edit",
                handler:function(){
                    if (checkUser1()){
                        modifyRequest();
                    }
                }
            },{
                text:"取消",
                iconCls:"icon-redo",
                handler:function(){
                    $("#userForm").dialog("close").form("reset");
                }
            }]
        });


        //数据操作方法
        userOperate ={
            add:function(){
                $("#userForm").dialog("open").dialog('setTitle','新增用户');
                $("#userName").focus();
            },
            remove:function(){
                var rows = $("#userList").datagrid("getSelections");
                if (rows.length < 1) {
                    $.messager.alert("Error", "请勾选一条记录删除", "warning");
                } else if (rows.length == 1) {
                    $.messager.confirm("确定", "确定要删除该用户？",function(){
                        deleteRequest(rows[0].userId);
                    });
                } else {
                    $.messager.alert("Error", "只能勾选一条记录进行删除", "warning");
                }
            },
            edit:function(){
                var rows = $("#userList").datagrid("getSelections");
                if(rows.length < 1){
                    $.messager.alert("Error","请勾选一条记录修改","warning");
                }else if(rows.length == 1){
                    getOneUserRequest(rows[0].userId)
                } else{
                    $.messager.alert("Error","只能勾选一条记录进行修改","warning");
                }
            },
            reload:function(){
                $("#userList").datagrid("reload");
            },
            redo:function(){
                $("#userList").datagrid("unselectAll");
                $("#searchName").val("");
                $("#startDate").datebox("setValue","");
                $("#endDate").datebox("setValue","");
                $('#userList').datagrid("options").queryParams=[];
                $("#userList").datagrid("reload");
            },
            search:function(){
                if(checkForSearch()){
                    var queryParams = $("#userList").datagrid("options").queryParams;
                    queryParams.name = $("#searchName").val();
                    queryParams.startDate= $("#startDate").datebox("getValue");
                    queryParams.endDate= $("#endDate").datebox("getValue");
                    $('#userList').datagrid("options").queryParams=queryParams;
                    $("#userList").datagrid("reload");
                }
            }
        }
        //新增用户异步请请求
        function addUserRequest(){
            $.ajax({
                url: "/user/addUser.do",
                type: "post",
                data: {
                    name:$("#userName").val(),
                    nickName:$("#nickName").val(),
                    password:$("#passWord").val()
                },
                beforeSend: function(){
                    $.messager.progress({
                        text:"正在新建用户，请稍候..."
                    });
                },
                success: function(data){
                    $.messager.progress("close");
                    if(data == "1"){
                        $.messager.show({
                            title:"OK",
                            msg:"新建用户成功"
                        });
                        $("#userForm").dialog("close").form("reset");
                        $("#userList").datagrid("reload");
                    }else{
                        $.messager.alert("创建失败","账号已存在，不允许重复设置","warning",function(){
                            $("#name").select();
                        });
                    }
                }
            });
        }
        //获取用户详情异步请求
        function getOneUserRequest(userId){
            $.ajax({
                url: "/user/getOneUser.do",
                type: "post",
                data: {
                    userId:userId
                },
                beforeSend: function(){
                    $.messager.progress({
                        text:"正在获取用户信息，请稍候..."
                    });
                },
                success: function(data){
                    $.messager.progress("close");
                    if(data != "0"){
                        var user = $.parseJSON(data);
                        $("#userForm1").form("load",{
                            userId:user[0].userId,
                            userName1:user[0].name,
                            nickName1:user[0].nickName
                        }).dialog("open").dialog("setTitle","修改用户");
                    }else{
                        $.messager.alert("Error","未知错误，获取用户信息失败","warning");
                    }
                }
            });
        }
        //修改用户请求
        function modifyRequest(){
            $.ajax({
                url: "/user/modifyUser.do",
                type: "post",
                data: {
                    userId:$("#userId").val(),
                    name:$("#userName1").val(),
                    nickName:$("#nickName1").val()
                },
                beforeSend: function(){
                    $.messager.progress({
                        text:"正在修改用户，请稍候..."
                    });
                },
                success: function(data){
                    $.messager.progress("close");
                    if(data == "1"){
                        $.messager.show({
                            title:"OK",
                            msg:"修改用户成功"
                        });
                        $("#userForm1").dialog("close").form("reset");
                        $("#userList").datagrid("reload");
                    }else{
                        $.messager.alert("修改失败","账号已存在，不允许重复设置","warning",function(){
                            $("#name").select();
                        });
                    }
                }
            });
        }
        //删除用户请求
        function deleteRequest(userId){
            $.ajax({
                url: "/user/deleteUser.do",
                type: "post",
                data: {
                    userId:userId
                },
                beforeSend: function(){
                    $.messager.progress({
                        text:"正在删除用户，请稍候..."
                    });
                },
                success: function(data){
                    $.messager.progress("close");
                    if(data == "1"){
                        $.messager.show({
                            title:"OK",
                            msg:"删除用户成功"
                        });
                        $("#userList").datagrid("reload");
                    }else{
                        $.messager.alert("创建失败","账号已存在后续业务，不允许删除","warning");
                    }
                }
            });
        }
        //检查新增用户信息（全部）
        function checkUser(){
            var boolean = false;
            if (!$("#userName").validatebox("isValid")) {
                $("#userName").focus();
            }else if (!$("#nickName").validatebox("isValid")) {
                $("#nickName").focus();
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
        //检查修改用户信息（全部）
        function checkUser1(){
            var boolean = false;
            if (!$("#userName1").validatebox("isValid")) {
                $("#userName1").focus();
            }else if (!$("#nickName1").validatebox("isValid")) {
                $("#nickName1").focus();
            }else{
                boolean = true;
            }
            return boolean;
        }
        //检查查询条件
        function checkForSearch(){
            var boolean = false;
            if($("#searchName").val() == ""){
                $.messager.alert("Error","请输入用户名",function(){
                    $("#searchName").focus();
                });
            }else{
                boolean = true;
            }
            return boolean;
        }
    });
</script>
<table id="userList"></table>
<div id="userListTooBar" style="padding: 5px">
    <div style="margin-bottom: 5px">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add"
           plain="true" onclick="userOperate.add()">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit"
           plain="true" onclick="userOperate.edit()">修改</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove"
           plain="true" onclick="userOperate.remove()">删除</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload"
           plain="true" onclick="userOperate.reload()">刷新</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-redo"
           plain="true" onclick="userOperate.redo()">重新载入</a>
    </div>
    <div style="padding:0 0 0 7px;color: #162087">
        用户姓名：<input type="text" class="textbox" id="searchName" style="width: 110px"/>
        创建日期：从<input type="text" class="easyui-datebox" id="startDate" editable="false" style="width: 110px"/>
        到<input type="text" class="easyui-datebox" id="endDate" editable="false" style="width: 110px"/>
        <button class="easyui-linkbutton" iconCls="icon-search" style="width: 50px"
                onclick="userOperate.search()"/>
    </div>
</div>

<form id="userForm">
    <p>账号&nbsp;&nbsp;<input name="userName" id="userName"  type="input" class="textbox"></p>
    <p>昵称&nbsp;&nbsp;<input name="nickName" id="nickName" type="input" class="textbox"></p>
    <p>密码&nbsp;&nbsp;<input name="passWord" id="passWord" type="password" class="textbox"></p>
    <p>密码&nbsp;&nbsp;<input name="confirmPass"id="confirmPass" type="password" class="textbox"></p>
</form>
<form id="userForm1">
    <input id="userId" name="userId" type="hidden" class="textbox">
    <p>账号&nbsp;&nbsp;<input name="userName1" id="userName1"  type="input" class="textbox"></p>
    <p>昵称&nbsp;&nbsp;<input name="nickName1" id="nickName1" type="input" class="textbox"></p>
</form>




