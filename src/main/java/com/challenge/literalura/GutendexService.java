package com.challenge.literalura;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Service
public class GutendexService {

    private final String API_URL = "https://gutendex.com/books?search=";

    public void buscarLibroPorTitulo(String titulo) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            // Construir la URL de la búsqueda
            String url = API_URL + titulo;

            // Hacer la solicitud GET
            ResponseEntity<GutendexResponse> response = restTemplate.getForEntity(url, GutendexResponse.class);

            // Procesar la respuesta
            if (response.getBody() != null && response.getBody().getResults().size() > 0) {
                System.out.println("\nLibros encontrados en Gutendex:");
                response.getBody().getResults().forEach(libro ->
                        System.out.println("- " + libro.getTitle() + " (Autor: " + libro.getAuthorsString() + ")"));
            } else {
                System.out.println("\nNo se encontraron libros con el título: " + titulo);
            }
        } catch (Exception e) {
            System.out.println("\nOcurrió un error al buscar el libro: " + e.getMessage());
        }
    }
}
