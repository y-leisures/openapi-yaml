package org.bms;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import static org.bms.BmsConstants.*;

@OpenAPIDefinition(
//        info = @Info(
//                title = "BMS API Endpoints",
//                version = "0.1.0",
//                description = "Here is description of BMS API.",
//                license = @org.eclipse.microprofile.openapi.annotations.info.License(
//                        name = "MIT License",
//                        url = "https://opensource.org/licenses/MIT"
//                ),
//                contact = @org.eclipse.microprofile.openapi.annotations.info.Contact(
//                        name = "BMS administrator",
//                        email = "contact@email.example.com"
//                )
//        ),
        info = @org.eclipse.microprofile.openapi.annotations.info.Info(
                title = "Baseball Management System",
                version = "1.0.0"
        ),
        // Or setting quarkus.smallrye-openapi.auto-add-server to true in application.properties
        servers = {
                @Server(url = "http://localhost:8080", description = "Local server"),
                @Server(url = "http://192.168.0.119:8080", description = "A remote server")
        },
        tags = {
                @Tag(name = BMS_BASIC_API, description = BMS_COMMON_APIS_FOR_AUTHENTICATED_USERS),
                @Tag(name = BMS_GUEST_API, description = BMS_GUEST_APIS_FOR_AUTHENTICATED_USERS)
        }
)
public class OpenApiApplication extends Application {
}
