package business.feature.impl;

import business.entity.OrderDetail;
import business.feature.IOrderDetailsFeature;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsFeatureImpl implements IOrderDetailsFeature {
    public static List<OrderDetail> orderDetailsList= new ArrayList<>();
    @Override
    public void addOrUpdate(OrderDetail orderDetail) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public int findIndexById(Integer id) {
        return 0;
    }

    @Override
    public Integer getNewId() {
        return 0;
    }
}
