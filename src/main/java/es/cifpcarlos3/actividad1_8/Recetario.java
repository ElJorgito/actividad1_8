package es.cifpcarlos3.actividad1_8;

import es.cifpcarlos3.actividad1_8.vo.Ingrediente;
import es.cifpcarlos3.actividad1_8.vo.Receta;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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
        List<Ingrediente> ingredientes = receta.getIngredientes();
        if (ingredientes == null || ingredientes.isEmpty()){
            System.err.println("Ingredientes no validos");
        }
        if(ingredientes != null && !ingredientes.isEmpty()){
            for (Ingrediente ingrediente : ingredientes) {
                if (ingrediente.getNombre() == null || ingrediente.getCantidad() == null) {
                    System.err.println("Ingrediente no valido");
                }
            }
        }
    }

    private static void mostrarReceta(Receta receta) {
        System.out.println("Receta: " + receta.getNombre() + " (tipo: " + receta.getTipo() + ")");
        System.out.println("Origen: " + receta.getOrigen().getPais() + " - " + receta.getOrigen().getRegion());
        System.out.println("Ingredientes " + "(" + receta.getIngredientes().size() + ")");
        for (Ingrediente ingrediente : receta.getIngredientes()) {
            System.out.println("• " +  ingrediente.getNombre() + " - " + ingrediente.getCantidad());
        }
    }
//    Receta: Gazpacho (tipo: Sopa fría)
//    Origen: España - Andalucía
//    Ingredientes (2):
//            • Tomate — 1 kg
//            • Pimiento verde — 100 g
}
