package com.yasyl.sailtotm.es;

import com.yasyl.sailtotm.entity.TaobaoGoodList.Product;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class OneBoundApiTaobaoProductESV2 implements OneBoundApiTaobaoProductES{
    @Override
    public List<Product> selectByQ(String q, Integer page, Integer pageSize) {
        return null;
    }

    @Override
    public Boolean insert(Product item, String q, Timestamp created_date) {
        return null;
    }

    @Override
    public Boolean update(Product item, String q, Timestamp created_date) {
        return null;
    }

    @Override
    public Boolean syncDataFromDB() throws IOException {
        return null;
    }
}
