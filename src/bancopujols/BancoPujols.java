
package bancopujols;

import java.util.ArrayList;
import java.util.Scanner;

public class BancoPujols {

    static Scanner scanner = new Scanner(System.in);
    static Cliente clienteActual;
    static ArrayList<Cliente> clientes = new ArrayList<>();

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            mostrarPaginaPrincipal();
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1 -> registrarCliente();
                case 2 -> iniciarSesion();
                case 3 -> salir = true;
                default -> System.out.println("OpciÃ³n no vÃ¡lida. Intente de nuevo.");
            }
        }
    }

    public static void mostrarPaginaPrincipal() {
        System.out.println("Bienvenido al Banco Pujols");
        System.out.println("1. Registrarse");
        System.out.println("2. Iniciar SesiÃ³n");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opciÃ³n: ");
    }

    public static void registrarCliente() {
        System.out.println("\nRegistro de Cliente");
        System.out.print("Ingrese su cÃ©dula (11 dÃ­gitos): ");
        String cedula = scanner.nextLine();
        if (!validarLongitudCedula(cedula)) {
            System.out.println("Longitud de cÃ©dula incorrecta. La cÃ©dula debe tener 11 dÃ­gitos.");
            return;
        }
        System.out.print("Ingrese su provincia (RepÃºblica Dominicana): ");
        String provincia = scanner.nextLine();
        if (!provinciaValida(provincia)) {
            System.out.println("Provincia incorrecta. Intente de nuevo.");
            return;
        }
        System.out.print("Ingrese su PIN (4 dÃ­gitos): ");
        int pin = scanner.nextInt();
        scanner.nextLine(); 
        if (!validarLongitudPIN(pin)) {
            System.out.println("Longitud de PIN incorrecta. El PIN debe tener 4 dÃ­gitos.");
            return;
        }

        clienteActual = new Cliente(cedula, provincia, pin);
        clientes.add(clienteActual);
        System.out.println("Â¡Registro exitoso!");
        iniciarSesion();
    }

    public static boolean validarLongitudCedula(String cedula) {
        return cedula.length() == 11;
    }

    public static boolean provinciaValida(String provincia) {
        return true;
    }

    public static boolean validarLongitudPIN(int pin) {
        String pinStr = String.valueOf(pin);
        return pinStr.length() == 4;
    }

    public static void iniciarSesion() {
        System.out.println("\nInicio de SesiÃ³n");
        System.out.print("Ingrese su cÃ©dula: ");
        String cedula = scanner.nextLine();
        System.out.print("Ingrese su PIN: ");
        int pin = scanner.nextInt();
        scanner.nextLine(); 

        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(cedula) && cliente.getPin() == pin) {
                clienteActual = cliente;
                mostrarMenuCliente();
                return;
            }
        }
        System.out.println("CÃ©dula o PIN incorrectos. Intente de nuevo.");
    }

    public static void mostrarMenuCliente() {
        System.out.println("\nÂ¡Bienvenido, " + clienteActual.getCedula() + "!");
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Ver Saldo");
            System.out.println("2. Transferir Dinero");
            System.out.println("3. Pedir Dinero");
            System.out.println("4. Cerrar SesiÃ³n");
            System.out.print("Seleccione una opciÃ³n: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); 
            switch (opcion) {
                case 1 -> System.out.println("Saldo: $XXX.XX"); 
                case 2 -> transferirDinero();
                case 3 -> pedirDinero();
                case 4 -> salir = true;
                default -> System.out.println("OpciÃ³n no vÃ¡lida. Intente de nuevo.");
            }
        }
    }

    public static void transferirDinero() {
        System.out.println("\nTransferir Dinero");
        System.out.print("Ingrese el nÃºmero de cuenta del destinatario (11 dÃ­gitos): ");
        String numeroCuenta = scanner.nextLine();
        if (numeroCuenta.length() != 11) {
            System.out.println("NÃºmero de cuenta incorrecto. Debe tener 11 dÃ­gitos.");
            return;
        }
        
        System.out.print("Ingrese el monto a transferir: $");
        double monto = scanner.nextDouble();
        scanner.nextLine(); 
        System.out.println("Transferencia exitosa de $" + monto + " a la cuenta " + numeroCuenta);
    }

    public static void pedirDinero() {
        System.out.println("\nPedir Dinero");
        System.out.print("Ingrese el nÃºmero de telÃ©fono del usuario al que le solicita dinero: ");
        String numeroTelefono = scanner.nextLine();
        System.out.print("Ingrese el monto que desea solicitar: $");
        double monto = scanner.nextDouble();
        scanner.nextLine(); 
        System.out.println("Se ha enviado una solicitud de $" + monto + " al usuario con el nÃºmero de telÃ©fono " + numeroTelefono);
    }
}

class Cliente {
    private final String cedula;
    private final int pin;

    public Cliente(String cedula, String provincia, int pin) {
        this.cedula = cedula;
        this.pin = pin;
    }

    public String getCedula() {
        return cedula;
    }

    public int getPin() {
        return pin;
    }
}

