package petproject.exchangerate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "currency_rate")
public class CurrencyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code", nullable = false, length = 10)
    private String code;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "nominal", nullable = false)
    private Integer nominal;

    @Column(name = "rate", nullable = false, precision = 19, scale = 4)
    private BigDecimal rate;

    @Column(name = "date", nullable = false)
    private LocalDate date;

}