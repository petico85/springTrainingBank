package bank.backend;

import org.springframework.context.ApplicationEvent;

/**
 * esemény kezelést átnézni
 * */
public class ClientHasCreatedEvent extends ApplicationEvent {

    private String name; //ki az akit létrehoztun

    public ClientHasCreatedEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
