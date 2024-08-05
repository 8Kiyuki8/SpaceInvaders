import Lógica.Entidades.Misil;
import Lógica.Entidades.NaveEnemiga;
import Lógica.Entidades.NaveJugador;
import Lógica.Entidades.Posición;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PuntuaciónJugadorTest {
  @Test
  public void Dado_UnJugadorYUnEnemigo_CuandoJugadorEliminaAlEnemigo_Entonces_ObtieneSusPuntos() {
    boolean boolFlag = true;
    int puntosEnemigos = 0;

    NaveJugador jugador = new NaveJugador(
      new Posición(0, 0)
    );
    Misil misil = jugador.disparar();
    NaveEnemiga enemigo = new NaveEnemiga(
      new Posición(0, 0),
      10
    );

    while (boolFlag) {
      misil.dispararArriba();
      if (
        misil.obtenerPosiciónMisil().obtenerPosiciónY() ==
          enemigo.obtenerPosición().obtenerPosiciónY() &&
          misil.obtenerPosiciónMisil().obtenerPosiciónX() ==
            enemigo.obtenerPosición().obtenerPosiciónX()
      ) {
        puntosEnemigos += enemigo.obtenerPuntos();
        enemigo = null;
      }

      if (enemigo == null) {
        boolFlag = false;
      }
    }
    jugador.establecerPuntos(puntosEnemigos);

    assertEquals(10, jugador.obtenerPuntos());
  }

  @Test
  public void Dado_UnJugadorYUnEnemigo_CuandoJugadorEliminaAlEnemigo_Entonces_EnemigoEsNull() {
    boolean boolFlag = true;

    NaveJugador jugador = new NaveJugador(
      new Posición(0, 0)
    );
    Misil misil = jugador.disparar();
    NaveEnemiga enemigo = new NaveEnemiga(
      new Posición(0, 0),
      10
    );

    while (boolFlag) {
      misil.dispararArriba();
      if (
        misil.obtenerPosiciónMisil().obtenerPosiciónY() ==
          enemigo.obtenerPosición().obtenerPosiciónY() &&
          misil.obtenerPosiciónMisil().obtenerPosiciónX() ==
            enemigo.obtenerPosición().obtenerPosiciónX()
      ) {
        enemigo = null;
      }

      if (enemigo == null) {
        boolFlag = false;
      }
    }

    assertNull(enemigo);
  }
}
