import java.util.Scanner;

import controller.AccountController;
import controller.CardController;
import controller.LoanController;
import services.AccountService;
import services.LoanService;

public class App {
    private static AccountService accountService = new AccountService();
    private static AccountController createAccount = new AccountController();

    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                printMainMenu();
                String option = sc.nextLine().trim();
                switch (option) {
                    case "1":
                        runCrudMenu("Account");
                        break;
                    case "2":
                        runCrudMenu("Balance");
                        break;
                    case "3":
                        LoanController loanController = new LoanController(new LoanService());
                        loanController.menu();
                        break;
                    case "4":
                        runCrudMenu("Cards");
                        break;
                    case "0":
                        running = false;
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║             MENÚ PRINCIPAL           ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  1. 💼  Account                      ║");
        System.out.println("║  2. 💰  Balance                      ║");
        System.out.println("║  3. 🏦  Loans                        ║");
        System.out.println("║  4. 💳  Cards                        ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  0. 🚪  Salir                        ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("Seleccione una opción: ");
    }

    private static void runCrudMenu(String entityName) {
        switch (entityName) {
            case "Account" -> {
                AccountController accountController = new AccountController();
                accountController.menu();
            }
            case "Cards" -> {
                CardController cardController = new CardController();
                cardController.menu();
            }
            case "Balance" -> {
                System.out.println("⚠️  Módulo de Balance aún no implementado.");
            }
            default -> System.out.println("Entidad no soportada.");
        }
    }
}
