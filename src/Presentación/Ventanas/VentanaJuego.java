package Presentación.Ventanas;

import Lógica.Entidades.*;
import Lógica.Enumeraciones.AcciónUsuario;
import Presentación.Pintores.PintorColmena;
import Presentación.Pintores.PintorJugador;
import Lógica.Servicios.AdministradorEventoTeclas;
import Presentación.Pintores.PintorMisilEnemigos;
import Presentación.Pintores.PintorMisilJugador;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class VentanaJuego extends JPanel implements Runnable {
  public static final int FPS_JUEGO = 60;
  private static final int TIEMPO_ENTRE_DISPAROS_JUGADOR = 450;
  private static final int TIEMPO_ENTRE_DISPAROS_ENEMIGOS = 900;

  //Pintores
  private final PintorJugador pintorJugador = new PintorJugador("Jugador");
  private final PintorColmena pintorColmena = new PintorColmena();
  private final PintorMisilJugador pintorMisilJugador = new PintorMisilJugador("MisilJugador");
  private final PintorMisilEnemigos pintorMisilEnemigos = new PintorMisilEnemigos("MisilEnemigos");
  private final AdministradorEventoTeclas administradorTeclas = new AdministradorEventoTeclas();

  //Entidades
  private NaveJugador naveJugador;
  private NaveEnemiga[][] navesEnemigas;
  private ArrayList<Misil> misilesJugador = new ArrayList<>();
  private ArrayList<Misil> misilesEnemigos = new ArrayList<>();
  private Colmena colmena;

  private Thread hiloJuego;
  private BufferedImage fondoJuego;
  private long últimoTiempoDisparoJugador = 0;
  private long últimoTiempoDisparoColmena = 0;

  public VentanaJuego() {
    naveJugador = new NaveJugador(
      new Posición(
        (VentanaAdministradora.obtenerAnchoVentana() / 2) - (VentanaAdministradora.obtenerTamañoEntidad() / 2),
        VentanaAdministradora.obtenerAltoVentana() - (VentanaAdministradora.obtenerTamañoEntidad() * 2))
    );
    int filaColmena = 3;
    int columnaColmena = 8;
    colmena = new Colmena(
      new Posición(
        (VentanaAdministradora.obtenerAnchoVentana() / 2
          - VentanaAdministradora.obtenerTamañoEntidad() * (columnaColmena / 2)),
        (VentanaAdministradora.obtenerTamañoEntidad() * 2)
      )
    );
    navesEnemigas = colmena.generarColmenaEnemigos(filaColmena, columnaColmena);

    configurarVentana();
    cargarImagenDeFondo();
    setFocusable(true);
    addKeyListener(administradorTeclas);
    iniciarHiloJuego();
  }

  private void configurarVentana() {
    setPreferredSize(new Dimension(
      VentanaAdministradora.obtenerAnchoVentana(), VentanaAdministradora.obtenerAltoVentana()));
  }

  private void cargarImagenDeFondo() {
    try {
      fondoJuego = ImageIO.read(
        Objects.requireNonNull(getClass().getResource("/Presentación/Recursos/FondoDeJuego/fondodejuego.png")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void iniciarHiloJuego() {
    hiloJuego = new Thread(this);
    hiloJuego.start();
  }

  public void actualizar() {
    AcciónUsuario acciónJugador = administradorTeclas.obtenerAcción();
    if (acciónJugador == AcciónUsuario.IZQUIERDA) {
      naveJugador.moverIzquierda();
    } else if (acciónJugador == AcciónUsuario.DERECHA) {
      naveJugador.moverDerecha();
    } else if (acciónJugador == AcciónUsuario.DISPARAR) {
      long tiempoActual = System.currentTimeMillis();
      if (tiempoActual - últimoTiempoDisparoJugador >= TIEMPO_ENTRE_DISPAROS_JUGADOR) {
        misilesJugador.add(naveJugador.disparar());
        últimoTiempoDisparoJugador = tiempoActual;
      }
    }
    misilesJugador.forEach(Misil::dispararArriba);
    misilesJugador.removeIf(misil -> misil.obtenerPosiciónMisil().obtenerPosiciónY() < 0);

    long tiempoActual = System.currentTimeMillis();
    if (tiempoActual - últimoTiempoDisparoColmena >= TIEMPO_ENTRE_DISPAROS_ENEMIGOS) {
      misilesEnemigos.add(colmena.disparar());
      últimoTiempoDisparoColmena = tiempoActual;
    }
    misilesEnemigos.forEach(Misil::dispararAbajo);
    misilesEnemigos.removeIf(misil -> misil.obtenerPosiciónMisil().obtenerPosiciónY() > getHeight());

    administradorTeclas.limpiarAcción();
    pintorJugador.actualizarImagenEntidad();
    actualizarMovimientoColmena(navesEnemigas);
    colisionaEnemigoConMisil();
  }

  public void colisionaEnemigoConMisil() {
    for (int i = 0; i < navesEnemigas.length; i++) {
      for (int j = 0; j < navesEnemigas[i].length; j++) {
        NaveEnemiga naveEnemiga = navesEnemigas[i][j];
        if (naveEnemiga != null) {
          for (Misil misil : misilesJugador) {
            if (naveEnemiga.colisionaConMisil(misil)) {
              navesEnemigas[i][j] = null;
              misilesJugador.remove(misil);
              break;
            }
          }
        }
      }
    }
  }

  public void actualizarMovimientoColmena(NaveEnemiga[][] colmenaEnemigos) {
    for (int i = 0; i < colmenaEnemigos.length; i++) {
      for (int j = 0; j < colmenaEnemigos[0].length; j++) {
        if (colmenaEnemigos[i][j] != null) {
          if (colmena.obtenerDirección()) {
            colmenaEnemigos[i][j].moverDerecha();
          } else {
            colmenaEnemigos[i][j].moverIzquierda();
          }
        }
      }
    }
    boolean bordeAlcanzado = false;
    for (int i = 0; i < colmenaEnemigos.length; i++) {
      for (int j = 0; j < colmenaEnemigos[0].length; j++) {
        if (colmenaEnemigos[i][j] != null) {
          int posiciónX = colmenaEnemigos[i][j].obtenerPosición().obtenerPosiciónX();
          if (posiciónX <= 0 || posiciónX >= VentanaAdministradora.obtenerAnchoVentana() - VentanaAdministradora.obtenerTamañoEntidad()) {
            bordeAlcanzado = true;
            break;
          }
        }
      }
      if (bordeAlcanzado) break;
    }

    if (bordeAlcanzado) {
      colmena.cambiarDirección();
      for (int i = 0; i < colmenaEnemigos.length; i++) {
        for (int j = 0; j < colmenaEnemigos[0].length; j++) {
          if (colmenaEnemigos[i][j] != null) {
            colmenaEnemigos[i][j].moverAbajo();
          }
        }
      }
    }
  }

  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    graphics.drawImage(fondoJuego, 0, 0, getWidth(), getHeight(), this);
    Graphics2D graphics2D = (Graphics2D) graphics;

    pintorColmena.dibujar(graphics2D, navesEnemigas);
    pintorJugador.dibujar(graphics2D,
      naveJugador.obtenerPosición().obtenerPosiciónX(), naveJugador.obtenerPosición().obtenerPosiciónY());
    for (Misil misil : misilesJugador) {
      pintorMisilJugador.dibujar(graphics2D, misil.obtenerPosiciónMisil().obtenerPosiciónX(), misil.obtenerPosiciónMisil().obtenerPosiciónY());
      pintorMisilJugador.actualizarImagenEntidad();
    }
    for (Misil misil : misilesEnemigos) {
      pintorMisilEnemigos.dibujar(graphics2D, misil.obtenerPosiciónMisil().obtenerPosiciónX(), misil.obtenerPosiciónMisil().obtenerPosiciónY());
      pintorMisilEnemigos.actualizarImagenEntidad();
    }
  }

  @Override
  public void run() {
    double intervaloDibujo = (double) 1000000000 / FPS_JUEGO;
    double delta = 0;
    long últimoTiempo = System.nanoTime();
    long tiempoActual;

    while (hiloJuego != null) {
      tiempoActual = System.nanoTime();
      delta += (tiempoActual - últimoTiempo) / intervaloDibujo;
      últimoTiempo = tiempoActual;
      if (delta >= 1) {
        actualizar();
        repaint();
        delta -= 1;
      }
    }
  }

}
