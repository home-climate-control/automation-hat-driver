package com.homeclimatecontrol.autohat.base;

import com.homeclimatecontrol.autohat.Light;
import com.homeclimatecontrol.autohat.Status;

/**
 * Pimoroni Automation HAT "status lights" implementation.
 *
 * @author Copyright &copy; <a href="mailto:vt@homeclimatecontrol.com">Vadim Tkachenko</a> 2019-2021
 */
public class StatusImpl implements Status {

    private final Light power;
    private final Light comms;
    private final Light warn;

    public StatusImpl(Light power, Light comms, Light warn) {
        this.power = power;
        this.comms = comms;
        this.warn = warn;
    }

    @Override
    public Light power() {
        return power;
    }

    @Override
    public Light comms() {
        return comms;
    }

    @Override
    public Light warn() {
        return warn;
    }
}
