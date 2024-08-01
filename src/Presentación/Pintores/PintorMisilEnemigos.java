package Presentación.Pintores;

import java.awt.*;

public class PintorMisilEnemigos extends PintorEntidad {
  public static final int NÚMERO_MÁXIMO_SPRITES_ENTIDAD = 1;

  public PintorMisilEnemigos(String nombre) {
    super(nombre);

  }

  @Override
  public void actualizarImagenEntidad() {
    long currentTime = System.nanoTime();
    if ((currentTime - obtenerÚltimaActualización()) >= 100000000) {
      cambiarÍndiceActualImagen((obtenerÍndiceActualImagen() + 1) % NÚMERO_MÁXIMO_SPRITES_ENTIDAD);
      cambiarÚltimaActualización(currentTime);
    }
  }

  public void dibujar(Graphics2D graphics2D, int posiciónX, int posiciónY) {
    graphics2D.drawImage(obtenerImágenesNaveEntidad().get(obtenerÍndiceActualImagen()), posiciónX,
      posiciónY,
      null);
  }

  public int obtenerNúmeroMáximoDeSprite() {
    return NÚMERO_MÁXIMO_SPRITES_ENTIDAD;
  }
}
