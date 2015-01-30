/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import hibernate.Inmueble;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ModeloInmueble;

/**
 *
 * @author 2dam
 */
@WebServlet(name = "Controlador", urlPatterns = {"/control"})
public class Controlador extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1º recibir los dator para decidir: getParameters()
        //2º decide quién sabe o debe responder en función de lo que ha recibido
        //3º genera información (a veces) para el que sepa responder: setAttributes()
        //4º decidir cómo transferir el control
        // redirección: se usa cuando hayamos hecho modificación de datos
        // forward dispatch: se usa el resto de los casos
        //5º transferir el control
        //do: insert, delete, update, select
        //target: tabla
        //action; view, do
        //view: vista
        String destino = "index.html";
        boolean forward = false;
        String target, op, action, view;
        
        target = request.getParameter("target");
        op = request.getParameter("op");
        action = request.getParameter("action");
        System.out.println("Target: " + target + " Op: " + op + " Action: " + action);
        //view = request.getParameter("view");
        
        if(target != null && op != null && action != null){
            if(target.equals("inmueble") && op.equals("select") && action.equals("view")){
                forward = true;
                destino = "WEB-INF/inmueble/index.jsp";
                request.setAttribute("datos", ModeloInmueble.get());
            }else if(target.equals("inmueble") && op.equals("delete") && action.equals("op")){
                forward = false;
                ModeloInmueble.delete(request.getParameter("id"));
                destino = "control?target=inmueble&op=select&action=view";
            }else if(target.equals("inmueble") && op.equals("insert") && action.equals("view")){
                forward = true;
                destino = "WEB-INF/inmueble/insert.jsp";
            }else if(target.equals("inmueble") && op.equals("insert") && action.equals("op")){
                forward = false;
                destino = "control?target=inmueble&op=select&action=view";
                Inmueble inmueble = new Inmueble();
                inmueble.setLocalidad(request.getParameter("localidad"));
                inmueble.setDireccion(request.getParameter("direccion"));
                inmueble.setTipo(Integer.parseInt(request.getParameter("tipo")));
                inmueble.setHabitaciones(Integer.parseInt(request.getParameter("habitaciones")));
                inmueble.setPrecio(Float.valueOf(request.getParameter("tipo")));
                inmueble.setUsuario(request.getParameter("usuario"));
                ModeloInmueble.insert(inmueble);
            }else if(target.equals("inmueble") && op.equals("update") && action.equals("view")){
                forward = true;
                destino = "WEB-INF/inmueble/update.jsp";
                request.setAttribute("datos", ModeloInmueble.get(request.getParameter("id")));
            }else if(target.equals("prueba") && op.equals("update") && action.equals("op")){
                forward = false;
                destino = "control?target=prueba&op=select&action=view";
                Inmueble inmueble = new Inmueble();
                //inmueble.setId(Integer.parseInt(request.getParameter("id"))); LOS CAMPOS QUE SEAN
                //inmueble.setCampo1(request.getParameter("campo1"));
                //inmueble.setCampo2(request.getParameter("campo2"));
                ModeloInmueble.update(inmueble);
            }
                
                
            /*if(target.equals("inmueble") && op.equals("insert") && action.equals("view")){
                String valor = target+op+action+view;
                //String valor = target+op+action+view;
                request.setAttribute("valorcalculado", valor);
                forward = true;
                destino = "inmueble/viewinsert.jsp";
            }else if(target.equals("inmueble") && op.equals("insert") && action.equals("op")){
                //modelo para insertar
                forward = false;
                destino = "inmueble/index.jsp";
            }*/
            if(forward)
                request.getRequestDispatcher(destino).forward(request, response);
            else
                response.sendRedirect(destino);
        }
            
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
