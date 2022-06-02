<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/5/30
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
    $(function () {
        $("#name").blur(function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/a3",
                data: {name: $("#name").val()},
                type: "post",
                success: function (data) {
                    if (data === "name行") {
                        $("#name").css("border", "");
                        $("#name_msg").css("color", "green");
                    } else {
                        $("#name").css("border", "1px solid red");
                        $("#name_msg").css("color", "red");
                    }
                    $("#name_msg").html(data);
                }
            })
        })

        $("#pwd").blur(function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/a3",
                data: {pwd: $("#pwd").val()},
                type: "post",
                success: function (data) {
                    $("#pwd_msg").html(data);
                }
            })
        })
    })
</script>
<body>

用户名<input type="text" id="name">
<span id="name_msg"></span>
<br>
密码<input type="text" id="pwd">
<span id="pwd_msg"></span>

</body>
</html>
