package business.feature.impl;

import business.entity.Address;
import business.entity.Products;
import business.feature.IAddressFeature;

import java.util.ArrayList;
import java.util.List;

public class AddressFeatureImpl implements IAddressFeature {
   public  static  List<Address> addressList= new ArrayList<>();

    @Override
    public void addOrUpdate(Address address) {
        int index = findIndexById(address.getAddressId());
        if (index==-1){
            addressList.add(address);
        }else {
            addressList.set(index, address);
        }
    }

    @Override
    public void delete(Integer id) {
        addressList.remove(id);

    }

    @Override
    public int findIndexById(Integer id) {
        return addressList.stream().map(Address::getAddressId).toList().indexOf(id);
    }

    @Override
    public Integer getNewId() {
        return 0;
    }

    @Override
    public void searchAddressById(int addressId) {
        for (Address address : addressList) {
            if (address.getAddressId() == addressId) {
                System.out.println("Chi tiết địa chỉ người dùng");
                address.displayData();
                return;
            }

        }
    }

}
