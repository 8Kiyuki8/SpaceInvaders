package Lógica.Entidades;

import Lógica.MovimientoEntidades.MovimientoAbajo;
import Lógica.MovimientoEntidades.MovimientoDerecha;
import Lógica.MovimientoEntidades.MovimientoIzquierda;

public abstract class Nave {
  private Posición posición;
  private final MovimientoIzquierda movimientoIzquierda;
  private final MovimientoDerecha movimientoDerecha;
  private final MovimientoAbajo movimientoAbajo;

  public Nave(Posición nuevaPosición) {
    posición = nuevaPosición;
    movimientoIzquierda = new MovimientoIzquierda();
    movimientoDerecha = new MovimientoDerecha();
    movimientoAbajo = new MovimientoAbajo();
  }

  public Posición obtenerPosición() {
    return posición;
  }

  public void moverDerecha() {
    movimientoDerecha.mover(posición);
  }

  public void moverIzquierda() {
    movimientoIzquierda.mover(posición);
  }

  public void moverAbajo() {
    movimientoAbajo.mover(posición);
  }
}
