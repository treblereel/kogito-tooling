/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
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

package org.kie.workbench.common.stunner.bpmn.definition.property.diagram;

import javax.validation.constraints.NotNull;

import io.crysknife.ui.databinding.client.api.Bindable;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.kie.workbench.common.forms.adf.definitions.annotations.metaModel.FieldDefinition;
import org.kie.workbench.common.forms.adf.definitions.annotations.metaModel.FieldValue;
import org.kie.workbench.common.forms.adf.definitions.annotations.metaModel.I18nMode;
import org.kie.workbench.common.stunner.bpmn.definition.BPMNProperty;
import org.kie.workbench.common.stunner.core.definition.annotation.Property;
import org.kie.workbench.common.stunner.core.definition.annotation.property.Value;

@Portable
@Bindable
@Property
@FieldDefinition(i18nMode = I18nMode.OVERRIDE_I18N_KEY)
public class ProcessType implements BPMNProperty {

    @Value
    @NotNull
    @NotEmpty
    @FieldValue
    private String value;

    private static final String DefaultValue = "Public";
    private static final String InvalidValue = "None";

    public ProcessType() {
        this(DefaultValue);
    }

    public ProcessType(String value) {
        if (value != null && value.equals(InvalidValue)) {
            value = DefaultValue;
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return (null != value) ? value.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ProcessType) {
            ProcessType other = (ProcessType) o;
            return (null != value) ? value.equals(other.value) : null == other.value;
        }
        return false;
    }
}
