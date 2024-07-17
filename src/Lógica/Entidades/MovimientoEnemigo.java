package Lógica.Entidades;

import Lógica.Enumeraciones.Dirección;
import Lógica.Servicios.VerificadorColisiones;
import Presentación.Ventanas.VentanaAdministradora;

public class MovimientoEnemigo extends Movimiento {


  private final int altoEnemigo = VentanaAdministradora.obtenerTamañoEntidad();
  private final int anchoEnemigo = VentanaAdministradora.obtenerTamañoEntidad();
  private static final int VELOCIDAD_ENEMIGO_POR_DEFECTO = 2;
  private Dirección dirección;

  public MovimientoEnemigo() {

    establecerPosiciónX(0);
    establecerPosiciónY(0);
    dirección = Dirección.DERECHA;
    velocidadEntidad = VELOCIDAD_ENEMIGO_POR_DEFECTO;
  }

  @Override
  public void moverDerecha() {
    posiciónX += velocidadEntidad;
  }

  @Override
  public void moverIzquierda() {
    posiciónX -= velocidadEntidad;
  }

  public void moverAbajo() {
    posiciónY += velocidadEntidad;
  }

  @Override
  public void actualizarMovimiento() {
    if (dirección == Dirección.DERECHA) {
      moverDerecha();
    } else if (dirección == Dirección.IZQUIERDA) {
      moverIzquierda();
    }

    if (obtenerPosiciónX() < 0) {
      moverAbajo();
      if ((posiciónY % altoEnemigo == 0)) {
        dirección = Dirección.DERECHA;
      }
    } else if (obtenerPosiciónX() > VentanaAdministradora.obtenerAnchoVentana() - anchoEnemigo - 336) {
      moverAbajo();
      if ((posiciónY % altoEnemigo == 0)) {
        dirección = Dirección.IZQUIERDA;
      }
    }
    VerificadorColisiones.verificarColisionesEnemigo(anchoEnemigo, this);
  }

}
