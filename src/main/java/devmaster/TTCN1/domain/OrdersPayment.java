package devmaster.TTCN1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders_payment")
public class OrdersPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDORD")
    private Order idord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDPAYMENT")
    private PaymentMethod idpayment;

    @Column(name = "TOTAL")
    private Integer total;

    @Column(name = "NOTES")
    private Integer notes;

    @Column(name = "STATUS", length = 50)
    private String status;

}