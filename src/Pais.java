import java.util.*;


public class Pais {

    private String nombre;

    private List<Participante> participantes = new ArrayList<>();

    public Pais(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarParticipante(Participante participante) {
        this.participantes.add(participante);
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public int getCantidadMedallasObtenidasPorTipo(TipoMedalla tipoMedalla) {
        return this.participantes
                .stream()
                .mapToInt(participante -> participante.getCantMedallasPorTipo(tipoMedalla)) // Mapea cada participante (Atleta o Equipo) en un long para poder aplicar .sum()
                .sum();
    }

    public int getCantidadMedallasTotal() {

        int medallasTotales = 0;
        for (TipoMedalla tipoMedalla : TipoMedalla.values()) {
            medallasTotales =+ this.getCantidadMedallasObtenidasPorTipo(tipoMedalla);
        }
        return medallasTotales;

    }

    public int getCantidadAtletas() {

        return this.participantes
                .stream()
                .mapToInt(participante -> participante.getCantidadAtletas())
                .sum();

    }

    /**
     * Poder obtener entre 2 países cual fué más medallero que otro. Para esto es necesario comparar la cantidad de medallas de Oro que obtuvo cada uno,
     *  si hay empate definen las de bronce y si hay nuevamente empate, decidir de manera arbitraria cual es el más medallero.
     * @param otroPais
     * @return
     */
    public Pais masMedallero(Pais otroPais) {

        Pais masMedallero; // si se da empate en ORO, PLATA y BRONCE devolver el pais actual

        // uso del operador ternario para evitar else
        masMedallero = getCantidadMedallasObtenidasPorTipo(TipoMedalla.ORO) > otroPais.getCantidadMedallasObtenidasPorTipo(TipoMedalla.ORO)
                ? this
                : getCantidadMedallasObtenidasPorTipo(TipoMedalla.PLATA) > otroPais.getCantidadMedallasObtenidasPorTipo(TipoMedalla.PLATA)
                    ? this
                    : getCantidadMedallasObtenidasPorTipo(TipoMedalla.BRONCE) > otroPais.getCantidadMedallasObtenidasPorTipo(TipoMedalla.BRONCE)
                        ? this
                        : otroPais;
        return masMedallero;
    }
}
