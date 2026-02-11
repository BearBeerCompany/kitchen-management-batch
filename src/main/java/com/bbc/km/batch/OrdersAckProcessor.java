package com.bbc.km.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class OrdersAckProcessor implements ItemProcessor<OrdersAckCsv, OrdersAckCsv> {

    private final DomainProperties domainProperties;

    public OrdersAckProcessor(DomainProperties domainProperties) {
        this.domainProperties = domainProperties;
    }

    @Override
    public OrdersAckCsv process(OrdersAckCsv item) {

        if (!domainProperties.getCategories().containsKey(item.getCategoryId())) {
            throw new IllegalArgumentException("Invalid category id: " + item.getCategoryId());
        }

        if (!domainProperties.getMenuItems().isEmpty() &&
                !domainProperties.getMenuItems().containsKey(item.getMenuItemId())) {
            throw new IllegalArgumentException("Invalid menu item id: " + item.getMenuItemId());
        }

        return item;
    }
}
