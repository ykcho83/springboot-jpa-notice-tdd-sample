package com.ykcho.base.common.constants;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ResultCodeTest {

    @Test
    void getCode() {
        assertEquals(0, ResultCode.OK.getCode());
    }

    @Test
    void getDescription() {
        assertEquals("OK", ResultCode.OK.getDescription());
    }

    @Test
    void values() {
        assertNotNull(ResultCode.values());
    }

    @Test
    void valueOf() {
        assertEquals(ResultCode.OK, ResultCode.valueOf("OK"));
    }
}
