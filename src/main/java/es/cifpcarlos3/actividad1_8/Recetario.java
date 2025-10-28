package es.cifpcarlos3.actividad1_8;

import es.cifpcarlos3.actividad1_8.vo.Receta;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Recetario {
    public static void main(String[] args) throws IOException {
        Path base = Path.of("src","main","java","es","cifpcarlos3","actividad1_8");
        Path json = base.resolve("receta.json");

        var mapper = JsonMapper.builder().build();
        try (var reader = Files.newBufferedReader(json)) {
            Receta receta = mapper.readValue(reader, Receta.class);
            validarReceta(receta);
            mostrarReceta(receta);
        } catch (JacksonException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void validarReceta(Receta receta) {
        if (receta.getNombre() == null || receta.getTipo() == null || receta.getOrigen() == null || receta.getIngredientes() == null) {
            System.err.println("Receta no valida");
        }
        if (receta.getIngredientes() == null) {
            System.err.println("Ingredientes no valida");
        }
    }

    private static void mostrarReceta(Receta receta) {

    }
}
