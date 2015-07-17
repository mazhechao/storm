/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.storm.solr.trident;

import backtype.storm.task.IMetricsContext;
import org.apache.storm.solr.config.SolrCommitStrategy;
import org.apache.storm.solr.config.SolrConfig;
import org.apache.storm.solr.mapper.SolrMapper;
import storm.trident.state.State;
import storm.trident.state.StateFactory;

import java.util.Map;

public class SolrStateFactory implements StateFactory {
    private final SolrConfig solrConfig;
    private final SolrMapper solrMapper;

    public SolrStateFactory(SolrConfig solrConfig, SolrMapper solrMapper) {
        this.solrConfig = solrConfig;
        this.solrMapper = solrMapper;
    }

    @Override
    public State makeState(Map map, IMetricsContext iMetricsContext, int partitionIndex, int numPartitions) {
        SolrState state = new SolrState(solrConfig, solrMapper);
        state.prepare();
        return state;
    }
}
