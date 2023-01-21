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

package org.kie.workbench.common.stunner.sw.definition.custom;

import java.lang.reflect.Type;

import jakarta.json.JsonValue;
import jakarta.json.bind.serializer.DeserializationContext;
import jakarta.json.bind.serializer.JsonbDeserializer;
import jakarta.json.bind.serializer.JsonbSerializer;
import jakarta.json.bind.serializer.SerializationContext;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.kie.workbench.common.stunner.client.json.mapper.internal.deserializer.StringJsonDeserializer;
import org.kie.workbench.common.stunner.client.json.mapper.internal.deserializer.array.ArrayJsonDeserializer;
import org.kie.workbench.common.stunner.client.json.mapper.internal.serializer.StringJsonSerializer;
import org.kie.workbench.common.stunner.client.json.mapper.internal.serializer.array.ArrayBeanJsonSerializer;
import org.kie.workbench.common.stunner.sw.definition.Event;
import org.kie.workbench.common.stunner.sw.definition.Event_JsonDeserializerImpl;
import org.kie.workbench.common.stunner.sw.definition.Event_JsonSerializerImpl;

public class EventJsonbTypeSerializer implements JsonbDeserializer<Object>, JsonbSerializer<Object> {
    private static final Event_JsonSerializerImpl serializer =
            Event_JsonSerializerImpl.INSTANCE;

    private static final StringJsonSerializer stringJsonSerializer = new StringJsonSerializer();

    private static final Event_JsonDeserializerImpl deserializer =
            Event_JsonDeserializerImpl.INSTANCE;

    private static final StringJsonDeserializer stringJsonDeserializer = new StringJsonDeserializer();

    @Override
    public void serialize(Object obj, JsonGenerator generator, SerializationContext ctx) {
        if (obj instanceof String) {
            stringJsonSerializer.serialize((String) obj, generator, ctx);
        } else if (obj instanceof Event[]) {
            new ArrayBeanJsonSerializer<>(serializer)
                    .serialize((Event[]) obj,
                             generator, ctx);
        }
    }

    @Override
    public Object deserialize(JsonParser parser, DeserializationContext ctx, Type rtType) {
        JsonValue value = parser.getValue();
        if(value != null) {
            if (value.getValueType() != JsonValue.ValueType.NULL) {
                if (value.getValueType() == JsonValue.ValueType.STRING) {
                    return stringJsonDeserializer.deserialize(value, ctx);
                } else if (value.getValueType() == JsonValue.ValueType.ARRAY) {
                    return new ArrayJsonDeserializer<>(deserializer, Event[]::new)
                            .deserialize(parser, ctx, rtType);
                }
            }
        }
        return null;
    }
}