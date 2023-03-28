import java.util.Scanner;
import java.util.Random;

public class git {
    private String nombre;
    private String email;
    private String password;
    private String codigoSeguridad;
    private Scanner scanner;
    private static String[] baseDeDatos = {"Juan", "Ana", "Pablo", "María", "Lucía", "Carlos", "Sofía", "Pedro", "Elena", "José"};

    public git() {
        this.scanner = new Scanner(System.in);
    }

    public void registrarUsuario() {
        System.out.println("Introduce tu nombre:");
        this.nombre = scanner.nextLine();

        System.out.println("Introduce tu email:");
        this.email = scanner.nextLine();

        System.out.println("Introduce tu contraseña:");
        this.password = scanner.nextLine();

        System.out.println("Introduce el código de seguridad:");
        this.codigoSeguridad = scanner.nextLine();
    }

    public boolean validarFormulario() {
        return compruebaNombre() && compruebaEmail() && compruebaPassword() && compruebaCodigoSeguridad();
    }

    private boolean compruebaNombre() {
        boolean valido = false;
        if (nombre.length() <= 16 && nombre.matches("^[A-Z][a-z]+[_-]\\d{3}$")) {
            boolean encontrado = false;
            for (String nombreBD : baseDeDatos) {
                if (nombre.equalsIgnoreCase(nombreBD)) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                valido = true;
            }
        }
        return valido;
    }

    private boolean compruebaEmail() {
        boolean valido = false;
        if (email.matches(".+@(.+\\.)?(paucasesnovescifp|yahoo|gmail|hotmail)\\.(com|es|cat)$")) {
            valido = true;
        }
        return valido;
    }

    private boolean compruebaPassword() {
        boolean valido = false;
        if (password.matches("^[A-Z](?=.*[@\\-#_])(?=.*\\d{2}).{6}$")) {
            valido = true;
        }
        return valido;
    }

    private boolean compruebaCodigoSeguridad() {
        return codigoSeguridad.equals(generaCodigoSeguridad());
    }

    private String generaCodigoSeguridad() {
        Random random = new Random();
        String caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890@-#_";
        StringBuilder codigo = new StringBuilder();

        for (int i = 0; i < 11; i++) {

            int indice = random.nextInt(caracteres.length());
            codigo.append(caracteres.charAt(indice));
        }
        System.out.println("Código de seguridad generado: " + codigo);
        return codigo.toString();
    }

    public static void main(String[] args) {
        git registro = new git();
        registro.registrarUsuario();
        if (registro.validarFormulario()) {
            System.out.println("Formulario válido");
        } else {
            System.out.println("Formulario inválido");
        }
    }
}