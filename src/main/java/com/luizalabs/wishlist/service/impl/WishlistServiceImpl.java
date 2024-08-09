package com.luizalabs.wishlist.service.impl;
import com.luizalabs.wishlist.entity.ProductEntity;
import com.luizalabs.wishlist.entity.WishlistEntity;
import com.luizalabs.wishlist.exceptions.ExistsProductOnWishlistExeception;
import com.luizalabs.wishlist.exceptions.ProductNotExistsOnWishlistException;
import com.luizalabs.wishlist.exceptions.ProductNotFoundException;
import com.luizalabs.wishlist.exceptions.WishlistNotFoundException;
import com.luizalabs.wishlist.repository.ProductRepository;
import com.luizalabs.wishlist.repository.WishlistRepository;
import com.luizalabs.wishlist.service.ValidationWishlist;
import com.luizalabs.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private List<ValidationWishlist> validationWishlists;

    @Override
    public WishlistEntity add(String productId) {

        var product = findProductById(productId);
        var wishlist = wishlistExists();

        productExistsOnWishlist(productId, wishlist);
        validationWishlists.forEach(v -> v.valid(wishlist));

        wishlist.getProductsId().add(product);

        return this.wishlistRepository.save(wishlist);
    }

    @Override
    public WishlistEntity deleteById(String productId) {

        var wishlist = wishlistExists();

        boolean productRemoved = wishlist.getProductsId().removeIf(prod -> prod.getId().equals(productId));

        if (!productRemoved) {
            throw new ProductNotExistsOnWishlistException();
        }

        return this.wishlistRepository.save(wishlist);
    }

    protected void productExistsOnWishlist(String productId, WishlistEntity wishlist) {
        for (ProductEntity product : wishlist.getProductsId()) {
            if (product.getId().equals(productId)) {
                throw new ExistsProductOnWishlistExeception();
            }
        }
    }

    protected WishlistEntity wishlistExists() {
        return wishlistRepository.findById("66b4eefff40c3c8dbd7f50ef")
                .orElseThrow(WishlistNotFoundException::new);
    }

    protected ProductEntity findProductById(String productId) {
        return productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
    }
}
