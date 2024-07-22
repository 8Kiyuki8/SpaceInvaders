package Lógica.Entidades;

public abstract class Nave {
  private Posición posición;

  public Nave(Posición nuevaPosición) {
    posición = nuevaPosición;
  }

  public Posición obtenerPosición() {
    return posición;
  }
}
