package com.milankas.training.services.order;

import com.milankas.training.Mappers.address.AddressMapperInterface;
import com.milankas.training.Mappers.lineItem.LineItemMapperInterface;
import com.milankas.training.Mappers.order.OrderMapperInterface;
import com.milankas.training.dtos.lineItem.PostLineItemInputDTO;
import com.milankas.training.dtos.order.PatchOrderInputDTO;
import com.milankas.training.dtos.order.PostOrderInputDTO;
import com.milankas.training.dtos.order.OrderOutputDTO;
import com.milankas.training.persistance.entities.AddressEntity;
import com.milankas.training.persistance.entities.LineItemEntity;
import com.milankas.training.persistance.entities.OrderEntity;
import com.milankas.training.persistance.repositories.OrderRepository;
import com.milankas.training.services.address.AddressServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService implements OrderServiceInterface {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderMapperInterface orderMapper;
    @Autowired
    AddressMapperInterface addressMapper;
    @Autowired
    LineItemMapperInterface lineItemMapper;
    @Autowired
    AddressServiceInterface addressService;

    @Override
    public List<OrderOutputDTO> findAllOrders() {
        List<OrderEntity> ordersReceived = orderRepository.findAll();
        List<OrderOutputDTO> ordersToSend = new ArrayList();
        ordersReceived.forEach(order -> {
            ordersToSend.add(orderMapper.EntityToDto(order));
        });
        return ordersToSend;
    }

    @Override
    public OrderOutputDTO findOrderById(UUID id) {
        return orderMapper.EntityToDto(orderRepository.findById(id).orElse(null));
    }

    @Override
    public OrderOutputDTO saveOrder(@Valid PostOrderInputDTO order) {
        AddressEntity address = addressService.validateAddressToSave(order.getShopAddress());
        List<LineItemEntity> lineItemEntities = new ArrayList<>();
        order.getLineItems().forEach((PostLineItemInputDTO lineItem) -> {
            lineItemEntities.add(lineItemMapper.PostDtoToEntity(lineItem));
        });
        OrderEntity orderToSave = orderMapper.PostDtoToEntity(order);
        orderToSave.setShopAddress(address);
        orderToSave.setLineItems(lineItemEntities);
        return orderMapper.EntityToDto(orderRepository.save(orderToSave));
    }

    @Override
    public OrderOutputDTO updateOrderById(UUID id, PatchOrderInputDTO orderForUpdate) {
        OrderEntity orderFound = orderRepository.findById(id).orElse(null);
        orderFound = orderMapper.PatchDtoToEntity(orderFound, orderForUpdate);
        return orderMapper.EntityToDto(orderRepository.save(orderFound));
    }

    @Override
    public Boolean deleteOrderById(UUID id) {
        OrderEntity orderFound = orderRepository.findById(id).orElse( null);
        if (orderFound == null) return null;
        orderRepository.delete(orderFound);
        return true;
    }

}
