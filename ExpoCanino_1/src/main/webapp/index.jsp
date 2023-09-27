
<%@page import="java.util.ArrayList"%>
<%@page import="com.umariana.expocanino_1.Persistencia"%>
<%@page import="com.umariana.expocanino_1.Perro"%>

<!-- include para incluir un archivo dentro de otro, en este caso el header.  -->
<%@include file="templates/header.jsp"%>
<!-- agregar banner para interfaz principal -->
<nav class="navbar navbar-light bg-light">
    <a class="navbar-brand" href="#">
        <img src="imagenes/banner.jpg" width="1300" height="180" class="d-inline-block align-top" alt="">    
    </a>
</nav>
<!-- clase contenedora -->    
<div class="container p-4"> 
    <div class="row">           
        <!-- clase division por 4 columnas -->
        <div class="col-md-4"> 
            <div class="card card-body"> 
                <!-- tarjeta de trabajo o formulario, enctype que permite enviar archivos medidos en bytes, img-->
                <form action ="svCaninos" method = "POST" enctype="multipart/form-data">
                    <h3>Agregar nuevo perrito</h3>                    
                    <!-- Label e input para el nombre-->
                    <div class="input-group mb-3">
                        <label class="input-group-text" for="nombre">Nombre</label>
                        <input type="text" name="nombre" class="form-control" placeholder="Ingrese el nombre" aria-label="Ingrese el nombre" aria-describedby="basic-addon1" id="nombre" required="ingresa name">                                         
                      </div>                                            
                      <!-- Label e input para la raza-->
                      <div class="input-group mb-3">
                          <label class="input-group-text" for="raza">Raza</label>
                          <input type="text" name="raza" class="form-control" placeholder="Ingrese la raza" aria-label="Ingrese la raza" aria-describedby="basic-addon1" id="raza" required>
                      </div>
                      <!--Label e input para la foto-->                     
                      <div class="input-group mb-3">
                          <label class="input-group-text" for="foto">Foto</label>
                          <!-- tipo file para subir las imagenes, permite modificar boton y examinar en explorador -->
                          <input type="file" class="form-control"  name="foto" id="foto" required>
                      </div>
                      <!--Label e inputnput para los puntos-->                   
                      <div class="input-group mb-3">
                          <label class="input-group-text" for="puntos">Puntos</label>
                          <select class="form-select" name="puntos" aria-label="Default select example" id="puntos" required>
                              <option selected>Selecione...</option>
                              <option value="1">1</option>
                              <option value="2">2</option>
                              <option value="3">3</option>
                              <option value="4">4</option>
                              <option value="5">5</option>
                              <option value="6">6</option>
                              <option value="7">7</option>
                              <option value="8">8</option>     
                              <option value="9">9</option>
                              <option value="10">10</option>                          
                          </select>                  
                      </div>
                      <!-- Label e input para la edad-->
                      <div class="input-group mb-3">
                          <label class="input-group-text" for="edad">Edad</label>
                          <input type="text" name="edad" class="form-control" placeholder="Ingrese la edad" aria-label="la edad" aria-describedby="basic-addon1" id="edad" required>
                      </div>
                      <!-- boton de agregar el perrito --> 
                      <div class="text-center">
                          <button type="submit" class="btn btn-success mx-auto">Agregar perrito</button>
                      </div>
                        </form>               
                    </div>    
                </div> 
                <!-- Tabla de datos, agregamos diseño gris en table-light -->
                <div class="col-md-8">
                    <table class="table  table-bordered table-light">                        
                        <thead>
                            <tr  class="text-center"> 
                                <th>Nombre</th>                           
                                <th>Raza</th>
                                <th>Foto</th>
                                <th>Puntos</th>
                                <th>Edad</th>
                                <th>Acciones</th>                                                  
                            </tr>                    
                        </thead>        
                        <tbody>                              
                            <!-- se toma el array creado en el POST para los datos de los perritos-->
                            <%
                                ArrayList<Perro> misPerros = new ArrayList<>();                    
                                ServletContext context = getServletContext();
                                //con el metodo de lectura de la clase persistencia se trae la informacion del archivo
                                Persistencia.lectura(misPerros, context);                            
                                // if usado para saber si no hay informacion en el archivo o algo no deseado paso
                                if (misPerros != null) 
                                {
                                //siclo for para hacer que la tabla se extienda las veces necesarias segun los datos del formulario
                                for (Perro perro : misPerros) 
                                {
                                %>
                                <tr class="text-center">
                                    <!-- las extensiones para la sub tabla con los datos registrados en el form -->
                                    <td><%= perro.getNombre() %></td>
                                    <td><%= perro.getRaza() %></td>
                                    <td><img src="<%= request.getContextPath() %>/foto/<%= perro.getFoto() %>"  class="text-center" alt="foto" width="auto" height="70"></td>
                                    <td><%= perro.getPuntos() %></td>
                                    <td><%= perro.getEdad() %></td>
                                    <td>
                                        <!-- se agrego de esta manera los iconos para poderlos ver sin espaciados y en el mismo bloque<td> -->
                                        <a href="svCaninos?id=<%=perro.getNombre()%>" title="Ver detalle"><i class="fas fa-eye"></i></a>
                                        <a href="#" title="Editar"><i class="fas fa-pencil-alt"></i></a>
                                        <a href="#" title="Eliminar"><i class="fas fa-trash"></i></a>
                                    </td>
                                </tr>
                                <!-- cierre de el ciclo for e if-->
                                <%                        
                                    }
                                }           
                                %>     
                        </tbody>
                    </table>                       
                </div>
            </div>          
        </div>    
                        <!-- boostrap -->
                        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>                       
                        <!-- include para incluir un archivo dentro de otro, en este caso el footer.-->
                      
                        <!-- agregamos la ventana modal desde boostrap -->
                        <div class="modal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p>Modal body text goes here.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
                        
                        <%@include file= "templates/footer.jsp" %>
