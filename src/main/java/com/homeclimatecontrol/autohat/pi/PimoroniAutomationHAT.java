package com.homeclimatecontrol.autohat.pi;

import com.homeclimatecontrol.autohat.ADC24;
import com.homeclimatecontrol.autohat.ADC33;
import com.homeclimatecontrol.autohat.AutomationHAT;
import com.homeclimatecontrol.autohat.Input;
import com.homeclimatecontrol.autohat.Output;
import com.homeclimatecontrol.autohat.Relay;
import com.homeclimatecontrol.autohat.Status;
import com.homeclimatecontrol.autohat.Writer;
import com.homeclimatecontrol.autohat.base.StatusImpl;
import com.homeclimatecontrol.sn3218.SN3218;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiGpioProvider;
import com.pi4j.io.gpio.RaspiPinNumberingScheme;

import java.io.IOException;
import java.util.List;

/**
 * Implementation that actually talks to hardware.
 *
 * @author Copyright &copy; <a href="mailto:vt@homeclimatecontrol.com">Vadim Tkachenko</a> 2019-2021
 */
public class PimoroniAutomationHAT implements AutomationHAT {

    private static AutomationHAT theHAT = null;

    private final List<ADC24> adc24;
    private final List<Input> input;
    private final List<Output> output;
    private final List<Relay> relay;
    private final ADC33 adc33;
    private final Status status;

    /**
     * There Can Be Only One.
     *
     * @return The singleton Pimoroni Automation HAT driver instance.
     */
    public static synchronized  AutomationHAT getInstance() throws IOException {

        if (theHAT == null) {
            theHAT = new PimoroniAutomationHAT();
        }

        return theHAT;
    }

    private PimoroniAutomationHAT() throws IOException {

        var gpio = GpioFactory.getInstance();
        GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));

        var sn3218 = SN3218.getInstance();
        sn3218.enable(true);
        sn3218.enableLEDs(SN3218.ALL_LEDS);

        adc24 = List.of(
                new HardwareADC24(gpio, 0, 0),
                new HardwareADC24(gpio, 1, 1),
                new HardwareADC24(gpio, 2, 2)
        );

        input = List.of(
                new HardwareInput(gpio, 26, 14),
                new HardwareInput(gpio, 20, 13),
                new HardwareInput(gpio, 21, 12)
        );

        output = List.of(
                new HardwareOutput(gpio, 5, 3),
                new HardwareOutput(gpio, 12, 4),
                new HardwareOutput(gpio, 6, 5)
        );

        relay = List.of(
                new HardwareRelay(gpio, 13, 6, 7),
                new HardwareRelay(gpio, 19, 8, 9),
                new HardwareRelay(gpio, 16, 10, 11)
        );

        adc33 = new HardwareADC33(gpio, 3);

        status = new StatusImpl(
                new HardwareLight(17),
                new HardwareLight(16),
                new HardwareLight(15)
        );
    }

    @Override
    public List<ADC24> adc24() {
        return adc24;
    }

    @Override
    public List<Input> input() {
        return input;
    }

    @Override
    public List<Output> output() {
        return output;
    }

    @Override
    public List<Relay> relay() {
        return relay;
    }

    @Override
    public ADC33 adc33() {
        return adc33;
    }

    @Override
    public Status status()  {
        return status;
    }

    @Override
    public Writer<Double> intensity() {
        throw new IllegalStateException("Not Implemented");
    }

    /**
     * Shut down everything that we can.
     *
     * @throws Exception if things go sour.
     */
    @Override
    public void close() throws Exception {

        relay().get(0).write(false);
        relay().get(1).write(false);
        relay().get(2).write(false);

        output().get(0).write(false);
        output().get(1).write(false);
        output().get(2).write(false);

        // Some lights are still on (in particular, relays). Let's just shut them off completely, with a shortcut
        SN3218.getInstance().output(new byte[SN3218.LED_COUNT]);
    }
}
