package com.homeclimatecontrol.autohat;

/**
 * A generic switch abstraction
 *
 * @param <T> Light type. There is either one (in {@link Output}) or two (in {@link Relay}).
 *
 * @author Copyright &copy; <a href="mailto:vt@homeclimatecontrol.com">Vadim Tkachenko</a> 2019-2021
 */
public interface Switch<T> extends Writer<Boolean> {
    T light();
}
