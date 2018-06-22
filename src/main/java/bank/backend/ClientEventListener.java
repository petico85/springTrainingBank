package bank.backend;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ClientEventListener implements ApplicationListener<ClientHasCreatedEvent> {

    @Override
    public void onApplicationEvent(ClientHasCreatedEvent clientHasCreatedEvent) {
        System.out.println("Client has created: " + clientHasCreatedEvent.getName());
    }
}
