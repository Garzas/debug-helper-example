package com.appunite.example.debugutils;

import org.junit.Test;

import static com.google.common.truth.Truth.assert_;

public class DebugHelperTest {

    @Test
    public void checkIfTestsAreWorking() throws Exception {
        final DebugHelper debugHelper = new DebugHelper();

        assert_().that(debugHelper.isWorking()).isTrue();
    }
}
