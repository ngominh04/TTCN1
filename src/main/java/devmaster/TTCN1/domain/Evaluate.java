package devmaster.TTCN1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "evaluate")
public class Evaluate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "VALUE", length = 200)
    private String value;

//    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDER")
    private Integer idOrder;

    @JoinColumn(name = "ID_PRO")
    private Integer idPro;

    @Column(name = "ISACTIVE")
    private Byte isActive;

    @Column(name = "ISDELETE")
    private Byte isDelete;

}