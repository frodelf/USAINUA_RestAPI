package com.avadamedia.USAINUA.config;

import com.avadamedia.USAINUA.mapper.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public CreditCardMapper creditCardMapper() {
        return new CreditCardMapper();
    }
    @Bean
    public FinancesMapper financesMapper() {
        return new FinancesMapper();
    }
    @Bean
    public OrderMapper orderMapper() {
        return new OrderMapper();
    }
    @Bean
    public ProductMapper productMapper() {
        return new ProductMapper();
    }
    @Bean
    public ShopMapper shopMapper() {
        return new ShopMapper();
    }
    @Bean
    public StorageMapper storageMapper() {
        return new StorageMapper();
    }
    @Bean
    public UserAddressMapper userAddressMapper() {
        return new UserAddressMapper();
    }
}
