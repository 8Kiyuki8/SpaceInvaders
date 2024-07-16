package Lógica.Entidades;

public abstract class Movimiento {
  protected int posiciónX, posiciónY;
  protected int velocidadEntidad;

  public void establecerPosiciónX(int posiciónXPorDefecto) {
    this.posiciónX = posiciónXPorDefecto;
  }

  public void establecerPosiciónY(int posiciónYPorDefecto) {
    this.posiciónY = posiciónYPorDefecto;
  }

  public abstract void moverDerecha();

  public abstract void moverIzquierda();

  public int obtenerPosiciónX() {
    return posiciónX;
  }

  public int obtenerPosiciónY() {
    return posiciónY;
  }

  public abstract void actualizarMovimiento();
}
