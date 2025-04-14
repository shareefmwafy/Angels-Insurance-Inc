package com.asal.insurance_system.Model;

import com.asal.insurance_system.Enum.AccidentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "accidents")
public class Accident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate accidentDate;

    private String location;

    private String description;

    @Enumerated(EnumType.STRING)
    private AccidentStatus status = AccidentStatus.OPEN;

    @ElementCollection
    @CollectionTable(name = "accident_documents", joinColumns = @JoinColumn(name = "accident_id"))
    @Column(name = "document_url")
    private List<String> documents;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }
}
