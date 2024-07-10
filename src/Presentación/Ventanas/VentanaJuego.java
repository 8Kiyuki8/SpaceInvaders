package Presentación.Ventanas;

import Lógica.Entidades.NaveJugador;
import Presentación.Pintores.PintorJugador;
import Presentación.Pintores.PintorEnemigo;
import Lógica.Servicios.AdministradorEventoTeclas;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class VentanaJuego extends JPanel implements Runnable {
  public static final int FPS_JUEGO = 60;

  private final PintorJugador pintorJugador = new PintorJugador("Jugador");
  private final ArrayList<PintorEnemigo> pintorEnemigos;

  private final AdministradorEventoTeclas administradorTeclas = new AdministradorEventoTeclas();
  private final NaveJugador naveJugador = new NaveJugador(administradorTeclas);

  private Thread hiloJuego;
  private BufferedImage fondoJuego;

  public VentanaJuego() {
    pintorEnemigos = new ArrayList<>();
    pintorEnemigos.add(new PintorEnemigo("Azul"));
    pintorEnemigos.add(new PintorEnemigo("IndexOutOfBounds"));
    pintorEnemigos.add(new PintorEnemigo("NullPointerException"));

    configurarVentana();
    cargarImagenDeFondo();
    setFocusable(true);
    addKeyListener(administradorTeclas);
    iniciarHiloJuego();
  }

  private void configurarVentana() {
    //setBackground(Color.GREEN);
    setPreferredSize(new Dimension(800, 600));
  }

  private void cargarImagenDeFondo() {
    try {
      fondoJuego = ImageIO.read(getClass().getResource("/Presentación/Recursos/FondoDeJuego/fondodejuego.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public void iniciarHiloJuego() {
    hiloJuego = new Thread(this);
    hiloJuego.start();
  }

  public void update() {
    naveJugador.actualizarMovimiento();
    pintorJugador.actualizarImagenEntidad();
    for (PintorEnemigo pintorEnemigo : pintorEnemigos) {
      pintorEnemigo.actualizarImagenEntidad();
    }
  }

  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    graphics.drawImage(fondoJuego, 0, 0, null);
    Graphics2D graphics2D = (Graphics2D) graphics;
    for (int i = 0; i < pintorEnemigos.size(); i += 1) {
      pintorEnemigos.get(i).dibujar(graphics2D, i + 1, 0, 0);
    }
    pintorJugador.dibujar(graphics2D, null, naveJugador.obtenerPosiciónX(),
      naveJugador.obtenerPosiciónY());
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
