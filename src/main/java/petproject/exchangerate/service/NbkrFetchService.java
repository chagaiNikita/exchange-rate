package petproject.exchangerate.service;

import petproject.exchangerate.model.CurrencyRate;

import java.util.List;

public interface NbkrFetchService {
    List<CurrencyRate> fetchRates();
}
