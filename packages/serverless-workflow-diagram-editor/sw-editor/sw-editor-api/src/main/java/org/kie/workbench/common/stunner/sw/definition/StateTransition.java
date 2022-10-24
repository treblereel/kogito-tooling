/*
 * Copyright 2022 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.stunner.sw.definition;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import org.kie.workbench.common.stunner.client.json.mapper.annotation.JSONMapper;

@JSONMapper
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class StateTransition {

    private String nextState;
    private Boolean compensate;

    @JsOverlay
    public final String getNextState() {
        return nextState;
    }

    @JsOverlay
    public final void setNextState(String nextState) {
        this.nextState = nextState;
    }

    @JsOverlay
    public final Boolean getCompensate() {
        return compensate;
    }

    @JsOverlay
    public final void setCompensate(Boolean compensate) {
        this.compensate = compensate;
    }
}
