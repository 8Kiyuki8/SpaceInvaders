package Lógica.Entidades;

import Lógica.Enumeraciones.Dirección;
import Lógica.Servicios.AdministradorEventoTeclas;
import Lógica.Servicios.VerificadorColisiones;
import Presentación.Ventanas.VentanaAdministradora;

public class MovimientoJugador extends Movimiento {
  private static final int VELOCIDAD_JUGADOR_POR_DEFECTO = 5;
  private final AdministradorEventoTeclas administradorTeclas;
  private final int anchoNave = VentanaAdministradora.obtenerTamañoEntidad();
  private final int altoNave = VentanaAdministradora.obtenerTamañoEntidad();


  public MovimientoJugador(AdministradorEventoTeclas administradorEventoTeclas) {
    administradorTeclas = administradorEventoTeclas;
    establecerPosiciónX((VentanaAdministradora.obtenerAnchoVentana() / 2) - (anchoNave / 2));
    establecerPosiciónY(VentanaAdministradora.obtenerAltoVentana() - (altoNave * 2));
    velocidadEntidad = VELOCIDAD_JUGADOR_POR_DEFECTO;
  }

  @Override
  public void moverDerecha() {
    posiciónX += velocidadEntidad;
  }

  @Override
  public void moverIzquierda() {
    posiciónX -= velocidadEntidad;
  }

  @Override
  public void actualizarMovimiento() {
    Dirección dirección = administradorTeclas.obtenerDirecciónMovimiento();
    System.out.println(dirección);
    if (dirección == null) {
      return;
    }

    if (dirección == Dirección.DERECHA) {
      moverDerecha();
    } else if (dirección == Dirección.IZQUIERDA) {
      moverIzquierda();
    }

    VerificadorColisiones.verificarColisionesJugador(anchoNave, this);
  }
}
