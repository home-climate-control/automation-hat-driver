package com.homeclimatecontrol.autohat;

/**
 * 12-bit ADC @ 0-3.3V (±2% accuracy).
 *
 * @author Copyright &copy; <a href="mailto:vt@homeclimatecontrol.com">Vadim Tkachenko</a> 2019-2021
 */
public interface ADC33 extends ADC {

    @Override
    default double maxVoltage() {
        return 3.3;
    }
}
