import java.time.LocalDate;
import java.util.UUID;

public class Person {

    private UUID id;
    private String name;
    private Address address;
    private LocalDate dod;

    public Person(UUID id, String name, Address address, LocalDate dod) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dod = dod;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public LocalDate getDod() {
        return dod;
    }


    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setDod(LocalDate dod) {
        this.dod = dod;
    }

    public static final class PersonBuilder {
        private UUID id;
        private String name;
        private Address address;
        private LocalDate dod;

        private PersonBuilder() {
        }

        public static PersonBuilder aPerson() {
            return new PersonBuilder();
        }

        public PersonBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public PersonBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public PersonBuilder withDod(LocalDate dod) {
            this.dod = dod;
            return this;
        }

        public Person build() {
            return new Person(id, name, address, dod);
        }
    }
}
