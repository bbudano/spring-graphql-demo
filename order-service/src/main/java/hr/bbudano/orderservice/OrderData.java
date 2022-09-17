package hr.bbudano.orderservice;

import org.springframework.data.annotation.Id;

public record OrderData(@Id Integer id, Integer customerId) {
}
