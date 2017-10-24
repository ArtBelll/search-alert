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
@Table(name = "areas")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Area {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @NonNull
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "area", fetch = FetchType.LAZY)
    private List<City> cities = new ArrayList<>();
}
