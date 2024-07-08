import java.util.HashSet;

public class Equipo extends Participante {

    private HashSet<Atleta> integrantes = new HashSet<>();

    @Override
    public int getCantidadAtletas() {
        return this.integrantes.size();
    }

    public Equipo(String nombre, Deporte deporteEnQueParticipa) {
        super(nombre, deporteEnQueParticipa);
    }

    public void agregarIntegrante(Atleta unAtleta) {
        integrantes.add(unAtleta);
    }
}
