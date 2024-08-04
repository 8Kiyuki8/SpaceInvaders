package Lógica.Entidades;

import Presentación.Ventanas.VentanaJuego;

import java.util.Random;

public class Colmena {
  private Posición posición;
  private NaveEnemiga[][] colmenaEnemigos;

  public Colmena(Posición posición) {
    this.posición = posición;
  }

  public Misil disparar() {
    Random random = new Random();
    NaveEnemiga naveEnemiga = null;
    int fila, columna;

    while (naveEnemiga == null) {
      fila = random.nextInt(colmenaEnemigos.length);
      columna = random.nextInt(colmenaEnemigos[0].length);
      naveEnemiga = colmenaEnemigos[fila][columna];
    }
    return naveEnemiga.disparar();
  }

  public NaveEnemiga[][] generarColmenaEnemigos(int filas, int columnas) {
    colmenaEnemigos = new NaveEnemiga[filas][columnas];
    for (int i = 0; i < colmenaEnemigos.length; i++) {
      for (int j = 0; j < colmenaEnemigos[0].length; j++) {
        colmenaEnemigos[i][j] = new NaveEnemiga(
          new Posición(
            (j * VentanaJuego.obtenerTamañoEntidad()) + posición.obtenerPosiciónX(),
            (i * VentanaJuego.obtenerTamañoEntidad() + posición.obtenerPosiciónY())
          )
        );
      }
    }
    return colmenaEnemigos;
  }

}
