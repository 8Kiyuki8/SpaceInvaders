package Utilidades;

import java.io.*;

public class GuardarPartida {


  public static void guardarEstado(EscrituraDeJuego escrituraDeJuego) throws IOException {
    try {
      File archivo = new File("partida_guardada.ser");
      FileOutputStream flujoDeSalida = new FileOutputStream(archivo);
      ObjectOutputStream manejadorDeEscritura = new ObjectOutputStream(flujoDeSalida);
      manejadorDeEscritura.writeObject(escrituraDeJuego);
      manejadorDeEscritura.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  public static EscrituraDeJuego cargarEstado() throws IOException, ClassNotFoundException {
    EscrituraDeJuego escrituraDeJuego;
    File archivo = new File("partida_guardada.ser");
    try {
      FileInputStream flujoDeEntrada = new FileInputStream(archivo);
      ObjectInputStream manejadorDeLectura = new ObjectInputStream(flujoDeEntrada);
      escrituraDeJuego = (EscrituraDeJuego) manejadorDeLectura.readObject();
      manejadorDeLectura.close();
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException();
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    return escrituraDeJuego;
  }

}
