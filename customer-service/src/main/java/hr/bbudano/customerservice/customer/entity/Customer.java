package hr.bbudano.customerservice.customer.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class Customer {

    @Id
    private Integer id;

    private String name;

    public Customer(String name) {
        this.name = name;
    }

}
