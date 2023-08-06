package data.databases;

import data.Seller;

/**
 * SellerDb is the Database for Storing Seller Objects.
 */
public class SellerDb extends Db<Seller>{
    public SellerDb(String path) {
        super(path);
    }
    @Override
    protected Class<Seller[]> getArrayClass() {
        return Seller[].class;
    }


}
