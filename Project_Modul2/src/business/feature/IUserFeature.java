package business.feature;


import business.entity.Users;

public interface IUserFeature extends IGenericFeature<Users,Integer>{
        Users login(Users users);

}
