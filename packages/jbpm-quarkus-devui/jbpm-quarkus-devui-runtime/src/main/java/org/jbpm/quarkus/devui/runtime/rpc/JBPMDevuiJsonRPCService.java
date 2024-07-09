/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.jbpm.quarkus.devui.runtime.rpc;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import io.smallrye.mutiny.Uni;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import jakarta.annotation.PostConstruct;
import org.eclipse.microprofile.config.ConfigProvider;
import org.jbpm.quarkus.devui.runtime.forms.FormsStorage;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class JBPMDevuiJsonRPCService {
    private static final String DATA_INDEX_URL = "kogito.data-index.url";

    private static final Logger LOGGER = LoggerFactory.getLogger(JBPMDevuiJsonRPCService.class);

    public static final String PROCESS_INSTANCES = "ProcessInstances";
    public static final String USER_TASKS = "UserTaskInstances";
    public static final String JOBS = "Jobs";

    public static final String ALL_TASKS_IDS_QUERY = "{ \"operationName\": \"getAllTasksIds\", \"query\": \"query getAllTasksIds{  UserTaskInstances{ id } }\" }";
    public static final String ALL_PROCESS_INSTANCES_IDS_QUERY = "{ \"operationName\": \"getAllProcessesIds\", \"query\": \"query getAllProcessesIds{  ProcessInstances{ id } }\" }";
    public static final String ALL_JOBS_IDS_QUERY = "{ \"operationName\": \"getAllJobsIds\", \"query\": \"query getAllJobsIds{  Jobs{ id } }\" }";

    private WebClient dataIndexWebClient;

    private final Vertx vertx;
    private final FormsStorage formsStorage;

    @Inject
    public JBPMDevuiJsonRPCService(Vertx vertx, FormsStorage formsStorage) {
        this.vertx = vertx;
        this.formsStorage = formsStorage;
    }

    @PostConstruct
    public void init() {
        Optional<String> dataIndexURL = ConfigProvider.getConfig().getOptionalValue(DATA_INDEX_URL, String.class);
        dataIndexURL.ifPresent(this::initDataIndexWebClient);
    }

    private void initDataIndexWebClient(String dataIndexURL) {
        try {
            this.dataIndexWebClient = WebClient.create(vertx, buildWebClientOptions(dataIndexURL));
        } catch (Exception ex) {
            LOGGER.warn("Cannot configure dataIndexWebClient with 'kogito.data-index.url'='{}':", dataIndexURL, ex);
        }
    }

    protected WebClientOptions buildWebClientOptions(String dataIndexURL) throws MalformedURLException {
        URL url = new URL(dataIndexURL);
        return new WebClientOptions()
                .setDefaultHost(url.getHost())
                .setDefaultPort((url.getPort() != -1 ? url.getPort() : url.getDefaultPort()))
                .setSsl(url.getProtocol().compareToIgnoreCase("https") == 0);
    }

    public Uni<String> queryProcessInstancesCount() {
        return doQuery(ALL_PROCESS_INSTANCES_IDS_QUERY, PROCESS_INSTANCES);
    }

    public Uni<String> queryTasksCount() {
        return doQuery(ALL_TASKS_IDS_QUERY, USER_TASKS);
    }

    public Uni<String> queryJobsCount() {
        return doQuery(ALL_JOBS_IDS_QUERY, JOBS);
    }

    private Uni<String> doQuery(String query, String graphModelName) {
        if(dataIndexWebClient == null) {
            LOGGER.warn("Cannot perform '{}' query, dataIndexWebClient couldn't be set. Is DataIndex correctly? Please verify '{}' value", graphModelName, DATA_INDEX_URL);
            return Uni.createFrom().item("-");
        }
        return Uni.createFrom().completionStage(this.dataIndexWebClient.post("/graphql")
                .putHeader("content-type", "application/json")
                .sendJson(new JsonObject(query))
                .map(response -> {
                    if(response.statusCode() == 200) {
                        JsonObject responseData = response.bodyAsJsonObject().getJsonObject("data");
                        return String.valueOf(responseData.getJsonArray(graphModelName).size());
                    }
                    return "-";
                }).toCompletionStage());
    }

    public Uni<String> getFormsCount() {
        return Uni.createFrom().item(String.valueOf(this.formsStorage.getFormsCount()));
    }
}
