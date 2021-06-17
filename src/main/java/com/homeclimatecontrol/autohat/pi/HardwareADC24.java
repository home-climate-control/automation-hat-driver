package com.homeclimatecontrol.autohat.pi;

import com.homeclimatecontrol.autohat.ADC24;
import com.homeclimatecontrol.autohat.Light;
import com.pi4j.io.gpio.GpioController;

public class HardwareADC24 extends HardwareADC implements ADC24 {

    private final Light light;
    public HardwareADC24(GpioController gpio, int pin, int lightPin) {
        super(gpio, pin);
        light = new HardwareLight(lightPin);
    }

    @Override
    public Light light() {
        return light;
    }
}
