package paquete_principal;

public class IMC {
    static String detalle = "";

    // Tipos de peso ----------------------------------------------------
    static int resultado (String nombre, String apellido, int edad, double peso, double estatura) {
        return (int) Math.round(peso / (estatura * estatura));
    }
    static String detalle(double imc) {
        if (imc < 16)
            detalle = "Tiene delgades severa!";

        else if (imc >= 16 && imc <= 16.9)
            detalle = "Tiene delgades moderada!";

        else if (imc >= 17 && imc <= 18.5)
            detalle = "Tiene delgades leve!";

        else if (imc >= 18.6 && imc <= 25)
            detalle = "Su peso es normal!";

        else if (imc >= 25.1 && imc <=30)
            detalle = "Tiene sobrepeso!";

        else if (imc >= 30.1 && imc <=35)
            detalle = "Tiene obecidad leve!";

        else if (imc >= 35.1 && imc <=40)
            detalle = "Tiene obecidad moderada!";

        else if (imc >= 40.1)
            detalle = "Tiene obecidad mórbida!";

        return detalle;
    }
}