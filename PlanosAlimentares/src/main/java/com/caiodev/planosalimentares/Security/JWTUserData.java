package com.caiodev.planosalimentares.Security;

import lombok.Builder;

@Builder
public record JWTUserData(Long userId, String email) {
}
