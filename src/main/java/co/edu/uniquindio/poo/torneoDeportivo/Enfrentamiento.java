package co.edu.uniquindio.poo.torneoDeportivo;

import static co.edu.uniquindio.poo.torneoDeportivo.util.AssertionUtil.ASSERTION;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Enfrentamiento {
    private String lugar;
    private LocalDateTime fechaYhora;
    private String equipoLocal;
    private String equipoVisitante;
    private Juez juez;
    private int golesLocal;
    private int golesVisitante;
    private EstadoEnfrentamiento estado;
    private String ganador;
    private String perdedor;
    private String empate;
    private Collection<Equipo> equipos;
    
    public Enfrentamiento (String lugar, LocalDateTime fechaYhora, Juez juez, String equipoLocal, String equipoVisitante, int golesLocal, int golesVisitante, EstadoEnfrentamiento estado){
        this.lugar = lugar;
        this.fechaYhora = fechaYhora;
        this.juez = juez;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        this.estado = estado;
        this.equipos = new LinkedList<>();
        ASSERTION.assertion(lugar != null && !lugar.isBlank(), "El lugar es requerido");
        ASSERTION.assertion(fechaYhora != null, "La fecha y hora son requeridas");
        ASSERTION.assertion(juez != null, "El juez es requerido");
        ASSERTION.assertion(equipos.size()<2 || equipos.size()>2,"El partido comenzará cuando se registren dos equipos solamente");
        if (equipos.size() == 2){
        realizarEnfrentamiento();
        }

    }

     /**
     * Permite registrar un equipo en un enfrentamiento siempre y cuando no exista ya un equipo registrado en el enfrentamiento con el mismo nombre, sin embargo, no el enfrentamiento no se realizará hasta que los dos equipos estén registrados.
     * @param equipo Equipo que se desea registrar.
     */
    public void registrarEquipo(Equipo equipo) {
        validarEquipoExiste(equipo);
        equipos.add(equipo);
        if (equipos.size() == 2){
            String [] nombreEquipos = equipos.stream().map(Equipo::getNombreCompleto).toArray(String[]::new);
            equipoLocal = nombreEquipos[0];
            equipoVisitante = nombreEquipos[1];
            realizarEnfrentamiento();
        }

    }

    /**
     * Realiza el enfrentamiento entre ambos equipos y guarda el equipo ganador y perdedor o si es empate.
     */
    private void realizarEnfrentamiento(){

        System.out.println("Enfrentamiento realizado entre: " + equipos.stream().map(Equipo::getNombreCompleto).collect(Collectors.joining(" vs ")));

        if (golesLocal > golesVisitante){
            ganador = equipoLocal;
            perdedor = equipoVisitante;
            estado = EstadoEnfrentamiento.FINALIZADO;
        } else if (golesLocal < golesVisitante){
             ganador = equipoVisitante;
             perdedor = equipoLocal;
             estado = EstadoEnfrentamiento.FINALIZADO;
        } else {
            empate = "Empate";
        }
        System.out.println("Resultado: Ganador -> " + ganador + ", Perdedor -> " + perdedor + ", Estado -> " + estado);
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

    public void actualizarEstado (EstadoEnfrentamiento estadoNuevo){
        EstadoEnfrentamiento estado = estadoNuevo;
        System.out.println("El estado del enfrentamiento ha sido actualizado satisfactoriamente a: " + estadoNuevo);
        ASSERTION.assertion(estado != EstadoEnfrentamiento.FINALIZADO, "No se puede actualizar a un nuevo estado");
    }

    public Collection<Equipo> equipos() {
        return equipos;
    }

    public String lugar() {
        return lugar;
    }

    public LocalDateTime fechaYhora() {
        return fechaYhora;
    }

    public String equipoLocal() {
        return equipoLocal;
    }

    public String equipoVisitante() {
        return equipoVisitante;
    }

    public int golesLocal() {
        return golesLocal;
    }

    public int golesVisitante() {
        return golesVisitante;
    }

    public EstadoEnfrentamiento estado() {
        return estado;
        
    }

    public String ganador() {
        return ganador;
    }

    public String perdedor() {
        return perdedor;
    }

    public Juez getJuez() {
        return juez;
    }


}
