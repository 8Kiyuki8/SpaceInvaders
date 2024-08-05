import Lógica.Entidades.NaveJugador;
import Lógica.Entidades.Posición;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovimientoJugadorTest {
  @Test
  public void Dado_UnJugador_Cuando_SeMueveALaDerecha_Entonces_AvanzaSeisPixelesHaciaLaDerecha() {
    NaveJugador jugador = new NaveJugador(
      new Posición(0, 0)
    );
    jugador.moverDerecha();
    assertEquals(6, jugador.obtenerPosición().obtenerPosiciónX());
  }

  @Test
  public void Dado_UnJugador_Cuando_SeMueveALaIzquierda_Entonces_AvanzaSeisPixelesHaciaLaIzquierda() {
    NaveJugador jugador = new NaveJugador(
      new Posición(0, 0)
    );
    jugador.moverIzquierda();
    assertEquals(-6, jugador.obtenerPosición().obtenerPosiciónX());
  }
}
