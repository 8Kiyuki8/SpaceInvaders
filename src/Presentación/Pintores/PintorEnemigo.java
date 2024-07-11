package Presentación.Pintores;

import Presentación.Ventanas.VentanaAdministradora;

import java.awt.*;
import java.io.*;
import java.util.*;

public class PintorEnemigo extends PintorEntidad {
  private int[][] mapaNúmerosEntidad;

  public PintorEnemigo(String nombre) {
    super(nombre);
    mapaNúmerosEntidad =
      new int[VentanaAdministradora.obtenerAnchoVentana()][VentanaAdministradora.obtenerAltoVentana()];
    cargarMapaEnemigo("/Presentación/Recursos/Mapa/mapa_enemigos.txt");
  }

  public void cargarMapaEnemigo(String mapa) {
    try {
      InputStream inputStream = getClass().getResourceAsStream(mapa);
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

      ArrayList<int[]> líneasNúmerosInt = new ArrayList<>();
      String línea;
      while ((línea = reader.readLine()) != null) {
        String[] númerosInt = línea.split(" ");
        int[] líneaNúmerosInt = Arrays.stream(númerosInt).mapToInt(Integer::parseInt).toArray();
        líneasNúmerosInt.add(líneaNúmerosInt);
      }
      reader.close();

      mapaNúmerosEntidad = líneasNúmerosInt.toArray(new int[0][]);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void dibujar(Graphics2D graphics2D, Integer índice, int posiciónX, int posiciónY) {
    for (int fila = 0; fila < mapaNúmerosEntidad.length; fila++) {
      for (int columna = 0; columna < mapaNúmerosEntidad[fila].length; columna++) {
        int númeroEntidad = mapaNúmerosEntidad[fila][columna];
        if (númeroEntidad != 0 && índice == númeroEntidad) {
          int posiciónEntidadX = columna * VentanaAdministradora.obtenerTamañoEntidad();
          int posiciónEntidadY = fila * VentanaAdministradora.obtenerTamañoEntidad();

          graphics2D.drawImage(obtenerImágenesNaveEntidad().get(obtenerÍndiceActualImagen()),
            posiciónEntidadX,
            posiciónEntidadY, null);
        }
      }
    }
  }
}
