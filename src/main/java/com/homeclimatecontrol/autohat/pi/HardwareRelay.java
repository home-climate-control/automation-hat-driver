package com.homeclimatecontrol.autohat.pi;

import com.homeclimatecontrol.autohat.Light;
import com.homeclimatecontrol.autohat.Relay;
import com.pi4j.io.gpio.GpioController;

import java.io.IOException;
import java.util.List;

/**
 * Hardware driver for controlling Pimoroni Automation HAT onboard relays (along with two lights for NC and NO).
 *
 * @author Copyright &copy; <a href="mailto:vt@homeclimatecontrol.com">Vadim Tkachenko</a> 2019-2021
 */
public class HardwareRelay extends HardwareSwitch<List<Light>> implements Relay {

    private final Light lightNO;
    private final Light lightNC;

    public HardwareRelay(GpioController gpio, int pin, int lightPinNO, int lightPinNC) {
        super(gpio, pin);

        lightNO = new HardwareLight(lightPinNO);
        lightNC = new HardwareLight(lightPinNC);
    }

    @Override
    public List<Light> light() {
        return List.of(lightNO, lightNC);
    }

    @Override
    protected void hardwareWrite(Boolean value) throws IOException {
        super.hardwareWrite(value);
        lightNC.write(value);
        lightNO.write(!value);
    }
}
