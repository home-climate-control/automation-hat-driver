package com.homeclimatecontrol.autohat;

import java.util.List;

/**
 * Pimoroni Automation HAT relay abstraction (along with two lights for NC and NO).
 *
 * @author Copyright &copy; <a href="mailto:vt@homeclimatecontrol.com">Vadim Tkachenko</a> 2019-2021
 */
public interface Relay extends Switch<List<Light>> {
}
