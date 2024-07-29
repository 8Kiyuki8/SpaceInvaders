package Lógica.MovimientoEntidades;

import Lógica.Entidades.Posición;
import Lógica.Servicios.VerificadorColisiones;

public class MovimientoIzquierda implements Movimiento {

  @Override
  public void mover(Posición posiciónActual) {
    posiciónActual.establecerPosiciónX(posiciónActual.obtenerPosiciónX() - velocidadMovimiento);
    VerificadorColisiones.verificarColisionesConLosBordes(posiciónActual);
  }
}
