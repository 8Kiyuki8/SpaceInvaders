package Presentación.Pintores;

import java.awt.*;

public class PintorBarrera extends PintorEntidad {
  public static final int NÚMERO_MÁXIMO_SPRITES_ENTIDAD = 1;

  public PintorBarrera(String nombre) {
    super(nombre);
  }

  public void dibujar(Graphics2D graphics2D, int posiciónX, int posiciónY) {
    graphics2D.drawImage(obtenerImágenesNaveEntidad().get(obtenerÍndiceActualImagen()), posiciónX,
      posiciónY,
      null);
  }

  @Override
  public int obtenerNúmeroMáximoDeSprite() {
    return NÚMERO_MÁXIMO_SPRITES_ENTIDAD;
  }

  @Override
  public void actualizarImagenEntidad() {
    long currentTime = System.nanoTime();
    if ((currentTime - obtenerÚltimaActualización()) >= 100000000) {
      cambiarÚltimaActualización(currentTime);
    }
  }
}
