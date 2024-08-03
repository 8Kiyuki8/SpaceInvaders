package Lógica.Entidades;

import Presentación.Ventanas.VentanaJuego;

import java.util.Random;

public class Colmena {
  private boolean moverDerecha = true;
  private Posición posición;
  private NaveEnemiga[][] colmenaEnemigos;
  private Random random = new Random();

  public Colmena(Posición posición) {
    this.posición = posición;
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

  public boolean obtenerDirección() {
    return moverDerecha;
  }

  public void cambiarDirección() {
    moverDerecha = !moverDerecha;
  }

  public Misil disparar() {
    int fila, columna;
    NaveEnemiga naveEnemiga = null;

    while (naveEnemiga == null) {
      fila = random.nextInt(colmenaEnemigos.length);
      columna = random.nextInt(colmenaEnemigos[0].length);
      naveEnemiga = colmenaEnemigos[fila][columna];
    }
    return naveEnemiga.disparar();
  }

}
