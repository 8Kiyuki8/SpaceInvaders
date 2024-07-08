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
  }

  public static VentanaPrincipal obtenerInstancia() {
    VentanaPrincipal gameWindowResult = instancia;
    if (gameWindowResult == null) {
      synchronized (VentanaPrincipal.class) {
        gameWindowResult = instancia;
        if (gameWindowResult == null) {
          instancia = gameWindowResult = new VentanaPrincipal();
        }
      }
    }
    return gameWindowResult;
  }

  public JFrame obtenerVentana() {
    return ventana;
  }
}
