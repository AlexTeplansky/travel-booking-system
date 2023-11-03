package sk.stuba.fei.uim;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationPath("/")
@OpenAPIDefinition(
        info = @Info(
                title = "Car Microservices",
                version = "1.0",
                contact = @Contact(name = "Alex Teplansky", email = "alex.teplansky@gmail.com")
        ),

        tags = {
                @Tag(name = "api", description = "Public API"),
        }

)
public class CarMicroservice extends Application {
}
