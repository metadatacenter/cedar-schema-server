package controllers;

import org.metadatacenter.config.CedarConfig;
import org.metadatacenter.server.play.AbstractCedarController;
import play.Configuration;
import play.Play;

public abstract class AbstractSchemaServerController extends AbstractCedarController {
  protected static CedarConfig cedarConfig;

  static {
    cedarConfig = CedarConfig.getInstance();
  }

}
