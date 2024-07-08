package Presentación.Ventanas;

import java.awt.*;
import javax.swing.*;

public class VentanaPrincipal extends JPanel {
  private static volatile VentanaPrincipal instancia;
  private final JFrame ventana;

  private VentanaPrincipal() {
    configurarVentana();
    ventana = VentanaAdministradora.crearVentana(this);
  }

  private void configurarVentana() {
    setBackground(Color.BLACK);
    add(crearBotónIniciarJuego());
  }

  private JPanel crearBotónIniciarJuego() {
    JPanel panel = new JPanel();
    JButton botón = new JButton("Iniciar Juego");
    botón.setPreferredSize(new Dimension(100, 50));
    botón.addActionListener(e -> abrirVentanaJuego());
    panel.add(botón);
    return panel;
  }

  private void abrirVentanaJuego() {
    VentanaJuego ventanaJuego = new VentanaJuego();
    VentanaAdministradora.crearVentana(ventanaJuego);
  }

  public static VentanaPrincipal obtenerVentana() {
    VentanaPrincipal ventanaPrincipal = instancia;
    if (ventanaPrincipal == null) {
      synchronized (VentanaPrincipal.class) {
        ventanaPrincipal = instancia;
        if (ventanaPrincipal == null) {
          instancia = ventanaPrincipal = new VentanaPrincipal();
        }
      }
    }
    return ventanaPrincipal;
  }
}

