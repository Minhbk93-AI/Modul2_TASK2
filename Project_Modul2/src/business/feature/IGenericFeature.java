package business.feature;

public interface IGenericFeature<T,E> {
    void addOrUpdate(T t);
    void delete(E id);
    int findIndexById(E id);
    E getNewId();
}
