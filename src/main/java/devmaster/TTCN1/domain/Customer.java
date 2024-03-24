package devmaster.TTCN1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAME", length = 250)
    private String name;

    @Column(name = "USERNAME", length = 50)
    private String username;

    @Column(name = "PASSWORD", length = 50)
    private String password;

    @Column(name = "ADDRESS", length = 500)
    private String address;

    @Column(name = "EMAIL", length = 150)
    private String email;

    @Column(name = "PHONE", length = 50)
    private String phone;

    @Column(name = "CREATED_DATE")
    private String createdDate;

    @Column(name = "ISACTIVE")
    private Byte isActive;

    @Column(name = "ISDELETE")
    private Byte isDelete;

    @Column(name = "phanquyen")
    private Integer phanquyen;

}