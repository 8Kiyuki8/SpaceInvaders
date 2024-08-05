package Lógica.Interfaces;

import Lógica.Entidades.NaveJugador;
import Lógica.Entidades.Posición;

public interface PowerUp {
  void establecerPowerUp(NaveJugador naveJugador);

  void caerPowerUp();

  Posición obtenerPosiciónPowerUp();
}
