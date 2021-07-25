package kr.heesu.practice.r2dbc.controller;

import kr.heesu.practice.r2dbc.repository.exception.AlreadyExistException;
import kr.heesu.practice.r2dbc.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.io.IOException;

import static kr.heesu.practice.r2dbc.dto.MemberCreateDto.Request;
import static kr.heesu.practice.r2dbc.dto.MemberCreateDto.Response;


@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping
    public Mono<Response> createMember(@RequestBody @Valid Request request) {
        return memberService.create(request)
                .map(member -> Response.of(true, member.getName()))
                .onErrorReturn(AlreadyExistException.class, Response.of(false, "이미 존재합니다."))
                .onErrorReturn(e -> e instanceof IOException, Response.of(false, "IO Exception"))
                .onErrorReturn(Response.of(false, "생성에 실패 하였습니다."));
    }
}
