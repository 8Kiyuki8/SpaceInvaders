package Lógica.Entidades;

import java.io.Serializable;

public class Posición implements Serializable {

  private int posiciónX;
  private int posiciónY;

  public Posición(int x, int y) {
    posiciónX = x;
    posiciónY = y;
  }

  public int obtenerPosiciónX() {
    return posiciónX;
  }

  public void establecerPosiciónX(int nuevaPosiciónX) {
    posiciónX = nuevaPosiciónX;
  }

  public int obtenerPosiciónY() {
    return posiciónY;
  }

  public void establecerPosiciónY(int nuevaPosiciónY) {
    posiciónY = nuevaPosiciónY;
  }
}
