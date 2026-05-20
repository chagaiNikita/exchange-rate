package petproject.exchangerate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import petproject.exchangerate.model.CurrencyRate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {

    Optional<CurrencyRate> findByCodeAndDate(String code, LocalDate date);

    List<CurrencyRate> findByDate(LocalDate date);
}
