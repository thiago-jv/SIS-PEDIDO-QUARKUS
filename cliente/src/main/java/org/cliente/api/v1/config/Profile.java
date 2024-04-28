/**
 * @author Thiago henrique on 28/04/2024
 */

package org.cliente.api.v1.config;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@Slf4j
@ApplicationScoped
public class Profile {

    @ConfigProperty(name = "profile.aplicacao", defaultValue = "DEFAULT")
    public String profile;

    void onStart(@Observes StartupEvent ev) {
        log.info("Iniciando no perfil: {}", profile);
    }

    void onStop(@Observes ShutdownEvent ev) {
        log.info("Finalizando aplicação...");
    }
}
