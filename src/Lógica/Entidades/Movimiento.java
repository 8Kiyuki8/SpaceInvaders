package Lógica.Entidades;

import Lógica.Enumeraciones.Dirección;

public abstract class Movimiento {

  public void moverDerecha(Posición posición, int desplazamientoEntidad) {
    posición.establecerPosiciónX(posición.obtenerPosiciónX() + desplazamientoEntidad);
  }

  public void moverIzquierda(Posición posición, int desplazamientoEntidad) {
    posición.establecerPosiciónX(posición.obtenerPosiciónX() - desplazamientoEntidad);
  }

  public abstract void actualizarMovimiento(Posición posición, Dirección dirección);
}
