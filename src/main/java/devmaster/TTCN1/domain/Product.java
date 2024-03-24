package devmaster.TTCN1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAME", length = 500)
    private String name;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @Lob
    @Column(name = "NOTES")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IMAGE")
    private ProductImage image;

//    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "IDCATEGORY")
    private Integer idCategory;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "QUATITY")
    private int quatity;

    @Column(name = "CREATED_DATE")
    private String createdDate;

    @Column(name = "UPDATED_DATE")
    private String updatedDate;

    @Column(name = "ISACTIVE")
    private Byte isactive;

    @Column(name = "ISDELETE")
    private int isdelete;

}