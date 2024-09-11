package business.entity;

import java.io.Serializable;

public class WishList implements Serializable {
    private int wishListId;
    private int userId;
    private int productId;

    public WishList() {
    }

    public WishList(int wishListId, int userId, int productId) {
        this.wishListId = wishListId;
        this.userId = userId;
        this.productId = productId;
    }

    public int getWishListId() {
        return wishListId;
    }

    public void setWishListId(int wishListId) {
        this.wishListId = wishListId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
