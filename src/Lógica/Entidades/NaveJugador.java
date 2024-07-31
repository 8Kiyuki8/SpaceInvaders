package Lógica.Entidades;

public class NaveJugador extends Nave {
  public static final int VELOCIDAD_MISIL = 10;

  public NaveJugador(Posición posición) {
    super(posición);
  }

  public Misil disparar() {
    return new Misil(
      new Posición(obtenerPosición().obtenerPosiciónX(), obtenerPosición().obtenerPosiciónY()));
  }

}
