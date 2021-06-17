package com.homeclimatecontrol.autohat.pi;

import com.homeclimatecontrol.autohat.Input;
import com.homeclimatecontrol.autohat.Light;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.RaspiBcmPin;

import java.io.IOException;
import java.util.Optional;

public class HardwareInput extends Pinnable implements Input {

    private final GpioPinDigitalInput input;
    private final Light light;

    public HardwareInput(GpioController gpio, int pin, int lightPin) {
        super(pin);
        input = gpio.provisionDigitalInputPin(RaspiBcmPin.getPinByAddress(pin));
        light = new HardwareLight(lightPin);
    }

    @Override
    public Light light() {
        return light;
    }

    @Override
    public Optional<Boolean> read() throws IOException {
        return Optional.of(input.isHigh());
    }
}
