package org.strokova.booker.client.jacksonUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import java.io.IOException;

/**
 * 21.11.2016.
 */
public class JacksonSortDeserializer extends JsonDeserializer<Sort> {
    @Override
    public Sort deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ArrayNode node = jp.getCodec().readTree(jp);
        Sort.Order[] orders = new Sort.Order[node.size()];
        int i = 0;
        for(JsonNode obj : node){
            orders[i] =  new Order(
                    Direction.valueOf(obj.get("direction").asText()),
                    obj.get("property").asText(),
                    Sort.NullHandling.valueOf(obj.get("nullHandling").asText()));
            i++;
        }
        return new Sort(orders);
    }
}
