package backend.com.adquisiciones.domain.events;

import backend.com.shared.events.DomainEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class OCRecepcionadaEvent implements DomainEvent {
    private final Long ordenCompraId;
    private final Long ordenProduccionId;
    private final LocalDateTime occurredOn = LocalDateTime.now();
}
