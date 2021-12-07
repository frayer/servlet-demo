<!-- This declaration allows us to use the JSTL Core Tags, specifically "c:forEach" -->
<!-- More are documented at: https://www.javatpoint.com/jstl-core-tags -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Items</title>
</head>
<body>

<h3>Items</h3>

<!-- This form will HTTP POST the 'itemName' parameter to the HomeServlet.doPost() method -->
<!--
    '/servlet-demo' is the default root context of this application.  HomeServlet
    listens on "/", therefore the full path to invoke actions on HomeServlet is
    '/servlet-demo/'.
-->
<form action="/servlet-demo/" method="post">
    <input type="text" name="itemName">
    <input type="submit" value="Add Item">
</form>

<ul>
    <!--
        Loop over the items in the "inventory" request attribute, which was populated in
        either the doGet() or doPost() methods of HomeServlet.  The type of "inventory" is
        List<Item>, and we store each element of that List to the variable "item" as we
        loop over.
    -->
    <c:forEach items="${inventory}" var="item">
        <!-- The type of "item" here is "com.mycompany.domain.Item".
            The {item.name} syntax calls the Item.getName() method under the covers.
        -->
        <li>${item.name}</li>
    </c:forEach>
</ul>

</body>
</html>
