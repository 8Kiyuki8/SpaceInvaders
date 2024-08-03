package Lógica.MovimientoEntidades;

import Lógica.Entidades.Posición;
import Lógica.Interfaces.Movimiento;
import Presentación.Servicios.VerificarColisiones;

public class MovimientoIzquierda implements Movimiento {

  @Override
  public void mover(Posición posiciónActual, int velocidad) {
    posiciónActual.establecerPosiciónX(posiciónActual.obtenerPosiciónX() - velocidad);
    VerificarColisiones.colisionaConBordesDeLaPantalla(posiciónActual);
  }
}
