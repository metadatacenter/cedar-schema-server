package controllers;

import org.metadatacenter.config.CedarConfig;
import org.metadatacenter.server.play.AbstractCedarController;

public abstract class AbstractSchemaServerController extends AbstractCedarController {

  protected static final CedarConfig cedarConfig;

  static {
    cedarConfig = CedarConfig.getInstance();
  }

}
