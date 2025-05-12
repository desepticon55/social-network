/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2025 VTB Group. All rights reserved.
 */

package dev.bd.work.socialnetwork.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Post create request.
 *
 * @author Alexey Bodyak
 */
@Data
@Accessors(chain = true)
public class PostCreateRequest {
    private String text;
}
