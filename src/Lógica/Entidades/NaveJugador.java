package Lógica.Entidades;

import Presentación.Ventanas.VentanaJuego;

import java.awt.*;

public class NaveJugador extends Nave {
  private static final int VELOCIDAD_NAVE = 6;
  public static final int MAXIMO_DE_VIDAS = 3;
  private static final int VELOCIDAD_MISIL_NAVE = 8;
  private int puntos;

  public NaveJugador(Posición posición) {
    super(posición, VELOCIDAD_NAVE, MAXIMO_DE_VIDAS);
    puntos = 0;
  }

  public Misil disparar() {
    return new Misil(
      new Posición(obtenerPosición().obtenerPosiciónX(), obtenerPosición().obtenerPosiciónY()), VELOCIDAD_MISIL_NAVE);
  }

  public int obtenerPuntos() {
    return puntos;
  }

  public void establecerPuntos(int puntos) {
    this.puntos = puntos;
  }

  @Override
  public void establecerVida(int nuevaVida) {
    if (nuevaVida >= MAXIMO_DE_VIDAS) {
      super.establecerVida(MAXIMO_DE_VIDAS);
    } else {
      super.establecerVida(nuevaVida);
    }
  }

  public boolean colisionaConMisil(Misil misil) {
    int hitBox = 24;
    Rectangle rectNaveJugador = new Rectangle(obtenerPosición().obtenerPosiciónX(),
      obtenerPosición().obtenerPosiciónY(),
      VentanaJuego.obtenerTamañoEntidad() - hitBox,
      VentanaJuego.obtenerTamañoEntidad() - hitBox
    );
    Rectangle rectMisilEnemigo = new Rectangle(misil.obtenerPosiciónMisil().obtenerPosiciónX(),
      misil.obtenerPosiciónMisil().obtenerPosiciónY(),
      VentanaJuego.obtenerTamañoEntidad() - hitBox,
      VentanaJuego.obtenerTamañoEntidad() - hitBox
    );
    return rectNaveJugador.intersects(rectMisilEnemigo);
  }

}
