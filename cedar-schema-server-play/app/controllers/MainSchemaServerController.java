package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import org.metadatacenter.server.play.AbstractCedarController;
import play.mvc.Result;

import java.util.HashMap;
import java.util.Map;

public class MainSchemaServerController extends AbstractSchemaServerController {

  private final static Map<String, Object> indexResponse;
  private final static JsonNode indexResponseNode;

  static {
    indexResponse = new HashMap<>();
    indexResponse.put("serverName", "cedar-schema-server");
    indexResponse.put("serverDescription", "CEDAR Schema Server.");
    indexResponseNode = asJson(indexResponse);
  }

  public static Result index() {
    return ok(indexResponseNode);
  }

  /* For CORS */
  public static Result preflight(String all) {
    return AbstractCedarController.preflight(all);
  }

}
