package com.up.feast.service;

import com.up.feast.mapper.GGoodsKindDAO;
import com.up.feast.model.GGoodsKind;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Description 商品
 * @author liub
 * @date 2018-12-01 19:17
 */
@Service
@Slf4j
public class GoodsService {

    @Resource
    private GGoodsKindDAO gGoodsKindDAO;

    public String selectByMerchantId(String merchantId){
        List<GGoodsKind> gGoodsKindList =  gGoodsKindDAO.selectByMerchantId(Long.parseLong(merchantId));

        Map<String,List<GGoodsKind>> map = new HashMap<>();

        gGoodsKindList.forEach(gGoodsKind -> {
            String key = String.valueOf(gGoodsKind.getId()).concat("&").concat(String.valueOf(gGoodsKind.getTyped()))
                    .concat("&").concat(gGoodsKind.getName());
            if(map.containsKey(key)){
                map.get(key).add(gGoodsKind);
            }else{
                List<GGoodsKind> list = new LinkedList<>();
                list.add(gGoodsKind);
                map.put(key,list);
            }
        });

        JSONObject allGoods = new JSONObject();
        JSONArray allGoodsArray = new JSONArray();
        map.forEach((key,value)->{
            String[] keys = key.split("&");
            JSONObject object = new JSONObject();
            object.put("id",keys[0]);
            object.put("type",keys[1]);
            object.put("name",keys[2]);

            JSONArray goodsArray = new JSONArray();
            for (GGoodsKind gGoodsKind : value) {
                JSONObject goods = new JSONObject();
                goods.put("id",gGoodsKind.getGoodsId());
                goods.put("name",gGoodsKind.getGoodsName());
                goods.put("goodKindId", gGoodsKind.getGoodKindId());
                goods.put("price",gGoodsKind.getPrice());
                goods.put("oldPrice",gGoodsKind.getOldPrice());
                goods.put("description",gGoodsKind.getDescription());
                goods.put("sellCount",gGoodsKind.getSellCount());
                goods.put("rating",gGoodsKind.getRating());
                goods.put("info",gGoodsKind.getInfo());

                goodsArray.add(goods);
            }
            object.put("foods",goodsArray);

            allGoodsArray.add(object);

        });
        allGoods.put("goods",allGoodsArray);
        return allGoods.toString();
    }

}
