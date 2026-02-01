package Octopi.example.demo4.Service;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Converter(autoApply = true)
public class JsonConverter implements AttributeConverter<Object,String>
{

   private final ObjectMapper mapper =new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Object attribute)
    {
        try{
            return mapper.writeValueAsString(attribute);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object convertToEntityAttribute(String dbData)
    {
        try {
         Object val;
            val = mapper.readValue(dbData,Object.class);
            return val;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }

}
