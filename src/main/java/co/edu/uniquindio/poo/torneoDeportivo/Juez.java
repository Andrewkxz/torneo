package co.edu.uniquindio.poo.torneoDeportivo;

public class Juez extends Persona{
    private String licencia;
    public Juez(String licencia, String nombre, String apellido, String email, String celular) {
        super(nombre, apellido, email, celular);
        this.licencia = licencia;

    }
    public String getLicencia() {
        return licencia;
    }

    

}
