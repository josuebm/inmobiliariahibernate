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
            <input type="hidden" name="id" value="<%= i.getId() %>" />
            <output>Localidad: </output>
            <input type="text" name="localidad" value="<%= i.getLocalidad()%>" />
            <br/>
            <output>Dirección: </output>
            <input type="text" name="direccion" value="<%= i.getDireccion() %>" />
            <br/>
            <output>Tipo: </output>
            <select name="tipo">
                <option value="0" >Piso</option>
                <option value="1" >Casa</option>
                <option value="2" >Estudio</option>
                <option value="3" >Local</option>
            </select>
            <br />
            <output>Habitaciones: </output>
            <select name="habitaciones">
                <option value="0" >1</option>
                <option value="1" >2</option>
                <option value="2" >3</option>
                <option value="3" >4</option>
                <option value="4" >5</option>
            </select>
            <br/>
            <output>Precio: </output>
            <input type="text" name="precio" value="" />
            <br/>
            <output>Usuario: </output>
            <input type="text" name="usuario" value="" />
            <br/>
            
            <input type="text" name="campo1" value="<%= i.getCampo1() %>" />
            <br/>
            <input type="text" name="campo2" value="<%= i.getCampo2() %>" />
            <br/>
            <input type="hidden" name="target" value="prueba" />
            <input type="hidden" name="op" value="update" />
            <input type="hidden" name="action" value="op" />
            <input type="submit" value="editar" />
        </form>
    </body>
</html>
