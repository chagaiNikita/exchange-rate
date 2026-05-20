package petproject.exchangerate.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import petproject.exchangerate.model.CurrencyRate;
import petproject.exchangerate.service.NbkrFetchService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class NbkrFetchServiceImpl implements NbkrFetchService {
    private static final String URL = "https://www.nbkr.kg/XML/daily.xml";
    private final RestTemplate restTemplate;

    @Override
    public List<CurrencyRate> fetchRates() {
        try {
            String xml = restTemplate.getForObject(URL, String.class);

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xml)));

            String rawDate = document.getDocumentElement().getAttribute("Date");
            LocalDate date = LocalDate.parse(rawDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

            NodeList currencies = document.getElementsByTagName("Currency");
            List<CurrencyRate> result = new ArrayList<>();

            for (int i = 0; i < currencies.getLength(); i++) {
                Element element = (Element) currencies.item(i);

                String code = element.getAttribute("ISOCode");
                int nominal = Integer.parseInt(element.getElementsByTagName("Nominal").item(0).getTextContent());
                BigDecimal rate = new BigDecimal(
                        element.getElementsByTagName("Value").item(0).getTextContent().replace(",", ".")
                );

                CurrencyRate currencyRate = new CurrencyRate();
                currencyRate.setCode(code);
                currencyRate.setName(getCurrencyName(code));
                currencyRate.setNominal(nominal);
                currencyRate.setRate(rate);
                currencyRate.setDate(date);

                result.add(currencyRate);
            }

            return result;

        } catch (Exception e) {
            log.error("Ошибка при получении курсов с НБКР", e);
            return Collections.emptyList();
        }
    }

    private String getCurrencyName(String code) {
        try {
            return Currency.getInstance(code).getDisplayName(new Locale("ru"));
        } catch (IllegalArgumentException e) {
            return code;
        }
    }
}
