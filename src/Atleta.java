public class Atleta extends Participante {

    @Override
    public int getCantidadAtletas() {
        return 1;
    }

    public Atleta(String nombre, Deporte deporteEnQueParticipa) {
        super(nombre, deporteEnQueParticipa);
    }
}
