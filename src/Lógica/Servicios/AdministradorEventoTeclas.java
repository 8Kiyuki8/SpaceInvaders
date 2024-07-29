package Lógica.Servicios;

import Lógica.Enumeraciones.AcciónUsuario;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class AdministradorEventoTeclas implements KeyListener {
  AcciónUsuario acción;

  private final HashMap<Integer, AcciónUsuario> ventanaJuegoEventoTeclas = new HashMap<>();

  public AdministradorEventoTeclas() {

    ventanaJuegoEventoTeclas.put(KeyEvent.VK_A, AcciónUsuario.IZQUIERDA);
    ventanaJuegoEventoTeclas.put(KeyEvent.VK_LEFT, AcciónUsuario.IZQUIERDA);
    ventanaJuegoEventoTeclas.put(KeyEvent.VK_D, AcciónUsuario.DERECHA);
    ventanaJuegoEventoTeclas.put(KeyEvent.VK_RIGHT, AcciónUsuario.DERECHA);
    //ventanaJuegoEventoTeclas.put(KeyEvent.VK_SPACE, AcciónUsuario.SHOOT);
    //ventanaJuegoEventoTeclas.put(KeyEvent.VK_UP, AcciónUsuario.UP);
    //ventanaJuegoEventoTeclas.put(KeyEvent.VK_DOWN, AcciónUsuario.ABAJO);
    //ventanaJuegoEventoTeclas.put(KeyEvent.VK_ENTER, AcciónUsuario.CONFIRM);
  }

  public AcciónUsuario obtenerAcción() {
    return acción;
  }

  public void limpiarAcción() {
    acción = null;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int códigoTecla = e.getKeyCode();
    if (ventanaJuegoEventoTeclas.containsKey(códigoTecla)) {
      acción = ventanaJuegoEventoTeclas.get(códigoTecla);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

}
