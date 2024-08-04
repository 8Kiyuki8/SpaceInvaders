package Presentación.Pintores;

import Lógica.Entidades.NaveEnemiga;

import java.awt.*;
import java.util.ArrayList;

public class PintorColmena {
  private final ArrayList<PintorEnemigo> pintorEnemigos = new ArrayList<>();

  public PintorColmena() {
    pintorEnemigos.add(new PintorEnemigo("Azul"));
    pintorEnemigos.add(new PintorEnemigo("IndexOutOfBounds"));
    pintorEnemigos.add(new PintorEnemigo("NullPointerException"));
  }

  public void dibujar(Graphics2D graphics2D, NaveEnemiga[][] navesEnemigas) {
    for (int fila = 0; fila < navesEnemigas.length; fila++) {
      for (int columna = 0; columna < navesEnemigas[fila].length; columna++) {
        NaveEnemiga naveEnemiga = navesEnemigas[fila][columna];
        if (naveEnemiga != null) {
          int posiciónX = naveEnemiga.obtenerPosición().obtenerPosiciónX();
          int posiciónY = naveEnemiga.obtenerPosición().obtenerPosiciónY();
          pintorEnemigos.get(fila).dibujar(graphics2D, posiciónX, posiciónY);
          pintorEnemigos.get(fila).actualizarImagenEntidad();
        }
      }
    }
  }
}
