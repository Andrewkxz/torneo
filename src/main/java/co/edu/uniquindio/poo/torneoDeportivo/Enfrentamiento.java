package co.edu.uniquindio.poo.torneoDeportivo;

import static co.edu.uniquindio.poo.torneoDeportivo.util.AssertionUtil.ASSERTION;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public record Enfrentamiento (String lugar, LocalDateTime fechaYhora, Collection<Equipo> equipos, Collection<EstadoEnfrentamiento> estadoEnfrentamientos){
    public Enfrentamiento{
    ASSERTION.assertion(lugar != null && !lugar.isBlank(), "El lugar es requerido");
    ASSERTION.assertion(fechaYhora != null, "La fecha y hora son requeridas");
    }

    public  Enfrentamiento(String lugar, LocalDateTime fechaYhora){
        this(lugar,fechaYhora,new LinkedList<>(), new LinkedList<>());
    }

     /**
     * Permite registrar un equipo en un enfrentamiento siempre y cuando no exista ya un equipo registrado en el enfrentamiento con el mismo nombre, sin embargo, no el enfrentamiento no se realizará hasta que los dos equipos estén registrados.
     * @param equipo Equipo que se desea registrar.
     */
    public void registrarEquipo(Equipo equipo) {
        validarEquipoExiste(equipo);
        equipos.add(equipo);
        if (equipos.size()==2){
            realizarEnfrentamiento();
        } else if (equipos.size()>2){
            ASSERTION.assertion(equipos.size()>2,"No se puede registrar más de dos equipos en un enfrentamiento");
        }
        ASSERTION.assertion(equipos.size()< 2, "Se requieren dos equipos para poder realizar el enfrentamiento");
    }

    private void realizarEnfrentamiento(){
        System.out.println("Enfrentamiento realizado entre: " + equipos.stream().map(Equipo::getNombreCompleto).collect(Collectors.joining(" vs ")));
    }

    /**
     * Permimte buscar un equipo en el enfrentamiento basado en su nombre.
     * @param equipo Equipo que se desea buscar
     * @return Optional con el equipo que coincida con el nombre del equipo buscado, 
     * o Optinal vacío en caso de no encontrar un equipo en el enfrentamiento con dicho nombre.
     */
    public Optional<Equipo> buscarEquipo(Equipo equipo){
        Predicate<Equipo> nombreIgual = j->j.getNombreCompleto().equals(equipo.getNombreCompleto());
        return equipos.stream().filter(nombreIgual).findAny();
    }

    /**
     * Valida que no exista ya un equipo registrado con el mismo nombre, en caso de haberlo genera un assertion error.
     */
    private void validarEquipoExiste(Equipo equipo) {
        boolean existeEquipo = buscarEquipo(equipo).isPresent();
        ASSERTION.assertion( !existeEquipo,"El equipo ya esta registrado");
    }

    public Collection<Equipo> equipos() {
        return equipos;
    }

}
