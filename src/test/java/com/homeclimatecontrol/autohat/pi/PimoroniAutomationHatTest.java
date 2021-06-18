package com.homeclimatecontrol.autohat.pi;

import com.homeclimatecontrol.autohat.AutomationHAT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Test the driver functionality.
 *
 * @author Copyright &copy; <a href="mailto:vt@homeclimatecontrol.com">Vadim Tkachenko</a> 2019-2021
 */
@EnabledIfSystemProperty(named = "java.vm.vendor", matches = "Raspbian", disabledReason = "Not Raspberry Pi?")
@EnabledIfSystemProperty(named = "os.arch", matches = "arm", disabledReason = "Not Raspberry Pi?")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PimoroniAutomationHatTest {

    private final Logger logger = LogManager.getLogger();

    @Test
    @Order(1)
    @EnabledIfEnvironmentVariable(
            named = "DZ_TEST_HAT",
            matches = "safe",
            disabledReason = "Only execute this test if there is no sensitive hardware connected"
    )
    void changeRelayState() throws IOException, InterruptedException {

        AutomationHAT hat = PimoroniAutomationHAT.getInstance();

        logger.info("obtained instance");
        assertThat(hat.relay().get(0).read()).isEmpty();

        assertThat(hat.relay().get(0).write(true)).isTrue();
        logger.info("wrote 1");

        Thread.sleep(100); // NOSONAR Not worth the effort

        assertThat(hat.relay().get(0).write(false)).isTrue();
        logger.info("wrote 0");

        assertThat(hat.relay().get(0).write(false)).isFalse();
        logger.info("wrote 0 again");
    }

    /**
     * Careful, this test will affect the actual hardware state, and you don't want it to run on the HAT that is already
     * connected to your expensive hardware. Hence it has to be specifically enabled.
     *
     * @throws IOException if there's a problem talking to the actual hardware.
     */
    @Test
    @Order(2)
    @EnabledIfEnvironmentVariable(
            named = "DZ_TEST_HAT",
            matches = "safe",
            disabledReason = "Only execute this test if there is no sensitive hardware connected"
    )
    void cycleRelays() throws IOException {

        assertThatCode(() -> {

            AutomationHAT hat = PimoroniAutomationHAT.getInstance();

            logger.info("setting all relays to ON");

            hat.relay().get(0).write(true);
            hat.relay().get(1).write(true);
            hat.relay().get(2).write(true);

            assertThat(hat.relay().get(0).read().get()).isTrue();
            assertThat(hat.relay().get(1).read().get()).isTrue();
            assertThat(hat.relay().get(2).read().get()).isTrue();

            Thread.sleep(1000); // NOSONAR Not worth the effort

            logger.info("setting all relays to OFF");

            hat.relay().get(0).write(false);
            hat.relay().get(1).write(false);
            hat.relay().get(2).write(false);

            assertThat(hat.relay().get(0).read().get()).isFalse();
            assertThat(hat.relay().get(1).read().get()).isFalse();
            assertThat(hat.relay().get(2).read().get()).isFalse();

            logger.info("done");

        }).doesNotThrowAnyException();
    }

    @Test
    @Order(3)
    @EnabledIfEnvironmentVariable(
            named = "DZ_TEST_HAT",
            matches = "safe",
            disabledReason = "Only execute this test if there is no sensitive hardware connected"
    )
    void setOutputs() {

        assertThatCode(() -> {

            AutomationHAT hat = PimoroniAutomationHAT.getInstance();

            hat.output().get(0).write(true);
            hat.output().get(1).write(true);
            hat.output().get(2).write(true);

            Thread.sleep(1000); // NOSONAR Not worth the effort

        }).doesNotThrowAnyException();
    }

    @Test
    @Order(4)
    void setStatusLights() {

        assertThatCode(() -> {

            AutomationHAT hat = PimoroniAutomationHAT.getInstance();

            hat.status().power().write(true);
            hat.status().comms().write(true);
            hat.status().warn().write(true);

            Thread.sleep(1000); // NOSONAR Not worth the effort

        }).doesNotThrowAnyException();
    }

    @Test
    @Order(5)
    @EnabledIfEnvironmentVariable(
            named = "DZ_TEST_HAT",
            matches = "safe",
            disabledReason = "Only execute this test if there is no sensitive hardware connected"
    )
    void reset() {

        assertThatCode(() -> {

            PimoroniAutomationHAT.getInstance().close();

        }).doesNotThrowAnyException();
    }
}
