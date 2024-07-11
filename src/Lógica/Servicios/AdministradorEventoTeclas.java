package Lógica.Servicios;

import Lógica.Enumeraciones.Dirección;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class AdministradorEventoTeclas implements KeyListener {
  private final HashMap<Integer, Dirección> teclaDirección = new HashMap<>();
  private Dirección direcciónMovimiento = null;

  public AdministradorEventoTeclas() {
    teclaDirección.put(KeyEvent.VK_A, Dirección.IZQUIERDA);
    teclaDirección.put(KeyEvent.VK_LEFT, Dirección.IZQUIERDA);
    teclaDirección.put(KeyEvent.VK_D, Dirección.DERECHA);
    teclaDirección.put(KeyEvent.VK_RIGHT, Dirección.DERECHA);
  }

  public Dirección obtenerDirecciónMovimiento() {
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
