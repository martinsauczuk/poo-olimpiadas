import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        // Deportes
        Deporte atletismo = new Deporte("Atletismo");
        Deporte remo = new Deporte("Remo");
        Deporte ciclismoBmx = new Deporte("Ciclismo BMX");
        Deporte ciclismoRuta = new Deporte("Ciclismo en Ruta");
        Deporte futbol = new Deporte("Futbol");
        Deporte hockey = new Deporte("Hockey");
        Deporte rugby = new Deporte("Rugby");
        Deporte pentatlon = new Deporte("Pentatlon");


        // Atletas individuales
        Participante florenciaBorelli = new Atleta("Florencia Borelli", atletismo);
        Participante daianaOcampo = new Atleta("Daiana Ocampo", atletismo);
        Participante elianLarregina = new Atleta("Elian Larregina", atletismo);

        // Equipos
        Equipo escaloneta = new Equipo("Seleccion Argentina de Futbol", futbol);
        escaloneta.agregarIntegrante(new Atleta("Javier Mascherano", futbol));
        escaloneta.agregarIntegrante(new Atleta("Lionel Messi", futbol));
        escaloneta.agregarIntegrante(new Atleta("Dibu", futbol));
        // TODO: Agregar al resto de los integrantes del equipo

        Participante losPumas = new Equipo("Seleccion Argentina de Rubgy", rugby);
        Participante leonas = new Equipo("Seleccion Argentina de Hockey femenino", hockey);


        // Agregar atletas individuales.
        Pais argentina = new Pais("Argentina");
        argentina.agregarParticipante(florenciaBorelli);
        argentina.agregarParticipante(daianaOcampo);
        argentina.agregarParticipante(elianLarregina);
        // Equipos
        argentina.agregarParticipante(escaloneta);
        argentina.agregarParticipante(losPumas);
        argentina.agregarParticipante(leonas);


        // Organizar Olimpiada
        Olimpiada paris2024 = new Olimpiada();
        paris2024.agregarDeporte(atletismo);
        paris2024.agregarDeporte(remo);
        paris2024.agregarDeporte(ciclismoBmx);
        paris2024.agregarDeporte(ciclismoRuta);
        paris2024.agregarDeporte(futbol);
        paris2024.agregarDeporte(hockey);
        paris2024.agregarDeporte(rugby);
        paris2024.agregarDeporte(pentatlon);
        paris2024.agregarPais(argentina);
        paris2024.agregarPais(new Pais("Brasil"));
        paris2024.agregarPais(new Pais("China"));



        /*
         * 1. Obtener la cantidad de medallas de Oro, Plata y Bronces que obtuvo un país en un Juego Olímpico.
         */
        escaloneta.agregarMedalla(new Medalla(TipoMedalla.ORO));            // Asiganar medallas para probar
        florenciaBorelli.agregarMedalla(new Medalla(TipoMedalla.ORO));      // Asiganar medallas para probar
        leonas.agregarMedalla(new Medalla(TipoMedalla.PLATA));              // Asiganar medallas para probar
        losPumas.agregarMedalla(new Medalla(TipoMedalla.BRONCE));           // Asiganar medallas para probar
        try {
            System.out.println("Argentina obtiene " + paris2024.getCantidadDeMedallasPorTipoYPais(TipoMedalla.ORO, argentina) + " medallas de oro");
            System.out.println("Argentina obtiene " + paris2024.getCantidadDeMedallasPorTipoYPais(TipoMedalla.PLATA, argentina) + " medallas de plata");
            System.out.println("Argentina obtiene " + paris2024.getCantidadDeMedallasPorTipoYPais(TipoMedalla.BRONCE, argentina) + " medallas de bronce");
        } catch (OlimpiadaException e) {
            System.out.println(e.getMessage());
        }


        /*
         * 2. Obtener el nombre del país que más medallas obtuvo.
         */
        try {
            System.out.println("El pais que mas medallas obtuvo es: " + paris2024.elPaisQueMasMedallasObtuvo().getNombre());
        } catch (OlimpiadaException e) {
            System.out.println(e.getMessage());
        }


        /*
         * 3. Obtener la cantidad de atletas participantes de un país para poder asignarle un hotel. (Contar todos los atletas de todos los equipos
         * y los individuales.)
         */
        System.out.println("Viajamos a Paris " + argentina.getCantidadAtletas() + " atletas");


        /*
         * 4. Obtener la lista de participantes que no obtuvieron ninguna medalla.
         */
        Set<Participante> equiposSinMedalla =
                paris2024
                        .getPaisesSinMedallasObtenidas()
                        .stream()
                        .map( pais -> pais.getParticipantes() )
                        .flatMap(participantes -> participantes.stream()) // "Aplanar" todos los participantes en uno solo
                        .collect(Collectors.toSet());


        /*
         * 5. Determinar si un juego olímpico fué popular. Se considera que un juego olímpico fué popular cuando la cantidad de atletas
         * en todos los equipos (sumando todos los paises) es al menos un 50% del total de participantes (sumando todos los atletas).
         */
        System.out.println("Fue popular? " + paris2024.fuePopular());


        /*
         * 6. Poder obtener entre 2 países cual fué más medallero que otro. Para esto es necesario comparar la cantidad de medallas de Oro que obtuvo cada uno,
         * si hay empate definen las de bronce y si hay nuevamente empate, decidir de manera arbitraria cual es el más medallero.
         */
        Pais australia = new Pais("Australia");
        Pais masMedallero = argentina.masMedallero(australia);

        System.out.printf("El mas medallero entre %s y %s es %s %n", argentina.getNombre(), australia.getNombre(), masMedallero.getNombre());



        /*
         * 7. Saber si un país participa (o no) de una Olimpiada.
         */
        Pais chile = new Pais("Chile");
        System.out.println( paris2024.participaEstePais(chile) );



    }
}