package com.homeclimatecontrol.autohat;

import java.io.IOException;
import java.util.Optional;

/**
 * Generic reader abstraction.
 *
 * @param <T> Result type.
 *
 * @author Copyright &copy; <a href="mailto:vt@homeclimatecontrol.com">Vadim Tkachenko</a> 2019-2021
 */
public interface Reader<T> {

    /**
     * Read the value.
     *
     * @return The value read, or {@code Optional.empty} if the value can't be read (yet; this may be the case with
     * {@link Writer writers} if {@link Writer#write(Object)} method hasn't been called yet and the state is
     * undefined).
     *
     * @throws IOException if there was a problem communicating with hardware.
     */
    Optional<T> read() throws IOException;
}
