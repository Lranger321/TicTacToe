package main.service.bot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Getter
@AllArgsConstructor
public class BotConfiguration {

    private String login;
    private String password;

}
