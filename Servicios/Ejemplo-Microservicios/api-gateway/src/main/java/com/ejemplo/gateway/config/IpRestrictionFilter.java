package com.ejemplo.gateway.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;

//@Configuration
public class IpRestrictionFilter {

    @Value("${app.allowed-ips}")
    private String allowedIpsString;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter ipRestrictionFilterBean() {
        List<String> allowedIps = Arrays.stream(allowedIpsString.split(","))
                .map(String::trim)
                .toList();

        return (exchange, chain) -> {
            String ip = getClientIp(exchange);
            if (!allowedIps.contains(ip)) {
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }
            return chain.filter(exchange);
        };
    }

    private String getClientIp(ServerWebExchange exchange) {
        // 1️⃣ Primero revisamos X-Forwarded-For (si hay proxy)
        String forwarded = exchange.getRequest().getHeaders().getFirst("X-Forwarded-For");
        if (forwarded != null && !forwarded.isBlank()) {
            return forwarded.split(",")[0].trim();
        }
    
        // 2️⃣ Obtenemos la IP remota directa
        InetSocketAddress remote = exchange.getRequest().getRemoteAddress();
        if (remote != null && remote.getAddress() != null) {
            return remote.getAddress().getHostAddress();
        }
    
        // 3️⃣ Si todo falla
        return "unknown";
    }
}