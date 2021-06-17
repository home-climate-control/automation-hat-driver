package com.homeclimatecontrol.autohat.pi;

import com.homeclimatecontrol.autohat.Light;
import com.homeclimatecontrol.autohat.Writer;
import com.homeclimatecontrol.autohat.base.AbstractWriter;
import com.homeclimatecontrol.sn3218.SN3218;

import java.io.IOException;

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

    public HardwareLight(int pin) {
        super(pin);
    }

    @Override
    public Writer<Double> intensity() {
        throw new IllegalStateException("Not Implemented");
    }

    @Override
    public void hardwareWrite(Boolean value) throws IOException {

        // It is assumed that SN3218 is already initialized and all LEDs are enabled
        SN3218.getInstance().setLED(pin, value ? intensity : 0);
    }
}
