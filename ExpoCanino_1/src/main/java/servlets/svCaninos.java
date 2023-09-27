package servlets;

import com.umariana.expocanino_1.Perro;
import com.umariana.expocanino_1.Persistencia;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;


/**
 * 
 *
 * @author esney
 */

// Este servlet permite añadir nuevos perros a una lista.

@MultipartConfig
@WebServlet(name = "svCaninos", urlPatterns = {"/svCaninos"})
public class svCaninos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
  
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }
// Inicio del método doPost()
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {                                
        //metodos para guardar las sesiones que almacenaran los datos del perrito y la ruta de la ejecucion
        HttpSession session = request.getSession();
        ServletContext context = getServletContext();
        //creacion del array para guardar los datos de los perritos
        ArrayList<Perro> misPerros = new ArrayList<>();
        //se intenta cargar la lista de perros desde un archivo
        try 
        {
            Persistencia.lectura(misPerros, context);
           //si se produce un error al leer el archivo, se captura la excepción y se registra un mensaje de error
        }    
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(svCaninos.class.getName()).log(Level.SEVERE, null, ex);
        }
        //aqui se recibiran los datos ingresados en el formulario
        String nombre = request.getParameter("nombre");
        String raza = request.getParameter("raza");       
        //obtener la parte del archivo o sea la foto
        Part filePart = request.getPart("foto");        
        String puntos = request.getParameter("puntos");
        String edad = request.getParameter("edad");
        //metodo realiazado para guardar la imagen y almacenarla, directorio
        String uploadPath = context.getRealPath("/foto");
        //metodo que recibe la imagen anterior
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();  
        //ruta completa del archivo a guardar
        String filePath = uploadPath + File.separator + fileName;
        //lee el archivo de imagen desde la solicitud y lo escribe en el servidor, guardando el archivo en el sistema de archivos
        try (InputStream fileContent = filePart.getInputStream(); 
                FileOutputStream outputStream = new FileOutputStream(filePath)) 
        {
            int read;
            //creacion un búfer para almacenar los bytes leídos del archivo de imagen
            byte[] buffer = new byte[1024];            
            while ((read = fileContent.read(buffer)) != -1) 
            {
                outputStream.write(buffer, 0, read);
            }
        }
        // creacion del objeto perro para que se guarden sus datos, creados de esta manera para poder guardarlos bien
        Perro perro = new Perro(nombre, raza, fileName, Integer.parseInt(puntos), Integer.parseInt(edad));
        misPerros.add(perro);
        //permite escribir los datos de los perritos que se guardaron en el objeto
        Persistencia.escritura(misPerros, context);
        session.setAttribute("misPerros", misPerros);
        //este metodo regresa al index o pagina principal
        response.sendRedirect("index.jsp");      
        // Fin del método doPost()
    }              
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }
        // </editor-fold>
}
