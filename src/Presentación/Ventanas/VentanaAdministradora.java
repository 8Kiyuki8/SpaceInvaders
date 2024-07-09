package Presentación.Ventanas;

import java.awt.*;
import javax.swing.*;
import java.util.Objects;

public class VentanaAdministradora {
  public static final int TAMAÑO_ESTÁNDAR_ENTIDAD = 16;
  public static final int ESCALA_ENTIDAD = 3;
  public static final int TAMAÑO_ENTIDAD = TAMAÑO_ESTÁNDAR_ENTIDAD * ESCALA_ENTIDAD;
  private static final int MÁXIMA_CANTIDAD_COLUMNAS = 16;
  private static final int MÁXIMA_CANTIDAD_FILAS = 12;
  private static final int ANCHO_VENTANA = MÁXIMA_CANTIDAD_COLUMNAS * TAMAÑO_ENTIDAD;
  private static final int ALTO_VENTANA = MÁXIMA_CANTIDAD_FILAS * TAMAÑO_ENTIDAD;

  public static JFrame crearVentana(JPanel panel) {
    JFrame ventana = new JFrame();
    Image iconoJuego =
      new ImageIcon(
        Objects.requireNonNull(
          VentanaAdministradora.class.getResource(
            "/Presentación/Recursos/IconoJuego/iconojuego.png")
        )
      ).getImage();
    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ventana.setResizable(false);
    ventana.setTitle("Space Invaders");
    ventana.setIconImage(iconoJuego);
    ventana.add(panel);
    ventana.setPreferredSize(new Dimension(ANCHO_VENTANA, ALTO_VENTANA));
    ventana.pack();
    ventana.setLocationRelativeTo(null);
    ventana.setVisible(true);
    return ventana;
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
