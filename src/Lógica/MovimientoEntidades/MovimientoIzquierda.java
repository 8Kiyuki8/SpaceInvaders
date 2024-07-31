package Lógica.MovimientoEntidades;

import Lógica.Entidades.Posición;
import Lógica.Servicios.VerificadorColisiones;

public class MovimientoIzquierda implements Movimiento {

  @Override
  public void mover(Posición posiciónActual, int velocidad) {
    posiciónActual.establecerPosiciónX(posiciónActual.obtenerPosiciónX() - velocidad);
    VerificadorColisiones.verificarColisionesConLosBordes(posiciónActual);
  }
}
