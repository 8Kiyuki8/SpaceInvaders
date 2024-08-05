package Lógica.Entidades;

import Presentación.Ventanas.VentanaJuego;

import java.awt.*;

public class Barrera {
  private final Posición posición;
  private int vidas = 15;

  public Barrera(Posición posición) {
    this.posición = posición;
  }

  public Posición obtenerPosición() {
    return posición;
  }

  public Rectangle obtenerÁreaColisión() {
    int hitBox = 24;
    return new Rectangle(obtenerPosición().obtenerPosiciónX(),
      obtenerPosición().obtenerPosiciónY(),
      VentanaJuego.obtenerTamañoEntidad() - hitBox,
      VentanaJuego.obtenerTamañoEntidad() - hitBox
    );
  }

  public void reducirVida() {
    vidas = vidas - 1;
  }

  public int obtenerVida() {
    return vidas;
  }
}
