package com.homeclimatecontrol.autohat;

/**
 * Pimoroni Automation HAT "Input" feature.
 *
 * @author Copyright &copy; <a href="mailto:vt@homeclimatecontrol.com">Vadim Tkachenko</a> 2019-2021
 */
public interface Input extends Reader<Boolean> {
    Light light();
}
