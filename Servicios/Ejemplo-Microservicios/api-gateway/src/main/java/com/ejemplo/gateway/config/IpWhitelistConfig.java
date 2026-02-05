package com.ejemplo.gateway.config;




import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;

import jakarta.annotation.PostConstruct;
import java.math.BigInteger;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class IpWhitelistConfig {

    /*
    @Value("${gateway.allowed.ips}")
    private String allowedIpsProperty;

    private List<String> allowedIps;

    @PostConstruct
    public void init() {
        allowedIps = Arrays.stream(allowedIpsProperty.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        System.out.println("✅ IPs/rangos permitidos: " + allowedIps);
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter ipWhitelistGlobalFilter() {
        return (exchange, chain) -> {
            String clientIp = getClientIp(exchange);

            if (!isAllowed(clientIp)) {
                System.out.println("❌ Bloqueada IP no permitida: " + clientIp);
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }

            System.out.println("✅ Petición permitida desde: " + clientIp);
            return chain.filter(exchange);
        };
    }

    // Obtiene la IP real del cliente (X-Forwarded-For o IP remota)
    private String getClientIp(ServerWebExchange exchange) {
        String xForwardedFor = exchange.getRequest().getHeaders().getFirst("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            return xForwardedFor.split(",")[0].trim();
        }else{
            return null;
        }
        
       // return exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
    }

    // Verifica si la IP está permitida
    private boolean isAllowed(String ip) {
        for (String allowed : allowedIps) {
            if (allowed.contains("/")) {
                if (isInRange(ip, allowed)) return true;
            } else if (allowed.equalsIgnoreCase(ip)) {
                return true;
            }
        }
        return false;
    }

    // Verifica si la IP está dentro del rango CIDR (IPv4 o IPv6)
    private boolean isInRange(String ip, String cidr) {
        try {
            String[] parts = cidr.split("/");
            String cidrIp = parts[0];
            int prefix = Integer.parseInt(parts[1]);

            InetAddress inetIp = InetAddress.getByName(ip);
            InetAddress inetCidr = InetAddress.getByName(cidrIp);

            byte[] ipBytes = inetIp.getAddress();
            byte[] cidrBytes = inetCidr.getAddress();

            if (ipBytes.length != cidrBytes.length) return false; // IPv4 vs IPv6 mismatch

            BigInteger ipValue = new BigInteger(1, ipBytes);
            BigInteger cidrValue = new BigInteger(1, cidrBytes);

            int totalBits = ipBytes.length * 8;
            BigInteger mask = prefix == 0 ? BigInteger.ZERO :
                    BigInteger.valueOf(-1).shiftLeft(totalBits - prefix);

            return ipValue.and(mask).equals(cidrValue.and(mask));
        } catch (Exception e) {
            System.err.println("⚠️ Error verificando rango CIDR: " + e.getMessage());
            return false;
        }
    }
    */
}