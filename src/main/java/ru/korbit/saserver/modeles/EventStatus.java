package ru.korbit.saserver.modeles;

/**
 * Created by Artur Belogur on 25.10.17.
 */
public enum EventStatus {
    FUTURE(0),
    OPEN(1),
    CLOSE_GOOD(2),
    CLOSE_BAD(3);

    private final int statusIndex;

    EventStatus(int status) {
        this.statusIndex = status;
    }

    public int getStatusIndex() {
        return statusIndex;
    }
}
