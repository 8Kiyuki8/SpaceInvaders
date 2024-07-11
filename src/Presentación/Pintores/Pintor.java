package Presentación.Pintores;

public abstract class Pintor {
  public static final int RENDERIZAR_EN = 4;

  protected int índiceActualImagen = 0;
  private long últimaActualización = System.nanoTime();
  private final String nombre;

  public Pintor(String nombre) {
    this.nombre = nombre;
  }

  public void actualizarImagenEntidad() {
    long tiempoActual = System.nanoTime();
    if ((tiempoActual - últimaActualización) >= 100000000) {
      índiceActualImagen = (índiceActualImagen + 1) % RENDERIZAR_EN;
      últimaActualización = tiempoActual;
    }
  }

  public int obtenerÍndiceActualImagen() {
    return índiceActualImagen;
  }

  public String obtenerNombre() {
    return nombre;
  }
}
