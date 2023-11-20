package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import co.edu.uniquindio.poo.torneoDeportivo.CaracterTorneo;
import co.edu.uniquindio.poo.torneoDeportivo.Equipo;
import co.edu.uniquindio.poo.torneoDeportivo.Juez;
import co.edu.uniquindio.poo.torneoDeportivo.Jugador;
import co.edu.uniquindio.poo.torneoDeportivo.Persona;
import co.edu.uniquindio.poo.torneoDeportivo.TipoGenero;
import co.edu.uniquindio.poo.torneoDeportivo.TipoTorneo;
import co.edu.uniquindio.poo.torneoDeportivo.Torneo;

public class JugadorTest {
    /**
     * Instancia para el manejo de logs
     */
    private static final Logger LOG = Logger.getLogger(JugadorTest.class.getName());
    
    /**
     * Verificar que sea posible registrar un jugador en un equipo 
     * 
     */
    @Test
    public void registrarJugadorEquipo() {
        LOG.info("Inicio de prueba registrarJugadorEquipo...");
        // Almacenar los datos de prueba Equipo{Uniquindio} Representante{Robinson,Pulgarin,rpulgarin@email.com,6067359300}, Jugador {Christian,Candela,chrcandela@email.com,6067431234, fechaActual - 15 años}

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquindio", representante);
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234",TipoGenero.MASCULINO,LocalDate.now().minusYears(15));

        equipo.registrarJugador(jugador);

        // Recuperación y verificación de datos
        assertTrue(equipo.jugadores().contains(jugador));
        assertEquals(1, equipo.jugadores().size());
        LOG.info("Fin de prueba registrarJugadorEquipo...");
    }


    /**
     * Verificar que sea posible registrar un jugador en un equipo desde el torneo 
     * 
     */
    @Test
    public void registrarJugadorTorneo() {
        LOG.info("Inicio de prueba registrarJugadorTorneo...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual - 15 días\|fechaActual+15 días\|24\|18\|0\|LOCAL|GRUPAL}  Equipo{Uniquindio} Representante{Robinson,Pulgarin,rpulgarin@email.com,6067359300}, Jugador {Christian,Candela,chrcandela@email.com,6067431234, fechaActual - 15 años}

        
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(1), LocalDate.now().plusDays(15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL,TipoGenero.FEMENINO,CaracterTorneo.GRUPAL);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var juez = new Juez("12345678", "Andrés", "Rodriguez", "ahjs@mdja.com", "123456789");
        var equipo = new Equipo("Uniquindio", representante);
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234",TipoGenero.MASCULINO,LocalDate.now().minusYears(15));

        torneo.registrarParticipante(equipo);
        torneo.registrarJugador("Uniquindio",jugador);
        torneo.registrarJuez(juez);
        // Recuperación y verificación de datos
        assertTrue(equipo.jugadores().contains(jugador));
        assertEquals(1, equipo.jugadores().size());
        LOG.info("Fin de prueba registrarJugadorTorneo...");
    }

    /**
     * Verificar que sea posible registrar un jugador en un equipo desde el torneo cuando el torneo no tiene limite de edad
     * 
     */
    @Test
    public void registrarJugadorTorneoSinLimiteEdad() {
        LOG.info("Inicio de prueba registrarJugadorTorneoSinLimiteEdad...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual - 15 días\|fechaActual+15 días\|24\|0\|0\|LOCAL|GRUPAL}  Equipo{Uniquindio} Representante{Robinson,Pulgarin,rpulgarin@email.com,6067359300}, Jugador {Christian,Candela,chrcandela@email.com,6067431234, fechaActual - 21 años}

        
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(1), LocalDate.now().plusDays(15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL,TipoGenero.FEMENINO,CaracterTorneo.GRUPAL);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var juez = new Juez("12345678", "Andrés", "Rodriguez", "ahjs@mdja.com", "123456789");
        var equipo = new Equipo("Uniquindio", representante);
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234",TipoGenero.MASCULINO,LocalDate.now().minusYears(15));

        torneo.registrarParticipante(equipo);
        torneo.registrarJugador("Uniquindio",jugador);
        torneo.registrarJuez(juez);

        // Recuperación y verificación de datos
        assertTrue(equipo.jugadores().contains(jugador));
        assertEquals(1, equipo.jugadores().size());
        LOG.info("Fin de prueba registrarJugadorTorneoSinLimiteEdad...");
    }

    /**
     * Verificar que no sea posible registrar un jugador en un equipo desde el torneo cuando el torneo con limite de edad
     * 
     * NOTA: Si el registro se hiciera desde el equipo sería posible de momento registrar el jugador, debido a que el torneo no tiene control sobre el código interno del equipo, esto se puede solucionar validando todos los equipos al momento de iniciar un torneo. O se podría aplicar el patron de diseño listener para que torneo sea advertido cuando se haga una modificación en la lista de jugadores del equipo.
     * 
     */
    @Test
    public void registrarJugadorTorneoConLimiteEdad() {
        LOG.info("Inicio de prueba registrarJugadorTorneoConLimiteEdad...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual - 15 días\|fechaActual+15 días\|24\|18\|0\|LOCAL|GRUPAL}  Equipo{Uniquindio} Representante{Robinson,Pulgarin,rpulgarin@email.com,6067359300}, Jugador {Christian,Candela,chrcandela@email.com,6067431234, fechaActual - 21 años}

        
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)18, 0,TipoTorneo.LOCAL,TipoGenero.FEMENINO,CaracterTorneo.GRUPAL);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var juez = new Juez("12345678", "Andrés", "Rodriguez", "ahjs@mdja.com", "123456789");
        var equipo = new Equipo("Uniquindio", representante);
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234",TipoGenero.MASCULINO,LocalDate.now().minusYears(21));

        torneo.registrarParticipante(equipo);
        torneo.registrarJuez(juez);
        assertThrows(Throwable.class,()->torneo.registrarJugador("Uniquindio",jugador));

        
        LOG.info("Fin de prueba registrarJugadorTorneoConLimiteEdad...");
    }

    /**
     * Verificar que no sea posible registrar un jugador en un equipo si las inscripciones ya cerraron
     * 
     * NOTA: Si el registro se hiciera desde el equipo sería posible de momento registrar el jugador, debido a que el torneo no tiene control sobre el código interno del equipo, esto se puede solucionar validando todos los equipos al momento de iniciar un torneo. O se podría aplicar el patron de diseño listener para que torneo sea advertido cuando se haga una modificación en la lista de jugadores del equipo.
     * 
     */
    @Test
    public void registrarJugadorTorneoInscripcionesCerradas() {
        LOG.info("Inicio de prueba registrarJugadorTorneoInscripcionesCerradas...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual - 15 días\|fechaActual-1 día\|24\|18\|0\|LOCAL|GRUPAL}  Equipo{Uniquindio} Representante{Robinson,Pulgarin,rpulgarin@email.com,6067359300} Jugador {Christian,Candela,chrcandela@email.com,6067431234, fechaActual - 15 años}

        
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(1), (byte)24, (byte)18, 0,TipoTorneo.LOCAL,TipoGenero.FEMENINO,CaracterTorneo.GRUPAL);
        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var juez = new Juez("12345678", "Andrés", "Rodriguez", "ahjs@mdja.com", "123456789");
        var equipo = new Equipo("Uniquindio", representante);
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234",TipoGenero.MASCULINO,LocalDate.now().minusYears(15));

        torneo.registrarParticipante(equipo);
        torneo.registrarJuez(juez);
        torneo.setFechaCierreInscripciones(LocalDate.now().minusDays(1));
        assertThrows(Throwable.class,()->torneo.registrarJugador("Uniquindio",jugador));

        
        LOG.info("Fin de prueba registrarJugadorTorneoInscripcionesCerradas...");
    }

    /**
     * Verificar que no sea posible registrar dos jugadores con el mismo nombre y apellido en un mismo  equipo 
     * 
     */
    @Test
    public void registrarJugadoresRepetidosEquipo() {
        LOG.info("Inicio de prueba registrarJugadorEquipo...");
        // Almacenar los datos de prueba Equipo{Uniquindio} Representante{Robinson,Pulgarin,rpulgarin@email.com,6067359300}, Jugador {Christian,Candela,chrcandela@email.com,6067431234, fechaActual - 15 años}

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquindio", representante);
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234",TipoGenero.MASCULINO,LocalDate.now().minusYears(15));
        var jugador2 = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234",TipoGenero.MASCULINO,LocalDate.now().minusYears(15));
        equipo.registrarJugador(jugador);
        assertThrows(Throwable.class,()->equipo.registrarJugador(jugador2));

        // Recuperación y verificación de datos
        
        LOG.info("Fin de prueba registrarJugadorEquipo...");
    }

    /**
     * Verificar que no sea posible registrar dos jugadores con el mismo nombre y apellido en un mismo  torneo 
     * 
     */
    @Test
    public void registrarJugadoresRepetidosTorneo() {
        LOG.info("Inicio de prueba registrarJugadoresRepetidosTorneo...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual - 15 días\|fechaActual+15 días\|24\|18\|0\|LOCAL|GRUPAL}  Equipo{Uniquindio} Representante{Robinson,Pulgarin,rpulgarin@email.com,6067359300},  Jugador {Christian,Candela,chrcandela@email.com,6067431234, fechaActual - 15 años}, Jugador {Christian,Candela,chrcandela@email.com,6067431234, fechaActual - 15 años}, Equipo{Quindío}

        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(1), LocalDate.now().plusDays(15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL,TipoGenero.FEMENINO,CaracterTorneo.GRUPAL);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var juez = new Juez("12345678", "Andrés", "Rodriguez", "ahjs@mdja.com", "123456789");
        var equipo = new Equipo("Uniquindio", representante);
        var equipo2 = new Equipo("Quindío", representante);
        torneo.registrarJuez(juez);
        torneo.registrarParticipante(equipo);
        torneo.registrarParticipante(equipo2);

        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234",TipoGenero.MASCULINO,LocalDate.now().minusYears(15));
        var jugador2 = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234",TipoGenero.MASCULINO,LocalDate.now().minusYears(15));
                
        torneo.registrarJugador("Uniquindio",jugador);
        assertThrows(Throwable.class,()->torneo.registrarJugador("Quindío",jugador2));

        // Recuperación y verificación de datos
        
        LOG.info("Fin de prueba registrarJugadoresRepetidosTorneo...");
    }

    @Test
    public void registrarJugadorGeneroMixto() {
        LOG.info("Inicio de prueba registrarJugadorGeneroMixto...");
        //La prueba pasa ya que lanza el assert de que el genero del jugador debe ser MASCULINO o FEMENINO pero no MIXTO, Jugador {Christian,Candela,chrcandela@email.com,6067431234, tipoGenero - MIXTO, fechaActual - 15 años}
        assertThrows(Throwable.class, ()-> new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234",TipoGenero.MIXTO,LocalDate.now().minusYears(15)));
        LOG.info("Fin de prueba registrarGeneroMixto...");
    }

     @Test
    public void registrarJuezRepetidoEnPartido() {
        LOG.info("Inicio de prueba registrarJuezRepetidoEnPartido...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual - 15 días\|fechaActual+15 días\|24\|18\|0\|LOCAL|GRUPAL}, Juez1{12345678, Andrés, Rodriguez, ahjs@mdja.com, 123456789}, Juez2{12345678, Andrés, Rodriguez, ahjs@mdja.com, 123456789}

        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(1), LocalDate.now().plusDays(15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL,TipoGenero.FEMENINO,CaracterTorneo.GRUPAL);
        var juez1 = new Juez("12345678", "Andrés", "Rodriguez", "ahjs@mdja.com", "123456789");
        var juez2 = new Juez("12345678", "Andrés", "Rodriguez", "ahjs@mdja.com", "123456789");
        torneo.registrarJuez(juez1);
        assertEquals("12345678",juez2.getLicencia());
        assertThrows(Throwable.class,()->torneo.registrarJuez(juez2));

        
        LOG.info("Fin de prueba registrarJuezRepetidoEnPartido...");
    }
    


}