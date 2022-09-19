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

import jakarta.json.bind.annotation.JsonbTypeDeserializer;
import jakarta.json.bind.annotation.JsonbTypeSerializer;
import org.kie.workbench.common.stunner.client.json.mapper.annotation.JSONMapper;
import org.kie.workbench.common.stunner.sw.definition.custom.ArgumentsValueHolderJsonbTypeSerializer;
import org.kie.workbench.common.stunner.sw.definition.custom.ValueHolderJsonbTypeDeserializer;

@JSONMapper
public class FunctionRef {

    private String refName;
    private String selectionSet;
    private FunctionRefType invoke;

    @JsonbTypeSerializer(ArgumentsValueHolderJsonbTypeSerializer.class)
    @JsonbTypeDeserializer(ValueHolderJsonbTypeDeserializer.class)
    private ValueHolder arguments;

    public String getRefName() {
        return refName;
    }

    public void setRefName(String refName) {
        this.refName = refName;
    }

    public String getSelectionSet() {
        return selectionSet;
    }

    public void setSelectionSet(String selectionSet) {
        this.selectionSet = selectionSet;
    }

    public FunctionRefType getInvoke() {
        return invoke;
    }

    public void setInvoke(FunctionRefType invoke) {
        this.invoke = invoke;
    }

    public ValueHolder getArguments() {
        return arguments;
    }

    public void setArguments(ValueHolder arguments) {
        this.arguments = arguments;
    }
}
