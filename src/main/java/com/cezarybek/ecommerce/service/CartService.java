package com.cezarybek.ecommerce.service;

import com.cezarybek.ecommerce.dto.CartItemDto;
import com.cezarybek.ecommerce.exception.EcommerceException;
import com.cezarybek.ecommerce.model.Cart;
import com.cezarybek.ecommerce.model.CartItem;
import com.cezarybek.ecommerce.model.Product;
import com.cezarybek.ecommerce.repository.CartItemRepository;
import com.cezarybek.ecommerce.repository.CartRepository;
import com.cezarybek.ecommerce.repository.ProductRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    public Cart addItemToCart(CartItemDto item) throws NotFoundException {
        if (item.getQuantity() >= 20)
            throw new EcommerceException("You cannot add to cart more than 20 same products.", HttpStatus.BAD_REQUEST);
        Optional<Cart> cart = cartRepository.findByUserId(1l);
        if (cart.isEmpty()) throw new NotFoundException("Cart not found");
        Optional<Product> product = productRepository.findById(item.getProductId());
        if (product.isEmpty()) throw new NotFoundException("Product not found");

        Optional<CartItem> cartItem = cartItemRepository.findByCartIdAndProductId(cart.get().getId(), item.getProductId());
        //Check if cart already contains product
        //if true update only the quantity
        if (cartItem.isPresent()) {
            cartItem.get().setQuantity(item.getQuantity());
            cartItem.get().setSubTotal(formatPrice(product.get().getPrice() * item.getQuantity()));
            cart.get().getCartItems().add(cartItem.get());
        } else {
            //else add to cart
            CartItem newCartItem = new CartItem();
            newCartItem.setProduct(product.get());
            newCartItem.setQuantity(item.getQuantity());
            newCartItem.setSubTotal(formatPrice(item.getQuantity() * product.get().getPrice()));
            newCartItem.setCart(cart.get());
            CartItem cartItemSaved = cartItemRepository.save(newCartItem);
            cart.get().getCartItems().add(cartItemSaved);
        }

        return cart.get();
    }

    public Cart removeProductFromCart(long productId) throws NotFoundException {
        Optional<Cart> cart = cartRepository.findByUserId(1l);
        if (cart.isEmpty()) throw new NotFoundException("Cart not found");
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) throw new NotFoundException("Product not found");

        Optional<CartItem> cartItem = cartItemRepository.findByCartIdAndProductId(cart.get().getId(), productId);
        if (cartItem.isEmpty()) throw new EcommerceException("CartItem not found", HttpStatus.BAD_REQUEST);
        cartItemRepository.delete(cartItem.get());

        return cart.get();
    }

    public void removeAllItemsFromCart() throws NotFoundException {
        Optional<Cart> cart = cartRepository.findByUserId(1l);
        if (cart.isEmpty()) throw new NotFoundException("Cart not found");
        cartItemRepository.deleteByCartId(cart.get().getId());
    }


    public Double formatPrice(Double price) {
        return (double) Math.round(price * 100d) / 100d;
    }

}
