package org.springframework.samples.petclinic.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

@Component
public class JdbcHealthIndicator implements HealthIndicator {

    private final DataSource dataSource;

    public JdbcHealthIndicator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Health health() {
        try (Connection connection = dataSource.getConnection()) {
            boolean isConnected = connection.isValid(3);
            if (isConnected) {
                return Health.up().build();
            } else {
                return Health.down().build();
            }
        } catch (SQLException e) {
            return Health.down(e).build();
        }
    }
}

