import java.util.List;
import java.util.UUID;

public class PersonService {

    private AddressService addressService;
    private PersonRepository personRepository;

    public PersonService(AddressService addressService, PersonRepository personRepository) {
        this.addressService = addressService;
        this.personRepository = personRepository;
    }

    public Person getPerson(UUID id) {

        Person foundPerson = personRepository.findById(id);

        List<Address> addreses = addressService.findAddress(id);

        foundPerson.setAddress(addreses.get(0));

        return foundPerson;
    }
}
