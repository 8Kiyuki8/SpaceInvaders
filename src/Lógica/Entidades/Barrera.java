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

  public boolean colisionaConMisilNaveJugador(Misil misil) {
    int hitBox = 24;
    Rectangle rectBarrera = new Rectangle(obtenerPosición().obtenerPosiciónX(),
      obtenerPosición().obtenerPosiciónY(),
      VentanaJuego.obtenerTamañoEntidad() - hitBox,
      VentanaJuego.obtenerTamañoEntidad() - hitBox
    );
    Rectangle rectMisil = new Rectangle(misil.obtenerPosiciónMisil().obtenerPosiciónX(),
      misil.obtenerPosiciónMisil().obtenerPosiciónY(),
      VentanaJuego.obtenerTamañoEntidad() - hitBox,
      VentanaJuego.obtenerTamañoEntidad() - hitBox
    );
    return rectBarrera.intersects(rectMisil);
  }

  public void reducirVida() {
    vidas = vidas - 1;
  }

  public int obtenerVida() {
    return vidas;
  }
}
