/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
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

package org.kie.workbench.common.stunner.forms.model;

import javax.validation.constraints.Pattern;

import io.crysknife.ui.databinding.client.api.Bindable;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.kie.workbench.common.forms.fields.shared.AbstractFieldDefinition;
import org.kie.workbench.common.forms.model.FieldDefinition;
import org.kie.workbench.common.stunner.forms.meta.definition.ColorPicker;

@Portable
@Bindable
public class ColorPickerFieldDefinition extends AbstractFieldDefinition {

    public static final ColorPickerFieldType FIELD_TYPE = new ColorPickerFieldType();
    public static final String COLOR_REGEXP = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";

    @ColorPicker
    @Pattern(regexp = COLOR_REGEXP, message = "Invalid color code")
    private String defaultValue;

    public ColorPickerFieldDefinition() {
        super(String.class.getName());
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    protected void doCopyFrom(FieldDefinition other) {
        if (other instanceof ColorPickerFieldDefinition) {
            this.setDefaultValue(((ColorPickerFieldDefinition) other).getDefaultValue());
        }
    }

    @Override
    public ColorPickerFieldType getFieldType() {
        return FIELD_TYPE;
    }
}
