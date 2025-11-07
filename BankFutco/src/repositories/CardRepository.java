package repositories;

import model.Cards;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



public class CardRepository {
    
private final List<Cards> storage= new ArrayList<>();

    public CardRepository() {
        initData();
    }

private void initData() {
    storage.add(new Cards("ACC001", "Credit", new BigDecimal("5000.00"), new BigDecimal("1200.00"), new BigDecimal("3800.00")));
    storage.add(new Cards("ACC002", "Debit", new BigDecimal("3000.00"), new BigDecimal("800.00"), new BigDecimal("2200.00")));
    storage.add(new Cards("ACC003", "Credit", new BigDecimal("7000.00"), new BigDecimal("1500.00"), new BigDecimal("5500.00")));
    storage.add(new Cards("ACC004", "Debit", new BigDecimal("2500.00"), new BigDecimal("600.00"), new BigDecimal("1900.00")));
    storage.add(new Cards("ACC005", "Credit", new BigDecimal("6000.00"), new BigDecimal("2000.00"), new BigDecimal("4000.00")));
    storage.add(new Cards("ACC006", "Debit", new BigDecimal("4000.00"), new BigDecimal("1000.00"), new BigDecimal("3000.00")));
    storage.add(new Cards("ACC007", "Credit", new BigDecimal("8000.00"), new BigDecimal("3000.00"), new BigDecimal("5000.00")));
    storage.add(new Cards("ACC008", "Debit", new BigDecimal("3500.00"), new BigDecimal("500.00"), new BigDecimal("3000.00")));
    storage.add(new Cards("ACC009", "Credit", new BigDecimal("9000.00"), new BigDecimal("2500.00"), new BigDecimal("6500.00")));
    storage.add(new Cards("ACC010", "Debit", new BigDecimal("2000.00"), new BigDecimal("400.00"), new BigDecimal("1600.00")));
}

    public Cards save(Cards card) {
		if (card == null || card.getCardNumber() == null) {
			throw new IllegalArgumentException("Account o accountNumber no puede ser null");
		}
		storage.removeIf(a -> a.getCardNumber().equals(card.getCardNumber()));
        storage.add(card);
        return card;
	}

	public Optional<Cards> findById(String CardNumber) {
		if (CardNumber == null) return Optional.empty();
		return storage.stream().filter(a -> CardNumber.equals(a.getCardNumber())).findFirst();
	}

	public List<Cards> findAll() {
		return new ArrayList<>(storage);
	}

    public boolean deleteById(String CardNumber) {
        return findById(CardNumber)
                .map(storage::remove)
                .orElse(false);
    }

	public boolean existsById(String CardNumber) {
		return storage.stream().anyMatch(a -> CardNumber!= null && CardNumber.equals(a.getCardNumber()));
	}


}
