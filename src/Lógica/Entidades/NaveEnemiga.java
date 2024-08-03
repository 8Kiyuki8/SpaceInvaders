package Lógica.Entidades;


import Presentación.Ventanas.VentanaJuego;

import java.awt.*;

public class NaveEnemiga extends Nave {
  private static final int VELOCIDAD_NAVE = 2;
  private static final int PUNTUACION_NAVE = 5;


  public NaveEnemiga(Posición posición) {
    super(posición, VELOCIDAD_NAVE);
  }

  public int obtenerPuntos() {
    return PUNTUACION_NAVE;
  }

  public Misil disparar() {
    return new Misil(
      new Posición(obtenerPosición().obtenerPosiciónX(), obtenerPosición().obtenerPosiciónY()));
  }

}
