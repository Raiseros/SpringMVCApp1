
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html>
<head>

</head>
<body>


<p></p>
<form:form action="submitName.html" modelAttribute="student" method="post">
    <table>
        <tr>
            <td>Select your name :</td>

            <td><form:select path="name" items="${nameList}"/></td>
        </tr>
    </table>
        <tr>
            <table>
            <td> <input type="submit" value="Submit" /> </td>
</form:form>


  <p></p>
<form action="registration">
    <td></td>
    <td> <input type="submit" value="Registration" /> </td>

</form>
        </tr>
            </table>

</body>
</html>
