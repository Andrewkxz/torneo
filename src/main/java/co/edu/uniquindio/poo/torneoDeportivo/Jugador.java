package co.edu.uniquindio.poo.torneoDeportivo;

public class Jugador extends Persona{
    private String genero;
    public Jugador(String genero, String nombre, String apellido, String email, String celular) {
        super(nombre, apellido, email, celular);
        this.genero = genero;

    }
    public String getGenero() {
        return genero;
    }

    


}

