/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
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

package com.sample;

import java.util.Map;

import org.dashbuilder.dataset.filter.ColumnFilter;
import org.dashbuilder.dataset.filter.FilterFactory;
import org.jbpm.services.api.query.QueryParamBuilder;


public class TestQueryParamBuilder implements QueryParamBuilder<ColumnFilter> {

    private Map<String, Object> parameters;
    private boolean built = false;
    public TestQueryParamBuilder(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
    
    @Override
    public ColumnFilter build() {
        // return null if it was already invoked
        if (built) {
            return null;
        }
        
        ColumnFilter filter1 = FilterFactory.lowerOrEqualsTo("processInstanceId", (Integer)parameters.get("maxProcessInstanceId"));
        ColumnFilter filter2 = FilterFactory.equalsTo("status", (Integer)parameters.get("status"));
        ColumnFilter topFilter = FilterFactory.AND(filter1, filter2);
        
        System.out.println("topFilter = " + topFilter);
       
        built = true;
        return topFilter;
    }

}
