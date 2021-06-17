package com.homeclimatecontrol.sn3218;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * @author Copyright &copy; <a href="mailto:vt@homeclimatecontrol.com">Vadim Tkachenko</a> 2019-2021
 */
@EnabledIfSystemProperty(named = "java.vm.vendor", matches = "Raspbian", disabledReason = "Not Raspberry Pi?")
@EnabledIfSystemProperty(named = "os.arch", matches = "arm", disabledReason = "Not Raspberry Pi?")
class SN3218Test {

    private static SN3218 sn3218;

    @BeforeAll
    static void setUp() throws IOException, InterruptedException {

        assertThatCode(() -> {

            sn3218 = SN3218.getInstance();
            sn3218.enable(true);
            sn3218.enableLEDs(SN3218.ALL_LEDS);

        }).doesNotThrowAnyException();
    }

    @AfterAll
    static void tearDown() throws Exception {
        sn3218.close();
    }

    /**
     * Cycle all LEDs on and off one by one.
     */
    @Test
    void cycle() throws IOException, InterruptedException {

        assertThatCode(() -> {

            var intensity = (byte) 0x22;

            for (var channel = 0; channel < SN3218.LED_COUNT; channel++) {
                sn3218.setLED(channel, intensity);
                Thread.sleep(10);
            }

            Thread.sleep(400);

            for (var channel = 0; channel < SN3218.LED_COUNT; channel++) {
                sn3218.setLED(channel, (byte) 0);
                Thread.sleep(10);
            }

            Thread.sleep(100);

        }).doesNotThrowAnyException();
    }

    /**
     * Cycle intensity on all LEDs from zero to max and back at the same time.
     */
    @Test
    void intensity() throws IOException, InterruptedException {

        assertThatCode(() -> {

            var values = new byte[SN3218.LED_COUNT];

            // All the way to 0xFF would just cause a migraine
            var max = 0x33;
            for (var intensity = 0; intensity < max; intensity++) {

                for (var channel = 0; channel < SN3218.LED_COUNT; channel++) {
                    values[channel] = (byte) intensity;
                }

                sn3218.output(values);
                Thread.sleep(2);
            }

            Thread.sleep(400);

            for (var intensity = max; intensity >= 0; intensity--) {

                for (var channel = 0; channel < SN3218.LED_COUNT; channel++) {
                    values[channel] = (byte) intensity;
                }

                sn3218.output(values);
                Thread.sleep(2);
            }

            Thread.sleep(100);

        }).doesNotThrowAnyException();
    }
}
