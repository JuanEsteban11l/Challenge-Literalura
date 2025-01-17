package com.challenge.literalura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public void listarAutores() {
        var autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("\nNo hay autores registrados.");
        } else {
            System.out.println("\nAutores registrados:");
            autores.forEach(autor ->
                    System.out.println("- " + autor.getNombre() + " (Vivo: " + autor.isVivo() + ")"));
        }
    }

    public void listarAutoresVivos(int anio) {
        var autores = autorRepository.findByVivoTrueAndAnioNacimientoLessThanEqual(anio);
        if (autores.isEmpty()) {
            System.out.println("\nNo se encontraron autores vivos en el año: " + anio);
        } else {
            System.out.println("\nAutores vivos en el año " + anio + ":");
            autores.forEach(autor ->
                    System.out.println("- " + autor.getNombre()));
        }
    }
}
