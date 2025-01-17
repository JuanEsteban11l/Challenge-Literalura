package com.challenge.literalura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public void listarLibros() {
        var libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("\nNo hay libros registrados.");
        } else {
            System.out.println("\nLibros registrados:");
            libros.forEach(libro ->
                    System.out.println("- " + libro.getTitulo() + " (" + libro.getIdioma() + ")"));
        }
    }

    public void listarLibrosPorIdioma(String idioma) {
        var libros = libroRepository.findByIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("\nNo se encontraron libros en el idioma: " + idioma);
        } else {
            System.out.println("\nLibros en el idioma " + idioma + ":");
            libros.forEach(libro ->
                    System.out.println("- " + libro.getTitulo()));
        }
    }
}
