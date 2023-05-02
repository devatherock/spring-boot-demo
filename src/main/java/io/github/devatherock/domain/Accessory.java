package io.github.devatherock.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(indexName = "kibana_sample_data_ecommerce")
public class Accessory {

	@Id
    private String id;
    
    private String category;
    
    private String currency;
    
    @Field("customer_first_name")
    private String customerFirstName;
    
    @Field("customer_last_name")
    private String customerLastName;
    
    @Field("order_date")
    private LocalDateTime orderDate;
    
    @Field("total_quantity")
    private Integer totalQuantity;
}
