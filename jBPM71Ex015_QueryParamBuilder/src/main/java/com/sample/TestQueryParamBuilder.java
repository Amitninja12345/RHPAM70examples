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
        
        String columnName = "processInstanceId";
        
        Long min = null;
        Object objMin = parameters.get("min");
        if (objMin instanceof Integer) {
            min = ((Integer)objMin).longValue();
        } else if (objMin instanceof Long) {
            min = (Long)objMin;
        } else {
            throw new RuntimeException("min = " + objMin + ", type = " + objMin.getClass());
        }
        
        System.out.println("min = " + min);
        
        Long max = null;
        Object objMax = parameters.get("max");
        if (objMax instanceof Integer) {
            max = ((Integer)objMax).longValue();
        } else if (objMax instanceof Long) {
            max = (Long)objMax;
        } else {
            throw new RuntimeException("max = " + objMax + ", type = " + objMax.getClass());
        }
        
        System.out.println("max = " + max);

        
        ColumnFilter filter = FilterFactory.OR(
                FilterFactory.greaterOrEqualsTo(min),
                FilterFactory.lowerOrEqualsTo(max));
        filter.setColumnId(columnName);
        
        System.out.println("filter = " + filter);
       
        built = true;
        return filter;
    }

}
