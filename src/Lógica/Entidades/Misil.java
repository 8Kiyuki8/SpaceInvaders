package Lógica.Entidades;

import Lógica.MovimientoEntidades.MovimientoAbajo;
import Lógica.MovimientoEntidades.MovimientoArriba;

public class Misil {
  private final MovimientoArriba movimientoArriba;
  private final MovimientoAbajo movimientoAbajo;

  private Posición posicion;

  public Misil(Posición posicion) {
    this.posicion = posicion;
    this.movimientoArriba = new MovimientoArriba();
    this.movimientoAbajo = new MovimientoAbajo();
  }

  public Posición obtenerPosiciónMisil() {
    return posicion;
  }

  public void dispararArriba() {
    movimientoArriba.mover(posicion);
  }

  public void dispararAbajo() {
    movimientoAbajo.mover(posicion);
  }
}
