package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import co.edu.uniquindio.poo.torneoDeportivo.CaracterTorneo;
import co.edu.uniquindio.poo.torneoDeportivo.Enfrentamiento;
import co.edu.uniquindio.poo.torneoDeportivo.Equipo;
import co.edu.uniquindio.poo.torneoDeportivo.EstadoEnfrentamiento;
import co.edu.uniquindio.poo.torneoDeportivo.Juez;
import co.edu.uniquindio.poo.torneoDeportivo.Persona;
import co.edu.uniquindio.poo.torneoDeportivo.TipoGenero;
import co.edu.uniquindio.poo.torneoDeportivo.TipoTorneo;
import co.edu.uniquindio.poo.torneoDeportivo.Torneo;

public class EnfrentamientoTest {
    /**
     * Instancia para el manejo de logs
     */
    private static final Logger LOG = Logger.getLogger(JugadorTest.class.getName());

    @Test
    public void equipoEnEnfrentamientoSi0No() {
        LOG.info("Inicio de prueba equipoEnEnfrentamientoSi0No...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual - 15 días\|fechaActual+15 días\|24\|18\|0\|LOCAL|GRUPAL}, Juez1{12345678, Andrés, Rodriguez, ahjs@mdja.com, 123456789}, 

        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(1), LocalDate.now().plusDays(15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL,TipoGenero.FEMENINO,CaracterTorneo.GRUPAL);
        var representante1 = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var representante2 = new Persona("Roberto", "Pulgar", "robpul@email.com", "2827178287");
        var juez1 = new Juez("12345678", "Andrés", "Rodriguez", "ahjs@mdja.com", "123456789");
        var equipo1 = new Equipo("equipo1", representante1);
        var equipo2 = new Equipo("equipo2", representante2);
        var enfrentamiento = new Enfrentamiento("Uniquindio", LocalDateTime.now().plusHours(1), juez1, "equipo1", "equipo2", 3, 5, EstadoEnfrentamiento.FINALIZADO);
        torneo.registrarJuez(juez1);
        //torneo.registrarEnfrentamiento(enfrentamiento);
        enfrentamiento.registrarEquipo(equipo1);
    
        assertTrue(torneo.equipoEnEnfrentamiento(equipo1, enfrentamiento));
        assertFalse(torneo.equipoEnEnfrentamiento(equipo2, enfrentamiento));

        
        LOG.info("Fin de prueba equipoEnEnfrentamientoSi0No...");
    }

     @Test
    public void equipoEnEnfrentamientoNumeroMenor() {
        LOG.info("Inicio de prueba equipoEnEnfrentamientoNumeroMenor...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual - 15 días\|fechaActual+15 días\|24\|18\|0\|LOCAL|GRUPAL}, Juez1{12345678, Andrés, Rodriguez, ahjs@mdja.com, 123456789}, 

        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(1), LocalDate.now().plusDays(15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL,TipoGenero.FEMENINO,CaracterTorneo.GRUPAL);
        var representante1 = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        //var representante2 = new Persona("Roberto", "Pulgar", "robpul@email.com", "2827178287");
        var juez1 = new Juez("12345678", "Andrés", "Rodriguez", "ahjs@mdja.com", "123456789");
        var equipo1 = new Equipo("equipo1", representante1);
        //var equipo2 = new Equipo("equipo2", representante2);
        var enfrentamiento = new Enfrentamiento("Uniquindio", LocalDateTime.now().plusHours(1), juez1, "equipo1", "equipo2", 3, 5, EstadoEnfrentamiento.FINALIZADO);
        torneo.registrarJuez(juez1);
        enfrentamiento.registrarEquipo(equipo1);
        //enfrentamiento.registrarEquipo(equipo2);
        //torneo.registrarEnfrentamiento(enfrentamiento);
    
        assertThrows(Throwable.class, ()-> torneo.registrarEnfrentamiento(enfrentamiento));

        
        LOG.info("Fin de prueba equipoEnEnfrentamientoNumeroMenor...");
    }

    @Test
    public void equipoEnEnfrentamientoNumeroMayor() {
        LOG.info("Inicio de prueba equipoEnEnfrentamientoNumeroMayor...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual - 15 días\|fechaActual+15 días\|24\|18\|0\|LOCAL|GRUPAL}, Juez1{12345678, Andrés, Rodriguez, ahjs@mdja.com, 123456789}, 

        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(1), LocalDate.now().plusDays(15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL,TipoGenero.FEMENINO,CaracterTorneo.GRUPAL);
        var representante1 = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var representante2 = new Persona("Roberto", "Pulgar", "robpul@email.com", "2827178287");
        var representante3 = new Persona("Ruben", "Gomez", "rubgo@email.com", "28712156");
        var juez1 = new Juez("12345678", "Andrés", "Rodriguez", "ahjs@mdja.com", "123456789");
        var equipo1 = new Equipo("equipo1", representante1);
        var equipo2 = new Equipo("equipo2", representante2);
        var equipo3 = new Equipo("equipo3", representante3);
        var enfrentamiento = new Enfrentamiento("Uniquindio", LocalDateTime.now().plusHours(1), juez1, "equipo1", "equipo2", 3, 5, EstadoEnfrentamiento.FINALIZADO);
        torneo.registrarJuez(juez1);
        enfrentamiento.registrarEquipo(equipo1);
        enfrentamiento.registrarEquipo(equipo2);
        enfrentamiento.registrarEquipo(equipo3);
        //torneo.registrarEnfrentamiento(enfrentamiento);
    
        assertThrows(Throwable.class, ()-> torneo.registrarEnfrentamiento(enfrentamiento));

        
        LOG.info("Fin de prueba equipoEnEnfrentamientoNumeroMayor...");
    }

    @Test
    public void buscarEnfrentamientoPorNombre() {
        LOG.info("Inicio de prueba buscarEnfrentamientoPorNombre...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual - 15 días\|fechaActual+15 días\|24\|18\|0\|LOCAL|GRUPAL}, Juez1{12345678, Andrés, Rodriguez, ahjs@mdja.com, 123456789}, 

        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(1), LocalDate.now().plusDays(15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL,TipoGenero.FEMENINO,CaracterTorneo.GRUPAL);
        var representante1 = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var representante2 = new Persona("Roberto", "Pulgar", "robpul@email.com", "2827178287");
        var juez1 = new Juez("12345678", "Andrés", "Rodriguez", "ahjs@mdja.com", "123456789");
        var equipo1 = new Equipo("equipo1", representante1);
        var equipo2 = new Equipo("equipo2", representante2);
        var enfrentamiento = new Enfrentamiento("Uniquindio", LocalDateTime.now().plusHours(1), juez1, "equipo1", "equipo2", 3, 5, EstadoEnfrentamiento.FINALIZADO);
        torneo.registrarJuez(juez1);
        enfrentamiento.registrarEquipo(equipo1);
        enfrentamiento.registrarEquipo(equipo2);
        torneo.registrarEnfrentamiento(enfrentamiento);
        //torneo.buscarEnfrentamientoPorNombre(equipo1);

        var resultado = torneo.buscarEnfrentamientoPorNombre(equipo1);
        assertTrue(resultado.isPresent());
        assertEquals(enfrentamiento, resultado.get());
        //assertThrows(Throwable.class, ()-> torneo.registrarEnfrentamiento(enfrentamiento));

        
        LOG.info("Fin de prueba buscarEnfrentamientoPorNombre...");
    }

    @Test
    public void estadoPartido() {
        LOG.info("Inicio de prueba estadoPartido...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual - 15 días\|fechaActual+15 días\|24\|18\|0\|LOCAL|GRUPAL}, Juez1{12345678, Andrés, Rodriguez, ahjs@mdja.com, 123456789}, 

        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(1), LocalDate.now().plusDays(15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL,TipoGenero.FEMENINO,CaracterTorneo.GRUPAL);
        var juez1 = new Juez("12345678", "Andrés", "Rodriguez", "ahjs@mdja.com", "123456789");
        var representante1 = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var representante2 = new Persona("Roberto", "Pulgar", "robpul@email.com", "2827178287");
        var equipo1 = new Equipo("equipo1", representante1);
        var equipo2 = new Equipo("equipo2", representante2);
       
        var enfrentamiento1 = new Enfrentamiento("Uniquindio", LocalDateTime.now().plusHours(1), juez1, "equipo1", "equipo2", 3, 5, EstadoEnfrentamiento.PENDIENTE);

        enfrentamiento1.registrarEquipo(equipo1);
        enfrentamiento1.registrarEquipo(equipo2);
        torneo.registrarEnfrentamiento(enfrentamiento1);

        torneo.estadoPartido();
        assertEquals(EstadoEnfrentamiento.FINALIZADO, enfrentamiento1.estado());

        LOG.info("Fin de prueba estadoPartido...");
    }

    @Test
    public void estadoPartidoEnJuego() {
        LOG.info("Inicio de prueba estadoPartidoEnJuego...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual - 15 días\|fechaActual+15 días\|24\|18\|0\|LOCAL|GRUPAL}, Juez1{12345678, Andrés, Rodriguez, ahjs@mdja.com, 123456789}, 

        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(1), LocalDate.now().plusDays(15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL,TipoGenero.FEMENINO,CaracterTorneo.GRUPAL);
        var juez1 = new Juez("12345678", "Andrés", "Rodriguez", "ahjs@mdja.com", "123456789");
        var representante1 = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var representante2 = new Persona("Roberto", "Pulgar", "robpul@email.com", "2827178287");
        var equipo1 = new Equipo("equipo1", representante1);
        var equipo2 = new Equipo("equipo2", representante2);
       
        var enfrentamiento1 = new Enfrentamiento("Uniquindio", LocalDateTime.now().plusHours(1), juez1, "equipo1", "equipo2", 3, 5, EstadoEnfrentamiento.FINALIZADO);

        enfrentamiento1.registrarEquipo(equipo1);
        enfrentamiento1.registrarEquipo(equipo2);
        torneo.registrarEnfrentamiento(enfrentamiento1);

        assertEquals("equipo1", enfrentamiento1.equipoLocal());
        assertEquals("equipo2", enfrentamiento1.equipoVisitante());

        LOG.info("Fin de prueba estadoPartidoEnJuego...");
    }
    
    
}
