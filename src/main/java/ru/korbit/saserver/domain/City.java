package ru.korbit.saserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artur Belogur on 24.10.17.
 */
@Entity
@Table(name = "cities")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class City {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @NonNull
    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "city_event",
            joinColumns = @JoinColumn(name = "city_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"))
    private List<Event> events = new ArrayList<>();
}
