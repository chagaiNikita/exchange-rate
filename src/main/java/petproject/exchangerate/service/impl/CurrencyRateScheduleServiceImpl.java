package petproject.exchangerate.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import petproject.exchangerate.service.CurrencyRateScheduleService;
import petproject.exchangerate.service.CurrencyRateService;

@Service
@RequiredArgsConstructor
public class CurrencyRateScheduleServiceImpl implements CurrencyRateScheduleService {
    private final CurrencyRateService currencyRateService;

    @Scheduled(cron = "0 0 9 * * *")
    public void fetch() {
        currencyRateService.fetchAndSave();
    }
}
