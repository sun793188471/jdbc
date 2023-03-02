/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cn.beep.data.jdbcspi;

import com.google.common.base.Strings;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import org.apache.shardingsphere.mode.repository.standalone.StandalonePersistRepository;

/**
 * JDBC SPI TEST.
 * 增加StandalonePersistRepository SPI的实现类，不启动hakira数据源
 */
public final class JDBCTestRepository implements StandalonePersistRepository {

    @Override
    public void init(final Properties props) {
        System.out.println("test");
    }

    @Override
    public String get(final String key) {
        return null;
    }

    @Override
    public String getDirectly(final String key) {
        return "";
    }

    @Override
    public List<String> getChildrenKeys(final String key) {
        return Collections.emptyList();
    }

    @Override
    public boolean isExisted(final String key) {
        return !Strings.isNullOrEmpty(getDirectly(key));
    }

    @Override
    public void persist(final String key, final String value) {

    }

    private void insert(final String key, final String value, final String parent) throws SQLException {
    }

    @Override
    public void update(final String key, final String value) {
    }

    @Override
    public void delete(final String key) {
    }

    @Override
    public void close() {
    }

    @Override
    public String getType() {
        return "TEST";
    }
}
