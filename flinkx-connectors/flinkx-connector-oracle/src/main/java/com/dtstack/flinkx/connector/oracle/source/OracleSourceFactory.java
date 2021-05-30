/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.dtstack.flinkx.connector.oracle.source;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import com.dtstack.flinkx.conf.SyncConf;
import com.dtstack.flinkx.connector.jdbc.source.JdbcInputFormatBuilder;
import com.dtstack.flinkx.connector.jdbc.source.JdbcSourceFactory;
import com.dtstack.flinkx.connector.oracle.OracleDialect;
import com.dtstack.flinkx.connector.oracle.converter.OracleRawTypeConverter;
import com.dtstack.flinkx.converter.RawTypeConverter;

/**
 * company www.dtstack.com
 *
 * @author jier
 */
public class OracleSourceFactory extends JdbcSourceFactory {

    private static final int DEFAULT_FETCH_SIZE = 0;

    public OracleSourceFactory(SyncConf syncConf, StreamExecutionEnvironment env) {
        super(syncConf, env);
        super.jdbcDialect = new OracleDialect();
    }

    @Override
    protected JdbcInputFormatBuilder getBuilder() {
        return new JdbcInputFormatBuilder(new OracleInputFormat());
    }

    @Override
    protected int getDefaultFetchSize() {
        return DEFAULT_FETCH_SIZE;
    }

    @Override
    public RawTypeConverter getRawTypeConverter() {
        return OracleRawTypeConverter::apply;
    }
}
