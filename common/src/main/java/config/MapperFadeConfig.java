package config;

import dev.akkinoc.spring.boot.orika.OrikaMapperFactoryConfigurer;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

/**
 * 自定义映射
 * @author zhuzishuang
 * @date 2022/5/17
 */
@Component
public class MapperFadeConfig implements OrikaMapperFactoryConfigurer {
    @Override
    public void configure(MapperFactory orikaMapperFactory) {

    }
}
