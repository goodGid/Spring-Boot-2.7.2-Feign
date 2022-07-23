package dev.be.feign.feign.logger;

import java.io.IOException;

import org.springframework.context.ApplicationContext;

import feign.Logger;
import feign.Request;
import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class FeignCustomLogger extends Logger {

    private final ApplicationContext applicationContext;

    @Override
    protected void log(String configKey, String format, Object... args) {
        // log를 어떤 형식으로 남길지 정해준다.
        System.out.println(String.format(methodTag(configKey) + format, args));
    }

    @Override
    protected void logRequest(String configKey, Logger.Level logLevel, Request request) {
        /**
         * [값]
         * configKey = DemoFeignClient#callGet(String,String,Long)
         * logLevel = BASIC # "feign.client.config.demo-client.loggerLevel" 참고
         *
         * [동작 순서]
         * `logRequest` 메소드 진입 -> 외부 요청 -> `logAndRebufferResponse` 메소드 진입
         *
         * [참고]
         * request에 대한 정보는
         * `logAndRebufferResponse` 메소드 파라미터인 response에도 있다.
         * 그러므로 request에 대한 정보를 [logRequest, logAndRebufferResponse] 중 어디에서 남길지 정하면 된다.
         * 만약 `logAndRebufferResponse`에서 남긴다면 `logRequest`는 삭제해버리자.
         */
        System.out.println(request);
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel,
                                              Response response, long elapsedTime) throws IOException {
        return response;
    }
}
