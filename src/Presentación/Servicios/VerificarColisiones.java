package Presentación.Servicios;

import Lógica.Entidades.*;
import Presentación.Ventanas.VentanaJuego;

import java.util.ArrayList;

public class VerificarColisiones {

  public static void evitarColisionarConBordesDeLaPantalla(Posición posiciónEntidad) {
    if (posiciónEntidad.obtenerPosiciónX() < 0) {
      posiciónEntidad.establecerPosiciónX(0);
    } else if (posiciónEntidad.obtenerPosiciónX() > VentanaJuego.obtenerAnchoVentana() - VentanaJuego.obtenerTamañoEntidad()) {
      posiciónEntidad.establecerPosiciónX(VentanaJuego.obtenerAnchoVentana() - VentanaJuego.obtenerTamañoEntidad());
    }
  }

  public static boolean colisionaConBordesDeLaPantalla(int posiciónEntidadEnX) {
    return posiciónEntidadEnX == 0 || posiciónEntidadEnX == VentanaJuego.obtenerAnchoVentana() - VentanaJuego.obtenerTamañoEntidad();
  }

  public static void colisionaPowerUpConNaveJugador(ArrayList<PowerUpVida> powerUpVidas, NaveJugador naveJugador) {
    for (int i = powerUpVidas.size() - 1; i >= 0; i--) {
      PowerUpVida powerUp = powerUpVidas.get(i);
      if (powerUp.colisionaConNaveJugador(naveJugador)) {
        powerUp.establecerPowerUp(naveJugador);
        powerUpVidas.remove(i);
      }
    }
  }

  public static void colisionaJugadorConMisilDeEnemigos(ArrayList<Misil> misilesEnemigos, NaveJugador naveJugador) {
    for (int i = misilesEnemigos.size() - 1; i >= 0; i--) {
      Misil misilEnemigo = misilesEnemigos.get(i);
      if (naveJugador.colisionaConMisil(misilEnemigo)) {
        naveJugador.establecerVida(naveJugador.obtenerVida() - 1);
        misilesEnemigos.remove(i);
        if (naveJugador.obtenerVida() <= 0) {
          System.exit(0);
        }
      }
    }
  }

  public static void colisionaEnemigoConMisilDeJugador(NaveEnemiga[][] navesEnemigas, ArrayList<Misil> misilesJugador, NaveJugador naveJugador) {
    for (int i = 0; i < navesEnemigas.length; i++) {
      for (int j = 0; j < navesEnemigas[i].length; j++) {
        NaveEnemiga naveEnemiga = navesEnemigas[i][j];
        if (naveEnemiga != null) {
          for (Misil misil : misilesJugador) {
            if (naveEnemiga.colisionaConMisil(misil)) {
              naveJugador.establecerPuntos(naveJugador.obtenerPuntos() + naveEnemiga.obtenerPuntos());
              navesEnemigas[i][j] = null;
              misilesJugador.remove(misil);
              break;
            }
          }
        }
      }
    }
  }

}
