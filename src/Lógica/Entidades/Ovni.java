package Lógica.Entidades;

import Lógica.MovimientoEntidades.MovimientoDerecha;
import Presentación.Ventanas.VentanaJuego;

import java.awt.*;

public class Ovni {
  private static final int VELOCIDAD_OVNI = 2;
  private static final int PUNTUACION_OVNI = 50;
  private Posición posición;
  private final MovimientoDerecha movimientoDerecha;
  private static Ovni ovni;

  public Ovni(Posición posición) {
    this.posición = posición;
    this.movimientoDerecha = new MovimientoDerecha();
  }

  public static Ovni generarOvni() {
    ovni = new Ovni(
      new Posición(-VentanaJuego.obtenerTamañoEntidad(), 0
      ));
    return ovni;
  }

  public void moverOvni() {
    movimientoDerecha.mover(posición, VELOCIDAD_OVNI);
  }

  public Posición obtenerPosiciónOvni() {
    return posición;
  }

  public int obtenerPuntosEspeciales() {
    return PUNTUACION_OVNI;
  }

  public Rectangle obtenerÁrea() {
    int hitBox = 24;
    Rectangle rectOvni = new Rectangle(obtenerPosiciónOvni().obtenerPosiciónX(),
      obtenerPosiciónOvni().obtenerPosiciónY(),
      VentanaJuego.obtenerTamañoEntidad() - hitBox,
      VentanaJuego.obtenerTamañoEntidad() - hitBox
    );
    return rectOvni;
  }
}
