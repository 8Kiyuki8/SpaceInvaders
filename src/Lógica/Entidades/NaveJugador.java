package Lógica.Entidades;

public class NaveJugador extends Nave {
  private static final int VELOCIDAD_NAVE = 10;

  public NaveJugador(Posición posición) {
    super(posición, VELOCIDAD_NAVE);
  }

  public Misil disparar() {
    return new Misil(
      new Posición(obtenerPosición().obtenerPosiciónX(), obtenerPosición().obtenerPosiciónY()));
  }

}
