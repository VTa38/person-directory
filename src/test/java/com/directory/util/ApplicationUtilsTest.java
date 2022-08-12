package com.directory.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationUtilsTest {

    @Test
    void isEmptyString() {
        // given
        String test = "notNull";

        // when
        boolean isFind = ApplicationUtils.isEmpty(test);

        // then
        assertThat(isFind).isFalse();
    }

    @Test
    void isEmptyNullString() {
        // given
        String test = null;

        // when
        boolean isFind = ApplicationUtils.isEmpty(test);

        // then
        assertThat(isFind).isTrue();
    }

    @Test
    void isEmptyLong() {
        // given
        Long test = 123456L;

        // when
        boolean isFind = ApplicationUtils.isEmpty(test);

        // then
        assertThat(isFind).isFalse();
    }

    @Test
    void isEmptyNullLong() {
        // given
        Long test = null;

        // when
        boolean isFind = ApplicationUtils.isEmpty(test);

        // then
        assertThat(isFind).isTrue();
    }
}