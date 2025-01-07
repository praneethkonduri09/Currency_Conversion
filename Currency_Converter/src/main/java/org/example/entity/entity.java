package org.example.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String baseCurrency;

    @ElementCollection
    @CollectionTable(name = "exchange_rate_values", joinColumns = @JoinColumn(name = "exchange_rate_id"))
    @MapKeyColumn(name = "currency")
    @Column(name = "rate")
    private Map<String, Double> rates;


    public entity(String base, Map<String, Double> rates) {
    }

    public Map<String, Double> getRates() {
        return null;
    }
}
