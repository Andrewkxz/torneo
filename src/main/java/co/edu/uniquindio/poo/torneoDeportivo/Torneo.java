package co.edu.uniquindio.poo.torneoDeportivo;

import static co.edu.uniquindio.poo.torneoDeportivo.util.AssertionUtil.ASSERTION;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class Torneo {
    private final String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaInicioInscripciones;
    private LocalDate fechaCierreInscripciones;
    private final byte numeroParticipantes;
    private final byte limiteEdad;
    private final int valorInscripcion;
    private final TipoTorneo tipoTorneo;
    private final TipoGenero tipoGenero;
    private final Collection<Participante> participantes;
    private final CaracterTorneo caracter;
    private final Collection<Juez> jueces;
    private Collection<Enfrentamiento> enfrentamientos;

    public Torneo(String nombre, LocalDate fechaInicio,
            LocalDate fechaInicioInscripciones,
            LocalDate fechaCierreInscripciones, byte numeroParticipantes,
            byte limiteEdad, int valorInscripcion,TipoTorneo tipoTorneo,TipoGenero tipoGenero,CaracterTorneo caracter) {
        
        ASSERTION.assertion( nombre != null , "El nombre es requerido");
        
        
        
        ASSERTION.assertion( numeroParticipantes >= 0, "El número de participantes no puede ser negativo");
        ASSERTION.assertion( limiteEdad >= 0,"El limite de edad no puede ser negativo");
        ASSERTION.assertion( valorInscripcion >= 0,"El valor de la inscripción no puede ser negativo");
        
        
        
        this.nombre = nombre;
        
        setFechaInicioInscripciones(fechaInicioInscripciones);
        setFechaCierreInscripciones(fechaCierreInscripciones); 
        setFechaInicio(fechaInicio);
        this.numeroParticipantes = numeroParticipantes;
        this.limiteEdad = limiteEdad;
        this.valorInscripcion = valorInscripcion;
        this.tipoTorneo = tipoTorneo;
        this.tipoGenero = tipoGenero;
        this.participantes = new LinkedList<>();
        this.caracter = Objects.requireNonNull(caracter,"El carácter del torneo es requerido");
        this.jueces = new LinkedList<>();
        this.enfrentamientos = new LinkedList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaInicioInscripciones() {
        return fechaInicioInscripciones;
    }

    public LocalDate getFechaCierreInscripciones() {
        return fechaCierreInscripciones;
    }

    public byte getNumeroParticipantes() {
        return numeroParticipantes;
    }

    public byte getLimiteEdad() {
        return limiteEdad;
    }

    public int getValorInscripcion() {
        return valorInscripcion;
    }

    public TipoTorneo getTipoTorneo() {
        return tipoTorneo;
    }

    public TipoGenero getTipoGenero(){
        return tipoGenero;
    }

    public CaracterTorneo getCaracter() {
        return caracter;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        ASSERTION.assertion( fechaInicio != null , "La fecha de inicio es requerida");
        ASSERTION.assertion( ( fechaInicioInscripciones == null || fechaInicio.isAfter(fechaInicioInscripciones) ) &&
                ( fechaCierreInscripciones == null || fechaInicio.isAfter(fechaCierreInscripciones) ),"La fecha de inicio no es válida" );
        this.fechaInicio = fechaInicio;
    }

    public void setFechaInicioInscripciones(LocalDate fechaInicioInscripciones) {
        ASSERTION.assertion( fechaInicioInscripciones != null , "La fecha de inicio de inscripciones es requerida");
        this.fechaInicioInscripciones = fechaInicioInscripciones;
    }


    public void setFechaCierreInscripciones(LocalDate fechaCierreInscripciones) {
        ASSERTION.assertion( fechaCierreInscripciones != null , "La fecha de cierre es requerida");
        ASSERTION.assertion( fechaCierreInscripciones.isAfter(fechaInicioInscripciones),"La fecha de cierre de inscripciones debe ser posterior a la fecha de inicio de inscripciones" );
        this.fechaCierreInscripciones = fechaCierreInscripciones;
    }
    
    /**
     * Permite registrar un participante en el torneo
     * @param participante Participante a ser registrado
     * @throws Se genera un error si ya existe un equipo registrado con el mismo nombre, o en caso de que las inscripciones del torneo no estén abiertas.
     */
    public void registrarParticipante(Participante participante) {
        validarParticipanteExiste(participante); 

        validarInscripciopnesAbiertas(); 
        validarCaracter(participante);

        participantes.add(participante);
    }

    /**
     * Valida que el participante sea acorde con el carácter del torneo.
     * @param participante Participante a ser registrado
     */
    private void validarCaracter(Participante participante) {
        ASSERTION.assertion( caracter.esValido(participante),"Las inscripciones no están abiertas");
    }

    /**
     * Valida que las inscripciones del torneo esten abiertas, en caso de no estarlo genera un assertion error.
     */
    private void validarInscripciopnesAbiertas() {
        boolean inscripcionAbierta = fechaInicioInscripciones.isBefore(LocalDate.now()) && fechaCierreInscripciones.isAfter(LocalDate.now());
        ASSERTION.assertion( inscripcionAbierta,"Las inscripciones no están abiertas");
    }

    /**
     * Valida que no exista ya un equipo registrado con el mismo nombre, en caso de haberlo genera un assertion error.
     */
    private void validarParticipanteExiste(Participante participante) {
        boolean existeEquipo = buscarParticipantePorNombre(participante.getNombreCompleto()).isPresent();
        ASSERTION.assertion( !existeEquipo,"El equipo ya esta registrado");
    }

    /**
     * Permite obtener una copia no modificable de la lista de los participantes registrados.
     * @return Collection<Participante> no modificable de los participantes registrados en el torneo.
     */
    public Collection<Participante> getParticipantes() {
        return Collections.unmodifiableCollection(participantes);
    }
    
    /**
     * Permite buscar un participante por su nombre entre los participantes registrados en el torneo
     * @param nombre Nombre del participante que se está buscando
     * @return Un Optional<Participante> con el participante cuyo nombre sea igual al nombre buscado, o un Optional vacío en caso de no encontrar un participante con nombre igual al dado.
     */
    public Optional<Participante> buscarParticipantePorNombre(String nombre){
        Predicate<Participante> condicion = participante->participante.getNombreCompleto().equals(nombre);
        return participantes.stream().filter(condicion).findAny();
    }

    /**
     * Permite registrar un jugador en el equipo siempre y cuando este dentro de las fechas validas de registro, 
     * no exista ya un jugador registrado con el mismo nombre y apellido y el jugador no exceda el limite de edad del torneo.
     *  
     * @param nombre Nombre del equipo en que se desea registrar el jugador
     * @param jugador Jugador que se desea registrar.
     */
    public void registrarJugador(String nombre, Jugador jugador) {
        var participante = buscarParticipantePorNombre(nombre);
        
        participante.ifPresent( (e)->{
            if( e instanceof Equipo equipo){
                registrarJugador(equipo, jugador);
            }
        } );
    }

    /**
     * Permite registrar un jugador en el equipo siempre y cuando este dentro de las fechas validas de registro, 
     * no exista ya un jugador registrado con el mismo nombre y apellido y el jugador no exceda el limite de edad del torneo.
     * 
     * @param equipo Equipo en el que se desea registrar el jugador.
     * @param jugador Jugador que se desea registrar.
     */
    public void registrarJugador(Equipo equipo, Jugador jugador) {
        ASSERTION.assertion( !LocalDate.now().isAfter(fechaCierreInscripciones) , "No se pueden registrar jugadores después del a fecha de cierre de inscripciones");
        validarLimiteEdadJugador(jugador); 
        validarJugadorExiste(jugador);
        equipo.registrarJugador(jugador);
    }

    /**
     * Permite buscar un jugador basado en su nombre y apellido en todos los equipos registrados en el torneo.
     * @param jugador Jugador que se desea buscar
     * @return Optional con el jugador encontrado o un optional vacío en caso de no haber encontrado un jugador con el nombre y apellido del jugador buscado.
     */
    public Optional<Jugador> buscarJugador(Jugador jugador){
        return participantes.stream()
            .filter(p->p instanceof Equipo)
            .map(p->(Equipo)p)
            .map(equipo->equipo.buscarJugador(jugador))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .findAny();
    }

    /**
     * Valida que no exista ya un jugador registrado con el mismo nombre y apellido, en caso de haberlo genera un assertion error.
     */
    private void validarJugadorExiste(Jugador jugador) {
        boolean existeJugador = buscarJugador(jugador).isPresent();
        ASSERTION.assertion( !existeJugador,"El jugador ya esta registrado");
    }

    /**
     * Valida que no exista se puedan registrar jugadores que al momento del inicio del torneo excedan el limite de edad.
     */
    private void validarLimiteEdadJugador(Jugador jugador) {
        var edadAlInicioTorneo = jugador.calcularEdad(fechaInicio);
        ASSERTION.assertion( limiteEdad == 0 || limiteEdad >= edadAlInicioTorneo , "No se pueden registrar jugadores que excedan el limite de edad del torneo"); 
    }

    /**
     * permite registrar un juez en el torneo
     * @param juez Juez a ser registrado.
     */
    public void registrarJuez(Juez juez){
        validarJuezExiste(juez);
        jueces.add(juez);
    }

    /**
     * Valida que no exista un juez registrado con la misma licencia, en caso de haberlo genera una assertion error.
     * @param juez
     */
    private void validarJuezExiste (Juez juez){
        boolean existeJuez = buscarJuezPorLicencia(juez.getLicencia()).isPresent();
        ASSERTION.assertion( !existeJuez, "El juez ya está registrado");
    }

    /**
     * Permite obtener una copia no modificable de la lista de los jueces registrados.
     * @return Collection<Juez> no modificable de los jueces registrados en el torneo.
     */
    public Collection<Juez> getJueces(){
        return Collections.unmodifiableCollection(jueces);
    }

    /**
     * Permite buscar un juez por su licencia entre los jueces registrados en el torneo.
     * @param licencia Licencia del juez que se está buscando.
     * @return un Optional<Juez> con el juez cuya licencia sea igual a la licencia buscada, o un Optional vacío en caso de no encontrar un juez con la licencia dada.
     */
    public Optional<Juez> buscarJuezPorLicencia(String licencia){
        Predicate<Juez> condicion = juez -> juez.getLicencia().equals(licencia);
        return jueces.stream().filter(condicion).findAny();
    }

    /**
    * Valida que las inscripciones del torneo estén abiertas, en caso de no estarlo genera un assertion error.
    */
    private void validarInscripcionesAbiertas() {
        boolean inscripcionAbierta = fechaInicioInscripciones.isBefore(LocalDate.now()) && fechaCierreInscripciones.isAfter(LocalDate.now());
        ASSERTION.assertion(inscripcionAbierta,"Las inscripciones no están abiertas");
    }

    /**
    * Permite registrar un enfrentamiento en el torneo
    * @param enfrentamiento Enfrentamiento a ser registrado
    * @throws Se genera un error si ya existe un enfrentamiento registrado con el mismo nombre, 
    * o si las inscripciones del torneo no están abiertas.
     */
    public void registrarEnfrentamiento(Enfrentamiento enfrentamiento) {
        validarInscripcionesAbiertas();
        ASSERTION.assertion(enfrentamiento.equipos().size() ==2, "Es requerido que se registren exactamente dos equipos en el enfrentamiento");

        enfrentamientos.add(enfrentamiento);
    }



    public boolean equipoEnEnfrentamiento(Equipo equipo, Enfrentamiento enfrentamiento){
        return enfrentamiento.equipos().contains(equipo);
    }

    /**
    * Permite obtener una copia no modificable de la lista de los enfrentamientos registrados.
    * @return Collection<Enfrentamiento> no modificable de los enfrentamientos registrados en el torneo.
    */
    public Collection<Enfrentamiento> getEnfrentamientos(){
        return Collections.unmodifiableCollection(enfrentamientos);
    }

    public void estadoPartido(){
        Collection<Enfrentamiento> enfrentamientos = getEnfrentamientos();
        for (Enfrentamiento enfrentamiento : enfrentamientos){
            LocalDateTime fechaPartido = enfrentamiento.fechaYhora();
            LocalDateTime fechaActual = LocalDateTime.now();
            if (fechaActual.isEqual(fechaPartido)){
                enfrentamiento.actualizarEstado(EstadoEnfrentamiento.EN_JUEGO);
            } else if (fechaActual.isAfter(fechaPartido) && Duration.between(fechaPartido, fechaActual).toMinutes() > 90){
                enfrentamiento.actualizarEstado(EstadoEnfrentamiento.FINALIZADO);
            } else if (fechaActual.isAfter(fechaPartido)){
                enfrentamiento.actualizarEstado(EstadoEnfrentamiento.APLAZADO);
            }
        }
    }


    /**
    * Permite buscar un enfrentamiento por el nombre del equipo entre los enfrentamientos registrados en el torneo.
    * @param nombre Nombre del equipo que se está buscando
    * @return Un Optional<Enfrentamiento> con el enfrentamiento cuyo nombre sea igual al nombre buscado, 
    *         o un Optional vacío en caso de no encontrar un enfrentamiento con nombre igual al dado.
    */
    public Optional<Enfrentamiento> buscarEnfrentamientoPorNombre(Equipo equipo){
        Predicate<Enfrentamiento> condicion = e -> e.equipos().contains(equipo);
        return enfrentamientos.stream().filter(condicion).findAny();
    }

}