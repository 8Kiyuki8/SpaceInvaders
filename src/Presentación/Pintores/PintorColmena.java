package Presentación.Pintores;

import Lógica.Entidades.NaveEnemiga;

import java.awt.*;
import java.util.ArrayList;

public class PintorColmena {
  private final ArrayList<PintorEntidad> pintorEnemigos = new ArrayList<>();

  public PintorColmena() {
    pintorEnemigos.add(new PintorEntidad("Azul", 4));
    pintorEnemigos.add(new PintorEntidad("IndexOutOfBounds", 4));
    pintorEnemigos.add(new PintorEntidad("NullPointerException", 4));
  }

  public void dibujar(Graphics2D graphics2D, NaveEnemiga[][] navesEnemigas) {
    for (int fila = 0; fila < navesEnemigas.length; fila++) {
      for (int columna = 0; columna < navesEnemigas[fila].length; columna++) {
        NaveEnemiga naveEnemiga = navesEnemigas[fila][columna];
        if (naveEnemiga != null) {
          int posiciónX = naveEnemiga.obtenerPosición().obtenerPosiciónX();
          int posiciónY = naveEnemiga.obtenerPosición().obtenerPosiciónY();
          pintorEnemigos.get(fila).dibujar(graphics2D, posiciónX, posiciónY);
          pintorEnemigos.get(fila).actualizarImagenEntidad(48, 4);
        }
      }
    }
  }
}
