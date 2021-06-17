package com.homeclimatecontrol.autohat;

/**
 * Generic Analog To Digital Converter abstraction.
 *
 * @author Copyright &copy; <a href="mailto:vt@homeclimatecontrol.com">Vadim Tkachenko</a> 2019-2021
 */
public interface ADC extends Reader<Double> {

    /**
     * Get the max voltage returned by this specific ADC type.
     *
     * @return Max voltage supported by the hardware.
     */
    double maxVoltage();
}
