package com.homeclimatecontrol.autohat.pi;

import com.homeclimatecontrol.autohat.ADC33;
import com.pi4j.io.gpio.GpioController;

public class HardwareADC33 extends HardwareADC implements ADC33 {
    public HardwareADC33(GpioController gpio, int pin) {
        super(gpio, pin);
    }
}
