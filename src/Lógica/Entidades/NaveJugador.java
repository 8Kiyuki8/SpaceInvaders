package Lógica.Entidades;

import Lógica.Servicios.AdministradorEventoTeclas;

public class NaveJugador extends Nave {
  private final Movimiento movimiento;

  public NaveJugador(AdministradorEventoTeclas administradorTeclas) {
    movimiento = new MovimientoJugador(administradorTeclas);
  }

  public Movimiento obtenerMovimiento() {
    return movimiento;
  }
}
