<%-- 
    Document   : index
    Created on : 30-ene-2015, 9:42:21
    Author     : Josué
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modelo.ModeloFoto"%>
<%@page import="hibernate.Foto"%>
<%@page import="hibernate.Inmueble"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Tabla Foto</title>
    </head>
    <body>
        <% String idinmueble = (String) request.getAttribute("idinmueble");%>
        <h2><a href="control?target=foto&op=insert&action=view&idinmueble=<%= idinmueble%>">Insertar registro</a></h2>
        <% List<Foto> lista = ModeloFoto.get(Integer.parseInt((String) (request.getAttribute("idinmueble"))));
            if (lista.size() > 0) {%>
        <div class="CSSTableGenerator">
            <table border="1">
                <caption>Tabla Foto</caption>
                <thead>

                    <tr>
                        <th>ID</th>
                        <th>ID INMUEBLE</th>
                        <th>IMAGEN</th>
                        <th>BORRAR</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Foto f : lista) {
                    %>
                    <tr>
                        <td><%= f.getId()%></td>
                        <td><%= f.getInmueble().getId()%></td>
                        <td><img src="<%= f.getRuta().substring(65)%>" height="100" width="100"></td>
                        <td><a href="control?target=foto&op=delete&action=op&id=<%= f.getId()%>&idinmueble=<%= f.getInmueble().getId()%>">Borrar</a></td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                <output>No hay fotos</output>
                    <%}%>
                </tbody>
            </table>
        </div>
    </body>
</html>
