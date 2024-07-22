package Presentación.Ventanas;

import Lógica.Entidades.NaveJugador;
import Lógica.Entidades.NaveEnemiga;
import Lógica.Entidades.Posición;
import Presentación.Pintores.PintorJugador;
import Presentación.Pintores.PintorEnemigo;
import Lógica.Servicios.AdministradorEventoTeclas;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class VentanaJuego extends JPanel implements Runnable {
  public static final int FPS_JUEGO = 60;

  private final PintorJugador pintorJugador = new PintorJugador("Jugador");
  private final ArrayList<PintorEnemigo> pintorEnemigos;
  private final AdministradorEventoTeclas administradorTeclas = new AdministradorEventoTeclas();
  private NaveJugador naveJugador;
  private final NaveEnemiga naveEnemiga;

  private Thread hiloJuego;
  private BufferedImage fondoJuego;

  public VentanaJuego() {
    pintorEnemigos = new ArrayList<>();
    pintorEnemigos.add(new PintorEnemigo("Azul"));
    pintorEnemigos.add(new PintorEnemigo("IndexOutOfBounds"));
    pintorEnemigos.add(new PintorEnemigo("NullPointerException"));
    naveJugador = new NaveJugador(
      new Posición(
        (VentanaAdministradora.obtenerAnchoVentana() / 2) - (VentanaAdministradora.obtenerTamañoEntidad() / 2),
        VentanaAdministradora.obtenerAltoVentana() - (VentanaAdministradora.obtenerTamañoEntidad() * 2))
    );

    naveEnemiga = new NaveEnemiga(new Posición(
      0,
      0)
    );

    configurarVentana();
    cargarImagenDeFondo();
    setFocusable(true);
    addKeyListener(administradorTeclas);
    iniciarHiloJuego();
  }

  private void configurarVentana() {
    setPreferredSize(new Dimension(VentanaAdministradora.obtenerAnchoVentana(), VentanaAdministradora.obtenerAltoVentana()));
  }

  private void cargarImagenDeFondo() {
    try {
      fondoJuego = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Presentación/Recursos/FondoDeJuego/fondodejuego.png")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void iniciarHiloJuego() {
    hiloJuego = new Thread(this);
    hiloJuego.start();
  }

  public void update() {
    naveJugador.obtenerMovimiento().actualizarMovimiento(naveJugador.obtenerPosición(), administradorTeclas.obtenerDirecciónMovimiento());
    pintorJugador.actualizarImagenEntidad();
    naveEnemiga.obtenerMovimiento().actualizarMovimiento(naveEnemiga.obtenerPosición(), null);
    for (PintorEnemigo pintorEnemigo : pintorEnemigos) {
      pintorEnemigo.actualizarImagenEntidad();
    }
  }

  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    graphics.drawImage(fondoJuego, 0, 0, getWidth(), getHeight(), this);
    Graphics2D graphics2D = (Graphics2D) graphics;

    for (int i = 0; i < pintorEnemigos.size(); i++) {
      pintorEnemigos.get(i).dibujar(graphics2D, i + 1,
        naveEnemiga.obtenerPosición().obtenerPosiciónX(), naveEnemiga.obtenerPosición().obtenerPosiciónY());
    }

    pintorJugador.dibujar(graphics2D, null,
      naveJugador.obtenerPosición().obtenerPosiciónX(), naveJugador.obtenerPosición().obtenerPosiciónY());
    graphics2D.dispose();
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
