package ru.korbit.saserver.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.korbit.saserver.modeles.EventStatus;

import javax.persistence.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artur Belogur on 24.10.17.
 */
@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Event {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "name_missing")
    private String nameMissing;

    @ElementCollection
    @Column(name = "links")
    private List<URL> photoUrls = new ArrayList<>();

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EventStatus status;

    @NonNull
    @Column(name = "miss_date")
    private LocalDateTime missDate;

    @NonNull
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NonNull
    @Column(name = "information", columnDefinition = "TEXT")
    private String information;

    @NonNull
    @Column(name = "contacts")
    private String contacts;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToMany(mappedBy = "event")
    private List<LatLng> startLocations = new ArrayList<>();

    @ElementCollection
    @Column(name = "logs")
    private List<String> logs = new ArrayList<>();

    @OneToMany(mappedBy = "event")
    private List<News> news = new ArrayList<>();

    @ManyToMany(mappedBy = "events")
    private List<City> cities = new ArrayList<>();
}
