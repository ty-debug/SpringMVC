<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/5/29
  Time: 22:51
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
        $("#btn").click(function () {
            $.post("${pageContext.request.contextPath}/a2", function (data) {
                let html = "";
                for (let i = 0; i < data.length; i++) {
                    html += "<tr>" +
                        "<td>" + data[i].name + "</td>" +
                        "<td>" + data[i].age + "</td>" +
                        "<td>" + data[i].sex + "</td>" +
                        "</tr>"
                }
                $("#content").html(html);
            });
        })
    });
</script>
<body>

<input id="btn" value="加载数据" type="button">
<table>
    <tr>
        <td>姓名</td>
        <td>年龄</td>
        <td>性别</td>
    </tr>
    <tbody id="content">

    </tbody>
</table>

</body>
</html>
