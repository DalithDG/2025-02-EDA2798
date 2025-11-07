import java.util.Scanner;

import controller.AccountController;
import controller.LoanController;
import model.Account;
import services.AccountService;
import services.LoanService;

public class App {
    private static AccountService accountService = new AccountService();
    private static AccountController createAccount = new AccountController();
    public static void main(String[] args) throws Exception {

        /*accountService.findAll().stream().forEach(a->System.out.println(a));
        Account account = new Account("ACC010", "Johanny Valencia", "johanny.valencia@example.com", "3000000001",
                "Savings", "Calle 20 de Turbaco-Bolivar");
        accountService.save(account);
        System.out.println("*".repeat(100));
        accountService.findAll().stream().forEach(a->System.out.println(a));
        System.out.println("-".repeat(100));
        accountService.findById("ACC009").ifPresentOrElse(
                acc -> System.out.println("Encontrado: " + acc),
                () -> System.out.println(" account number no encontrado."));
        accountService.deleteById("ACC010");
        System.out.println("/".repeat(100));
        accountService.findAll().stream().forEach(System.out::println);*/

        try (Scanner sc = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                printMainMenu();
                String option = sc.nextLine().trim();
                switch (option) {
                    case "1":
                        runCrudMenu(sc, "Account");
                        break;
                    case "2":
                        runCrudMenu(sc, "Balance");
                        break;
                    case "3":
                        //comentario
                        LoanController loanController = new LoanController(new LoanService());
                        loanController.menu();
                        break;
                    case "4":
                        runCrudMenu(sc, "Cards");
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

    private static void runCrudMenu(Scanner sc, String entityName) {
        boolean back = false;
        while (!back) {
            printCrudMenu(entityName);
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1":
                    AccountController accountController = new AccountController();
                    accountController.menu();
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void printCrudMenu(String entityName) {
        AccountController accountController = new AccountController();
        accountController.menu();
    }
}
