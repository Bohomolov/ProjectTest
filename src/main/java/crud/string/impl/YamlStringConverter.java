package crud.string.impl;

import crud.string.IPersonStringConverter;
import org.yaml.snakeyaml.Yaml;
import person.Person;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class YamlStringConverter implements IPersonStringConverter {

    @Override
    public String personToString(List<Person> person) {
        return null;
    }

    @Override
    public List<Person> stringToPerson(String personsStr) {
        return null;
    }
}
