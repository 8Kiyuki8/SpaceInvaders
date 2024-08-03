package Presentación.Ventanas;

import Lógica.Entidades.*;
import Presentación.Servicios.VerificarColisiones;
import Presentación.Enumeraciones.AcciónUsuario;
import Presentación.Enumeraciones.EstadoDeLaVentana;
import Presentación.Enumeraciones.Sonido;
import Presentación.Pintores.*;
import Presentación.Servicios.AdministradorEventoTeclas;
import Presentación.Servicios.AdministradorSonido;


import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class VentanaJuego extends JPanel implements Runnable {
  //Constantes
  public static final int TAMAÑO_ESTÁNDAR_ENTIDAD = 16;
  public static final int ESCALA_ENTIDAD = 3;
  public static final int TAMAÑO_ENTIDAD = TAMAÑO_ESTÁNDAR_ENTIDAD * ESCALA_ENTIDAD; //48
  private static final int MÁXIMA_CANTIDAD_COLUMNAS = 16;
  private static final int MÁXIMA_CANTIDAD_FILAS = 12;
  private static final int ANCHO_VENTANA = MÁXIMA_CANTIDAD_COLUMNAS * TAMAÑO_ENTIDAD; //768
  private static final int ALTO_VENTANA = MÁXIMA_CANTIDAD_FILAS * TAMAÑO_ENTIDAD; //576

  //Renderizado
  public static final int FPS_JUEGO = 60;
  private static final int TIEMPO_ENTRE_DISPAROS_JUGADOR = 450;
  private static final int TIEMPO_ENTRE_DISPAROS_ENEMIGOS = 900;
  private static final int TIEMPO_ENTRE_POWER_UPS = 10000;
  private Thread hiloJuego;

  //Controladores Juego
  private int opciónDeUsuario;
  private EstadoDeLaVentana estadoDeLaVentanaActual;


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

  //MovimientoColmena
  private boolean moviendoDerecha = true;
  private boolean colmenaDescendiendo = false;
  private double distanciaDescendida = 0;

  //Tiempo disparo
  private long últimoTiempoDisparoJugador = 0;
  private long últimoTiempoDisparoColmena = 0;
  private long últimoTiempoEntrePowerUps = 0;

  //Imágenes de Fondo de pantalla
  private BufferedImage fondoJuego;
  private long tiempoInicioDeJuego;
  private BufferedImage fondoMenúPrincipal;
  long tiempoDeInicioDeJuego;
  private AdministradorSonido administradorSonido = new AdministradorSonido();


  public VentanaJuego() {
    estadoDeLaVentanaActual = EstadoDeLaVentana.PRINCIPAL;
    opciónDeUsuario = 0;
    generarNaveJugador();
    generarColmena();
    configurarVentana();
    cargarImagenDeFondoDeMenúPrincipal();
    cargarImagenDeFondo();
    setFocusable(true);
    reproducirSonidoInfinito(Sonido.JUEGO);
    addKeyListener(administradorTeclas);
    iniciarHiloJuego();
    tiempoInicioDeJuego = System.currentTimeMillis();
  }

  private void generarNaveJugador() {
    int posiciónJugadorEnX = ANCHO_VENTANA / 2;
    int posiciónJugadorEnY = (ALTO_VENTANA / 2) + (TAMAÑO_ENTIDAD * 5);
    naveJugador = new NaveJugador(
      new Posición(
        posiciónJugadorEnX,
        posiciónJugadorEnY
      ));
  }

  private void generarColmena() {
    int filaColmena = 3;
    int columnaColmena = 8;
    colmena = new Colmena(
      new Posición(
        (ANCHO_VENTANA / 2
          - TAMAÑO_ENTIDAD * (columnaColmena / 2)),
        (TAMAÑO_ENTIDAD * 2)
      )
    );
    navesEnemigas = colmena.generarColmenaEnemigos(filaColmena, columnaColmena);
  }

  private void configurarVentana() {
    setPreferredSize(new Dimension(
      ANCHO_VENTANA, ALTO_VENTANA));
  }

  private void reproducirSonido(Sonido sonido) {
    administradorSonido.ponerSonido(sonido);
    administradorSonido.reproducir();
  }

  private void reproducirSonidoInfinito(Sonido sonido) {
    administradorSonido.ponerSonido(sonido);
    administradorSonido.reproducir();
    administradorSonido.loop();
  }

  private void detenerSonido() {
    administradorSonido.stop();
  }

  private void cargarImagenDeFondo() {
    try {
      fondoJuego = ImageIO.read(
        Objects.requireNonNull(getClass().getResource("/Presentación/Recursos/FondoDeJuego/fondodejuego.png")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void cargarImagenDeFondoDeMenúPrincipal() {
    try {
      fondoMenúPrincipal = ImageIO.read(
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
    AcciónUsuario acción = administradorTeclas.obtenerAcción();

    if (estadoDeLaVentanaActual == EstadoDeLaVentana.PRINCIPAL) {
      if (acción == AcciónUsuario.ARRIBA) {
        reproducirSonido(Sonido.OPCIÓN);
        opciónDeUsuario = (opciónDeUsuario - 1 + 3) % 3;
      } else if (acción == AcciónUsuario.ABAJO) {
        opciónDeUsuario = (opciónDeUsuario + 1) % 3;
        reproducirSonido(Sonido.OPCIÓN);
      } else if (acción == AcciónUsuario.CONFIRMAR && opciónDeUsuario == 0) {
        estadoDeLaVentanaActual = EstadoDeLaVentana.JUEGO;
        tiempoDeInicioDeJuego = System.currentTimeMillis();
        administradorTeclas.cambiarEstadoActualDeLaVentana(estadoDeLaVentanaActual);
      }
    }

    if (estadoDeLaVentanaActual == EstadoDeLaVentana.JUEGO) {
      actualizarJuego();
    }

    if (estadoDeLaVentanaActual == EstadoDeLaVentana.JUEGO_TERMINADO) {
      opciónDeUsuario = 0;
      if (acción == AcciónUsuario.CONFIRMAR) {
        estadoDeLaVentanaActual = EstadoDeLaVentana.PRINCIPAL;
        reiniciarJuego();
        administradorTeclas.cambiarEstadoActualDeLaVentana(estadoDeLaVentanaActual);
      }
      administradorTeclas.cambiarEstadoActualDeLaVentana(estadoDeLaVentanaActual);
    }
    administradorTeclas.limpiarAcción();
  }

  public void actualizarJuego() {
    AcciónUsuario acciónJugador = administradorTeclas.obtenerAcción();
    if (acciónJugador == AcciónUsuario.IZQUIERDA) {
      naveJugador.moverIzquierda();
    } else if (acciónJugador == AcciónUsuario.DERECHA) {
      naveJugador.moverDerecha();
    } else if (acciónJugador == AcciónUsuario.DISPARAR) {
      long tiempoActual = System.currentTimeMillis();
      if (tiempoActual - últimoTiempoDisparoJugador >= TIEMPO_ENTRE_DISPAROS_JUGADOR) {
        misilesJugador.add(naveJugador.disparar());
        reproducirSonido(Sonido.SONIDO_DE_BALA);
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

  private void reiniciarJuego() {
    generarNaveJugador();
    generarColmena();

    misilesJugador.clear();
    misilesEnemigos.clear();

    estadoDeLaVentanaActual = EstadoDeLaVentana.PRINCIPAL;
    opciónDeUsuario = 0;
    tiempoDeInicioDeJuego = 0;

  }


  public void actualizarMovimientoColmena(NaveEnemiga[][] colmenaEnemigos) {
    boolean bordeAlcanzado = false;
    for (int i = 0; i < colmenaEnemigos.length; i++) {
      for (int j = 0; j < colmenaEnemigos[0].length; j++) {
        if (colmenaEnemigos[i][j] != null) {
          if (colmenaDescendiendo) {
            colmenaEnemigos[i][j].moverAbajo();
          } else {
            if (moviendoDerecha) {
              colmenaEnemigos[i][j].moverDerecha();
            } else {
              colmenaEnemigos[i][j].moverIzquierda();
            }
          }

          if (VerificarColisiones.colisionaConBordesDeLaPantalla(
            colmenaEnemigos[i][j].obtenerPosición().obtenerPosiciónX())
          ) {
            bordeAlcanzado = true;
          }
          /*
          int posiciónX = colmenaEnemigos[i][j].obtenerPosición().obtenerPosiciónX();
          if (posiciónX <= 0 || posiciónX >= ANCHO_VENTANA - TAMAÑO_ENTIDAD) {
            bordeAlcanzado = true;
          }
           */
        }
      }
    }
    if (colmenaDescendiendo) {
      distanciaDescendida += 2.5;
      if (distanciaDescendida >= TAMAÑO_ENTIDAD) {
        colmenaDescendiendo = false;
        distanciaDescendida = 0;
      }
    } else if (bordeAlcanzado) {
      moviendoDerecha = !moviendoDerecha;
      colmenaDescendiendo = true;
      distanciaDescendida = 0;
    }
  }

  protected void paintComponent(Graphics graphics) {
    Graphics2D graphics2D = (Graphics2D) graphics;
    super.paintComponent(graphics);
    if (estadoDeLaVentanaActual == EstadoDeLaVentana.PRINCIPAL) {
      dibujarMenúPrincipal(graphics2D);
    }
    if (estadoDeLaVentanaActual == EstadoDeLaVentana.JUEGO) {
      dibujarVentanaJuego(graphics2D);
    }
    if (estadoDeLaVentanaActual == EstadoDeLaVentana.JUEGO_TERMINADO) {
      dibujarVentanaJuegoTerminado(graphics2D);
    }
    graphics2D.dispose();

  }

  private void dibujarVentanaJuegoTerminado(Graphics2D graphics2D) {
    graphics2D.drawImage(fondoJuego, 0, 0, ANCHO_VENTANA, ALTO_VENTANA, null);
    graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 96F));

    String text = "JUEGO TERMINADO";
    int x = obtenerXParaTextoCentrado(text, graphics2D);
    int y = TAMAÑO_ENTIDAD * 3;

    graphics2D.setColor(Color.gray);
    graphics2D.drawString(text, x + 3, y + 3);
    graphics2D.setColor(Color.white);
    graphics2D.drawString(text, x, y);

    graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 48F));

    text = "SALIR";
    x = obtenerXParaTextoCentrado(text, graphics2D);
    y += TAMAÑO_ENTIDAD * 3;
    graphics2D.drawString(text, x, y);
    if (opciónDeUsuario == 0) {
      graphics2D.drawString(">", x - TAMAÑO_ENTIDAD, y);
    }
  }

  public void dibujarVentanaJuego(Graphics2D graphics2D) {
    graphics2D.drawImage(fondoJuego, 0, 0, getWidth(), getHeight(), this);

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

  public void dibujarMenúPrincipal(Graphics2D graphics2D) {
    graphics2D.drawImage(fondoMenúPrincipal, 0, 0, ANCHO_VENTANA, ALTO_VENTANA, null);
    graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 96F));

    String texto = "Space Invaders";
    int x = obtenerXParaTextoCentrado(texto, graphics2D);
    int y = TAMAÑO_ENTIDAD * 3;

    graphics2D.setColor(Color.gray);
    graphics2D.drawString(texto, x + 3, y + 3);
    graphics2D.setColor(Color.white);
    graphics2D.drawString(texto, x, y);

    graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 48F));

    texto = "NUEVA PARTIDA";
    x = obtenerXParaTextoCentrado(texto, graphics2D);
    y += TAMAÑO_ENTIDAD * 3;
    graphics2D.drawString(texto, x, y);
    if (opciónDeUsuario == 0) {
      graphics2D.drawString(">", x - TAMAÑO_ENTIDAD, y);
    }

    texto = "CARGAR PARTIDA";
    x = obtenerXParaTextoCentrado(texto, graphics2D);
    y += TAMAÑO_ENTIDAD * 2;
    graphics2D.drawString(texto, x, y);
    if (opciónDeUsuario == 1) {
      graphics2D.drawString(">", x - TAMAÑO_ENTIDAD, y);
    }

    texto = "SALIR";
    x = obtenerXParaTextoCentrado(texto, graphics2D);
    y += TAMAÑO_ENTIDAD * 2;
    graphics2D.drawString(texto, x, y);
    if (opciónDeUsuario == 2) {
      graphics2D.drawString(">", x - TAMAÑO_ENTIDAD, y);
    }
  }

  private int obtenerXParaTextoCentrado(String texto, Graphics2D graphics2D) {
    int tamañoDeTexto;
    tamañoDeTexto = (int) graphics2D.getFontMetrics().getStringBounds(texto, graphics2D).getWidth();
    return (ANCHO_VENTANA / 2) - (tamañoDeTexto / 2);

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

  public static int obtenerAnchoVentana() {
    return ANCHO_VENTANA;
  }

  public static int obtenerAltoVentana() {
    return ALTO_VENTANA;
  }

  public static int obtenerTamañoEntidad() {
    return TAMAÑO_ENTIDAD;
  }
}
