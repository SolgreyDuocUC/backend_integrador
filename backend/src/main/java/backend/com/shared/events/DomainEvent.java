package backend.com.shared.events;

import java.time.LocalDateTime;

public interface DomainEvent {
    LocalDateTime getOccurredOn();
}
