import java.awt.*;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MiFrame extends JFrame {
  MiFrame(){
    this.setTitle("Space Invaders");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setSize(500,500);
    this.setVisible(true);
    ImageIcon icono = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/Recursos/IconoJuego/iconojuego.png")));
    this.setIconImage(icono.getImage());
    this.getContentPane().setBackground(new Color(100,180,100));

  }
}
