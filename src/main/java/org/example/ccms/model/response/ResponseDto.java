package org.example.ccms.model.response;

import lombok.Builder;

@Builder
public record ResponseDto(Long status, String message) {
}
