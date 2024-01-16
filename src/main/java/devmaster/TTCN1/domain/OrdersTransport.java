package devmaster.TTCN1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders_transport")
public class OrdersTransport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDORD")
    private Order idord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDTRANSPORT")
    private TransportMethod idtransport;

    @Column(name = "TOTAL")
    private Integer total;

    @Column(name = "NOTES")
    private Integer notes;

}