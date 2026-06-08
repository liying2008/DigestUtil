/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.crypto;

import java.security.spec.AlgorithmParameterSpec;

public class ParameterSpecAndIv {
    public AlgorithmParameterSpec parameterSpec;
    public byte[] iv;

    public ParameterSpecAndIv() {
    }

    public ParameterSpecAndIv(AlgorithmParameterSpec parameterSpec, byte[] iv) {
        this.parameterSpec = parameterSpec;
        this.iv = iv;
    }
}
