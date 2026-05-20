package petproject.exchangerate.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import petproject.exchangerate.dto.CurrencyRateDto;
import petproject.exchangerate.model.CurrencyRate;
import petproject.exchangerate.repository.CurrencyRateRepository;
import petproject.exchangerate.service.CurrencyRateService;
import petproject.exchangerate.service.NbkrFetchService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyRateServiceImpl implements CurrencyRateService {
    private final CurrencyRateRepository currencyRateRepository;
    private final NbkrFetchService nbkrFetchService;


    @Override
    public void fetchAndSave() {
        List<CurrencyRate> rates = nbkrFetchService.fetchRates();

        rates.forEach(rate -> {
            CurrencyRate entity = currencyRateRepository
                    .findByCodeAndDate(rate.getCode(), rate.getDate())
                    .orElse(new CurrencyRate());

            entity.setCode(rate.getCode());
            entity.setName(rate.getName());
            entity.setNominal(rate.getNominal());
            entity.setRate(rate.getRate());
            entity.setDate(rate.getDate());

            currencyRateRepository.save(entity);
        });

        log.info("Обработано записей: {}", rates.size());
    }

    @Override
    public List<CurrencyRateDto> getCurrencyRates(LocalDate date) {
        List<CurrencyRate> rates = currencyRateRepository.findByDate(date);
        return rates.stream()
                .map(t -> {
                    CurrencyRateDto dto = new CurrencyRateDto();
                    dto.setCode(t.getCode());
                    dto.setName(t.getName());
                    dto.setNominal(t.getNominal());
                    dto.setRate(t.getRate());
                    dto.setDate(t.getDate());
                    return dto;
                })
                .toList();
    }
}
