package devmaster.TTCN1.domain;

import groovy.transform.builder.Builder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "IDORDERS", length = 10)
    private String idOrders;

    @Column(name = "ORDERSDATE")
    private String ordersDate;

//    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDCUSTOMER")
    private Integer idCustomer;

    @Column(name = "TOTALMONEY")
    private Double totalMoney;

    @Lob
    @Column(name = "NOTES")
    private String notes;

    @Column(name = "NAMERECIVER", length = 250)
    private String nameReciver;

    @Column(name = "ADDRESS", length = 500)
    private String address;

    @Column(name = "PHONE", length = 50)
    private String phone;

}