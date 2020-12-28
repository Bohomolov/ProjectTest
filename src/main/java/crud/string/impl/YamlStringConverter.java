package crud.string.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import crud.string.IPersonStringConverter;
import person.Person;

import java.util.List;

public class YamlStringConverter implements IPersonStringConverter {
    @Override
    public String personToString(List<Person> person) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return  mapper.writeValueAsString(person);
    }

    @Override
    public List<Person> stringToPerson(String personsStr) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        List<Person> xz = mapper.readValue(personsStr,new TypeReference<List<Person>>(){} );//тут проблема в коде, надо фиксить
        return xz;
    }
}
