package services;

import model.Loans;
import repositories.LoanRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class LoanService implements ILoansService {

    private final LoanRepository loanRepository;

    public LoanService() {
        this(new LoanRepository());
    }

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Loans save(Loans loan) {
        return loanRepository.save(loan);
    }

    @Override
    public Optional<Loans> findById(String id) {
        try {
            LocalDate date = LocalDate.parse(id);
            return loanRepository.findByDate(date);
        } catch (Exception e) {
            System.out.println("Formato de fecha inválido: " + id);
            return Optional.empty();
        }
    }

    @Override
    public List<Loans> findAll() {
        return loanRepository.findAll();
    }

    @Override
    public boolean deleteById(String id) {
        try {
            LocalDate date = LocalDate.parse(id);
            return loanRepository.deleteByDate(date);
        } catch (Exception e) {
            System.out.println("Formato de fecha inválido: " + id);
            return false;
        }
    }
}
