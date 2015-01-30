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
        <form action="control?target=inmueble&op=insert&action=op" method="POST">
            <output>Localidad: </output>
            <input type="text" name="localidad" value="" />
            <br/>
            <output>Dirección: </output>
            <input type="text" name="direccion" value="" />
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
            <input type="hidden" name="target" value="inmueble" />
            <input type="hidden" name="op" value="insert" />
            <input type="hidden" name="action" value="op" />
            <input type="submit" value="Insertar" />
        </form>
    </body>
</html>
