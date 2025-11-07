package controller;

import model.Cards;
import services.CardService;
import services.ICardService;

import java.math.BigDecimal;
import java.util.Scanner;

public class CardController {

    private Scanner scanner;

    public void menu() {
        ICardService cardService = new CardService();
        scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n===== MENÚ DE TARJETAS =====");
            System.out.println("1. Crear Tarjeta");
            System.out.println("2. Ver Tarjeta");
            System.out.println("3. Listar Todas las Tarjetas");
            System.out.println("4. Actualizar Tarjeta");
            System.out.println("5. Eliminar Tarjeta");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> createCard(cardService);
                case 2 -> viewCard(cardService);
                case 3 -> listCards(cardService);
                case 4 -> updateCard(cardService);
                case 5 -> deleteCard(cardService);
                case 0 -> System.out.println("Saliendo del menú de tarjetas...");
                default -> System.out.println("Opción inválida.");
            }

        } while (option != 0);
    }

    private void createCard(ICardService cardService) {
        System.out.println("\n--- Crear Nueva Tarjeta ---");
        System.out.print("Número de Tarjeta: ");
        String number = scanner.nextLine().trim();

        System.out.print("Tipo (Débito o Crédito): ");
        String type = scanner.nextLine().trim();

        System.out.print("Límite Total: ");
        BigDecimal totalLimit = new BigDecimal(scanner.nextLine().trim());

        System.out.print("Monto Usado: ");
        BigDecimal amountUsed = new BigDecimal(scanner.nextLine().trim());

        BigDecimal available = totalLimit.subtract(amountUsed);

        Cards card = new Cards(number, type, totalLimit, amountUsed, available);
        cardService.save(card);

        System.out.println("Tarjeta creada exitosamente: " + card);
    }

    private void viewCard(ICardService cardService) {
        System.out.print("Ingrese el Número de Tarjeta para ver: ");
        String id = scanner.nextLine().trim();
        cardService.findById(id).ifPresentOrElse(
                c -> System.out.println("Tarjeta encontrada: " + c),
                () -> System.out.println("No se encontró la tarjeta.")
        );
    }

    private void listCards(ICardService cardService) {
        System.out.println("\n--- Todas las Tarjetas ---");
        cardService.findAll().forEach(System.out::println);
    }

    private void updateCard(ICardService cardService) {
        System.out.print("Ingrese el Número de Tarjeta a actualizar: ");
        String id = scanner.nextLine().trim();

        cardService.findById(id).ifPresentOrElse(existing -> {
            System.out.println("Tarjeta actual: " + existing);

            System.out.print("Nuevo Tipo (deje en blanco para mantener el actual): ");
            String type = scanner.nextLine().trim();
            if (!type.isEmpty()) existing.setType(type);

            System.out.print("Nuevo Límite Total (deje en blanco para mantener el actual): ");
            String totalStr = scanner.nextLine().trim();
            if (!totalStr.isEmpty()) existing.setTotalLimit(new BigDecimal(totalStr));

            System.out.print("Nuevo Monto Usado (deje en blanco para mantener el actual): ");
            String usedStr = scanner.nextLine().trim();
            if (!usedStr.isEmpty()) existing.setAmountUsed(new BigDecimal(usedStr));

            // Recalcular disponible
            existing.setAvailable(existing.getTotalLimit().subtract(existing.getAmountUsed()));

            cardService.save(existing);
            System.out.println("Tarjeta actualizada exitosamente: " + existing);
        }, () -> System.out.println("No se encontró el número de tarjeta."));
    }

    private void deleteCard(ICardService cardService) {
        System.out.print("Ingrese el Número de Tarjeta a eliminar: ");
        String idDel = scanner.nextLine().trim();
        boolean deleted = cardService.deleteById(idDel);
        if (deleted) {
            System.out.println("Tarjeta eliminada exitosamente.");
        } else {
            System.out.println("No se encontró el número de tarjeta.");
        }
    }
}
