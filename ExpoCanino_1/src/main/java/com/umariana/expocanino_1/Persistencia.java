package com.umariana.expocanino_1;
//librerias importadas
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.servlet.ServletContext;

/**
 *
 * @author esney
 */
public class Persistencia
{
    //metodo de escritura 
    /**
     * en este metodo se realiza la escritura de la informacion registrada en el formulario para despues
     * guardarla en la ruta .ser ya que se uso serializacion
     * @param misPerros
     * @param context
     * @throws FileNotFoundException
     * @throws IOException 
     */
   public static void escritura(ArrayList<Perro> misPerros, ServletContext context)
           throws FileNotFoundException, IOException 
   {
        //usamos ruta relativa para generar el archivo, lo hicimos con el fin de optimizar
        String rutaRelativa = "/data/archivo.ser";
        String rutaAbsoluta = context.getRealPath(rutaRelativa);
        //creamos el archivo
        File archivo = new File(rutaAbsoluta);

        try 
            (FileOutputStream fos = new FileOutputStream(archivo); ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            for (Perro perro : misPerros)
            {
                oos.writeObject(perro);
            }
        }
        catch (IOException e) 
        {
            //alerta que se mostrara cuando el archivo no funciono
            System.out.println("Archivo no encontrado");
        }
    }
   //metodo de lectura
   /**
    * 
    * @param misPerros
    * @param context
    * @throws IOException
    * @throws ClassNotFoundException 
    */
    public static void lectura(ArrayList<Perro> misPerros, ServletContext context) throws IOException, ClassNotFoundException {
        // Ruta relativa y absoluta del archivo de datos serializados
        String rutaRelativa = "/data/archivo.ser";
        String rutaAbsoluta = context.getRealPath(rutaRelativa);
        File archivo = new File(rutaAbsoluta);
        //if que analiza el archivo y segun sus datos lo interpreta, si no hay datos entra al sout
        if (archivo.length() == 0)
        {
            System.out.println("Sin informacion que mostrar");
            return;
        }        
        try (FileInputStream fis = new FileInputStream(archivo); ObjectInputStream ois = new ObjectInputStream(fis)) 
        {
            //metodo de limpieza de datos
            misPerros.clear();
            while (true)
            {
                try 
                {                    
                    //usando el objeto del servlet verificamos lo siguiente
                    Perro perro = (Perro) ois.readObject();                   
                    misPerros.add(perro);
                } catch (EOFException e) 
                {                    
                    break;
                }
            }
        } catch (IOException e)
        {
            //alerta que saldra cuando el archivo no funcione como se esperaba
            System.out.println("El archivo no pudo ser leido");
        }
    }   
}
