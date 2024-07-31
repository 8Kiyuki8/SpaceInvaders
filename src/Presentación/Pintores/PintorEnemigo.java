package Presentación.Pintores;

import java.awt.*;

public class PintorEnemigo extends PintorEntidad {
  public static final int NÚMERO_MÁXIMO_SPRITES_ENTIDAD = 4;
  private static final int RENDERIZADO_ENEMIGO = 48;
  //private long últimaActualización = System.nanoTime();

  public PintorEnemigo(String nombre) {
    super(nombre);
  }

  public void dibujar(Graphics2D graphics2D, int xPos, int yPos) {
    graphics2D.drawImage(obtenerImágenesNaveEntidad().get(obtenerÍndiceActualImagen())
      , xPos, yPos, null);
  }

  @Override
  public void actualizarImagenEntidad() {
    long tiempoActual = System.nanoTime();
    if ((tiempoActual - obtenerÚltimaActualización()) >= RENDERIZADO_ENEMIGO * 10000000) {
      cambiarÍndiceActualImagen((obtenerÍndiceActualImagen() + 1) % NÚMERO_MÁXIMO_SPRITES_ENTIDAD);
      cambiarÚltimaActualización(tiempoActual);
    }
  }

  @Override
  public int obtenerNúmeroMáximoDeSprite() {
    return NÚMERO_MÁXIMO_SPRITES_ENTIDAD;
  }
}
