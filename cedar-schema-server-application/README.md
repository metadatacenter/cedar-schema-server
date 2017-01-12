# CEDAR Schema Server

To run the server

    java \
      -Dkeycloak.config.path="$CEDAR_HOME/keycloak.json" \
      -jar $CEDAR_HOME/cedar-schema-server/cedar-schema-server-application/target/cedar-schema-server-application-*.jar \
      server \
      "$CEDAR_HOME/cedar-schema-server/cedar-schema-server-application/config.yml"

To access the application:

[http://localhost:9003/]()

To access the admin port:

[http://localhost:9103/]()