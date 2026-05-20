package petproject.exchangerate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petproject.exchangerate.service.CurrencyRateService;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("currency-rates")
public class CurrencyRateController {
    private final CurrencyRateService currencyRateService;

    @PostMapping("update")
    public ResponseEntity<?> updateCurrencyRates() {
        currencyRateService.fetchAndSave();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getCurrencyRates(@RequestParam LocalDate date) {
        return ResponseEntity.status(HttpStatus.OK).body(currencyRateService.getCurrencyRates(date));
    }
}
