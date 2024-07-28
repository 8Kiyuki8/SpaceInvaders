package Presentación.Pintores;

import java.awt.*;

public class PintorJugador extends PintorEntidad {
  public PintorJugador(String nombre) {
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

  public void dibujar(Graphics2D graphics2D, Integer índice, int posiciónX, int posiciónY) {
    graphics2D.drawImage(obtenerImágenesNaveEntidad().get(obtenerÍndiceActualImagen()), posiciónX,
      posiciónY,
      null);
  }
}
