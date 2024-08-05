package Lógica.Entidades;

import Lógica.MovimientoEntidades.MovimientoAbajo;
import Lógica.MovimientoEntidades.MovimientoDerecha;
import Lógica.MovimientoEntidades.MovimientoIzquierda;
import Presentación.Ventanas.VentanaJuego;

import java.awt.*;

public abstract class Nave {
  private final MovimientoIzquierda movimientoIzquierda;
  private final MovimientoDerecha movimientoDerecha;
  private final MovimientoAbajo movimientoAbajo;
  private Posición posición;
  private int vida;
  private int velocidad;

  public Nave(Posición nuevaPosición, int velocidad) {
    posición = nuevaPosición;
    movimientoIzquierda = new MovimientoIzquierda();
    movimientoDerecha = new MovimientoDerecha();
    movimientoAbajo = new MovimientoAbajo();
    this.velocidad = velocidad;
  }

  public Nave(Posición nuevaPosición, int velocidad, int vidaDeNave) {
    this(nuevaPosición, velocidad);
    this.vida = vidaDeNave;
  }

  public void establecerVida(int nuevaVida) {
    vida = nuevaVida;
  }

  public int obtenerVida() {
    return vida;
  }

  public Posición obtenerPosición() {
    return posición;
  }

  public void moverDerecha() {
    movimientoDerecha.mover(posición, velocidad);
  }

  public void moverIzquierda() {
    movimientoIzquierda.mover(posición, velocidad);
  }

  public void moverAbajo() {
    movimientoAbajo.mover(posición, velocidad);
  }

  public Rectangle obtenerÁrea() {
    int hitBox = 24;
    Rectangle rectNave = new Rectangle(obtenerPosición().obtenerPosiciónX(),
      obtenerPosición().obtenerPosiciónY(),
      VentanaJuego.obtenerTamañoEntidad() - hitBox,
      VentanaJuego.obtenerTamañoEntidad() - hitBox
    );
    return rectNave;
  }
}
