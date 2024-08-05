package Presentación.Pintores;

import Presentación.Servicios.AdministradorArchivos;

import java.awt.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PintorEntidad {
  private int índiceActualImagen = 0;
  private long últimaActualización = System.nanoTime();
  private final String nombre;

  private final ArrayList<BufferedImage> imágenesNaveEntidad = new ArrayList<>();
  private final int númeroMáximoDeSprite;

  public PintorEntidad(String nombre, int númeroMáximoDeSprite) {
    this.nombre = nombre;
    this.númeroMáximoDeSprite = númeroMáximoDeSprite;
    configurarSprites();
  }

  public void dibujar(Graphics2D graphics2D, int posiciónX, int posiciónY) {
    graphics2D.drawImage(obtenerImágenesNaveEntidad().get(obtenerÍndiceActualImagen()), posiciónX,
      posiciónY,
      null);
  }

  public void actualizarImagenEntidad(int tiempoDeRenderizado, int numeroMáximoSpritesEntidad) {
    long tiempoActual = System.nanoTime();
    if ((tiempoActual - obtenerÚltimaActualización()) >= tiempoDeRenderizado * 10000000L) {
      cambiarÍndiceActualImagen((obtenerÍndiceActualImagen() + 1) % numeroMáximoSpritesEntidad);
      cambiarÚltimaActualización(tiempoActual);
    }
  }

  private void configurarSprites() {
    AdministradorArchivos administradorArchivos = new AdministradorArchivos();
    for (int i = 0; i < númeroMáximoDeSprite; i += 1) {
      String fileName = administradorArchivos.obtenerURLRecurso(nombre, i);
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
