package org.metadatacenter.cedar.schema;

import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import org.metadatacenter.cedar.util.dw.CedarMicroserviceIndexResource;
import org.metadatacenter.cedar.util.dw.CedarMicroserviceApplication;
import org.metadatacenter.config.CedarConfig;
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
  protected void initializeWithBootstrap(Bootstrap<SchemaServerConfiguration> bootstrap, CedarConfig cedarConfig) {
  }

  @Override
  public void initializeApp() {
  }

  @Override
  public void runApp(SchemaServerConfiguration configuration, Environment environment) {
    final CedarMicroserviceIndexResource index =
        new CedarMicroserviceIndexResource(cedarConfig, getServerName());
    environment.jersey().register(index);

  }
}
