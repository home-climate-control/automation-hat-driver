package com.homeclimatecontrol.autohat.pi;

import com.homeclimatecontrol.autohat.Light;
import com.homeclimatecontrol.autohat.Writer;
import com.homeclimatecontrol.autohat.base.AbstractWriter;
import com.homeclimatecontrol.sn3218.SN3218;

import java.io.IOException;
import java.util.Optional;

/**
 * Hardware driver for controlling Pimoroni Automation HAT onboard lights.
 *
 * @author Copyright &copy; <a href="mailto:vt@homeclimatecontrol.com">Vadim Tkachenko</a> 2019-2021
 */
public class HardwareLight extends AbstractWriter<Boolean> implements Light {

    /**
     * LED intensity.
     *
     * Default is set to {@code 0x22} because these LEDs are extremely bright and can trigger seizures and migraines.
     */
    private byte intensity = (byte) 0x22;

    private Boolean lastValue = null;

    public HardwareLight(int pin) {
        super(pin);
    }

    @Override
    public Writer<Byte> intensity() {
        return new Writer<Byte>() {

            @Override
            public boolean write(Byte value) throws IOException {

                var changed = value == intensity;

                intensity = value;

                if (lastValue != null) {
                    hardwareWrite(lastValue);
                }

                return changed;
            }

            @Override
            public Optional<Byte> read() throws IOException {
                return Optional.of(intensity);
            }
        };
    }

    @Override
    public void hardwareWrite(Boolean value) throws IOException {

        if (value == null) {
            // The only reason it is Boolean and not boolean is generics
            throw new IllegalArgumentException("value can't be null");
        }

        // It is assumed that SN3218 is already initialized and all LEDs are enabled
        SN3218.getInstance().setLED(pin, value ? intensity : 0);

        lastValue = value;
    }
}
