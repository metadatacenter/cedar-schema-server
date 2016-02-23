package controllers;

import org.metadatacenter.server.play.AbstractCedarController;
import play.mvc.Result;

public class MainSchemaServerController extends AbstractSchemaServerController {

  public static Result index() {
    return ok("CEDAR Schema Server.");
  }

  /* For CORS */
  public static Result preflight(String all) {
    return AbstractCedarController.preflight(all);
  }

}
