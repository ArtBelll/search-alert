package ru.korbit.saserver.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "city_id")
    private Area area;
}
