package org.springframework.samples.petclinic.health;


import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthCheckController {

    private final HealthIndicator applicationHealthIndicator;
    private final HealthIndicator jdbcHealthIndicator;

    public HealthCheckController(HealthIndicator applicationHealthIndicator, HealthIndicator jdbcHealthIndicator) {
        this.applicationHealthIndicator = applicationHealthIndicator;
        this.jdbcHealthIndicator = jdbcHealthIndicator;
    }

    @GetMapping
    public Health healthCheck() {
        Health.Builder healthBuilder = Health.up();
        
        Health applicationHealth = applicationHealthIndicator.health();
        healthBuilder.withDetail("application", applicationHealth.getStatus());
        
        
        Health jdbcHealth = jdbcHealthIndicator.health();
        healthBuilder.withDetail("jdbc", jdbcHealth.getStatus());

        if (jdbcHealth.getStatus().equals(Status.UP)) {
            healthBuilder.withDetail("jdbc_connection_status", "OK");
        } else {
            healthBuilder.down().withDetail("jdbc_connection_status", "FAIL");
        }

        return healthBuilder.build();
    }
    public HttpStatus getHttpCode(Health health) {
        if (health.getStatus().equals(Status.UP)) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
