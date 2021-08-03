package kr.heesu.practice.webflux.clients.sms.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NotificationSmsRequest {

    private String type;
    private String contentType;
    private String countryCode;
    private String from;
    private String content;
    private List<Message> messages;

    @Builder(access = AccessLevel.PRIVATE)
    public NotificationSmsRequest(String type, String contentType, String countryCode, String from, String content, List<Message> messages) {
        this.type = type;
        this.contentType = contentType;
        this.countryCode = countryCode;
        this.from = from;
        this.content = content;
        this.messages = messages;
    }

    public static NotificationSmsRequest of(String from, List<Message> messages) {
        return NotificationSmsRequest.builder()
                .type("SMS")
                .contentType("COMM")
                .countryCode("82")
                .from(from)
                .content("WECADDIE")
                .messages(messages)
                .build();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Message {

        private String to;

        private String content;

        @Builder
        public Message(String to, String content) {
            this.to = to;
            this.content = content;
        }

        public static Message of(String destination, String content) {
            return Message.builder().to(destination).content(content).build();
        }
    }

}
