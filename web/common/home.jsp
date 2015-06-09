<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 15-2-1
  Time: 下午11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="/public/jquery.easyui-1.4.1/jquery.min.js"></script>
    <script type="text/javascript" src="/public/jquery.easyui-1.4.1/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/public/jquery.easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css" href="/public/jquery.easyui-1.4.1/themes/black/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="/public/jquery.easyui-1.4.1/themes/icon.css"/>
    <style type="text/css">
        .logo{
            width: 180px;
            height: 50px;
            text-align: center;
            line-height: 50px;
            font-size: 20px;
            font-weight: bold;
            float: left;
            color:#fff;
        }
        .logout{
            float: right;
            padding: 30px 15px 0 0;
            color: #fff;
        }
        a {
            text-decoration: none;
            color:#fff;
        }
        a:hover{
            text-decoration: underline;
        }

    </style>
    <script type="text/javascript" language="javascript">
        $(function(){
            $("#navTree").tree({
                url:"/common/getTree.do",
                lines:true,
                onLoadSuccess:function(node,data){
                    if(data){
                        $(data).each(function(index,value){
                            if(this.state == "closed"){
                                $("#navTree").tree("expandAll")
                            }
                        });
                    }
                },
                onClick: function (node) {
                    if(node.url){
                        if ($("#tabs").tabs("exists", node.text)) {
                            $("#tabs").tabs("select", node.text);
                        } else {
                            $("#tabs").tabs("add", {
                                title: node.text,
                                iconCls: node.iconCls,
                                closable: true,
                                href: node.url
                            });
                        }
                    }
                }
            });

            $("#tabs").tabs({
                fit:true,
                border:false
            });
        });
    </script>
</head>
<body class="easyui-layout">
    <div data-options="region:'north',title:'North Title',split:true,noheader:true" style="height:60px;">
        <div class="logo">后台管理</div>
        <div class="logout"> 你好！| <a href="/common/logout.do">退出</a> </div>
    </div>
    <div data-options="region:'south',title:'South Title',split:true,noheader:true" style="height:35px;line-height: 30px;text-align: center">
        学生信息管理系统</div>
    <div data-options="region:'west',title:'导航',split:true" style="width:100px;width: 180px;padding: 10px">
        <ul id="navTree"></ul>
    </div>
    <div data-options="region:'center'" style="style:overflow:hidden">
        <div id="tabs">
            <div title="起始页" closable="true" style="padding: 0px,10px;display: block">
                Welcome！
            </div>
        </div>
    </div>
</body>
</html>