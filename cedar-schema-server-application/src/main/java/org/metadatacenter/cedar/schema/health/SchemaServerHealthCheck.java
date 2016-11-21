package org.metadatacenter.cedar.schema.health;

import com.codahale.metrics.health.HealthCheck;

public class SchemaServerHealthCheck extends HealthCheck {

  public SchemaServerHealthCheck() {
  }

  @Override
  protected Result check() throws Exception {
    if (2 * 2 == 5) {
      return Result.unhealthy("Unhealthy, because 2 * 2 == 5");
    }
    return Result.healthy();
  }
}