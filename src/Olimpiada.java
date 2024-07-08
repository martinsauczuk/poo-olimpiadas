import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Olimpiada {

    private Set<Pais> paises = new HashSet<>();
    private Set<Deporte> deportes = new HashSet<>();

    public void agregarDeporte(Deporte unDeporte) {
        this.deportes.add(unDeporte);
    }

    public void agregarPais(Pais pais) {
        paises.add(pais);
    }

    public Set<Pais> getPaises() {
        return paises;
    }

    public long getCantidadDeMedallasPorTipoYPais(TipoMedalla unTipoMedalla, Pais unPais) throws OlimpiadaException {

        // Validar si el pais participa o no antes de obtener las medallas. (no pedido en la consigna, solo para practicar excepciones)
        if ( !participaEstePais(unPais) ) {
            throw new OlimpiadaException("Este pais" + unPais.getNombre() + " no participa en estas olimpiadas");
        }

        // Delegar el trabajo de contar sus medallas en la clase Pais
        return unPais.getCantidadMedallasObtenidasPorTipo(unTipoMedalla);

    }

    public Pais elPaisQueMasMedallasObtuvo() throws OlimpiadaException {

        // Nota: la operacion .max podria devolver un error, si no hay ningun pais participante, con el .orElseThrow
        // lanzamos la excepcion en ese caso
        return this.paises
                .stream()
                .max((pais1, pais2) -> Long.compare(pais1.getCantidadMedallasTotal(), pais2.getCantidadMedallasTotal()))
                .orElseThrow( () -> new OlimpiadaException("No hay ningun pais participante"));

    }

    public boolean participaEstePais(Pais pais) {
        return this.paises.contains(pais);
    }

    public Set<Pais> getPaisesSinMedallasObtenidas() {
        return this.paises
                .stream()
                .filter(pais -> pais.getCantidadMedallasTotal() == 0 )
                .collect(Collectors.toSet());
    }

    public int cantAtletasTotalesDeTodosLosPaises() {
        return this.paises
                .stream()
                .mapToInt(pais -> pais.getCantidadAtletas())
                .sum();
    }

    /**
     * Para obtener la cantidad de atletas en cada equipo sin preguntar si se trata de un equipo o de un atleta individual
     * filtro las instancias que devuelven mas de un atleta cuando le preguntamos la cantidad
     *
     */
    public int cantAtletasEnEquiposDeTodosLosPaises() {
        return this.paises
                .stream()
                .map( pais -> pais.getParticipantes())
                .flatMap( participantes -> participantes.stream())
                .filter( participante -> participante.getCantidadAtletas() > 1 )
                .mapToInt( participante -> participante.getCantidadAtletas() )
                .sum();
    }

    /**
     * Se considera que un juego olímpico fué popular cuando la cantidad de atletas
     * en todos los equipos (sumando todos los paises) es al menos un 50% del total de participantes (sumando todos los atletas).
     */
    public boolean fuePopular() {

        return this.cantAtletasEnEquiposDeTodosLosPaises() >= this.cantAtletasTotalesDeTodosLosPaises() * 0.5;


    }
}
