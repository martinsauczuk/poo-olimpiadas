import java.util.ArrayList;
import java.util.List;

public abstract class Participante {

    private String nombre;
    private Deporte deporteEnQueParticipa;
    private List<Medalla> medallasObtenidas = new ArrayList<>();

    /**
     * Este metodo retorna 1 para los atletas individuales y la cantidad
     * de integrantes para los equipos. Utilizando este metodo evitamos tener que "preguntar si se trata
     * de un atleta individual o un equipo.
     */
    public abstract int getCantidadAtletas();

    public Participante(String nombre, Deporte deporteEnQueParticipa) {
        this.nombre = nombre;
        this.deporteEnQueParticipa = deporteEnQueParticipa;
    }

    public List<Medalla> getMedallasObtenidas() {
        return medallasObtenidas;
    }

    public void agregarMedalla(Medalla medalla) {
        this.medallasObtenidas.add(medalla);
    }

    /**
     * Cantidad de medallas del Participante por tipo
     *
     * @param tipoMedalla Tipo de la medalla {@link TipoMedalla}
     * @return Un numero long indicando la cantida de medallas de {@code tipoMedalla}
     */
    public int getCantMedallasPorTipo(TipoMedalla tipoMedalla) {

        // el (int) es una cast, es decir una conversion de long a int.
        return (int) medallasObtenidas
                .stream()
                .filter(medalla -> medalla.getTipoMedalla().equals(tipoMedalla) )
                .count();
    }
}
