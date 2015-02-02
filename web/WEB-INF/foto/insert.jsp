<%-- 
    Document   : insert
    Created on : 19-ene-2015, 10:45:37
    Author     : 2dam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Insert</h1>
        <% String idInmueble = request.getAttribute("datos").toString(); %>
        <form action="control?target=foto&op=insert&action=op&idinmueble=<%= idInmueble %>" method="POST" enctype="multipart/form-data">
            <input type="file" name="file" required="required"/>
            <input type="hidden" name="target" value="foto" />
            <input type="hidden" name="op" value="insert" />
            <input type="hidden" name="action" value="op" />
            <input type="submit" value="Insertar" />
        </form>
    </body>
</html>
