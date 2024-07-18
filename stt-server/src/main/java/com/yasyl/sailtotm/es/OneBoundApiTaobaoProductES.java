package com.yasyl.sailtotm.es;

import com.yasyl.sailtotm.entity.TaobaoGoodList.Product;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public interface OneBoundApiTaobaoProductES {
    public List<Product> selectByQ(String q, Integer page, Integer pageSize);

    public Boolean insert(Product item, String q, Timestamp created_date);

    public Boolean update(Product item, String q, Timestamp created_date);

    public Boolean syncDataFromDB() throws IOException;
}
