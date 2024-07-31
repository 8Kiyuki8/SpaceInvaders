package Presentación.Pintores;

import Presentación.Servicios.AdministradorArchivos;

import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class PintorEntidad {
  protected int índiceActualImagen = 0;
  private long últimaActualización = System.nanoTime();
  private final String nombre;

  private final ArrayList<BufferedImage> imágenesNaveEntidad = new ArrayList<>();

  public PintorEntidad(String nombre) {
    this.nombre = nombre;
    configurarSpritesJugador();
  }

  public abstract void actualizarImagenEntidad();

  public abstract int obtenerNúmeroMáximoDeSprite();

  private void configurarSpritesJugador() {
    AdministradorArchivos administradorArchivos = new AdministradorArchivos();
    for (int i = 0; i < obtenerNúmeroMáximoDeSprite(); i += 1) {
      String fileName = administradorArchivos.obtenerURLRecurso(obtenerNombre(), i);
      try {
        imágenesNaveEntidad.add(ImageIO.read(
            Objects.requireNonNull(getClass().getResourceAsStream(fileName))
          )
        );
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public ArrayList<BufferedImage> obtenerImágenesNaveEntidad() {
    return imágenesNaveEntidad;
  }

  public int obtenerÍndiceActualImagen() {
    return índiceActualImagen;
  }

  public String obtenerNombre() {
    return nombre;
  }

  public long obtenerÚltimaActualización() {
    return últimaActualización;
  }

  public void cambiarÚltimaActualización(long últimaActualización) {
    this.últimaActualización = últimaActualización;
  }

  public void cambiarÍndiceActualImagen(int índiceActualImagen) {
    this.índiceActualImagen = índiceActualImagen;
  }

}
