package kr.heesu.practice.r2dbc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    private final Path basePath = Paths.get("./src/main/resource/uploaded/");

    @PostMapping("/upload")
    public Mono<Void> uploadFile(
            @RequestPart("user-name") String name,
            @RequestPart Mono<FilePart> file
    ) {
        log.info("user = " + name);
        return file.doOnNext(filePart -> log.info("Uploaded File = " + filePart.filename()))
                .flatMap(filePart -> filePart.transferTo(basePath.resolve(filePart.filename())))
                .then();
    }
}
