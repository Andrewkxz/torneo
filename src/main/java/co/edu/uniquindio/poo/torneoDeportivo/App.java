package co.edu.uniquindio.poo.torneoDeportivo;


import java.time.LocalDate;

public class App {
    public static void main(String[] args) {

        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual - 15 días\|fechaActual+15 días\|24\|18\|0\|LOCAL|INDIVIDUAL}   Jugador {Christian,Candela,chrcandela@email.com,6067431234, fechaActual - 15 años}

        
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)18, 0,TipoTorneo.LOCAL,TipoGenero.MASCULINO,CaracterTorneo.INDIVIDUAL);

        var juez = new Juez("123456789", "Andrés", "Rodríguez", "jaidera.melor@uqvirtual.edu.co", "123456789");
        
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234",TipoGenero.MASCULINO,LocalDate.now().minusYears(15));

        torneo.registrarParticipante(jugador);
        torneo.registrarJuez(juez);

    }

}
