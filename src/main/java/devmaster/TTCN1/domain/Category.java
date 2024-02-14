package devmaster.TTCN1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "IDPARENT")
    private Integer idparent;

    @Column(name = "NAME", length = 500)
    private String name;

    @Lob
    @Column(name = "NOTES")
    private String notes;

    @Column(name = "ICON", length = 250)
    private String icon;

    @Column(name = "CREATED_DATE")
    private String createdDate;

    @Column(name = "UPDATED_DATE")
    private String updatedDate;

    @Column(name = "ISACTIVE")
    private Byte isActive;

    @Column(name = "ISDELETE")
    private Byte isDelete;
}