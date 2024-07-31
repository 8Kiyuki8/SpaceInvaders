package Lógica.Entidades;

public class NaveEnemiga extends Nave {
  private static final int VELOCIDAD_NAVE = 3;

  public NaveEnemiga(Posición posición) {
    super(posición, VELOCIDAD_NAVE);
  }

}
