package org.springframework.samples.petclinic.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ApplicationHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        boolean applicationStatus = true; 
        if (applicationStatus) {
            return Health.up().build();
        } else {
            return Health.down().build();
        }
    }
}

