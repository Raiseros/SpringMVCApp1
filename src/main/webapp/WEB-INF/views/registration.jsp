<%--
  Created by IntelliJ IDEA.
  User: holya
  Date: 03.01.2020
  Time: 1:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html>
<head>

</head>
<body>

<p></p>
<form:form action="saveStudent" modelAttribute="student" method="POST">
<table>
    <tr>
        <td> Name :</td>

        <td><form:input  path="name" /></td>
    </tr>
</table>
    <p></p>
    <td> <input type="submit" value="Save" /> </td>
</form:form>




</body>
</html>
