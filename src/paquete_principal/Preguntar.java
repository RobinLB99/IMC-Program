package paquete_principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.LinkedList;

public class Preguntar {
    static PrintStream Out = new PrintStream(System.out);
    static InputStreamReader in = new InputStreamReader(System.in);
    static BufferedReader buffer = new BufferedReader(in);

    static void volverAlMenu() {
        Out.flush();
        boolean repetir;

        do {
            repetir = false;
            Out.println("\nQue desea hacer?\n* Salir del programa.(1)    * Volver al menu.(2)");
            try {
                int opc = Integer.parseInt(buffer.readLine());

                if (opc == 1){
                    Out.println("El programa va a cerrarse!");
                    Main.pressToContinue();
                    System.exit(0);

                } else if (opc == 2){
                    Main.menu();

                } else {
                    Out.println("\nNo eligio una opcion valida\n");
                    Main.pressToContinue();
                    repetir = true;
                }

            } catch (Exception e){
                repetir = true;
                Out.println("\nNo eligio una opcion valida\n");
                Main.pressToContinue();
            }

        } while (repetir);
    }

    public static void ingresarOtroDato() {
        boolean repetir;
        do{
            repetir = false;
            Out.flush();
            Out.println("\nDesea ingresar mas Datos\n* Si.(1)   * No.(2)");
            try {
                int YN = Integer.parseInt(buffer.readLine());
                if (YN == 1) {
                    Datos.ingresarDatos();
                } else if (YN == 2) {
                    volverAlMenu();
                }
                else {
                    repetir = true;
                    Out.println("No escogio una opcion valida");
                    Main.pressToContinue();
                }
            } catch (IOException a){
                repetir = true;
                Out.println("No se puede ingresar caracteres que no sean numericos");
                Main.pressToContinue();
            }
        } while (repetir);
    }

    public static void exportar(LinkedList lista, int imc, String detalle) {
        boolean repetir;
        do {
            repetir = false;
            Out.println("Desea exportar los Datos seleccionados a un archivo de texto?\nSi(1)   No(2)");
            try {
                int opc = Integer.parseInt(buffer.readLine());
                if (opc == 1) {
                    Datos.exportarArchivo("".concat((String) lista.get(1)).concat((String) lista.get(2)).concat(".txt"), (String) lista.get(1), (String) lista.get(2), (Integer) lista.get(3), (Double) lista.get(4), (Double) lista.get(5), imc, detalle);
                    Main.pressToContinue();
                }
                else if (opc == 2) {
                    break;
                }
                else {
                    repetir = true;
                    Out.println("No selecciona una opcion correcta");
                    Main.pressToContinue();
                }

            } catch (Exception e) {
                repetir = true;
                Out.println("Ingreso un caracter no numerico");
                Main.pressToContinue();
            }
        } while (repetir);
    }
}
