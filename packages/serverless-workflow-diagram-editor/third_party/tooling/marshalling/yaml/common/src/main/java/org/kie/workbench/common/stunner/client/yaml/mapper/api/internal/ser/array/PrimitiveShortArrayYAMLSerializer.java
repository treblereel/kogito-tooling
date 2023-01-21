/*
 * Copyright 2023 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.stunner.client.yaml.mapper.api.internal.ser.array;

import org.kie.workbench.common.stunner.client.yaml.mapper.api.internal.ser.AbstractYAMLSerializer;
import org.kie.workbench.common.stunner.client.yaml.mapper.api.internal.ser.BaseNumberYAMLSerializer;
import org.kie.workbench.common.stunner.client.yaml.mapper.api.internal.ser.YAMLSerializationContext;
import org.kie.workbench.common.stunner.client.yaml.mapper.api.stream.YAMLSequenceWriter;
import org.kie.workbench.common.stunner.client.yaml.mapper.api.stream.YAMLWriter;
import org.kie.workbench.common.stunner.client.yaml.mapper.api.stream.impl.DefaultYAMLSequenceWriter;

/**
 * Default {@link AbstractYAMLSerializer} implementation for array of short.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public class PrimitiveShortArrayYAMLSerializer extends BasicArrayYAMLSerializer<short[]> {

  public static final PrimitiveShortArrayYAMLSerializer INSTANCE =
      new PrimitiveShortArrayYAMLSerializer();

  private final BaseNumberYAMLSerializer.ShortYAMLSerializer serializer =
      BaseNumberYAMLSerializer.ShortYAMLSerializer.INSTANCE;

  /** {@inheritDoc} */
  @Override
  protected boolean isEmpty(short[] value) {
    return null == value || value.length == 0;
  }

  /** {@inheritDoc} */
  @Override
  public void serialize(
      YAMLWriter writer, String propertyName, short[] values, YAMLSerializationContext ctx) {
    if (!ctx.isWriteEmptyYAMLArrays() && values.length == 0) {
      writer.nullValue(propertyName);
      return;
    }

    YAMLSequenceWriter yamlSequenceWriter = new DefaultYAMLSequenceWriter();
    for (short value : values) {
      serializer.serialize(yamlSequenceWriter, value, ctx);
    }
    writer.value(propertyName, yamlSequenceWriter.getWriter());
  }

  @Override
  public void serialize(YAMLSequenceWriter writer, short[] value, YAMLSerializationContext ctx) {
    for (short val : value) {
      serializer.serialize(writer, val, ctx);
    }
  }
}
