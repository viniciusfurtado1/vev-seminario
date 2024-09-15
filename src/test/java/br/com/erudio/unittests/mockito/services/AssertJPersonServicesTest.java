package br.com.erudio.unittests.mockito.services;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import br.com.erudio.services.PersonServices;
import br.com.erudio.unittests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class AssertJPersonServicesTest {

    MockPerson input;

    @Mock
    PersonRepository repository;

    @InjectMocks
    private PersonServices service;

    @BeforeEach
    void setUpMocks() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<Person> list = input.mockEntityList();

        Mockito.when(repository.findAll()).thenReturn(list);

        var people = service.findAll();

        assertThat(people).isNotNull();
        assertThat(people.size()).isEqualTo(14);

        var personOne = people.get(1);

        assertThat(personOne).isNotNull();
        assertThat(personOne.getKey()).isNotNull();
        assertThat(personOne.getLinks()).isNotEmpty();
        assertThat(personOne.toString()).contains("links: [</api/person/v1/1>;rel=\"self\"]");
        assertThat(personOne.getAddress()).isEqualTo("Addres Test1");
        assertThat(personOne.getFirstName()).isEqualTo("First Name Test1");
        assertThat(personOne.getLastName()).isEqualTo("Last Name Test1");
        assertThat(personOne.getGender()).isEqualTo("Female");

        var personFour = people.get(4);

        assertThat(personFour).isNotNull();
        assertThat(personFour.getKey()).isNotNull();
        assertThat(personFour.getLinks()).isNotEmpty();
        assertThat(personFour.toString()).contains("links: [</api/person/v1/4>;rel=\"self\"]");
        assertThat(personFour.getAddress()).isEqualTo("Addres Test4");
        assertThat(personFour.getFirstName()).isEqualTo("First Name Test4");
        assertThat(personFour.getLastName()).isEqualTo("Last Name Test4");
        assertThat(personFour.getGender()).isEqualTo("Male");
    }

    @Test
    void findById() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = service.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getKey()).isNotNull();
        assertThat(result.getLinks()).isNotEmpty();
        assertThat(result.toString()).contains("links: [</api/person/v1/1>;rel=\"self\"]");
        assertThat(result.getAddress()).isEqualTo("Addres Test1");
        assertThat(result.getFirstName()).isEqualTo("First Name Test1");
        assertThat(result.getLastName()).isEqualTo("Last Name Test1");
        assertThat(result.getGender()).isEqualTo("Female");
    }

    @Test
    void create() {
        Person entity = input.mockEntity(1);

        Person persisted = entity;
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        Mockito.when(repository.save(entity)).thenReturn(persisted);

        var result = service.create(vo);

        assertThat(result).isNotNull();
        assertThat(result.getKey()).isNotNull();
        assertThat(result.getLinks()).isNotEmpty();
        assertThat(result.toString()).contains("links: [</api/person/v1/1>;rel=\"self\"]");
        assertThat(result.getAddress()).isEqualTo("Addres Test1");
        assertThat(result.getFirstName()).isEqualTo("First Name Test1");
        assertThat(result.getLastName()).isEqualTo("Last Name Test1");
        assertThat(result.getGender()).isEqualTo("Female");
    }

    @Test
    void createWithNullPerson() {
        assertThatThrownBy(() -> service.create(null))
                .isInstanceOf(RequiredObjectIsNullException.class)
                .hasMessageContaining("It is not allowed to persist a null object!");
    }

    @Test
    void update() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        Person persisted = entity;
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(entity));
        Mockito.when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(vo);

        assertThat(result).isNotNull();
        assertThat(result.getKey()).isNotNull();
        assertThat(result.getLinks()).isNotEmpty();
        assertThat(result.toString()).contains("links: [</api/person/v1/1>;rel=\"self\"]");
        assertThat(result.getAddress()).isEqualTo("Addres Test1");
        assertThat(result.getFirstName()).isEqualTo("First Name Test1");
        assertThat(result.getLastName()).isEqualTo("Last Name Test1");
        assertThat(result.getGender()).isEqualTo("Female");
    }

    @Test
    void updateWithNullPerson() {
        assertThatThrownBy(() -> service.update(null))
                .isInstanceOf(RequiredObjectIsNullException.class)
                .hasMessageContaining("It is not allowed to persist a null object!");
    }

    @Test
    void delete() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(entity));

        service.delete(1L);
    }
}
