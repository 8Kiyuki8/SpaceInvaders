package Presentación.Servicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.net.URISyntaxException;

public class AdministradorArchivos {
  public String obtenerURLRecurso(String nombreCarpeta, int númeroSprite) {
    String ruta = "/Presentación/Recursos/" + nombreCarpeta + "/" + nombreCarpeta.toLowerCase() + "_" +
      númeroSprite + ".png";

    try {
      URL url = getClass().getResource(ruta);
      if (url == null) {
        throw new FileNotFoundException("El URL no existe: " + ruta);
      }

      File archivo = new File(url.toURI());
      if (!archivo.exists()) {
        throw new FileNotFoundException("El archivo no existe: " + ruta);
      }

      return ruta;
    } catch (FileNotFoundException e) {
      System.out.println("Error: " + e.getMessage());
      return null;
    } catch (URISyntaxException e) {
      System.out.println("Error de dirección de URL: " + e.getMessage());
      return null;
    }
  }
}
