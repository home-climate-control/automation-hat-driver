package com.homeclimatecontrol.autohat;

/**
 * Pimoroni Automation HAT "status lights" abstraction.
 *
 * @author Copyright &copy; <a href="mailto:vt@homeclimatecontrol.com">Vadim Tkachenko</a> 2019-2021
 */
public interface Status {
    Light power();
    Light comms();
    Light warn();
}
