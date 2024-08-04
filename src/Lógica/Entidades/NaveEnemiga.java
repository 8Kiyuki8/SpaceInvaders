package Lógica.Entidades;


import Presentación.Ventanas.VentanaJuego;

import java.awt.*;

public class NaveEnemiga extends Nave {
  private static final int VELOCIDAD_NAVE = 2;
  private static final int VELOCIDAD_MISIL_NAVE = 4;
  private final int puntajeNave;

  public NaveEnemiga(Posición posición, int puntajeNave) {
    super(posición, VELOCIDAD_NAVE);
    this.puntajeNave = puntajeNave;
  }

  public int obtenerPuntos() {
    return puntajeNave;
  }

  public Misil disparar() {
    return new Misil(
      new Posición(obtenerPosición().obtenerPosiciónX(), obtenerPosición().obtenerPosiciónY()), VELOCIDAD_MISIL_NAVE);
  }

  public boolean colisionaConMisilNaveJugador(Misil misil) {
    int hitBox = 24;
    Rectangle rectNaveEnemiga = new Rectangle(obtenerPosición().obtenerPosiciónX(),
      obtenerPosición().obtenerPosiciónY(),
      VentanaJuego.obtenerTamañoEntidad() - hitBox,
      VentanaJuego.obtenerTamañoEntidad() - hitBox
    );
    Rectangle rectMisilNaveJugador = new Rectangle(misil.obtenerPosiciónMisil().obtenerPosiciónX(),
      misil.obtenerPosiciónMisil().obtenerPosiciónY(),
      VentanaJuego.obtenerTamañoEntidad() - hitBox,
      VentanaJuego.obtenerTamañoEntidad() - hitBox
    );
    return rectNaveEnemiga.intersects(rectMisilNaveJugador);
  }

}
