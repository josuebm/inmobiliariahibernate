<%-- 
    Document   : update
    Created on : 19-ene-2015, 11:00:32
    Author     : 2dam
--%>

<%@page import="hibernate.Inmueble"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Edit</h1>
        <%
                    Inmueble i= (Inmueble)request.getAttribute("datos");
                        %>
        <form action="control?target=inmueble&op=update&action=op" method="POST">   
            <input type="hidden" name="id" value="<%= i.getId() %>" />
            <input type="hidden" name="idAndroid" value="<%= i.getIdAndroid() %>" />
            <output>Localidad: </output>
            <input type="text" name="localidad" value="<%= i.getLocalidad()%>" />
            <br/>
            <output>Dirección: </output>
            <input type="text" name="direccion" value="<%= i.getDireccion() %>" />
            <br/>
            <output>Tipo: </output>
            <select name="tipo">
                <option value="0" <% if(i.getTipo() == 0){ %> selected="selected" <%}%>>Piso</option>
                <option value="1" <% if(i.getTipo() == 1){ %> selected="selected" <%}%>>Casa</option>
                <option value="2" <% if(i.getTipo() == 2){ %> selected="selected" <%}%>>Estudio</option>
                <option value="3" <% if(i.getTipo() == 3){ %> selected="selected" <%}%>>Local</option>
            </select>
            <br />
            <output>Habitaciones: </output>
            <select name="habitaciones">
                <option value="0" <% if(i.getHabitaciones() == 0){ %> selected="selected" <%}%>>1</option>
                <option value="1" <% if(i.getHabitaciones() == 1){ %> selected="selected" <%}%>>2</option>
                <option value="2" <% if(i.getHabitaciones() == 2){ %> selected="selected" <%}%>>3</option>
                <option value="3" <% if(i.getHabitaciones() == 3){ %> selected="selected" <%}%>>4</option>
                <option value="4" <% if(i.getHabitaciones() == 4){ %> selected="selected" <%}%>>5</option>
            </select>
            <br/>
            <output>Precio: </output>
            <input type="text" name="precio" value="<%= i.getPrecio() %>" />
            <br/>
            <output>Usuario: </output>
            <input type="text" name="usuario" value="<%= i.getUsuario() %>" />
            <br/>
            <input type="hidden" name="target" value="inmueble" />
            <input type="hidden" name="op" value="update" />
            <input type="hidden" name="action" value="op" />
            <input type="submit" value="editar" />
        </form>
    </body>
</html>
