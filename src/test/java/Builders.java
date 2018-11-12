import uk.co.caeldev.builder4test.Creator;
import uk.co.caeldev.builder4test.Field;

import java.time.LocalDate;
import java.util.UUID;

public class Builders {

    public static Field<String> street = new Field<>();
    public static Field<UUID> id = new Field<>();

    public static Creator<Address> defaultAddress = (lookUp) -> {
        Address address = new Address();
        address.setStreet(lookUp.get(street, "default Street"));
        address.setPersonId(lookUp.get(id, UUID.randomUUID()));
        return address;
    };

    public static Field<Address> address = new Field<>();
    public static Field<LocalDate> dod = new Field<>();

    public static Creator<Person> defaultPerson = (lookUp) ->
        Person.PersonBuilder.aPerson()
                .withAddress(lookUp.get(address))
                .withId(lookUp.get(id, UUID.randomUUID()))
                .withDod(lookUp.get(dod, LocalDate.now()))
                .build();
}
