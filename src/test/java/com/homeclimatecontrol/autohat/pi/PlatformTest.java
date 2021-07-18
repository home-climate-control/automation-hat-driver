package com.homeclimatecontrol.autohat.pi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Dump the properties required for {@link PimoroniAutomationHatTest} into the log
 * so that new platforms can be diagnosed.
 *
 * @author Copyright &copy; <a href="mailto:vt@homeclimatecontrol.com">Vadim Tkachenko</a> 2019-2021
 */
class PlatformTest {

    private final Logger logger = LogManager.getLogger();

    @Test
    void platformProperties() {

        var systemProperties = List.of(
                "java.vm.vendor",
                "os.arch"
        );

        var environmentVariables = List.of("TEST_AUTOMATION_HAT");

        assertThatCode(() -> {

            for (var entry : systemProperties) {
                log(entry, System.getProperty(entry));
            }

            for (var entry : environmentVariables) {
                log(entry, System.getenv(entry));
            }

        }).doesNotThrowAnyException();
    }

    private void log(String key, String value) {
        logger.info("{}={}", key, value);
    }
}
