package Lógica.Entidades;

import Lógica.MovimientoEntidades.MovimientoAbajo;
import Lógica.MovimientoEntidades.MovimientoDerecha;
import Lógica.MovimientoEntidades.MovimientoIzquierda;

public abstract class Nave {
  private Posición posición;
  private final MovimientoIzquierda movimientoIzquierda;
  private final MovimientoDerecha movimientoDerecha;
  private final MovimientoAbajo movimientoAbajo;
  private final int VELOCIDAD_NAVE;

  public Nave(Posición nuevaPosición, int VELOCIDAD_NAVE) {
    posición = nuevaPosición;
    movimientoIzquierda = new MovimientoIzquierda();
    movimientoDerecha = new MovimientoDerecha();
    movimientoAbajo = new MovimientoAbajo();
    this.VELOCIDAD_NAVE = VELOCIDAD_NAVE;
  }


  public Posición obtenerPosición() {
    return posición;
  }

  public void moverDerecha() {
    movimientoDerecha.mover(posición, VELOCIDAD_NAVE);
  }

  public void moverIzquierda() {
    movimientoIzquierda.mover(posición, VELOCIDAD_NAVE);
  }

  public void moverAbajo() {
    movimientoAbajo.mover(posición, VELOCIDAD_NAVE);
  }
}
