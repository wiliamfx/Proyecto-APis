package pe.edu.idat.managment_customer.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;

public class OpenApiConfig {

    public OpenAPI getOpenAPI() {
        return new OpenAPI().info(
                new Info()
                        .title("Customer")
                        .version("1.0")
                        .description("Customer API"));

    }
}
