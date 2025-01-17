package com.challenge.literalura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication {

	@Autowired
	private GutendexService gutendexService;

	@Autowired
	private LibroRepository libroRepository;

	@Autowired
	private AutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args).getBean(LiteraluraApplication.class).run();
	}

	public void run() {
		Scanner scanner = new Scanner(System.in);
		int opcion;

		do {
			System.out.println("\nElija la opción a través de su número:");
			System.out.println("1- Buscar libro por título en Gutendex (API)");
			System.out.println("2- Listar libros registrados (Base de datos)");
			System.out.println("3- Listar autores registrados (Base de datos)");
			System.out.println("4- Listar autores vivos en un determinado año (Base de datos)");
			System.out.println("5- Listar libros por idioma (Base de datos)");
			System.out.println("0- Salir");
			System.out.print("\nIngrese una opción: ");

			opcion = scanner.nextInt();
			scanner.nextLine();

			switch (opcion) {
				case 1:
					System.out.print("\nIngrese el nombre del libro que desea buscar: ");
					String titulo = scanner.nextLine();
					gutendexService.buscarLibroPorTitulo(titulo);
					break;
				case 2:
					listarLibrosRegistrados();
					break;
				case 3:
					listarAutoresRegistrados();
					break;
				case 4:
					System.out.print("\nIngrese el año para filtrar autores vivos: ");
					int anio = scanner.nextInt();
					listarAutoresVivos(anio);
					break;
				case 5:
					System.out.print("\nIngrese el idioma para listar libros: ");
					String idioma = scanner.nextLine();
					listarLibrosPorIdioma(idioma);
					break;
				case 0:
					System.out.println("\n¡Saliendo del programa!");
					break;
				default:
					System.out.println("\nOpción no válida. Inténtelo de nuevo.");
			}
		} while (opcion != 0);

		scanner.close();
	}

	private void listarLibrosRegistrados() {
		System.out.println("\nListando libros registrados...");
		libroRepository.findAll().forEach(libro ->
				System.out.println("- " + libro.getTitulo() + " (Idioma: " + libro.getIdioma() + ")"));
	}

	private void listarAutoresRegistrados() {
		System.out.println("\nListando autores registrados...");
		autorRepository.findAll().forEach(autor ->
				System.out.println("- " + autor.getNombre() + " (Nacionalidad: " + autor.getNacionalidad() + ")"));
	}

	private void listarAutoresVivos(int anio) {
		System.out.println("\nListando autores vivos en el año: " + anio);
		autorRepository.findAllByAnioNacimientoBeforeAndAnioMuerteAfter(anio, anio).forEach(autor ->
				System.out.println("- " + autor.getNombre() + " (Nacionalidad: " + autor.getNacionalidad() + ")"));
	}

	private void listarLibrosPorIdioma(String idioma) {
		System.out.println("\nListando libros en el idioma: " + idioma);
		libroRepository.findAllByIdioma(idioma).forEach(libro ->
				System.out.println("- " + libro.getTitulo() + " (Autor: " + libro.getAutor().getNombre() + ")"));
	}
}
