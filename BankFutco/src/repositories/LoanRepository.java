package repositories;

import model.Loans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoanRepository {

    private final List<Loans> storage = new ArrayList<>();

    public Loans save(Loans loan) {
        if (loan == null || loan.getDate() == null) {
            throw new IllegalArgumentException("El préstamo o su fecha no puede ser null");
        }

        storage.removeIf(l -> l.getDate().equals(loan.getDate()));
        storage.add(loan);
        return loan;
    }

    public Optional<Loans> findByDate(LocalDate date) {
        return storage.stream()
                .filter(l -> l.getDate().equals(date))
                .findFirst();
    }

    public List<Loans> findAll() {
        return new ArrayList<>(storage);
    }

    public boolean deleteByDate(LocalDate date) {
        return storage.removeIf(l -> l.getDate().equals(date));
    }
}
