package com.flutter.alloffootball.common.config;

import ban.inspector.config.InspectConfig;
import ban.inspector.factory.WordFactory;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class WordConfig implements InspectConfig {

    @Override
    public void addBanWords(WordFactory factory) {
        factory.add(List.of(
            "감자", "고구마", "바나나"
        ));
    }
}
