package com.homeclimatecontrol.autohat.pi;

import com.homeclimatecontrol.autohat.ADC;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinAnalogInput;

import java.io.IOException;
import java.util.Optional;

public abstract class HardwareADC extends Pinnable implements ADC {

    private final GpioPinAnalogInput input;

    protected HardwareADC(GpioController gpio, int pin) { // NOSONAR, known issue
        super(pin);
        // input = gpio.provisionAnalogInputPin(RaspiBcmPin.getPinByAddress(pin)); // NOSONAR, known issue
        input = null;
    }

    @Override
    public Optional<Double> read() throws IOException {
        return Optional.of(input.getValue());
    }
}
