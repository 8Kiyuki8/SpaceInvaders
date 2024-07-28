package Lógica.Entidades;

import Presentación.Ventanas.VentanaAdministradora;

public class Colmena extends Nave {

  public Colmena(Posición posición) {
    super(posición);
  }

  public NaveEnemiga[][] generarColmenaEnemigos(int filas, int columnas) {
    NaveEnemiga[][] colmenaEnemigos = new NaveEnemiga[filas][columnas];
    for (int i = 0; i < colmenaEnemigos.length; i++) {
      for (int j = 0; j < colmenaEnemigos[0].length; j++) {
        colmenaEnemigos[i][j] = new NaveEnemiga(
          new Posición(
            (j * VentanaAdministradora.obtenerTamañoEntidad()),
            (i * VentanaAdministradora.obtenerTamañoEntidad())
          )
        );
      }
    }
    return colmenaEnemigos;
  }
}
