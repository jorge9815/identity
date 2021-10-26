package com.identity.shared;

import java.util.UUID;

public class UuidId {
    private final UUID value;

    public UuidId(String value) {
        this.value = UUID.fromString(value);
    }

    public String getValue() {
        return value.toString();
    }
}
