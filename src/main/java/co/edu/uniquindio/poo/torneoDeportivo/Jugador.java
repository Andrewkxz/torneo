package co.edu.uniquindio.poo.torneoDeportivo;
import static co.edu.uniquindio.poo.torneoDeportivo.util.AssertionUtil.ASSERTION;

import java.time.LocalDate;
import java.time.Period;

public class Jugador extends Persona implements Participante{
    private TipoGenero tipoGenero;
    private final LocalDate fechaNacimiento;
    
    public Jugador(String nombre, String apellido, String email, String celular, TipoGenero tipoGenero,
            LocalDate fechaNacimiento) {
        super(nombre, apellido, email, celular);
        ASSERTION.assertion(tipoGenero == TipoGenero.MASCULINO || tipoGenero == TipoGenero.FEMENINO, "El genero del jugador debe ser Masculino ó Femenino");
        this.tipoGenero = tipoGenero;
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaNacimiento(){
        return fechaNacimiento;
    }

    public TipoGenero getTipoGenero() {
        return tipoGenero;
    }
    
    public byte calcularEdad(LocalDate fecha){
        return (byte) Period.between(fechaNacimiento, fecha).getYears();
    }

    @Override
    public String getNombreCompleto() {
        return getNombre()+ " "+getApellido();
    }


}

