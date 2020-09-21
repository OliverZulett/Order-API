package com.milankas.training.services.order;

import com.milankas.training.Mappers.address.AddressMapperInterface;
import com.milankas.training.Mappers.lineItem.LineItemMapperInterface;
import com.milankas.training.Mappers.order.OrderMapperInterface;
import com.milankas.training.dtos.lineItem.LineItemOutputDTO;
import com.milankas.training.dtos.lineItem.PostLineItemInputDTO;
import com.milankas.training.dtos.order.PatchOrderInputDTO;
import com.milankas.training.dtos.order.PostOrderInputDTO;
import com.milankas.training.dtos.order.OrderOutputDTO;
import com.milankas.training.persistance.entities.AddressEntity;
import com.milankas.training.persistance.entities.LineItemEntity;
import com.milankas.training.persistance.entities.OrderEntity;
import com.milankas.training.persistance.repositories.AddressRepository;
import com.milankas.training.persistance.repositories.LineItemRepository;
import com.milankas.training.persistance.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService implements OrderServiceInterface {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    LineItemRepository lineItemRepository;

    @Autowired
    OrderMapperInterface orderMapper;

    @Autowired
    AddressMapperInterface addressMapper;

    @Autowired
    LineItemMapperInterface lineItemMapper;

    @Override
    public List<OrderOutputDTO> findAllOrders() {
        List<OrderEntity> ordersReceived = orderRepository.findAll();
        List<OrderOutputDTO> ordersToSend = new ArrayList();
        ordersReceived.forEach(order -> {
            ordersToSend.add(this.orderMapper.EntityToDto(order));
        });
        return ordersToSend;
    }

    @Override
    public OrderOutputDTO findOrderById(UUID id) {
        return null;
    }

    @Override
    public OrderOutputDTO saveOrder(PostOrderInputDTO order) {
        AddressEntity addressToSave = addressMapper.PostDtoToEntity(order.getShopAddress());
        OrderEntity orderToSave = orderMapper.PostDtoToEntity(order);
        List<LineItemEntity> lineItemsToSave = new ArrayList<>();
        order.getLineItems().forEach((PostLineItemInputDTO lineItem ) -> {
            lineItemsToSave.add(lineItemRepository.save(lineItemMapper.PostDtoToEntity(lineItem)));
        });

        orderToSave.setLineItemEntities(lineItemsToSave);
        orderToSave.setShopAddressEntity(addressRepository.save(addressToSave));

        OrderEntity orderSaved = orderRepository.save(orderToSave);

        OrderOutputDTO orderForDisplay = orderMapper.EntityToDto(orderSaved);
        orderForDisplay.setShopAddress(addressMapper.EntityToDto(orderSaved.getShopAddressEntity()));
        List<LineItemOutputDTO> lineItemsForDisplay = new ArrayList<>();
        orderSaved.getLineItemEntities().forEach((LineItemEntity lineItem) -> {
            lineItemsForDisplay.add(lineItemMapper.EntityToDto(lineItem));
        });
        orderForDisplay.setLineItems(lineItemsForDisplay);
        return orderForDisplay;
    }

    @Override
    public OrderOutputDTO updateOrderById(UUID id, PatchOrderInputDTO orderForUpdate) {
        return null;
    }

    @Override
    public Boolean deleteOrderById(UUID id) {
        return null;
    }


}
