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
