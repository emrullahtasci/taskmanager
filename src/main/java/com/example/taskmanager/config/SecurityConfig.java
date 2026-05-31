package com.example.taskmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Postman ile rahatça POST/PUT istekleri atabilmek için CSRF'i kapatıyoruz
                .csrf(AbstractHttpConfigurer::disable)

                // İstek yönlendirme kuralları
                .authorizeHttpRequests(auth -> auth
                        // /tasks ve /h2-console ile başlayan her şeye ŞİFRESİZ erişim izni ver
                        .requestMatchers("/tasks/**", "/h2-console/**").permitAll()
                        // Geri kalan herhangi bir istek olursa (güvenlik protokolü gereği) logın istesin
                        .anyRequest().authenticated()
                )

                // H2 veritabanı arayüzünün tarayıcıda bloklanmadan açılması için bu ayar şart
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        return http.build();
    }
}