<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/5/28
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<body>
<script>
    $(function () {
        $("#username").blur(function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/a1",
                type: "post",
                data: {
                    "name": $("#username").val()
                },
                success: function (data) {
                    if (data.code == 200) {
                        alert(data);
                    } else {
                        alert(data);
                    }
                }
            });
        });
    });
    <%--function ajax1() {--%>
    <%--    $.post({--%>
    <%--        url: "${pageContext.request.contextPath}/a1",--%>
    <%--        data: {"name": $("#username").val()},--%>
    <%--        success: function (data) {--%>
    <%--            alert(data);--%>
    <%--        }--%>
    <%--    })--%>
    <%--}--%>
</script>
<input type="text" id="username">
</body>
</html>
