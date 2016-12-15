package org.metadatacenter.cedar.schema;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.metadatacenter.cedar.schema.health.SchemaServerHealthCheck;
import org.metadatacenter.cedar.schema.resources.IndexResource;
import org.metadatacenter.cedar.util.dw.CedarDropwizardApplicationUtil;

public class SchemaServerApplication extends Application<SchemaServerConfiguration> {
  public static void main(String[] args) throws Exception {
    new SchemaServerApplication().run(args);
  }

  @Override
  public String getName() {
    return "schema-server";
  }

  @Override
  public void initialize(Bootstrap<SchemaServerConfiguration> bootstrap) {
    CedarDropwizardApplicationUtil.setupKeycloak();
  }

  @Override
  public void run(SchemaServerConfiguration configuration, Environment environment) {
    final IndexResource index = new IndexResource();
    environment.jersey().register(index);

    final SchemaServerHealthCheck healthCheck = new SchemaServerHealthCheck();
    environment.healthChecks().register("message", healthCheck);

    CedarDropwizardApplicationUtil.setupEnvironment(environment);
  }
}
