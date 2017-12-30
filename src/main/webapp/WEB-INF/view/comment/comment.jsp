<%--
  Created by IntelliJ IDEA.
  User: zzy
  Date: 2017/12/20
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <link href="css/commentc.css" rel="stylesheet"/>
    <title>评论</title>
</head>

<body STYLE="margin: 0px; padding: 0px;">
<div style="padding: 0px; margin: 0px; width: 100%; height:100%; background-color:rgba(241, 241, 241, 0.33);">
<div class="big" style="background-color: #FFFFFF;">
<s:iterator value="commentlist" var="cl">
    <div style="width: 100%; height: 30px; "></div>
    <div class="comment">
        <div class="left">
            <img src="images/jikepic1.png" style="height:36px; width:36px"/>
        </div>
        <div class="right">
            <font color="#8080FF"><span><s:property value="#cl.user_id" />回复 <s:property value="#cl.parent"/> :</span></font>
            <span><s:property value="#cl.content"/> </span>
            <div class="time">
                <font color="#B9B9B9" size="-1"><span><s:property value="#cl.comment_time"/>
                <a href="javascript:;" class="del" data-id="<s:property value="#cl.id"/>">
                    <%--<span style="visibility: hidden" name="id"><s:property value="#cl.id"/></span>--%>
                    <img src="images/garbage1.jpg"  style="height: 14px; width:14px" title="删除"/>
                </a>
                <a href="javascript:;" class="wcomment" data-id="<s:property value="#cl.id"/>">
                    <%--<span style="visibility: hidden " name="id"><s:property value="#cl.id"/></span>--%>
                    <img src="images/reply.jpg"  style="height: 14px; width:14px" title="回复"/>
                </a>
                </span></font>
            </div>
        </div>
    </div>

</s:iterator>
</div>
</div>
</body>
</html>

<script src="layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['layer','form','jquery'],function(){
        var layer = layui.layer,
            $ = layui.jquery;
        //写评论
        $('.wcomment').on('click',function(){
            var rid = $(this).data('id');
            layer.open({
                type: 2,
                title: ['写评论'],
                content: ["${pageContext.request.contextPath}/CommentWriteAction.action?id="+rid,'no'],
                area:['520px','350px'],  //宽高
                resize: false,    //是否允许拉伸
                scrollbar: false,
                maxmin: true,
                end: function(){
                    location.reload();
                }
            });
        });
        $('.del').on('click',function(){
            var delId = $(this).data('id');
            layer.open({
                type: 2,
                title: ['删除确认'],
                content: ["${pageContext.request.contextPath}/CommentIsDelAction.action"+"?delId="+delId, 'no'],
                area:['280px','200px'],  //宽高
                resize: false,    //是否允许拉伸
                scrollbar: false,
                maxmin: true,
                end: function(){
                    location.reload();
                }
            });
        });
    });

</script>