package kr.heesu.practice.webflux.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.File;
import java.nio.file.Path;

@Slf4j
@RestController
@RequestMapping("/v1/api/images")
@RequiredArgsConstructor
public class UploadController {

    @PostMapping
    public Mono<Void> upload(@RequestPart FilePart image) {
        log.info("image size: {}", image.headers().getContentLength());

        return DataBufferUtils.write(image.content(), getPath());
    }

    private Path getPath() {
        String filename = RandomStringUtils.randomAlphanumeric(10);

        return new File("/Users/we/upload/temp" + File.separator + filename).toPath();
    }
}
