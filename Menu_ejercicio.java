/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab_2;

import java.util.Scanner;

/**
 *
 * @author Martin Montes
 */
public class Menu_ejercicio {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Multi´programacion");
            System.out.println("1. Contar caracter mas repetido en una cadena");
            System.out.println("2. Calcular promedio, nota mayor y menor");
            System.out.println("3. Procesar observaciones de clases de ingles");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    contarCaracterMasRepetido();
                    break;
                case 2:
                    calcularPromedioNotas();
                    break;
                case 3:
                    procesarObservacionesInstituto(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    // Programa para contar el carácter más repetido,primero AMMM
    public static void contarCaracterMasRepetido() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese una cadena: ");
        String cadena = scanner.nextLine().toLowerCase();

        int[] contadorCaracteres = new int[256];

        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);
            contadorCaracteres[caracter]++;
        }

        int maxRepeticiones = 0;
        char caracterMasRepetido = ' ';

        for (int i = 0; i < contadorCaracteres.length; i++) {
            if (contadorCaracteres[i] > maxRepeticiones) {
                maxRepeticiones = contadorCaracteres[i];
                caracterMasRepetido = (char) i;
            }
        }

        System.out.println("El caracter que mas se repite es: '" + caracterMasRepetido + "' con " + maxRepeticiones + " un total de repeticiones.");
    }

    // Programa para calcular promedio y notas,AMMM
    public static void calcularPromedioNotas() {
        Scanner scanner = new Scanner(System.in);
        int cantidadNotas;

        while (true) {
            System.out.print("Ingrese la cantidad de notas (**Que desea ingresar**): ");
            cantidadNotas = scanner.nextInt();
            if (cantidadNotas > 0) break;
            else System.out.println("Por favor, ingrese un valor positivo.");
        }

        double[] notas = new double[cantidadNotas];

        for (int i = 0; i < cantidadNotas; i++) {
            while (true) {
                System.out.print("Nota #" + (i + 1) + ": ");
                double nota = scanner.nextDouble();
                
                if (nota >= 0 && nota <= 100) {
                    notas[i] = nota;
                    break;
                } else {
                    System.out.println("La nota debe estar en el rango de 0 a 100. Intentelo nuevamente.");
                }
            }
        }

        double suma = 0, notaMayor = notas[0], notaMenor = notas[0];

        for (double nota : notas) {
            suma += nota;
            if (nota > notaMayor) notaMayor = nota;
            if (nota < notaMenor) notaMenor = nota;
        }

        double promedio = suma / cantidadNotas;

        System.out.printf("Promedio: %.2f %%\n", promedio);
        System.out.printf("Nota mayor: %.2f\n", notaMayor);
        System.out.printf("Nota menor: %.2f\n", notaMenor);
    }

    // Programa para procesar observaciones de clases de inglés,AMMM
    public static void procesarObservacionesInstituto(Scanner scanner) {
        scanner.nextLine();  // Limpiar buffer
        System.out.print("Ingrese la fecha actual (día, DD/MM): ");
        String fecha = scanner.nextLine().toLowerCase();

        // Dividir la entrada en día y fecha
        String[] partesFecha = fecha.split(", ");
        if (partesFecha.length != 2) {
            System.out.println("Error: Formato de fecha incorrecto.");
            return;
        }

        String diaSemana = partesFecha[0].trim();
        String[] partesDiaMes = partesFecha[1].split("/");

        if (partesDiaMes.length != 2) {
            System.out.println("Error: Formato de fecha incorrecto.");
            return;
        }

        int dia, mes;
        try {
            dia = Integer.parseInt(partesDiaMes[0]);
            mes = Integer.parseInt(partesDiaMes[1]);
        } catch (NumberFormatException e) {
            System.out.println("Error: Día o mes no es un número.");
            return;
        }

        // Validar el rango de día y mes
        if (dia < 1 || dia > 31 || mes < 1 || mes > 12) {
            System.out.println("Error: Día o mes fuera de rango.");
            return;
        }

        switch (diaSemana) {
            case "lunes":
                procesarClaseConExamen("inicial", scanner);
                break;
            case "martes":
                procesarClaseConExamen("intermedio", scanner);
                break;
            case "miércoles":
            case "miercoles":
                procesarClaseConExamen("avanzado", scanner);
                break;
            case "jueves":
                procesarPracticaHablada(scanner);
                break;
            case "viernes":
                procesarInglesViajeros(dia, mes, scanner);
                break;
            default:
                System.out.println("Error: Día de la semana no válido.");
        }
    }

    public static void procesarClaseConExamen(String nivel, Scanner scanner) {
        System.out.println("Clase de nivel " + nivel + " seleccionada.");
        System.out.print("¿Se tomaron exámenes? (si/no): ");
        String respuesta = scanner.next().toLowerCase();

        if (respuesta.equals("si")) {
            System.out.print("Ingrese cantidad de alumnos aprobados: ");
            int aprobados = scanner.nextInt();
            System.out.print("Ingrese cantidad de alumnos no aprobados: ");
            int noAprobados = scanner.nextInt();

            int total = aprobados + noAprobados;
            double porcentajeAprobados = (total > 0) ? (aprobados * 100.0 / total) : 0;

            System.out.printf("Porcentaje de aprobados: %.2f%%\n", porcentajeAprobados);
        } else {
            System.out.println("No hubo examenes hoy.");
        }
    }

    public static void procesarPracticaHablada(Scanner scanner) {
        System.out.println("Clase de practica hablada seleccionada.");
        System.out.print("Ingrese el porcentaje de asistencia: ");
        double porcentajeAsistencia = scanner.nextDouble();

        if (porcentajeAsistencia > 50) {
            System.out.println("Asistio la mayoría.");
        } else {
            System.out.println("No asistio la mayoría.");
        }
    }

    public static void procesarInglesViajeros(int dia, int mes, Scanner scanner) {
        System.out.println("Clase de ingles para viajeros seleccionada.");
        if ((dia == 1 && mes == 1) || (dia == 1 && mes == 7)) {
            System.out.println("Comienzo de nuevo ciclo.");
            System.out.print("Ingrese la cantidad de alumnos del nuevo ciclo: ");
            int cantidadAlumnos = scanner.nextInt();
            System.out.print("Ingrese el precio por alumno: ");
            double precioPorAlumno = scanner.nextDouble();

            double ingresoTotal = cantidadAlumnos * precioPorAlumno;
            System.out.printf("Ingreso total del nuevo ciclo: $%.2f\n", ingresoTotal);
        } else {
            System.out.println("No es el inicio de un nuevo ciclo.");
        }
    }
}


