/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2025 VTB Group. All rights reserved.
 */

package dev.bd.work.socialnetwork.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

/**
 * Post update request.
 *
 * @author Alexey Bodyak
 */
@Data
@Accessors(chain = true)
public class PostUpdateRequest {
    private UUID id;
    private String text;
}
