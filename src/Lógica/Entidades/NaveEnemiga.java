package Lógica.Entidades;

public class NaveEnemiga extends Nave {
  private static final int VELOCIDAD_NAVE = 3;

  public NaveEnemiga(Posición posición) {
    super(posición, VELOCIDAD_NAVE);
  }

  public Misil disparar() {
    return new Misil(
      new Posición(obtenerPosición().obtenerPosiciónX(), obtenerPosición().obtenerPosiciónY()));
  }

}
