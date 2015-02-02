/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import hibernate.Foto;
import hibernate.Inmueble;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.ModeloFoto;
import modelo.ModeloInmueble;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author 2dam
 */
@WebServlet(name = "Controlador", urlPatterns = {"/control"})
@MultipartConfig
public class Controlador extends HttpServlet {

    boolean error;

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

        if (target != null && op != null && action != null) {
            switch (target) {
                case "inmueble": {
                    if (target.equals("inmueble") && op.equals("select") && action.equals("view")) {
                        forward = true;
                        destino = "WEB-INF/inmueble/index.jsp";
                        request.setAttribute("datos", ModeloInmueble.get());
                    } else if (target.equals("inmueble") && op.equals("delete") && action.equals("op")) {
                        forward = false;
                        ModeloInmueble.delete(request.getParameter("id"));
                        destino = "control?target=inmueble&op=select&action=view";
                    } else if (target.equals("inmueble") && op.equals("insert") && action.equals("view")) {
                        forward = true;
                        destino = "WEB-INF/inmueble/insert.jsp";
                    } else if (target.equals("inmueble") && op.equals("insert") && action.equals("op")) {
                        forward = false;
                        destino = "control?target=inmueble&op=select&action=view";
                        Inmueble inmueble = new Inmueble();
                        inmueble.setLocalidad(request.getParameter("localidad"));
                        inmueble.setDireccion(request.getParameter("direccion"));
                        inmueble.setTipo(Integer.parseInt(request.getParameter("tipo")));
                        inmueble.setHabitaciones(Integer.parseInt(request.getParameter("habitaciones")));
                        inmueble.setPrecio(Float.valueOf(request.getParameter("precio")));
                        inmueble.setUsuario(request.getParameter("usuario"));
                        ModeloInmueble.insert(inmueble);
                    } else if (target.equals("inmueble") && op.equals("update") && action.equals("view")) {
                        forward = true;
                        destino = "WEB-INF/inmueble/update.jsp";
                        request.setAttribute("datos", ModeloInmueble.get(request.getParameter("id")));
                    } else if (target.equals("inmueble") && op.equals("update") && action.equals("op")) {
                        forward = false;
                        destino = "control?target=inmueble&op=select&action=view";
                        Inmueble inmueble = new Inmueble();
                        inmueble.setId(Integer.parseInt(request.getParameter("id")));
                        inmueble.setLocalidad(request.getParameter("localidad"));
                        inmueble.setDireccion(request.getParameter("direccion"));
                        inmueble.setTipo(Integer.parseInt(request.getParameter("tipo")));
                        inmueble.setHabitaciones(Integer.parseInt(request.getParameter("habitaciones")));
                        inmueble.setPrecio(Float.valueOf(request.getParameter("precio")));
                        inmueble.setUsuario(request.getParameter("usuario"));
                        ModeloInmueble.update(inmueble);
                    }
                }
                break;
                case "foto": {
                    if (target.equals("foto") && op.equals("select") && action.equals("view")) {
                        forward = true;
                        destino = "WEB-INF/foto/index.jsp";
                        request.setAttribute("idinmueble", request.getParameter("idinmueble"));
                    } else if (target.equals("foto") && op.equals("delete") && action.equals("op")) {//HACER ESTO!!
                        forward = false;
                        Foto foto = ModeloFoto.get(request.getParameter("id"));
                        File file = new File(foto.getRuta());
                        if (file.exists()) {
                            file.delete();
                        }
                        ModeloFoto.delete(request.getParameter("id"));
                        destino = "control?target=foto&op=select&action=view&idinmueble=" + request.getParameter("idinmueble");
                    } else if (target.equals("foto") && op.equals("insert") && action.equals("view")) {
                        forward = true;
                        destino = "WEB-INF/foto/insert.jsp";
                        request.setAttribute("datos", request.getParameter("idinmueble"));
                    } else if (target.equals("foto") && op.equals("insert") && action.equals("op")) {
                        forward = false;
                        error = false;
                        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
                        String fileName = getFileName(filePart);
                        InputStream fileContent = filePart.getInputStream();
                        String idinmueble = request.getParameter("idinmueble");
                        String destinoFoto = getServletContext().getRealPath("/") + "subir/subido/Inmueble_" + idinmueble;
                        String ruta = guardarImagen(fileContent, fileName, destinoFoto, idinmueble);
                        Foto foto = new Foto();
                        foto.setInmueble(ModeloInmueble.get(idinmueble));
                        foto.setRuta(ruta);
                        response.setContentType("application/json");
                        if(!error)
                            ModeloFoto.insert(foto);
                        else
                            System.out.println("ERRORRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
                        destino = "control?target=foto&op=select&action=view&idinmueble=" + idinmueble;
                    }
                }
            }
            if (forward) {
                request.getRequestDispatcher(destino).forward(request, response);
            } else {
                response.sendRedirect(destino);
            }
        }

    }

    private String guardarImagen(InputStream fileContent, String fileName, String destino, String idinmueble) {
        try {
            GregorianCalendar fecha = new GregorianCalendar();
            String nombre;
            String year = String.valueOf(fecha.get(Calendar.YEAR));
            String mes = String.valueOf(fecha.get(Calendar.MONTH));
            if (mes.length() == 1) {
                mes = "0" + mes;
            }
            String dia = String.valueOf(fecha.get(Calendar.DAY_OF_MONTH));
            if (dia.length() == 1) {
                dia = "0" + dia;
            }
            String hora = String.valueOf(fecha.get(Calendar.HOUR_OF_DAY));
            if (hora.length() == 1) {
                hora = "0" + hora;
            }
            String minuto = String.valueOf(fecha.get(Calendar.MINUTE));
            if (minuto.length() == 1) {
                minuto = "0" + minuto;
            }
            String segundo = String.valueOf(fecha.get(Calendar.SECOND));
            if (segundo.length() == 1) {
                segundo = "0" + segundo;
            }
            nombre = "_" + year + "_" + mes + "_" + dia + "_" + hora + "_" + minuto + "_" + segundo;
            BufferedImage img = ImageIO.read(fileContent);
            if (fileName.endsWith("png")) {
                ImageIO.write(img, "png", new File(destino + nombre + ".png"));
                return destino + nombre + ".png";
            } else if (fileName.endsWith("jpg")) {
                ImageIO.write(img, "jpg", new File(destino + nombre + ".jpg"));
                return destino + nombre + ".jpg";
            }
        } catch (Exception ex) {
            error = true;
            System.out.println(ex.toString());
        }
        return null;
    }

    private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    public void eliminarFotos(List<Foto> fotos) {
        File file = null;
        for (int i = 0; i < fotos.size(); i++) {
            file = new File(fotos.get(i).getRuta());
            if (file.exists()) {
                file.delete();
            }
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
