package com.github.nutt1101;

import com.github.nutt1101.models.Bed;
import com.github.nutt1101.models.LoginParameters;
import com.github.nutt1101.models.RequestParameters;
import org.htmlunit.HttpMethod;
import org.htmlunit.WebClient;
import org.htmlunit.WebRequest;
import org.htmlunit.WebResponse;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class DormDataApi {
    static WebClient webClient;
    static LocalDateTime lastRequestTime;

    static {
        setup();
    }

    static void setup() {
        webClient = new WebClient();
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
    }

    public static List<Bed> getBedData(LoginParameters params, RequestParameters reqParams) throws IOException {
        if (lastRequestTime == null ||
                isAtLeastMinutesApart(lastRequestTime, LocalDateTime.now(), 5)
        ) {
            login(params);
        }

        WebRequest request = new WebRequest(
                new URL(Keys.URL_DATA_SOURCE.getVal())
        );

        request.setHttpMethod(HttpMethod.POST);
        request.setRequestBody(reqParams.toRequestBody());
        WebResponse response = webClient.loadWebResponse(request);
        lastRequestTime = LocalDateTime.now();
        DormJsonUtils jsonUtils = new DormJsonUtils(response.getContentAsString());
        return jsonUtils.parse();
    }

    static void login(LoginParameters params)  {
        try {
            WebRequest request = new WebRequest(
                    new URL(Keys.URL_LOGIN.getVal())
            );
            request.setHttpMethod(HttpMethod.POST);
            request.setRequestBody(params.toRequestBody());
            webClient.loadWebResponse(request);
            lastRequestTime = LocalDateTime.now();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isAtLeastMinutesApart(LocalDateTime time1, LocalDateTime time2, int minutes) {
        Duration duration = Duration.between(time1, time2);
        long minutesDifference = Math.abs(duration.toMinutes());
        return minutesDifference >= minutes;
    }
}
