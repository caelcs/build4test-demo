import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.caeldev.builder4test.Builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static uk.co.caeldev.builder4test.Builder.build;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private AddressService addressService;

    @InjectMocks
    private PersonService personService;

    @Test
    public void testOldFashionWayBuildEntity() {
        //Given
        UUID id = UUID.randomUUID();

        //And
        Address address = new Address();
        address.setNumber("99");
        address.setPersonId(id);
        address.setPostCode("DDDFFFF");
        address.setStreet("la la");
        when(addressService.findAddress(id)).thenReturn(Collections.singletonList(address));

        //And
        Person person = Person.PersonBuilder.aPerson().withAddress(address).withDod(LocalDate.now()).withId(id).withName("lala").build();
        when(personRepository.findById(id)).thenReturn(person);

        //When
        Person person1 = personService.getPerson(id);

        //then
        assertEquals(person, person1);
    }

    @Test
    public void testUsingBuilderForBuildingEntityTest() {
        //Given
        UUID id = UUID.randomUUID();

        //And
        Address address = build().entity(Builders.defaultAddress)
                .override(Builders.street, "new street")
                .override(Builders.id, id)
                .get();
        when(addressService.findAddress(id)).thenReturn(Collections.singletonList(address));

        //And
        Person person = build().entity(Builders.defaultPerson)
                .override(Builders.id, id)
                .override(Builders.address, address)
                .get();
        when(personRepository.findById(id)).thenReturn(person);

        //When
        Person person1 = personService.getPerson(id);

        //then
        assertEquals(person, person1);
    }

    @Test
    public void testOldFashionWayBuildingAListOfentities() {
        //Given
        UUID id = UUID.randomUUID();

        //And
        Address address = new Address();
        address.setNumber("99");
        address.setPersonId(id);
        address.setPostCode("DDDFFFF");
        address.setStreet("la la");

        Address address2 = new Address();
        address.setNumber("99");
        address.setPersonId(id);
        address.setPostCode("DDDFFFF");
        address.setStreet("la la");
        List<Address> addresses = new ArrayList<>();
        addresses.add(address);
        addresses.add(address2);
        when(addressService.findAddress(id)).thenReturn(addresses);

        //And
        Person person = Person.PersonBuilder.aPerson().withAddress(addresses.get(0)).withDod(LocalDate.now()).withId(id).withName("lala").build();
        when(personRepository.findById(id)).thenReturn(person);

        //When
        Person person1 = personService.getPerson(id);

        //then
        assertEquals(person, person1);
    }

    @Test
    public void testUsingBuilderForBuildingListOfEntitiesTest() {
        //Given
        UUID id = UUID.randomUUID();

        //And
        List<Address> addresses = Builder.build().list(Builders.defaultAddress)
                .element().field(Builders.street, "test1").end()
                .element().field(Builders.street, "test2").end()
                .get();
        when(addressService.findAddress(id)).thenReturn(addresses);

        //And
        Person person = build().entity(Builders.defaultPerson)
                .override(Builders.id, id)
                .override(Builders.address, addresses.get(0))
                .get();
        when(personRepository.findById(id)).thenReturn(person);

        //When
        Person person1 = personService.getPerson(id);

        //then
        assertEquals(person, person1);
    }
}