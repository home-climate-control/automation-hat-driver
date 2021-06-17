package com.homeclimatecontrol.autohat.pi;

import com.homeclimatecontrol.autohat.Light;
import com.homeclimatecontrol.autohat.Output;
import com.pi4j.io.gpio.GpioController;

import java.io.IOException;

/**
 * Hardware driver for controlling Pimoroni Automation HAT outputs (along with a light).
 *
 * @author Copyright &copy; <a href="mailto:vt@homeclimatecontrol.com">Vadim Tkachenko</a> 2019-2021
 */
public class HardwareOutput extends HardwareSwitch<Light> implements Output {

    private final Light light;

    public HardwareOutput(GpioController gpio, int pin, int lightPin) {
        super(gpio, pin);
        light = new HardwareLight(lightPin);
    }

    @Override
    public Light light() {
        return light;
    }

    @Override
    protected void hardwareWrite(Boolean value) throws IOException {
        super.hardwareWrite(value);
        light.write(value);
    }
}
