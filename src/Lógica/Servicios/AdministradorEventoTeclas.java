package Lógica.Servicios;

import Lógica.Enumeraciones.Movimiento;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class AdministradorEventoTeclas implements KeyListener {
  private final HashMap<Integer, Movimiento> teclaDirección = new HashMap<>();
  private Movimiento direcciónMovimiento = null;

  public AdministradorEventoTeclas() {
    teclaDirección.put(KeyEvent.VK_A, Movimiento.IZQUIERDA);
    teclaDirección.put(KeyEvent.VK_LEFT, Movimiento.IZQUIERDA);
    teclaDirección.put(KeyEvent.VK_D, Movimiento.DERECHA);
    teclaDirección.put(KeyEvent.VK_RIGHT, Movimiento.DERECHA);
  }

  public Movimiento obtenerDirecciónMovimiento() {
    return direcciónMovimiento;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int códigoTecla = e.getKeyCode();
    if (teclaDirección.containsKey(códigoTecla)) {
      direcciónMovimiento = teclaDirección.get(códigoTecla);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int códigoTecla = e.getKeyCode();
    if (teclaDirección.containsKey(códigoTecla)) {
      direcciónMovimiento = null;
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }
}
