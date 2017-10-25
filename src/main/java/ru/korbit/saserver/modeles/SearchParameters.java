package ru.korbit.saserver.modeles;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artur Belogur on 25.10.17.
 */
@Data
public class SearchParameters {
    private List<Long> citiesId = new ArrayList<>();
    private List<EventStatus> statuses = new ArrayList<>();
}
