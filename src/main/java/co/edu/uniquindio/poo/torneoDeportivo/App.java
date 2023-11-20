package co.edu.uniquindio.poo.torneoDeportivo;



import java.time.LocalDate;
import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) {

        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(1), LocalDate.now().plusDays(15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL,TipoGenero.FEMENINO,CaracterTorneo.GRUPAL);
        var juez1 = new Juez("12345678", "Andrés", "Rodriguez", "ahjs@mdja.com", "123456789");
        var representante1 = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var representante2 = new Persona("Roberto", "Pulgar", "robpul@email.com", "2827178287");
        var equipo1 = new Equipo("America", representante1);
        var equipo2 = new Equipo("Nacional", representante2);
       
        var enfrentamiento1 = new Enfrentamiento("Uniquindio", LocalDateTime.now().plusHours(1), juez1, "equipo1", "equipo2", 3, 5, EstadoEnfrentamiento.FINALIZADO);

        enfrentamiento1.registrarEquipo(equipo1);
        enfrentamiento1.registrarEquipo(equipo2);
        torneo.registrarEnfrentamiento(enfrentamiento1);

        torneo.estadoPartido();
    
    }

    }


