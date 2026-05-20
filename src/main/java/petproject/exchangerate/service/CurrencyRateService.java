package petproject.exchangerate.service;

import petproject.exchangerate.dto.CurrencyRateDto;

import java.time.LocalDate;
import java.util.List;

public interface CurrencyRateService {
    void fetchAndSave();

    List<CurrencyRateDto> getCurrencyRates(LocalDate date);
}
