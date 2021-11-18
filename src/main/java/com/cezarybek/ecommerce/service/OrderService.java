package com.cezarybek.ecommerce.service;

import com.cezarybek.ecommerce.exception.EcommerceException;
import com.cezarybek.ecommerce.model.*;
import com.cezarybek.ecommerce.repository.CartRepository;
import com.cezarybek.ecommerce.repository.OrderItemRepository;
import com.cezarybek.ecommerce.repository.OrderRepository;
import com.cezarybek.ecommerce.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CartService cartService;

    public Order createOrder() throws NotFoundException {
        User user = userRepository.findById(1l).get();
        Optional<Cart> cart = cartRepository.findByUserId(1l);
        if (cart.isEmpty()) throw new NotFoundException("Cart not found");
        if (cart.get().getCartItems().isEmpty())
            throw new EcommerceException("Cannot create order without products in cart", HttpStatus.BAD_REQUEST);
        Order order = new Order();

        List<CartItem> cartItems = cart.get().getCartItems();
        List<OrderItem> orderItems = new ArrayList<>();
        Double totalOrderAmount = 0.00;

        Order orderSaved = orderRepository.save(order);

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setSubTotal(cartItem.getSubTotal());
            orderItem.setOrder(orderSaved);
            OrderItem orderItemSaved = orderItemRepository.save(orderItem);
            orderItems.add(orderItemSaved);
            totalOrderAmount += cartItem.getSubTotal();
        }

        order.setOrderItems(orderItems);
        order.setTotalAmount(totalOrderAmount);
        order.setUser(user);

        cartService.removeAllItemsFromCart();
        return orderSaved;

    }
}
