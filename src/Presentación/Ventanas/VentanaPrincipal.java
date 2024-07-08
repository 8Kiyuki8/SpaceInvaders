package Presentación.Ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VentanaPrincipal extends JPanel implements ActionListener {
  private static volatile VentanaPrincipal instancia;
  private final JFrame ventana;
  private JButton botónDeInicio;

  private VentanaPrincipal() {
    configurarVentana();
    ventana = VentanaAdministradora.crearVentana(this);

    botónDeInicio = VentanaAdministradora.crearBotonDeInicioDeJuego(this);
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

  @Override
  public void actionPerformed(ActionEvent acción) {
    if (acción.getSource() == botónDeInicio) {
      ventana.dispose();
      VentanaDeJuego ventanaDeJuego = new VentanaDeJuego();
    }
  }
}

