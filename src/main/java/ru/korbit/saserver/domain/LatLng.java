package ru.korbit.saserver.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

/**
 * Created by Artur Belogur on 25.10.17.
 */
@Entity
@Table(name = "locations")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class LatLng {
    @Id
    @GeneratedValue
    private long id;
    @NonNull private Float lat;
    @NonNull private Float lng;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
