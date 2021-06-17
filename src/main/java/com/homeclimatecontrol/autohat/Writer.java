package com.homeclimatecontrol.autohat;

import java.io.IOException;

/**
 * A writer.
 *
 * Every writer is also a reader for the same type.
 *
 * @param <T> Value type.
 *
 * @author Copyright &copy; <a href="mailto:vt@homeclimatecontrol.com">Vadim Tkachenko</a> 2019-2021
 */
public interface Writer<T> extends Reader<T> {

    /**
     * Write the value.
     *
     * @param value Value to write.
     * @return {@code true} if the value written is NOT the same as already present, {@code false} otherwise.
     * @throws IOException if there was a problem communicating with hardware.
     */
    boolean write(T value) throws IOException;
}
