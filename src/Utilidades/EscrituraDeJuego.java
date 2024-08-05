package Utilidades;

import Lógica.Entidades.*;

import java.io.Serializable;
import java.util.ArrayList;

public class EscrituraDeJuego implements Serializable {
  private NaveJugador naveJugador;
  private NaveEnemiga[][] navesEnemigas;
  private Barrera[] barreras;
  private ArrayList<Ovni> ovnis;
  private ArrayList<Misil> misilesJugador;
  private ArrayList<Misil> misilesEnemigos;
  private boolean moviendoDerecha;
  private boolean colmenaDescendiendo;
  private double distanciaDescendida;
  private long tiempoDeInicioDeJuego;


  public ArrayList<Misil> obtenerMisilesJugador() {
    return misilesJugador;
  }

  public void setMisilesJugador(ArrayList<Misil> misilesJugador) {
    this.misilesJugador = misilesJugador;
  }

  public ArrayList<Ovni> obtenerOvnis() {
    return ovnis;
  }

  public void setOvnis(ArrayList<Ovni> ovnis) {
    this.ovnis = ovnis;
  }

  public ArrayList<Misil> obtenerMisilesEnemigos() {
    return misilesEnemigos;
  }

  public void setMisilesEnemigos(ArrayList<Misil> misilesEnemigos) {
    this.misilesEnemigos = misilesEnemigos;
  }

  public Barrera[] obtenerBarreras() {
    return barreras;
  }

  public void setBarreras(Barrera[] barreras) {
    this.barreras = barreras;
  }

  public NaveJugador obtenerNaveJugador() {
    return naveJugador;
  }

  public void setNaveJugador(NaveJugador naveJugador) {
    this.naveJugador = naveJugador;
  }

  public NaveEnemiga[][] obtenerNavesEnemigas() {
    return navesEnemigas;
  }

  public void setNavesEnemigas(NaveEnemiga[][] navesEnemigas) {
    this.navesEnemigas = navesEnemigas;
  }

  public long obtenerTiempoDeInicioDeJuego() {
    return tiempoDeInicioDeJuego;
  }

  public void setTiempoDeInicioDeJuego(long tiempoDeInicioDeJuego) {
    this.tiempoDeInicioDeJuego = tiempoDeInicioDeJuego;
  }

  public boolean estaColmenaDescendiendo() {
    return colmenaDescendiendo;
  }

  public void setColmenaDescendiendo(boolean colmenaDescendiendo) {
    this.colmenaDescendiendo = colmenaDescendiendo;
  }

  public double obtenerDistanciaDescendida() {
    return distanciaDescendida;
  }

  public void setDistanciaDescendida(double distanciaDescendida) {
    this.distanciaDescendida = distanciaDescendida;
  }

  public boolean estaMoviendoDerecha() {
    return moviendoDerecha;
  }

  public void setMoviendoDerecha(boolean moviendoDerecha) {
    this.moviendoDerecha = moviendoDerecha;
  }
}
