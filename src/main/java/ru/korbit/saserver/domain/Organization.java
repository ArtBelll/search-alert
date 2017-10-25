package ru.korbit.saserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Artur Belogur on 24.10.17.
 */
@Entity
@Table(name = "organizations")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Organization {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "contacts")
    private String contacts;

    @Column(name = "logo")
    private String logo;

    @JsonIgnore
    @OneToMany(mappedBy = "organization")
    private List<Event> events;
}
