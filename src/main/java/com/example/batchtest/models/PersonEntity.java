package com.example.batchtest.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Builder
@Entity
@Table(name = "person", schema = "db")
@SecondaryTable(name = "address", pkJoinColumns = @PrimaryKeyJoinColumn(name = "person_id"), schema = "db")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Nonnull
    private String name;
    @Nonnull
    private LocalDate birthdate;
    @Nonnull
    private String cpf;
    @Nonnull
    private String email;
    @Nonnull
    private String phone;
    //Address
    @Column(table = "address")
    @Nonnull
    private String street;
    @Column(table = "address")
    @Nonnull
    private String number;
    @Column(table = "address")
    private String complement;
    @Column(table = "address")
    @Nonnull
    private String neighborhood;
    @Column(table = "address")
    @Nonnull
    private String city;
    @Column(table = "address")
    @Nonnull
    private String state;
    @Column(table = "address")
    @Nonnull
    private String zipCode;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ?
                ((HibernateProxy) o).getHibernateLazyInitializer()
                        .getPersistentClass() :
                o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer()
                        .getPersistentClass() :
                this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        PersonEntity that = (PersonEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer()
                        .getPersistentClass()
                        .hashCode() :
                getClass().hashCode();
    }
}
