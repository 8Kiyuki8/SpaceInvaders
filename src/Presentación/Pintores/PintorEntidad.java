package Presentación.Pintores;

import Presentación.Servicios.AdministradorArchivos;

import java.awt.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class PintorEntidad extends Pintor {
  public static final int NÚMERO_MÁXIMO_SPRITES_ENTIDAD = 4;

  private final ArrayList<BufferedImage> imágenesNaveEntidad = new ArrayList<>();

  public PintorEntidad(String nombre) {
    super(nombre);
    configurarSpritesJugador();
  }

  private void configurarSpritesJugador() {
    AdministradorArchivos administradorArchivos = new AdministradorArchivos();
    for (int i = 0; i < NÚMERO_MÁXIMO_SPRITES_ENTIDAD; i += 1) {
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

  public abstract void dibujar(Graphics2D graphics2D, Integer enemyIndex, int xPos, int yPos);
}
