package Presentación.Ventanas;

import Lógica.Entidades.*;
import Lógica.Enumeraciones.AcciónUsuario;
import Presentación.Pintores.PintorColmena;
import Presentación.Pintores.PintorJugador;
import Presentación.Pintores.PintorEnemigo;
import Lógica.Servicios.AdministradorEventoTeclas;
import Presentación.Pintores.PintorMisil;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class VentanaJuego extends JPanel implements Runnable {
  public static final int FPS_JUEGO = 60;

  //Pintores
  private final PintorJugador pintorJugador = new PintorJugador("Jugador");
  private final PintorColmena pintorColmena = new PintorColmena();
  private final PintorMisil pintorMisil = new PintorMisil("Misil");
  private final AdministradorEventoTeclas administradorTeclas = new AdministradorEventoTeclas();

  //Entidades
  private NaveJugador naveJugador;
  private NaveEnemiga[][] navesEnemigas;
  private ArrayList<Misil> misilesJugador = new ArrayList<>();
  private Colmena colmena;

  private Thread hiloJuego;
  private BufferedImage fondoJuego;

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

  public void update() {
    AcciónUsuario acciónJugador = administradorTeclas.obtenerAcción();
    if (acciónJugador == AcciónUsuario.IZQUIERDA) {
      naveJugador.moverIzquierda();
    } else if (acciónJugador == AcciónUsuario.DERECHA) {
      naveJugador.moverDerecha();
    } else if (acciónJugador == AcciónUsuario.DISPARAR) {
      misilesJugador.add(naveJugador.disparar());
    }
    misilesJugador.forEach(Misil::dispararArriba);

    misilesJugador.removeIf(misil -> misil.obtenerPosiciónMisil().obtenerPosiciónY() < 0);

    administradorTeclas.limpiarAcción();
    pintorJugador.actualizarImagenEntidad();

  }

  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    graphics.drawImage(fondoJuego, 0, 0, getWidth(), getHeight(), this);
    Graphics2D graphics2D = (Graphics2D) graphics;

    pintorColmena.dibujar(graphics2D, colmena.obtenerPosición(), navesEnemigas);
    pintorJugador.dibujar(graphics2D, null,
      naveJugador.obtenerPosición().obtenerPosiciónX(), naveJugador.obtenerPosición().obtenerPosiciónY());
    for (Misil misil : misilesJugador) {
      pintorMisil.dibujar(graphics2D, null, misil.obtenerPosiciónMisil().obtenerPosiciónX(), misil.obtenerPosiciónMisil().obtenerPosiciónY());
      pintorMisil.actualizarImagenEntidad();
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
        update();
        repaint();
        delta -= 1;
      }
    }
  }
}
