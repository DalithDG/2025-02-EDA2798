package controller;

import model.Balance;
import model.BalanceType;
import services.IBalanceService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class BalanceController {

    private final IBalanceService balanceService;
    private final Scanner sc = new Scanner(System.in);

    public BalanceController(IBalanceService balanceService) {
        this.balanceService = balanceService;
    }

    public void menu() {
        int option;
        do {
            System.out.println("\n===== BALANCE MENU =====");
            System.out.println("1. Registrar movimiento");
            System.out.println("2. Buscar movimiento por fecha");
            System.out.println("3. Listar todos los movimientos");
            System.out.println("4. Eliminar movimiento por fecha");
            System.out.println("0. Salir");
            System.out.print("Seleccione opción: ");
            option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 1 -> createBalance();
                case 2 -> findBalance();
                case 3 -> listBalances();
                case 4 -> deleteBalance();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida");
            }
        } while (option != 0);
    }


    private void createBalance() {
        System.out.println("\n--- Nuevo movimiento ---");

        System.out.print("Número de cuenta: ");
        String accountNumber = sc.nextLine();

        LocalDate date = LocalDate.now();

        System.out.print("Descripción: BILL, HOUSE, SHOP,");
        BalanceType type = BalanceType.valueOf(sc.nextLine().toUpperCase());

        System.out.print("Monto entrante (cashIn): ");
        BigDecimal cashIn = new BigDecimal(sc.nextLine());

        System.out.print("Monto saliente (cashOut): ");
        BigDecimal cashOut = new BigDecimal(sc.nextLine());

        BigDecimal closingBalance = cashIn.subtract(cashOut);

        Balance balance = new Balance(accountNumber, date, type, cashIn, cashOut, closingBalance);
        balanceService.save(balance);

        System.out.println("Movimiento registrado correctamente.");
    }


    private void findBalance() {
        System.out.print("Ingrese la fecha a buscar (YYYY-MM-DD): ");
        String date = sc.nextLine();

        balanceService.findById(date).ifPresentOrElse(
                b -> System.out.println("Movimiento encontrado: " + b),
                () -> System.out.println("No existe un movimiento registrado ese día")
        );
    }


    private void listBalances() {
        System.out.println("\n--- Lista de movimientos ---");
        balanceService.findAll().forEach(System.out::println);
    }


    private void deleteBalance() {
        System.out.print("Ingrese la fecha del movimiento a eliminar (YYYY-MM-DD): ");
        String date = sc.nextLine();

        boolean deleted = balanceService.deleteById(date);
        System.out.println(deleted ? "Eliminado correctamente" : "No existe ese movimiento");
    }
}
