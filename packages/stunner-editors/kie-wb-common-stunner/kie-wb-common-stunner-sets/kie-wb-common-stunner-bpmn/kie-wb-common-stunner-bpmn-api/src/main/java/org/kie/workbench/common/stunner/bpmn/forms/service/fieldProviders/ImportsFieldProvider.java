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

package org.kie.workbench.common.stunner.bpmn.forms.service.fieldProviders;

import javax.enterprise.inject.Model;
import javax.inject.Singleton;

import org.kie.workbench.common.forms.fields.shared.fieldTypes.basic.BasicTypeFieldProvider;
import org.kie.workbench.common.forms.model.TypeInfo;
import org.kie.workbench.common.stunner.bpmn.definition.property.diagram.imports.ImportsValue;
import org.kie.workbench.common.stunner.bpmn.forms.model.ImportsFieldDefinition;
import org.kie.workbench.common.stunner.bpmn.forms.model.ImportsFieldType;

@Model
@Singleton
public class ImportsFieldProvider extends BasicTypeFieldProvider<ImportsFieldDefinition> {

    @Override
    public int getPriority() {
        return 2512;
    }

    @Override
    protected void doRegisterFields() {
        registerPropertyType(ImportsValue.class);
    }

    @Override
    public ImportsFieldDefinition createFieldByType(TypeInfo typeInfo) {
        return getDefaultField();
    }

    @Override
    public Class<ImportsFieldType> getFieldType() {
        return ImportsFieldType.class;
    }

    @Override
    public String getFieldTypeName() {
        return ImportsFieldDefinition.FIELD_TYPE.getTypeName();
    }

    @Override
    public ImportsFieldDefinition getDefaultField() {
        return new ImportsFieldDefinition();
    }
}