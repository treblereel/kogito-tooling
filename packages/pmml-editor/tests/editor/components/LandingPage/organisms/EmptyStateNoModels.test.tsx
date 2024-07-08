/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
import { render } from "@testing-library/react";
import * as React from "react";
import { EmptyStateNoModels } from "@kie-tools/pmml-editor/dist/editor/components/LandingPage/organisms";

const createModel = jest.fn(() => {
  /*NOP*/
});

describe("EmptyStateNoModels", () => {
  test("render", () => {
    const { getByTestId } = render(<EmptyStateNoModels createModel={createModel} />);
    expect(getByTestId("empty-state-no-models")).toMatchSnapshot();
  });

  test("render::createModel", async () => {
    const { getByTestId } = render(<EmptyStateNoModels createModel={createModel} />);
    const element: HTMLElement = await getByTestId("empty-state-no-models__create-model");
    expect(element).toBeInstanceOf(HTMLButtonElement);

    (element as HTMLButtonElement).click();
    expect(createModel).toHaveBeenCalledTimes(1);
  });
});
