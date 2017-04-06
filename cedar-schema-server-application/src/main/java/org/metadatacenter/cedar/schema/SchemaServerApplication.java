package org.metadatacenter.cedar.schema;

import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.metadatacenter.cedar.schema.health.SchemaServerHealthCheck;
import org.metadatacenter.cedar.schema.resources.IndexResource;
import org.metadatacenter.cedar.util.dw.CedarMicroserviceApplication;
import org.metadatacenter.model.ServerName;

public class SchemaServerApplication extends CedarMicroserviceApplication<SchemaServerConfiguration> {

  public static void main(String[] args) throws Exception {
    new SchemaServerApplication().run(args);
  }

  @Override
  protected ServerName getServerName() {
    return ServerName.SCHEMA;
  }

  @Override
  public void initializeApp(Bootstrap<SchemaServerConfiguration> bootstrap) {
  }

  @Override
  public void runApp(SchemaServerConfiguration configuration, Environment environment) {
    final IndexResource index = new IndexResource();
    environment.jersey().register(index);

    final SchemaServerHealthCheck healthCheck = new SchemaServerHealthCheck();
    environment.healthChecks().register("message", healthCheck);
  }
}
