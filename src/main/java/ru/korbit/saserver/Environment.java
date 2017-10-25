package ru.korbit.saserver;

import lombok.Data;
import lombok.Getter;
import lombok.val;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Artur Belogur on 25.10.17.
 */

@Component
public class Environment {
    private final String HOST_TEMPLATE = "http://%s:8080";

    @Getter
    private final String host;

    public Environment() throws UnknownHostException {
        val currentIp = InetAddress.getLocalHost();
        host = String.format(HOST_TEMPLATE, currentIp);
    }
}
