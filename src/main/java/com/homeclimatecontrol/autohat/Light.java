package com.homeclimatecontrol.autohat;

/**
 * An abstraction to control Pimoroni Automation HAT onboard lights.
 *
 * @author Copyright &copy; <a href="mailto:vt@homeclimatecontrol.com">Vadim Tkachenko</a> 2019-2021
 */
public interface Light extends Writer<Boolean> {

    /**
     * Control individual LED intensity.
     *
     * @return Writer to control this LED intensity. {@code 0.0} is off, {@code 1.0} is full intensity (VERY bright).
     */
    Writer<Double> intensity();
}
