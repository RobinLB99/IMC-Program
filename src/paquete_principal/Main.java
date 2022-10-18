package paquete_principal;

import java.io.*;
import java.util.*;

import static paquete_principal.Datos.datosMostrados;

public class Main {
    static PrintStream Out = new PrintStream(System.out);
    static Scanner keyPress = new Scanner(System.in);
    static InputStreamReader in = new InputStreamReader(System.in);
    static BufferedReader buffer = new BufferedReader(in);

    static void menu() {
        boolean repetir;


        do {
            repetir = false;
            Out.flush();
            Out.println("PROGRAMA DE INDICE DE MASA CORPORAL\n------------------------------------\n");
            Out.println("Que desea hacer?\n* Datos de personas.(1)    * Ingresar una nueva persona.(2)    * Eliminar Datos de una persona.(3)   * Salir. (4)");

            Out.println("\nIngrese el numero segun la opcion:");

            try {
                int opc = Integer.parseInt(buffer.readLine());
                switch (opc){
                    case 1:
                        Out.println("\nDatos de personas\n-----------------------");
                        Datos.mostrarDatos();
                        if (datosMostrados){
                            Datos.seleccionarPersona();
                        }
                        Preguntar.volverAlMenu();
                        break;

                    case 2:
                        Out.println("\nIngresar una persona\n--------------------");
                        Datos.ingresarDatos();
                        break;

                    case 3:
                        Out.println("\nEliminar una persona\n--------------------");
                        Datos.eliminarPersona();
                        pressToContinue();
                        Preguntar.volverAlMenu();
                        break;

                    case 4:
                        Out.println("El programa va a cerrarse!");
                        pressToContinue();
                        System.exit(0);

                    default:
                        Out.println("\nNo se escogio una opcion valida\n");
                        pressToContinue();
                        repetir = true;
                }

            } catch (Exception e) {
                Out.println("\nNo se escogio una opcion valida\n");
                pressToContinue();
                repetir = true;
            }

        } while (repetir);
    }

    static public void pressToContinue() {
        String seguir;
        try {
            seguir = keyPress.nextLine();
        } catch(Exception ignored) { }
    }

    public static void main(String[] args) {
        menu();
    }
}
