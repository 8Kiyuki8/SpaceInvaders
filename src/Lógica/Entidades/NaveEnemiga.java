package Lógica.Entidades;

import Presentación.Ventanas.VentanaAdministradora;
import Presentación.Ventanas.VentanaJuego;

public class NaveEnemiga extends Nave {
  private static final int VELOCIDAD_NAVE = 3;

  public NaveEnemiga(Posición posición) {
    super(posición, VELOCIDAD_NAVE);
  }

  public Misil disparar() {
    return new Misil(
      new Posición(obtenerPosición().obtenerPosiciónX(), obtenerPosición().obtenerPosiciónY()));
  }

  public boolean colisionaConMisil(Misil misil) {
    int naveX = obtenerPosición().obtenerPosiciónX();
    int naveY = obtenerPosición().obtenerPosiciónY();
    int misilX = misil.obtenerPosiciónMisil().obtenerPosiciónX();
    int misilY = misil.obtenerPosiciónMisil().obtenerPosiciónY();

    return misilX >= naveX && misilX <= naveX + VentanaJuego.obtenerTamañoEntidad() &&
      misilY >= naveY && misilY <= naveY + VentanaJuego.obtenerTamañoEntidad();
  }

}
