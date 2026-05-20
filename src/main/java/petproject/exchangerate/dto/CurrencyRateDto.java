package petproject.exchangerate.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CurrencyRateDto {
    private String code;
    private String name;
    private Integer nominal;
    private BigDecimal rate;
    private LocalDate date;
}
