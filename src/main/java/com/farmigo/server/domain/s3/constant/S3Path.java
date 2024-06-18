package com.farmigo.server.domain.s3.constant;

import com.farmigo.server.domain.s3.model.request.PreSignedUrlRequest;

public enum S3Path {
    PROFILE("profile"),
    ACTIVITY("activity"),
    FARM("farm");

    private final String name;

    S3Path(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getPath(PreSignedUrlRequest.IdMap idMap, String fileName) {
        return switch (this) {
            case PROFILE -> "user/{userId}/profile/img/{fileName}".replace("{userId}", idMap.getUserId())
                    .replace("{fileName}", fileName);
            case FARM -> "farm/{farmId}/img/{fileName}".replace("{farmId}", idMap.getFarmId())
                    .replace("{fileName}", fileName);
            case ACTIVITY ->
                    "farm/{farmId}/{activityId}/img/{fileName}".replace("{farmId}", idMap.getFarmId())
                            .replace("{activityId}", idMap.getActivityId())
                            .replace("{fileName}", fileName);
            default -> "";
        };
    }
}
