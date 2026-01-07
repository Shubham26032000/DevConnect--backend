package com.devconnect.use_service.dto;

public record LoginSuccessResponse(
        long userId,
        String username
) {}