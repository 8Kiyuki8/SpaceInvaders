package Lógica.Entidades;

import Lógica.MovimientoEntidades.MovimientoAbajo;
import Lógica.MovimientoEntidades.MovimientoArriba;
import Presentación.Ventanas.VentanaJuego;

import java.awt.*;

public class Misil {
  private Posición posicion;
  private final MovimientoArriba movimientoArriba;
  private final MovimientoAbajo movimientoAbajo;
  private final int velocidad;

  public Misil(Posición posicion, int velocidad) {
    this.posicion = posicion;
    this.velocidad = velocidad;
    this.movimientoArriba = new MovimientoArriba();
    this.movimientoAbajo = new MovimientoAbajo();
  }

  public Posición obtenerPosiciónMisil() {
    return posicion;
  }

  public void dispararArriba() {
    movimientoArriba.mover(posicion, velocidad);
  }

  public void dispararAbajo() {
    movimientoAbajo.mover(posicion, velocidad);
  }

  public Rectangle obtenerÁrea() {
    int hitBox = 24;
    Rectangle rectMisil = new Rectangle(obtenerPosiciónMisil().obtenerPosiciónX(),
      obtenerPosiciónMisil().obtenerPosiciónY(),
      VentanaJuego.obtenerTamañoEntidad() - hitBox,
      VentanaJuego.obtenerTamañoEntidad() - hitBox
    );
    return rectMisil;
  }
}
