/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
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

package org.kie.workbench.common.stunner.bpmn.definition.models.bpmn2;

import org.kie.workbench.common.stunner.bpmn.definition.BPMNCategories;
import org.kie.workbench.common.stunner.bpmn.definition.property.background.BackgroundSet;
import org.kie.workbench.common.stunner.bpmn.definition.property.dimensions.RectangleDimensionsSet;
import org.kie.workbench.common.stunner.bpmn.definition.property.font.FontSet;
import org.kie.workbench.common.stunner.bpmn.definition.property.simulation.SimulationSet;
import org.kie.workbench.common.stunner.bpmn.definition.property.variables.AdvancedData;
import org.kie.workbench.common.stunner.core.definition.annotation.definition.Category;
import org.kie.workbench.common.stunner.core.definition.annotation.morph.MorphBase;

@MorphBase(defaultType = ReusableSubprocess.class, targets = {BaseTask.class})
public abstract class BaseNonContainerSubprocess extends BaseSubprocess {

    @Category
    public static final transient String category = BPMNCategories.SUB_PROCESSES;

    public BaseNonContainerSubprocess(String name,
                                      String documentation,
                                      BackgroundSet backgroundSet,
                                      FontSet fontSet,
                                      RectangleDimensionsSet dimensionsSet,
                                      SimulationSet simulationSet,
                                      AdvancedData advancedData) {
        super(name, documentation, backgroundSet, fontSet, dimensionsSet, simulationSet, advancedData);
    }
}