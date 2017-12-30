<%--
  Created by IntelliJ IDEA.
  User: zzy
  Date: 2017/12/25
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <link rel="stylesheet" href="css/commentc.css"/>

    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="layer/layer.js"></script>
    <script src="layer/extend/layer.ext.js"></script>

    <script type="text/javascript">
        function checkContent(){
            var result = document.getElementById("content").value;

            if(result == ""  ){
                layer.msg('您未输入,请输入点什么吧!');
                window.parent.location.reload();
            }
            window.parent.location.reload();
//            alert(document.getElementById("content").value);
        }

    </script>
    <title>回复</title>
    </head>

    <body>
        <form id="formid" method="post" action="${pageContext.request.contextPath}/CommentWriteToAction.action?wbytrend=<s:property value="wbytrend"/>&repliedId=<s:property value="repliedId"/>">
        <table class="up" border="0" width="100%">
            <tr class="top"><td><textarea name="content"></textarea></td></tr>

            <tr class="bottom">
                <td><a href = "javascript:"
                       <%--onclick="location.href='${pageContext.request.contextPath}/CommentWriteToAction.action?wbytrend=<s:property value="wbytrend"/>&repliedId=<s:property value="repliedId"/>'">--%>>
                    <input type="submit" lay-submit lay-filter="pushBtn" class="send" value="发表" onclick = "checkContent();"/></a></td></tr>
        </table>
    </form>
    </body>
</html>