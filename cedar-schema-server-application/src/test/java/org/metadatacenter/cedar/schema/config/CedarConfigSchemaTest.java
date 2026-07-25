package org.metadatacenter.cedar.schema.config;

import org.metadatacenter.model.SystemComponent;
import org.metadatacenter.util.test.AbstractCedarConfigTest;

public class CedarConfigSchemaTest extends AbstractCedarConfigTest {

  @Override
  protected SystemComponent getSystemComponent() {
    return SystemComponent.SERVER_SCHEMA;
  }

}
