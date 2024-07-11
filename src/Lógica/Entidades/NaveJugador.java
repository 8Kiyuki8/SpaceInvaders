package Lógica.Entidades;

import Lógica.Servicios.AdministradorEventoTeclas;

public class NaveJugador extends Nave {
  private MovimientoJugador movimiento;

  public NaveJugador(AdministradorEventoTeclas administradorTeclas) {
    movimiento = new MovimientoJugador(administradorTeclas);
  }

  public Movimiento obtenerMovimiento() {
    return movimiento;
  }
}
