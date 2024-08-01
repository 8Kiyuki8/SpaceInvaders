package Lógica.Entidades;

import Presentación.Ventanas.VentanaAdministradora;

public class Colmena {
  private boolean moverDerecha = true;
  private Posición posición;

  public Colmena(Posición posición) {
    this.posición = posición;
  }

  public NaveEnemiga[][] generarColmenaEnemigos(int filas, int columnas) {
    NaveEnemiga[][] colmenaEnemigos = new NaveEnemiga[filas][columnas];
    for (int i = 0; i < colmenaEnemigos.length; i++) {
      for (int j = 0; j < colmenaEnemigos[0].length; j++) {
        colmenaEnemigos[i][j] = new NaveEnemiga(
          new Posición(
            (j * VentanaAdministradora.obtenerTamañoEntidad()) + posición.obtenerPosiciónX(),
            (i * VentanaAdministradora.obtenerTamañoEntidad() + posición.obtenerPosiciónY())
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

}
