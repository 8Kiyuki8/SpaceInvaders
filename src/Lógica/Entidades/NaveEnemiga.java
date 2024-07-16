package Lógica.Entidades;

public class NaveEnemiga extends Nave {
  private final MovimientoEnemigo movimiento;

  public NaveEnemiga() {
    movimiento = new MovimientoEnemigo();
  }

  public MovimientoEnemigo obtenerMovimiento() {
    return movimiento;
  }

}
