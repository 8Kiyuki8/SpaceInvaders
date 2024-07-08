package Presentación.Servicios;

public class AdministradorArchivos {
  public String obtenerURLRecurso(String nombreCarpeta, int númeroSprite) {
    return "/Presentación/Recursos/" + nombreCarpeta + "/" + nombreCarpeta.toLowerCase() + "_" +
      númeroSprite + ".png";
  }
}
