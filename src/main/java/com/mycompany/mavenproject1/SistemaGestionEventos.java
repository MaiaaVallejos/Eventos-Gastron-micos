package com.mycompany.mavenproject1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaGestionEventos {

    private static List<EventoGastronomico> eventos = new ArrayList<>();
    private static List<Participante> participantes = new ArrayList<>();
    private static List<Chef> chefs = new ArrayList<>();
    private static List<Reseña> reseñas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Crear Evento");
            System.out.println("2. Inscribir Participante");
            System.out.println("3. Gestionar Chefs");
            System.out.println("4. Dejar Reseña");
            System.out.println("5. Listar Eventos Disponibles");
            System.out.println("6. Exportar Eventos No Disponibles");
            System.out.println("7. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    crearEvento(scanner);
                    break;
                case 2:
                    inscribirParticipante(scanner);
                    break;
                case 3:
                    gestionarChefs(scanner);
                    break;
                case 4:
                    dejarReseña(scanner);
                    break;
                case 5:
                    listarEventosDisponibles(scanner);
                    break;
                case 6:
                    exportarEventosNoDisponibles(scanner);
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }

        scanner.close();
    }

    private static void crearEvento(Scanner scanner) {
        System.out.print("Ingrese ID del Evento: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese Nombre del Evento: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese Descripción del Evento: ");
        String descripcion = scanner.nextLine();

        System.out.print("Ingrese Fecha y Hora del Evento (yyyy-MM-ddTHH:mm): ");
        LocalDateTime fechaHora = LocalDateTime.parse(scanner.nextLine());

        System.out.print("Ingrese Ubicación del Evento: ");
        String ubicacion = scanner.nextLine();

        System.out.print("Ingrese Capacidad Máxima del Evento: ");
        int capacidadMaxima = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Seleccione el Chef para el Evento (ID): ");
        int chefId = scanner.nextInt();
        scanner.nextLine();
        Chef chef = findChefById(chefId);

        EventoGastronomico evento = new EventoGastronomico(id, nombre, descripcion, fechaHora, ubicacion, capacidadMaxima, chef);
        eventos.add(evento);

        if (chef != null) {
            chef.agregarEvento(evento);
        }

        System.out.println("Evento creado exitosamente.");
    }

    private static void inscribirParticipante(Scanner scanner) {
        System.out.print("Ingrese ID del Participante: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese Nombre del Participante: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese Apellido del Participante: ");
        String apellido = scanner.nextLine();

        System.out.print("Ingrese Intereses Culinarios (separados por coma): ");
        List<String> interesesCulinarios = List.of(scanner.nextLine().split(","));

        Participante participante = new Participante(id, nombre, apellido, interesesCulinarios);
        participantes.add(participante);

        System.out.println("Participante registrado exitosamente.");

        System.out.println("Seleccione el Evento en el que desea inscribirse (ID): ");
        int eventoId = scanner.nextInt();
        scanner.nextLine();
        EventoGastronomico evento = findEventoById(eventoId);

        if (evento != null && evento.inscribirParticipante(participante)) {
            participante.agregarEventoAlHistorial(evento);
            System.out.println("Participante inscrito exitosamente.");
        } else {
            System.out.println("No se pudo inscribir al participante. Verifique la capacidad del evento.");
        }
    }

    private static void gestionarChefs(Scanner scanner) {
        System.out.print("Ingrese ID del Chef: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese Nombre del Chef: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese Especialidad del Chef: ");
        String especialidad = scanner.nextLine();

        Chef chef = new Chef(id, nombre, especialidad);
        chefs.add(chef);

        System.out.println("Chef creado exitosamente.");
    }

    private static void dejarReseña(Scanner scanner) {
        System.out.print("Ingrese ID de la Reseña: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese ID del Evento: ");
        int eventoId = scanner.nextInt();
        scanner.nextLine();
        EventoGastronomico evento = findEventoById(eventoId);

        System.out.print("Ingrese ID del Participante: ");
        int participanteId = scanner.nextInt();
        scanner.nextLine();
        Participante participante = findParticipanteById(participanteId);

        System.out.print("Ingrese Calificación (1 a 5): ");
        int calificacion = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese Comentario: ");
        String comentario = scanner.nextLine();

        Reseña reseña = new Reseña(id, evento, participante, calificacion, comentario);
        reseñas.add(reseña);

        System.out.println("Reseña agregada exitosamente.");
    }

    private static void listarEventosDisponibles(Scanner scanner) {
        System.out.print("Ingrese la fecha (yyyy-MM-dd): ");
        LocalDateTime fecha = LocalDateTime.parse(scanner.nextLine() + "T00:00");

        System.out.println("Eventos disponibles a partir de " + fecha + ":");
        for (EventoGastronomico evento : eventos) {
            if (evento.getFechaHora().isAfter(fecha) && evento.getCapacidadMaxima() > evento.getParticipantes().size()) {
                System.out.println("ID: " + evento.getId() + ", Nombre: " + evento.getNombre() + ", Fecha y Hora: " + evento.getFechaHora());
            }
        }
    }

    private static void exportarEventosNoDisponibles(Scanner scanner) {
        System.out.print("Ingrese la fecha (yyyy-MM-dd): ");
        LocalDateTime fecha = LocalDateTime.parse(scanner.nextLine() + "T00:00");

        System.out.println("Eventos no disponibles a partir de " + fecha + ":");
        for (EventoGastronomico evento : eventos) {
            if (evento.getFechaHora().isAfter(fecha) && evento.getCapacidadMaxima() <= evento.getParticipantes().size()) {
                System.out.println("ID: " + evento.getId() + ", Nombre: " + evento.getNombre() + ", Fecha y Hora: " + evento.getFechaHora());
            }
        }
    }

    private static Chef findChefById(int id) {
        for (Chef chef : chefs) {
            if (chef.getId() == id) {
                return chef;
            }
        }
        return null;
    }

    private static EventoGastronomico findEventoById(int id) {
        for (EventoGastronomico evento : eventos) {
            if (evento.getId() == id) {
                return evento;
            }
        }
        return null;
    }

    private static Participante findParticipanteById(int id) {
        for (Participante participante : participantes) {
            if (participante.getId() == id) {
                return participante;
            }
        }
        return null;
    }
}
