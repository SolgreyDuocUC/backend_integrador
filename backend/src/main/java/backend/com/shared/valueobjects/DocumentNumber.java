package backend.com.shared.valueobjects;

import lombok.Value;

import java.util.Objects;

@Value
public class DocumentNumber {
    Long value;

    public DocumentNumber(Long value) {
        this.value = Objects.requireNonNull(value, "Document number cannot be null");
        if (value <= 0) {
            throw new IllegalArgumentException("Document number must be positive");
        }
    }
}
