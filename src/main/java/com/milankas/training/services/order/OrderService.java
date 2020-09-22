package com.milankas.training.services.order;

import com.milankas.training.Mappers.address.AddressMapperInterface;
import com.milankas.training.Mappers.lineItem.LineItemMapperInterface;
import com.milankas.training.Mappers.order.OrderMapperInterface;
import com.milankas.training.dtos.order.PatchOrderInputDTO;
import com.milankas.training.dtos.order.PostOrderInputDTO;
import com.milankas.training.dtos.order.OrderOutputDTO;
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
        return orderMapper.EntityToDto(orderRepository.findById(id).orElse(null));
    }

    @Override
    public OrderOutputDTO saveOrder(PostOrderInputDTO order) {
        return orderMapper.EntityToDto(orderRepository.save(orderMapper.PostDtoToEntity(order)));
    }

    @Override
    public OrderOutputDTO updateOrderById(UUID id, PatchOrderInputDTO orderForUpdate) {
        OrderEntity orderFound = orderRepository.findById(id).orElse(null);
        if (orderFound == null) return null;
        orderFound = orderMapper.PatchDtoToEntity(orderFound, orderForUpdate);
        return orderMapper.EntityToDto(orderRepository.save(orderFound));
    }

    @Override
    public Boolean deleteOrderById(UUID id) {
        return null;
    }

}
