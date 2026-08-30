package org.metadatacenter.cedar.schema;

import io.dropwizard.testing.DropwizardTestSupport;
import io.dropwizard.testing.ResourceHelpers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.metadatacenter.cedar.schema.resources.IndexResource;
import org.metadatacenter.config.environment.CedarEnvironmentSource;
import org.metadatacenter.util.test.RouteSurface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Route safety net: probes every endpoint this server's resources declare and requires each to
 * answer. A 404 or 405 means the route vanished or changed verb — the failure a framework upgrade
 * or a refactor introduces, which a happy-path smoke and a config test both miss.
 *
 * <p>The expected status is 200 rather than the 401 its siblings assert, because this server
 * declares no authenticated endpoint: its whole surface is the microservice index. That also makes
 * the surface itself worth pinning. Today it is a single inherited route, so this test overlaps the
 * smoke test; its value is that the route list comes from reflection, so an endpoint added later is
 * probed automatically, and one added unauthenticated to a server that is supposed to have none
 * shows up as a surface change rather than passing unnoticed.
 */
public class SchemaRoutesRespondTest {

  static {
    // Must run before the test support boots the server, which reads the port env vars. Ports are
    // assigned by the OS, so they cannot collide with the dev server or another test.
    Map<String, String> environment = new HashMap<>(CedarEnvironmentSource.getAll());
    environment.put("CEDAR_SCHEMA_HTTP_PORT", "0");
    environment.put("CEDAR_SCHEMA_ADMIN_PORT", "0");
    environment.put("CEDAR_SCHEMA_STOP_PORT", "0");
    CedarEnvironmentSource.setOverride(environment);
  }

  private static final DropwizardTestSupport<SchemaServerConfiguration> SERVER =
      new DropwizardTestSupport<>(SchemaServerApplication.class, ResourceHelpers.resourceFilePath("test-config.yml"));

  @BeforeAll
  public static void startServer() throws Exception {
    SERVER.before();
  }

  @AfterAll
  public static void stopServer() {
    SERVER.after();
  }

  @Test
  public void everyDeclaredRouteAnswers() {
    RouteSurface.assertEveryRouteAnswers(
        "http://localhost:" + SERVER.getLocalPort(),
        RouteSurface.endpoints(IndexResource.class),
        200);
  }

  /**
   * The surface this server is meant to have. An endpoint appearing here is a deliberate act on a
   * server whose entire contract is the index, so it should require updating this list rather than
   * arriving silently.
   */
  @Test
  public void theRouteSurfaceIsOnlyTheIndex() {
    List<String> routes = RouteSurface.endpoints(IndexResource.class).stream()
        .map(RouteSurface.Endpoint::key)
        .toList();

    Assertions.assertEquals(List.of("GET /"), routes,
        "The schema server's declared route surface changed");
  }
}
