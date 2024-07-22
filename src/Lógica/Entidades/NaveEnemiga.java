package Lógica.Entidades;

public class NaveEnemiga extends Nave {
  private final MovimientoEnemigo movimiento;

  public NaveEnemiga(Posición posición) {
    super(posición);
    movimiento = new MovimientoEnemigo();
  }

  public MovimientoEnemigo obtenerMovimiento() {
    return movimiento;
  }

}
