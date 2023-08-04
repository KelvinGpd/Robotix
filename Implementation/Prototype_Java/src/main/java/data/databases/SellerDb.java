package data.databases;

import data.Seller;
import data.User;

public class SellerDb extends Db<Seller>{
    public SellerDb(String path) {
        super(path);
    }
    @Override
    protected Class<Seller[]> getArrayClass() {
        return Seller[].class;
    }


}
