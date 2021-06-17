package com.homeclimatecontrol.autohat.base;

import com.homeclimatecontrol.autohat.Writer;
import com.homeclimatecontrol.autohat.pi.Pinnable;

import java.io.IOException;
import java.util.Optional;

/**
 * A generic writer abstraction.
 *
 * @param <T> Value type.
 *
 * @author Copyright &copy; <a href="mailto:vt@homeclimatecontrol.com">Vadim Tkachenko</a> 2019-2021
 */
public abstract class AbstractWriter<T> extends Pinnable implements Writer<T> {

    private T value = null;

    protected AbstractWriter(int pin) {
        super(pin);
    }

    @Override
    public final synchronized boolean write(T value) throws IOException {

        hardwareWrite(value);

        var diff = value.equals(this.value);
        this.value = value;

        return !diff;
    }

    protected abstract void hardwareWrite(T value) throws IOException;

    @Override
    public final synchronized Optional<T> read() throws IOException {
        return Optional.ofNullable(value);
    }
}
