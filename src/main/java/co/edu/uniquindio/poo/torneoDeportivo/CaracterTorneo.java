package co.edu.uniquindio.poo.torneoDeportivo;

public enum CaracterTorneo {
    INDIVIDUAL{
        public boolean esValido(Participante participante){
            return participante instanceof Jugador;
        }
    },GRUPAL{
        public boolean esValido(Participante participante){
            return participante instanceof Equipo;
        }
    };

    public abstract boolean esValido(Participante participante);
}
