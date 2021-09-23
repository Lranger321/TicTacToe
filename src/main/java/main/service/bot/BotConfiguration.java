package main.service.bot;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

//@Component
@Data
@AllArgsConstructor
public class BotConfiguration {

    private String login;
    private String password;

}
