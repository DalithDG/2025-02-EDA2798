package services;

import model.Balance;
import repositories.BalanceRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class BalanceService implements IBalanceService {

    private final BalanceRepository balanceRepository;

    public BalanceService() {
        this(new BalanceRepository());
    }

    public BalanceService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @Override
    public Balance save(Balance balance) {
        return balanceRepository.save(balance);
    }

    @Override
    public Optional<Balance> findById(String id) {
        try {
            LocalDate date = LocalDate.parse(id);
            return balanceRepository.findByDate(date);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Balance> findAll() {
        return balanceRepository.findAll();
    }

    @Override
    public boolean deleteById(String id) {
        try {
            LocalDate date = LocalDate.parse(id);
            return balanceRepository.deleteByDate(date);
        } catch (Exception e) {
            return false;
        }
    }
}
