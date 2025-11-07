package repositories;

import model.Balance;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BalanceRepository {

    private final List<Balance> storage = new ArrayList<>();

    public Balance save(Balance balance) {
        if (balance == null || balance.getDate() == null) {
            throw new IllegalArgumentException("Balance o su fecha no puede ser null");
        }


        storage.removeIf(b -> b.getDate().equals(balance.getDate())
                && b.getAccountNumber().equals(balance.getAccountNumber()));

        storage.add(balance);
        return balance;
    }

    public Optional<Balance> findByDate(LocalDate date) {
        return storage.stream()
                .filter(b -> b.getDate().equals(date))
                .findFirst();
    }

    public List<Balance> findAll() {
        return new ArrayList<>(storage);
    }

    public boolean deleteByDate(LocalDate date) {
        return storage.removeIf(b -> b.getDate().equals(date));
    }
}
