package Presentación.Pintores;

import Lógica.Entidades.NaveEnemiga;
import Lógica.Entidades.Posición;
import Presentación.Ventanas.VentanaAdministradora;

import java.awt.*;
import java.util.ArrayList;

public class PintorColmena {
  private final ArrayList<PintorEnemigo> pintorEnemigos = new ArrayList<>();

  public PintorColmena() {
    pintorEnemigos.add(new PintorEnemigo("Azul"));
    pintorEnemigos.add(new PintorEnemigo("IndexOutOfBounds"));
    pintorEnemigos.add(new PintorEnemigo("NullPointerException"));
  }

  public void dibujar(Graphics2D graphics2D, Posición posiciónColmena, NaveEnemiga[][] navesEnemigas) {
    for (int fila = 0; fila < navesEnemigas.length; fila++) {
      for (int columna = 0; columna < navesEnemigas[fila].length; columna++) {
        NaveEnemiga naveEnemiga = navesEnemigas[fila][columna];
        if (naveEnemiga != null) {
          int posiciónX = posiciónColmena.obtenerPosiciónX() + (columna * VentanaAdministradora.obtenerTamañoEntidad());
          int posiciónY = posiciónColmena.obtenerPosiciónY() + (fila * VentanaAdministradora.obtenerTamañoEntidad());
          pintorEnemigos.get(fila).dibujar(graphics2D, posiciónX, posiciónY);
          pintorEnemigos.get(fila).actualizarImagenEntidad();
        }
      }
    }
  }


}
