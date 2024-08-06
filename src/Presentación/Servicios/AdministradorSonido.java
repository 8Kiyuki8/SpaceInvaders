package Presentación.Servicios;

import Presentación.Enumeraciones.Sonido;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.HashMap;

public class AdministradorSonido {
  private Clip clip;
  private Clip clipMúsica;
  private HashMap<Sonido, URL> direcciónSonido = new HashMap<>();
  long clipTiempo;

  public AdministradorSonido() {
    direcciónSonido.put(Sonido.JUEGO, getClass().getResource("/Presentación/Recursos/Música/juego.wav"));
    direcciónSonido.put(Sonido.OPCIÓN, getClass().getResource("/Presentación/Recursos/Música/cambioOpción.wav"));
    direcciónSonido.put(Sonido.SONIDO_DE_BALA, getClass().getResource("/Presentación/Recursos/Música/disparo.wav"));
    direcciónSonido.put(Sonido.VIDA, getClass().getResource("/Presentación/Recursos/Música/vida.wav"));
    direcciónSonido.put(Sonido.COLISION, getClass().getResource("/Presentación/Recursos/Música/colision.wav"));
    direcciónSonido.put(Sonido.OVNI, getClass().getResource("/Presentación/Recursos/Música/ovni.wav"));
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
    clip.setMicrosecondPosition(clipTiempo);
    clip.start();
  }

  public void ponerMusicaFondo() {
    try {
      AudioInputStream inputStream = AudioSystem.getAudioInputStream(direcciónSonido.get(Sonido.JUEGO));
      clipMúsica = AudioSystem.getClip();
      clipMúsica.open(inputStream);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void reproducirMusicaFondo() {
    if (clipMúsica != null) {
      clipMúsica.start();
      clipMúsica.loop(Clip.LOOP_CONTINUOUSLY);
    }
  }

  public void detenerMusicaFondo() {
    if (clipMúsica != null) {
      clipMúsica.stop();
    }
  }

  public void reanudarMusicaFondo() {
    if (clipMúsica != null) {
      clipMúsica.start();
      clipMúsica.loop(Clip.LOOP_CONTINUOUSLY);
    }
  }

}
