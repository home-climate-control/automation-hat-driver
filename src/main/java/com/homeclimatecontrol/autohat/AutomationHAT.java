package com.homeclimatecontrol.autohat;

import java.util.List;

/**
 * Pimoroni Automation HAT API.
 *
 * <a href="https://learn.pimoroni.com/tutorial/sandyj/getting-started-with-automation-hat-and-phat">Documentation</a>
 *
 * @author Copyright &copy; <a href="mailto:vt@homeclimatecontrol.com">Vadim Tkachenko</a> 2019-2021
 */
public interface AutomationHAT extends AutoCloseable {

    List<ADC24> adc24();
    List<Input> input();
    List<Output> output();
    List<Relay> relay();
    ADC33 adc33();
    Status status();

    /**
     * Control LED intensity for all LEDs.
     *
     * This will override all individual intensity settings that may have been previously applied.
     *
     * @return Writer to control this LED intensity. {@code 0} is off, {@code 255} is full intensity (VERY bright).
     * For simplicity, if any individual LED intensity is changed afterwards via {@link Light#intensity()}, the value
     * returned by this reader is unaffected.
     */
    Writer<Byte> intensity();
}
