package devmaster.TTCN1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "ID_ORDERS", length = 10)
    private String idOrders;

    @Column(name = "ORDERS_DATE")
    private String ordersDate;

//    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CUSTOMER")
    private Integer idCustomer;

    @Column(name = "TOTAL_MONEY")
    private Double totalMoney;

    @Lob
    @Column(name = "NOTES")
    private String notes;

    @Column(name = "NAME_RECIVER", length = 250)
    private String nameReciver;

    @Column(name = "ADDRESS", length = 500)
    private String address;

    @Column(name = "PHONE", length = 50)
    private String phone;

    @Column(name = "STATUS")
    private Integer status;

    @OneToMany(mappedBy = "idord")
    private Set<OrdersDetail> ordersDetails = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idord")
    private Set<OrdersPayment> ordersPayments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idord")
    private Set<OrdersTransport> ordersTransports = new LinkedHashSet<>();

}