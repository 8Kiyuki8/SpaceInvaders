package Presentación.Ventanas;

import Lógica.Entidades.*;
import Lógica.Enumeraciones.AcciónUsuario;
import Presentación.Pintores.*;
import Presentación.Servicios.AdministradorEventoTeclas;
import Presentación.Servicios.VerificarColisiones;

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
  private static final int TIEMPO_ENTRE_POWER_UPS = 10000;

  //Pintores
  private final PintorJugador pintorJugador = new PintorJugador("Jugador");
  private final PintorColmena pintorColmena = new PintorColmena();
  private final PintorMisilJugador pintorMisilJugador = new PintorMisilJugador("MisilJugador");
  private final PintorMisilEnemigos pintorMisilEnemigos = new PintorMisilEnemigos("MisilEnemigos");
  private final PintorPowerUpVida pintorPowerUpVida = new PintorPowerUpVida("PowerUpVida");
  private final AdministradorEventoTeclas administradorTeclas = new AdministradorEventoTeclas();

  //Entidades
  private NaveJugador naveJugador;
  private PowerUpVida powerUpVida;
  private NaveEnemiga[][] navesEnemigas;
  private ArrayList<Misil> misilesJugador = new ArrayList<>();
  private ArrayList<Misil> misilesEnemigos = new ArrayList<>();
  private ArrayList<PowerUpVida> powerUpVidas = new ArrayList<>();
  private Colmena colmena;
  //Tiempo disparo
  private long últimoTiempoDisparoJugador = 0;
  private long últimoTiempoDisparoColmena = 0;
  private long últimoTiempoEntrePowerUps = 0;

  private Thread hiloJuego;
  private BufferedImage fondoJuego;
  private long tiempoInicioDeJuego;

  public VentanaJuego() {
    generarNaveJugador();
    generarColmena();
    configurarVentana();
    cargarImagenDeFondo();
    setFocusable(true);
    addKeyListener(administradorTeclas);
    iniciarHiloJuego();
    tiempoInicioDeJuego = System.currentTimeMillis();
  }

  private void generarNaveJugador() {
    naveJugador = new NaveJugador(
      new Posición(
        (VentanaAdministradora.obtenerAnchoVentana() / 2) - (VentanaAdministradora.obtenerTamañoEntidad() / 2),
        VentanaAdministradora.obtenerAltoVentana() - (VentanaAdministradora.obtenerTamañoEntidad() * 2))
    );
  }

  private void generarColmena() {
    int filaColmena = 3;
    int columnaColmena = 8;
    colmena = new Colmena(
      new Posición(
        (VentanaAdministradora.obtenerAnchoVentana() / 2 - VentanaAdministradora.obtenerTamañoEntidad() * (columnaColmena / 2)),
        (VentanaAdministradora.obtenerTamañoEntidad() * 2)
      )
    );
    navesEnemigas = colmena.generarColmenaEnemigos(filaColmena, columnaColmena);
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

    if (tiempoActual - últimoTiempoEntrePowerUps >= TIEMPO_ENTRE_POWER_UPS) {
      powerUpVidas.add(powerUpVida.generarPowerUpVida());
      últimoTiempoEntrePowerUps = tiempoActual;
    }
    powerUpVidas.forEach(PowerUpVida::caerPowerUp);
    powerUpVidas.removeIf(powerUp -> powerUp.obtenerPosiciónPowerUp().obtenerPosiciónY() > getHeight());

    VerificarColisiones.colisionaPowerUpConNaveJugador(powerUpVidas, naveJugador);
    VerificarColisiones.colisionaJugadorConMisilDeEnemigos(misilesEnemigos, naveJugador);
    VerificarColisiones.colisionaEnemigoConMisilDeJugador(navesEnemigas, misilesJugador, naveJugador);
    generarNuevaColmena();
    actualizarMovimientoColmena(navesEnemigas);
    pintorJugador.actualizarImagenEntidad();
    administradorTeclas.limpiarAcción();

  }

  private void generarNuevaColmena() {
    boolean colmenaEliminada = true;
    for (NaveEnemiga[] fila : navesEnemigas) {
      for (NaveEnemiga nave : fila) {
        if (nave != null) {
          colmenaEliminada = false;
          break;
        }
      }
    }
    if (colmenaEliminada) {
      generarColmena();
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

    graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 14F));
    graphics2D.setColor(Color.LIGHT_GRAY);
    graphics2D.drawString("Puntos: " + naveJugador.obtenerPuntos(), 10, 25);
    graphics2D.drawString("Vidas: " + naveJugador.obtenerVida(), 10, 50);
    long tiempoActual = System.currentTimeMillis();
    long tiempoTranscurrido = tiempoActual - tiempoInicioDeJuego;
    long segundosTranscurridos = tiempoTranscurrido / 1000;
    long minutosTranscurridos = segundosTranscurridos / 60;
    segundosTranscurridos = segundosTranscurridos % 60;
    String tiempoCadena = String.format("Tiempo: %02d:%02d", minutosTranscurridos, segundosTranscurridos);
    graphics2D.drawString(tiempoCadena, 10, 75);

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
    for (PowerUpVida powerUpVida : powerUpVidas) {
      pintorPowerUpVida.dibujar(graphics2D, powerUpVida.obtenerPosiciónPowerUp().obtenerPosiciónX(), powerUpVida.obtenerPosiciónPowerUp().obtenerPosiciónY());
      pintorPowerUpVida.actualizarImagenEntidad();
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
