package com.homeclimatecontrol.autohat.pi;

import com.homeclimatecontrol.autohat.base.AbstractWriter;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiBcmPin;

import java.io.IOException;

/**
 * Hardware driver for controlling Pimoroni Automation HAT GPIO controlled switches.
 *
 * @author Copyright &copy; <a href="mailto:vt@homeclimatecontrol.com">Vadim Tkachenko</a> 2019-2021
 */
public abstract class HardwareSwitch<L> extends AbstractWriter<Boolean> {

    private final GpioPinDigitalOutput output;

    protected HardwareSwitch(GpioController gpio, int pin) {
        super(pin);
        output = gpio.provisionDigitalOutputPin(RaspiBcmPin.getPinByAddress(pin), PinState.LOW);
    }

    public abstract  L light();

    @Override
    protected void hardwareWrite(Boolean value) throws IOException {
        output.setState(value);
    }
}
