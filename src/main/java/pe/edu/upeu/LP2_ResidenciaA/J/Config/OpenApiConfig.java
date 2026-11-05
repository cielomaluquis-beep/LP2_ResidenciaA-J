package pe.edu.upeu.LP2_ResidenciaA.J.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Api Residencia_A&J - LP2 ")
                        .version("1.0")
                        .description("Documentacion de servicios para el cliente")
                );
    }
}
