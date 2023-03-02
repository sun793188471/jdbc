package com.cn.beep.data.dynamaic;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.provider.AbstractDataSourceProvider;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.mode.ModeConfiguration;
import org.apache.shardingsphere.infra.config.rule.RuleConfiguration;
import org.apache.shardingsphere.infra.yaml.config.swapper.mode.YamlModeConfigurationSwapper;
import org.apache.shardingsphere.spring.boot.datasource.DataSourceMapSetter;
import org.apache.shardingsphere.spring.boot.prop.SpringBootPropertiesConfiguration;
import org.apache.shardingsphere.spring.boot.schema.DatabaseNameSetter;
import org.apache.shardingsphere.spring.transaction.TransactionTypeScanner;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

/**
 * @author YCKJ3465
 * @since 2023/3/2 下午4:07
 */
@Configuration
@EnableConfigurationProperties(SpringBootPropertiesConfiguration.class)
@AutoConfigureBefore({DataSourceAutoConfiguration.class, DynamicDataSourceAutoConfiguration.class,
        SpringBootConfiguration.class})
public class DynamaicConfiguration implements EnvironmentAware {

    private String databaseName;

    private final SpringBootPropertiesConfiguration props;

    private final Map<String, DataSource> dataSourceMap = new LinkedHashMap<>();

    /**
     * 动态数据源配置项
     */
    private final DynamicDataSourceProperties dynamicDataSourceProperties;

    private final ObjectProvider<List<RuleConfiguration>> rules;

    private final ObjectProvider<ModeConfiguration> modeConfig;
    /**
     * 分表数据源名称
     */
    public static final String SHARDING_DATA_SOURCE_NAME = "cst-sharding";

    public DynamaicConfiguration(SpringBootPropertiesConfiguration props,
            DynamicDataSourceProperties dynamicDataSourceProperties,
            ObjectProvider<List<RuleConfiguration>> rules,
            ObjectProvider<ModeConfiguration> modeConfig) {
        this.props = props;
        this.dynamicDataSourceProperties = dynamicDataSourceProperties;
        this.rules = rules;
        this.modeConfig = modeConfig;
    }

    /**
     * Get mode configuration.
     *
     * @return mode configuration
     */
    @Bean
    public ModeConfiguration modeConfiguration() {
        return null == props.getMode() ? null : new YamlModeConfigurationSwapper().swapToObject(props.getMode());
    }

    /**
     * Get ShardingSphere data source bean.
     *
     * @return data source bean
     * @throws SQLException SQL exception
     */
    private DataSource shardingSphereDataSource() throws SQLException {
        Collection<RuleConfiguration> ruleConfigs = Optional.ofNullable(rules.getIfAvailable())
                .orElseGet(Collections::emptyList);
        return ShardingSphereDataSourceFactory
                .createDataSource(databaseName, modeConfig.getIfAvailable(), dataSourceMap, ruleConfigs,
                        props.getProps());
    }


    @Bean
    public DynamicDataSourceProvider dynamicDataSourceProvider() throws SQLException {
        DataSource shardingSphereDataSource = this.shardingSphereDataSource();
        Map<String, DataSourceProperty> datasourceMap = dynamicDataSourceProperties.getDatasource();
        return new AbstractDataSourceProvider() {
            @Override
            public Map<String, DataSource> loadDataSources() {
                Map<String, DataSource> dataSourceMap = createDataSourceMap(datasourceMap);
                // 将 shardingjdbc 管理的数据源也交给动态数据源管理
                dataSourceMap.put(SHARDING_DATA_SOURCE_NAME, shardingSphereDataSource);
                return dataSourceMap;
            }
        };
    }


    /**
     * 将动态数据源设置为首选的 当spring存在多个数据源时, 自动注入的是首选的对象 设置为主要的数据源之后，就可以支持shardingjdbc原生的配置方式了
     */
    @Primary
    @Bean
    public DataSource dataSource() {
        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
        dataSource.setPrimary(dynamicDataSourceProperties.getPrimary());
        dataSource.setStrict(dynamicDataSourceProperties.getStrict());
        dataSource.setStrategy(dynamicDataSourceProperties.getStrategy());
        dataSource.setP6spy(dynamicDataSourceProperties.getP6spy());
        dataSource.setSeata(dynamicDataSourceProperties.getSeata());
        return dataSource;
    }

    /**
     * Create transaction type scanner.
     *
     * @return transaction type scanner
     */
    @Bean
    public TransactionTypeScanner transactionTypeScanner() {
        return new TransactionTypeScanner();
    }

    @Override
    public final void setEnvironment(final Environment environment) {
        dataSourceMap.putAll(DataSourceMapSetter.getDataSourceMap(environment));
        databaseName = DatabaseNameSetter.getDatabaseName(environment);
    }
}
