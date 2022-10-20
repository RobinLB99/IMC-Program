package paquete_principal;

import java.util.*;
import java.io.*;

public class Datos {
    static PrintStream Out = new PrintStream(System.out);
    public static LinkedList<Object> DatosIMC = new LinkedList<>();
    static LinkedList<Object> temp = new LinkedList<>();
    static InputStreamReader in = new InputStreamReader(System.in);
    static BufferedReader buffer = new BufferedReader(in);

    static boolean datosMostrados;
    static int id = 0;

    // Ingresar un nuevo dato --------------------------------------------------
    static void ingresarDatos() {
        int valido = 1;

        if (id != 0)    id += 1;
        if (id == 0)    id += 1;

        Out.println("Ingrese los siguientes Datos:");
        temp.add(id);

        do {
            Out.println("\nNombres:");
            try {
                String nombres = buffer.readLine();
                temp.addLast(nombres);

            } catch (Exception e) {
                Out.println("\nSolo esta permitido caracteres de texto\n");
                valido = 0;
                Main.pressToContinue();
            }

        } while (valido == 0);

        do {
            valido = 1;
            Out.println("\nApellidos:");
            try {
                String Apellidos = buffer.readLine();
                temp.addLast(Apellidos);

            } catch (Exception e) {
                Out.println("\nSolo esta permitido caracteres de texto\n");
                valido = 0;
                Main.pressToContinue();
            }

        } while (valido == 0);

        do {
            valido = 1;
            Out.println("\nEdad:");
            try {
                int edad = Integer.parseInt(buffer.readLine());
                temp.addLast(edad);

            } catch (Exception e) {
                Out.println("\nSolo esta permitido caracteres de numericos enteros\n");
                valido = 0;
                Main.pressToContinue();
            }

        } while (valido == 0);

        do {
            valido = 1;
            Out.println("\nPeso(kg):");
            try {
                double peso = Double.parseDouble(buffer.readLine());
                temp.addLast(peso);

            } catch (Exception e) {
                Out.println("\nSolo esta permitido caracteres de numericos enteros o decimales\n");
                valido = 0;
                Main.pressToContinue();
            }

        } while (valido == 0);

        do {
            valido = 1;
            Out.println("\nEstatura(m):");
            try {
                double estatura = Double.parseDouble(buffer.readLine());
                temp.addLast(estatura);

            } catch (Exception e) {
                Out.println("\nSolo esta permitido caracteres de numericos enteros o decimales\n");
                valido = 0;
                Main.pressToContinue();
            }

        } while (valido == 0);

        DatosIMC.add(temp.clone());
        temp.clear();
        Preguntar.ingresarOtroDato();
    }

    // Mostrar una lista de los Datos ingresados --------------------------------
    static void mostrarDatos() {
        if (Datos.DatosIMC.size() == 0) {
            datosMostrados = false;
            Out.println("No existen Datos a registrados");
            Main.pressToContinue();
        }
        else {
            Out.print("\nId | Nombres | Apellidos | Edad | Peso (kg) | Estatura (m)\n");

            for (int i = 0; i < Datos.DatosIMC.size(); i++) {
                LinkedList temp = new LinkedList((Collection) Datos.DatosIMC.get(i));

                for (int j = 0; j < temp.size(); j++) {
                    Out.print(temp.get(j) + "           ");
                }
                Out.println("");
            }
            datosMostrados = true;
        }
    }

    // Seleccionar persona----------------------------------------------
    static void seleccionarPersona() {
        boolean repetir, encontrado = false;
        LinkedList listaTemp;
        int idTemp;

        do {
            Out.flush();
            repetir = false;
            int id, siNo;
            boolean volverPregunta;

            Out.println("Seleccione una persona por su Id:");
            try {
                id = Integer.parseInt(buffer.readLine());

                for (int i = 0; i < Datos.DatosIMC.size(); i++) {
                    listaTemp = (LinkedList) ((LinkedList) Datos.DatosIMC.get(i)).clone();
                    idTemp = (int) listaTemp.get(0);

                    if (idTemp == id) {
                        encontrado = true;

                        int imc = IMC.resultado((String) listaTemp.get(1), (String) listaTemp.get(2), (Integer) listaTemp.get(3), (Double) listaTemp.get(4), (Double) listaTemp.get(5));
                        Out.println("El indice de masa corporal de " + listaTemp.get(1) + " es :" + imc);

                        String detalle = IMC.detalle(imc);
                        Out.println(detalle);

                        Main.pressToContinue();

                        Preguntar.exportar(listaTemp, imc, detalle);
                    }
                    listaTemp.clear();
                }

                if (!encontrado) {
                    repetir = true;
                    Out.println("No se encontro Datos registrados con el ID: " + id);
                }
                else {
                    do {
                        try {
                            volverPregunta = false;
                            Out.println("\nDesea seleccionar otra persona?\nSi (1)    No (2)");
                            siNo = Integer.parseInt(buffer.readLine());
                            if (siNo == 1) {
                                repetir = true;
                            } else if (siNo == 2) {
                                repetir = false;
                            }
                            else {
                                volverPregunta = true;
                            }
                        } catch (Exception P) {
                            volverPregunta = true;
                            Out.println("Escoja una opcion valida.");
                            Main.pressToContinue();
                        }
                    } while(volverPregunta);
                }

            } catch (Exception ID) {
                repetir = true;
                id = 0;
                Out.println("El caracter a ingresar debe ser numerico");
            }
        } while (repetir);
    }

    // Eliminar persona ------------------------------------------------
    static void eliminarPersona() {
        int Id, value, siNo;
        LinkedList temp;
        boolean encontrado = false, repetir, volverAPreguntar;

        if (DatosIMC.size() == 0) {
            Out.println("La base de Datos esta vacia");
        }
        else {
            do {
                Out.flush();
                repetir = false;
                Out.println("\nIngrese el ID de la persona a eliminar:");
                try {
                    Id = Integer.parseInt(buffer.readLine());
                    for (int i = 0; DatosIMC.size() >= 1; i++) {
                        temp = (LinkedList) DatosIMC.get(i);
                        value = (int) temp.get(0);
                        if (value == Id) {
                            encontrado = true;
                            DatosIMC.remove(i);
                            Out.println("La persona fue eliminada correctamente de la lista!");
                            break;
                        }
                    }
                } catch (Exception e) {
                    repetir = true;
                    Out.println("No se ingreso un ID valido");
                    Main.pressToContinue();
                }

                if (!encontrado) {
                    Out.println("El ID ingresado no coincide con ninguno en la lista.\nLa persona no existe!");
                    Main.pressToContinue();
                    do {
                        Out.flush();
                        volverAPreguntar = false;
                        try {
                            Out.println("Desea eliminar a otra persona?\n* Si(1)   * No(2)");
                            siNo = Integer.parseInt(buffer.readLine());
                            if (siNo == 1) {
                                repetir = true;
                            }
                            else if (siNo == 2) {
                                Preguntar.volverAlMenu();
                            }
                            else {
                                volverAPreguntar = true;
                                Out.println("No se escogio una opcion valida");
                                Main.pressToContinue();
                            }
                        } catch (Exception a) {
                            volverAPreguntar = true;
                            Out.println("Error! Ingreso un caracter no numerico.");
                            Main.pressToContinue();
                        }
                    } while (volverAPreguntar);
                }
            } while (repetir);
        }
    }

    // Exportar Datos a .txt
    static void exportarArchivo(String nombreArchivo, String nombre, String apellido, int edad, double peso, double estatura, int imc, String detalle) {
        File f;
        f = new File(nombreArchivo);

        try {
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);

            wr.write("--- Indice de masa corporal ---\n");
            wr.write("Nombre - : ".concat(nombre).concat("\n"));
            wr.write("Apellido : ".concat(apellido).concat("\n"));
            wr.write("Edad --- : ".concat(String.valueOf(edad)).concat(" años\n"));
            wr.write("Peso --- : ".concat(String.valueOf(peso)).concat(" kg\n"));
            wr.write("Estatura : ".concat(String.valueOf(estatura)).concat(" m\n"));
            wr.write("IMC ---- : ".concat(String.valueOf(imc)).concat("\n"));
            wr.write("Detalle  : ".concat(detalle));

            wr.close();
            bw.close();
            Out.println("Archivo creado. Datos exportados con exito");

        } catch (IOException e) {
            Out.println("Error al escribir el archivo");
        }
    }
}
