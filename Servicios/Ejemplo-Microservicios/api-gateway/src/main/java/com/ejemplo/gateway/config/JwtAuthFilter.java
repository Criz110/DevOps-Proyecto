package com.ejemplo.gateway.config;



import com.ejemplo.gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthFilter implements GlobalFilter, Ordered {

    @Autowired
    private JwtUtil jwtUtil;

    // 🔹 Variables leídas del application.properties
    @Value("${security.internal.header}")
    private String internalHeader;

    @Value("${security.internal.value}")
    private String internalValue;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().toString();

        // Permitir rutas públicas (como /auth/**)
        if (path.startsWith("/auth/")) {
            return chain.filter(exchange);
        }

        // Extraer el header Authorization
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);

        try {
            if (!jwtUtil.validateToken(token)) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        } catch (Exception e) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // 🔹 Agregar el header interno antes de enviar la petición al microservicio
        ServerWebExchange mutatedExchange = exchange.mutate()
                .request(builder -> builder.header(internalHeader, internalValue))
                .build();

        return chain.filter(mutatedExchange);
    }

    @Override
    public int getOrder() {
        return -1; // Ejecutar antes del enrutamiento
    }

    /*
    public static void main(String[] args) {
        System.out.println(new JwtUtil().generateToken("usuarioDemo"));
    }
    */
}