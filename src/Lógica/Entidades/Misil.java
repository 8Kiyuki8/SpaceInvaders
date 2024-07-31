package Lógica.Entidades;

import Lógica.MovimientoEntidades.MovimientoAbajo;
import Lógica.MovimientoEntidades.MovimientoArriba;

public class Misil {
  private final MovimientoArriba movimientoArriba;
  private final MovimientoAbajo movimientoAbajo;
  private static final int VELOCIDAD_MISIL = 8;

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
    movimientoArriba.mover(posicion, VELOCIDAD_MISIL);
  }

  public void dispararAbajo() {
    movimientoAbajo.mover(posicion, VELOCIDAD_MISIL);
  }
}
