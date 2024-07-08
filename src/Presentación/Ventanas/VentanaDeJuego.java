package Presentación.Ventanas;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class VentanaDeJuego {
  JFrame ventanaDeJuego = new JFrame();
  Image imagen =
    new ImageIcon(
      Objects.requireNonNull(
        VentanaAdministradora.class.getResource(
          "/Presentación/Recursos/IconoJuego/iconojuego.png")
      )
    ).getImage();
  JLabel textoEjemplo = new JLabel();
  ImageIcon enemigoPrueba = new ImageIcon("/Presentación/Recursos/Enemigos/NullPointerException/NullPointerException_0.png");

  VentanaDeJuego() {
    ventanaDeJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ventanaDeJuego.setBackground(Color.BLACK);
    ventanaDeJuego.setResizable(false);
    ventanaDeJuego.setTitle("Space Invaders");
    ventanaDeJuego.setIconImage(imagen);
    ventanaDeJuego.setSize(800, 600);
    ventanaDeJuego.setLocationRelativeTo(null);
    ventanaDeJuego.setVisible(true);

    textoEjemplo.setText("Esta es la ventana de juego");
    textoEjemplo.setHorizontalAlignment(SwingConstants.CENTER);
    textoEjemplo.setIcon(enemigoPrueba);
    ventanaDeJuego.add(textoEjemplo);
  }
}
