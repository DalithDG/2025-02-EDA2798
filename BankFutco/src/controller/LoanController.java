package controller;

import model.Loans;
import model.TypeLoans;
import services.ILoansService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class LoanController {

    private final ILoansService loanService;
    private final Scanner sc = new Scanner(System.in);

    public LoanController(ILoansService loanService) {
        this.loanService = loanService;
    }

    public void menu() {
        int option;
        do {
            System.out.println("\n===== LOANS MENU =====");
            System.out.println("1. Registrar préstamo");
            System.out.println("2. Buscar préstamo por fecha");
            System.out.println("3. Listar todos los préstamos");
            System.out.println("4. Eliminar préstamo por fecha");
            System.out.println("0. Salir");
            System.out.print("Seleccione opción: ");
            option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 1 -> createLoan();
                case 2 -> findLoan();
                case 3 -> listLoans();
                case 4 -> deleteLoan();
                case 0 -> System.out.println("Saliendo del menú de préstamos...");
                default -> System.out.println("Opción inválida.");
            }
        } while (option != 0);
    }

    private void createLoan() {
        System.out.println("\n--- Registrar nuevo préstamo ---");
        LocalDate date = LocalDate.now();

        System.out.print("Tipo (HOME / VEHICLE / UNIVERSITY / PERSONAL): ");
        String type = sc.nextLine().toUpperCase();

        System.out.print("Monto total del préstamo: ");
        BigDecimal totalLoan = new BigDecimal(sc.nextLine());

        System.out.print("Monto pagado: ");
        BigDecimal amountPaid = new BigDecimal(sc.nextLine());

        BigDecimal outstandingAmt = totalLoan.subtract(amountPaid);

        System.out.print("Tipo de préstamo (HOME / VEHICLE / UNIVERSITY / PERSONAL): ");
        TypeLoans typeLoans = TypeLoans.valueOf(type);

        Loans loan = new Loans(date, type, totalLoan, amountPaid, outstandingAmt, typeLoans);
        loanService.save(loan);

        System.out.println("Préstamo registrado correctamente.");
    }

    private void findLoan() {
        System.out.print("Ingrese la fecha del préstamo (YYYY-MM-DD): ");
        String date = sc.nextLine();

        loanService.findById(date).ifPresentOrElse(
                l -> System.out.println("Préstamo encontrado: " + l),
                () -> System.out.println("No existe un préstamo con esa fecha.")
        );
    }

    private void listLoans() {
        System.out.println("\n--- Lista de préstamos registrados ---");
        loanService.findAll().forEach(System.out::println);
    }

    private void deleteLoan() {
        System.out.print("Ingrese la fecha del préstamo a eliminar (YYYY-MM-DD): ");
        String date = sc.nextLine();

        boolean deleted = loanService.deleteById(date);
        System.out.println(deleted ? "Préstamo eliminado correctamente." : "No existe un préstamo con esa fecha.");
    }
}
