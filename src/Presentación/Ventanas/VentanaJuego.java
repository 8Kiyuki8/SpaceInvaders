package Presentación.Ventanas;

import Lógica.Entidades.NaveJugador;
import Presentación.Servicios.AdministradorArchivos;
import Lógica.Servicios.AdministradorEventoTeclas;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class VentanaJuego extends JPanel implements Runnable {
  public static final int FPS_JUEGO = 60;
  public static final int NÚMERO_MÁXIMO_SPRITES_ENTIDAD = 4;

  private final ArrayList<BufferedImage> imágenesNaveJugador = new ArrayList<>();
  private final AdministradorEventoTeclas administradorTeclas = new AdministradorEventoTeclas();
  private final NaveJugador naveJugador = new NaveJugador(administradorTeclas);

  private int índiceActualImagen = 0;
  private long últimaActualización = System.nanoTime();

  private Thread hiloJuego;

  public VentanaJuego() {
    configurarVentana();
    try {
      configurarSpritesJugador();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    addKeyListener(administradorTeclas);
    iniciarHiloJuego();
  }

  private void configurarVentana() {
    setBackground(Color.BLACK);
    setFocusable(true);
  }

  private void configurarSpritesJugador() throws IOException {
    AdministradorArchivos administradorArchivos = new AdministradorArchivos();
    for (int i = 0; i < NÚMERO_MÁXIMO_SPRITES_ENTIDAD; i++) {
      String nombreRecurso = administradorArchivos.obtenerURLRecurso("Jugador", i);
      imágenesNaveJugador.add(ImageIO.read(
        Objects.requireNonNull(getClass().getResourceAsStream(nombreRecurso)))
      );
    }
  }

  public void iniciarHiloJuego() {
    hiloJuego = new Thread(this);
    hiloJuego.start();
  }

  public void actualizarImagen() {
    long tiempoActual = System.nanoTime();
    if ((tiempoActual - últimaActualización) >= 100000000) {
      índiceActualImagen = (índiceActualImagen + 1) % imágenesNaveJugador.size();
      últimaActualización = tiempoActual;
    }
  }

  public void update() {
    actualizarImagen();
    naveJugador.actualizarMovimiento();
  }

  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    Graphics2D g2d = (Graphics2D) graphics;
    g2d.drawImage(imágenesNaveJugador.get(índiceActualImagen), naveJugador.obtenerPosiciónX(),
      naveJugador.obtenerPosiciónY(),
      null);
    g2d.dispose();
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
