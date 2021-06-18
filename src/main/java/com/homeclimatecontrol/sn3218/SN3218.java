package com.homeclimatecontrol.sn3218;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

import java.io.IOException;

/**
 * SN3218 18 Channel LED Driver Driver.
 *
 * <a href="https://github.com/pimoroni/sn3218/blob/master/datasheets/sn3218-datasheet.pdf">Datasheet</a>
 *
 * Loosely based on <a href="https://github.com/climategadgets/mqtt-automation-hat-go/blob/master/src/sn3218/sn3218.go">Go implementation</a>
 * from <a href="https://github.com/climategadgets/mqtt-automation-hat-go">mqtt-automation-hat-go</a>.
 *
 * @author Copyright &copy; <a href="mailto:vt@homeclimatecontrol.com">Vadim Tkachenko</a> 2019-2021
 */
public class SN3218 implements AutoCloseable {

    private static SN3218 instance = null;

    private static final int SN3218_I2C_ADDRESS = 0x54;

    /**
     * Number of LEDs this controller supports.
     */
    public static final int LED_COUNT = 18;

    /**
     * Mask to enable all the LEDs.
     *
     * @see #enableLEDs(int)
     */
    public static final int ALL_LEDS = 0x3FFFF;

    interface Command { // NOSONAR Consequences have been considered.
        byte ENABLE_OUTPUT = (byte) 0x00;
        byte SET_PWM_VALUES = (byte) 0x01;
        byte ENABLE_LEDS = (byte) 0x13;
        byte UPDATE = (byte) 0x16;
        byte RESET = (byte) 0x17;
    }

    private final byte[] values = new byte[18];
    private final byte[][] gamma = new byte[18][];

    private final I2CDevice device;

    public static synchronized SN3218 getInstance() throws IOException {

        if (instance == null) {
            instance = new SN3218();
        }

        return instance;
    }

    private SN3218() throws IOException {

        try {

            device = I2CFactory.getInstance(I2CBus.BUS_1).getDevice(SN3218_I2C_ADDRESS);

        } catch (I2CFactory.UnsupportedBusNumberException ex) {
            throw new IllegalStateException(
                    "Check if your I2C bus is enabled (use raspi-config 'Interface Options')",
                    ex);
        }
    }

    public void reset() throws IOException {

        for (var channel = 0; channel < LED_COUNT; channel++) {
            values[channel] = 0x00;
            setChannelGamma(channel, null);
        }

        device.write(Command.RESET, new byte[] { (byte) 0xFF });
    }

    public void enable(boolean enable) throws IOException {
        device.write(Command.ENABLE_OUTPUT, new byte[] { enable ? (byte) 0x01 : (byte) 0x00 });
    }

    public void enableLEDs(int mask) throws IOException {
        device.write(Command.ENABLE_LEDS, new byte[] {(byte) (mask & 0x3F), (byte) ((mask >> 6) & 0x3F), (byte) ((mask >> 12) & 0x3F)});
        device.write(Command.UPDATE, new byte[] { (byte) 0xFF });
    }

    public byte[] getChannelGamma(int channel) {
        return gamma[channel];
    }

    public void setChannelGamma(int channel, byte[] gamma) {
        this.gamma[channel] = gamma;
    }

    public void output(byte[] values) throws IOException {

        if (values == null || values.length != LED_COUNT) {
            throw new IllegalArgumentException("values can't be null or be size other than " + LED_COUNT);
        }

        var mapped = new byte[LED_COUNT];

        for (var channel = 0; channel < LED_COUNT; channel++) {

            this.values[channel] = values[channel];

            mapped[channel] = gamma[channel] == null ? values[channel] : gamma[channel][values[channel]];
        }

        device.write(Command.SET_PWM_VALUES, mapped);
        device.write(Command.UPDATE, new byte[] { (byte) 0xFF });
    }

    public byte getLED(int channel) {
        checkChannel(channel);
        return values[channel];
    }

    public void setLED(int channel, byte intensity) throws IOException {
        checkChannel(channel);
        values[channel] = intensity;

        output(values);
    }

    @Override
    public void close() throws Exception {
        reset();
    }

    private void checkChannel(int channel) {
        if (channel < 0 || channel >= LED_COUNT) {
            throw new IllegalArgumentException("channel must be between 0 and " + LED_COUNT + ", " + channel + " given");
        }

    }
}
