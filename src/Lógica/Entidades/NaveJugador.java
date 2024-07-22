package Lógica.Entidades;

public class NaveJugador extends Nave {
  private final Movimiento movimiento;

  public NaveJugador(Posición posición) {
    super(posición);
    movimiento = new MovimientoJugador();
  }

  public Movimiento obtenerMovimiento() {
    return movimiento;
  }
}
