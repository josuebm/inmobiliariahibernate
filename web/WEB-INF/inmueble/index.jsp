<%-- 
    Document   : index
    Created on : 30-ene-2015, 9:42:21
    Author     : Josué
--%>

<%@page import="hibernate.Inmueble"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Tabla Inmueble</title>
    </head>
    <body>
        <h2><a href="control?target=inmueble&op=insert&action=view">Insertar registro</a></h2>
        <div>
            <table border="1">
                <caption>Tabla Inmueble</caption>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>IDANDROID</th>
                        <th>LOCALIDAD</th>
                        <th>DIRECCIÓN</th>
                        <th>TIPO</th>
                        <th>HABITACIONES</th>
                        <th>PRECIO</th>
                        <th>USUARIO</th>
                        <th>BORRAR</th>
                        <th>EDICION</th>
                        <th>FOTOS</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Inmueble> lista = (List) request.getAttribute("datos");
                        for (Inmueble i : lista) {
                    %>
                    <tr>
                        <td><%= i.getId()%></td>
                        <td><%= i.getIdAndroid() %></td>
                        <td><%= i.getLocalidad()%></td>
                        <td><%= i.getDireccion()%></td>
                        <td><%= i.getTipo()%></td>
                        <td><%= i.getHabitaciones()%></td>
                        <td><%= i.getPrecio()%></td>
                        <td><%= i.getUsuario()%></td>
                        <td><a href="control?target=inmueble&op=delete&action=op&id=<%= i.getId()%>">Borrar</a></td>
                        <td><a href="control?target=inmueble&op=update&action=view&id=<%= i.getId()%>">Editar</a></td>
                        <td><a href="control?target=foto&op=select&action=view&idinmueble=<%= i.getId()%>">Fotos</a></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>
