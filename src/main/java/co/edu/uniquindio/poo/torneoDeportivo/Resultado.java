package co.edu.uniquindio.poo.torneoDeportivo;

public class Resultado {
    private int puntosEquipoLocal;
    private int puntosEquipoVisitante;
    public Resultado(int puntosEquipoLocal, int puntosEquipoVisitante) {
        this.puntosEquipoLocal = puntosEquipoLocal;
        this.puntosEquipoVisitante = puntosEquipoVisitante;
    }
    public int getPuntosEquipoLocal() {
        return puntosEquipoLocal;
    }
    public void setPuntosEquipoLocal(int puntosEquipoLocal) {
        this.puntosEquipoLocal = puntosEquipoLocal;
    }
    public int getPuntosEquipoVisitante() {
        return puntosEquipoVisitante;
    }
    public void setPuntosEquipoVisitante(int puntosEquipoVisitante) {
        this.puntosEquipoVisitante = puntosEquipoVisitante;
    }
}
