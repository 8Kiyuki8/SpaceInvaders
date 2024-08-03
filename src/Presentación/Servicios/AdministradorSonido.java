package Presentación.Servicios;

import Presentación.Enumeraciones.Sonido;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.HashMap;

public class AdministradorSonido {
  private Clip clip;
  private HashMap<Sonido, URL> direcciónSonido = new HashMap<>();

  public AdministradorSonido() {
    direcciónSonido.put(Sonido.JUEGO, getClass().getResource("/Presentación/Recursos/Música/juego.wav"));
    direcciónSonido.put(Sonido.OPCIÓN, getClass().getResource("/Presentación/Recursos/Música/cambioOpción.wav"));
    direcciónSonido.put(Sonido.SONIDO_DE_BALA, getClass().getResource("/Presentación/Recursos/Música/disparo.wav"));
  }

  public void ponerSonido(Sonido sound) {
    try {
      AudioInputStream inputStream = AudioSystem.getAudioInputStream(direcciónSonido.get(sound));
      clip = AudioSystem.getClip();
      clip.open(inputStream);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void reproducir() {
    clip.start();
  }

  public void stop() {
    clip.stop();
  }

  public void loop() {
    clip.loop(Clip.LOOP_CONTINUOUSLY);
  }
}
