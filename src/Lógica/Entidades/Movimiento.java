package Lógica.Entidades;

public abstract class Movimiento {
  private int posiciónX, posiciónY;
  private final int velocidad = 4;

  public void establecerPosiciónX(int posiciónXPorDefecto) {
    posiciónX = posiciónXPorDefecto;
  }

  public void establecerPosiciónY(int posiciónYPorDefecto) {
    posiciónY = posiciónYPorDefecto;
  }

  public void moverDerecha() {
    posiciónX += velocidad;
  }

  public void moverIzquierda() {
    posiciónX -= velocidad;
  }

  public int obtenerPosiciónX() {
    return posiciónX;
  }

  public int obtenerPosiciónY() {
    return posiciónY;
  }

  public abstract void actualizarMovimiento();
}
