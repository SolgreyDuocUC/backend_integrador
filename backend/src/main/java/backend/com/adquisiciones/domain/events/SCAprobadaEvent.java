package backend.com.adquisiciones.domain.events;

import backend.com.shared.events.DomainEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class SCAprobadaEvent implements DomainEvent {
    private final Long solicitudCompraId;
    private final LocalDateTime occurredOn = LocalDateTime.now();
}
