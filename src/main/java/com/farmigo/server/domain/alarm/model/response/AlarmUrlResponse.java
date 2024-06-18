package com.farmigo.server.domain.alarm.model.response;

import com.farmigo.server.global.model.response.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmUrlResponse extends Response {
    // Redirect url
    public String redirectUrl;
}
