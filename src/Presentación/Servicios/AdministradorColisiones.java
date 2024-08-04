package Presentación.Servicios;

import Lógica.Entidades.*;
import Presentación.Ventanas.VentanaJuego;

import java.util.ArrayList;

public class AdministradorColisiones {

  public static void evitarColisionarConBordesDeLaPantallaJugador(NaveJugador naveJugador) {
    if (naveJugador.obtenerPosición().obtenerPosiciónX() < 0) {
      naveJugador.obtenerPosición().establecerPosiciónX(0);
    } else if (naveJugador.obtenerPosición().obtenerPosiciónX() > VentanaJuego.obtenerAnchoVentana() - VentanaJuego.obtenerTamañoEntidad()) {
      naveJugador.obtenerPosición().establecerPosiciónX(VentanaJuego.obtenerAnchoVentana() - VentanaJuego.obtenerTamañoEntidad());
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

  public static void colisionaOvniConMisilDeNaveJugador(ArrayList<Ovni> ovnis, ArrayList<Misil> misilesNaveJugador, NaveJugador naveJugador) {
    for (int i = misilesNaveJugador.size() - 1; i >= 0; i--) {
      Misil misilNaveJugador = misilesNaveJugador.get(i);
      for (int j = ovnis.size() - 1; j >= 0; j--) {
        Ovni ovni = ovnis.get(j);
        if (ovni.colisionaConMisilNaveJugador(misilNaveJugador)) {
          naveJugador.establecerPuntos(naveJugador.obtenerPuntos() + ovni.obtenerPuntosEspeciales());
          ovnis.remove(j);
          misilesNaveJugador.remove(i);
          break;
        }
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
            if (naveEnemiga.colisionaConMisilNaveJugador(misil)) {
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

  public static void colisionaBarreraConMisilJugador(ArrayList<Misil> misilesJugador, Barrera[] barreras) {
    for (int i = 0; i < barreras.length; i++) {
      Barrera barrera = barreras[i];
      if (barrera != null) {
        for (Misil misil : misilesJugador) {
          if (barrera.colisionaConMisilNaveJugador(misil)) {
            barrera.reducirVida();
            misilesJugador.remove(misil);
            if (barrera.obtenerVida() < 1) {
              barreras[i] = null;
            }
            break;
          }
        }
      }
    }
  }
}

