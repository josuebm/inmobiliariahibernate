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
        <title>Tabla Inmueble</title>
    </head>
    <body>
        <h1>Tabla Inmueble</h1>
        <h2><a href="control?target=inmueble&op=insert&action=view">insertar registro</a></h2>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>LOCALIDAD</th>
                    <th>DIRECCIÓN</th>
                    <th>TIPO</th>
                    <th>HABITACIONES</th>
                    <th>PRECIO</th>
                    <th>USUARIOS</th>
                    <th>BORRAR</th>
                    <th>EDICION</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Inmueble> lista = (List)request.getAttribute("datos");
                    for(Inmueble i: lista){
                        %>
                <tr>
                    <td><%= i.getId() %></td>
                    <td><%= i.getLocalidad() %></td>
                    <td><%= i.getDireccion() %></td>
                    <td><%= i.getTipo() %></td>
                    <td><%= i.getHabitaciones() %></td>
                    <td><%= i.getPrecio() %></td>
                    <td><%= i.getUsuario() %></td>
                    <td><a href="control?target=inmueble&op=delete&action=op&id=<%= i.getId() %>">Borrar</a></td>
                    <td><a href="control?target=inmueble&op=update&action=view&id=<%= i.getId() %>">Editar</a></td>
                </tr>
                <%
                    }
                    %>
            </tbody>
        </table>
    </body>
</html>
