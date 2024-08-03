package Presentación.Servicios;

import Presentación.Enumeraciones.AcciónUsuario;
import Presentación.Enumeraciones.EstadoDeLaVentana;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class AdministradorEventoTeclas implements KeyListener {
  private EstadoDeLaVentana estadoDeLaVentanaActual;
  private final HashMap<Integer, AcciónUsuario> mapaTeclas = new HashMap<>();
  private final HashMap<AcciónUsuario, Boolean> accionesActivas = new HashMap<>();
  AcciónUsuario acción = null;

  public AdministradorEventoTeclas() {
    estadoDeLaVentanaActual = EstadoDeLaVentana.PRINCIPAL;

    mapaTeclas.put(KeyEvent.VK_A, AcciónUsuario.IZQUIERDA);
    mapaTeclas.put(KeyEvent.VK_LEFT, AcciónUsuario.IZQUIERDA);
    mapaTeclas.put(KeyEvent.VK_D, AcciónUsuario.DERECHA);
    mapaTeclas.put(KeyEvent.VK_RIGHT, AcciónUsuario.DERECHA);
    mapaTeclas.put(KeyEvent.VK_SPACE, AcciónUsuario.DISPARAR);
    mapaTeclas.put(KeyEvent.VK_UP, AcciónUsuario.ARRIBA);
    mapaTeclas.put(KeyEvent.VK_DOWN, AcciónUsuario.ABAJO);
    mapaTeclas.put(KeyEvent.VK_ENTER, AcciónUsuario.CONFIRMAR);

    for (AcciónUsuario acción : AcciónUsuario.values()) {
      accionesActivas.put(acción, false);
    }
  }

  public void cambiarEstadoActualDeLaVentana(EstadoDeLaVentana nuevoEstado) {
    estadoDeLaVentanaActual = nuevoEstado;
  }

  public AcciónUsuario obtenerAcción() {
    for (AcciónUsuario acción : AcciónUsuario.values()) {
      if (accionesActivas.get(acción)) {
        return acción;
      }
    }
    return null;
  }

  public AcciónUsuario limpiarAcción() {
    return acción;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int códigoTecla = e.getKeyCode();
    if (mapaTeclas.containsKey(códigoTecla)) {
      AcciónUsuario acción = mapaTeclas.get(códigoTecla);
      accionesActivas.put(acción, true);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int códigoTecla = e.getKeyCode();
    if (mapaTeclas.containsKey(códigoTecla)) {
      AcciónUsuario acción = mapaTeclas.get(códigoTecla);
      accionesActivas.put(acción, false);
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

}
