package br.pucrio.ldw.aloLdw21.web;

import br.pucrio.ldw.aloLdw21.model.DAO.UserDAO;
import br.pucrio.ldw.aloLdw21.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ComponentScan(basePackages = "br.pucrio.ldw.aloLdw21.model.DAO")
public class UserDAOTest {

    @Autowired
    UserDAO userDAO;



    @Test
    public void testHacking() {
        User userTest = new User();
        userTest.setName("Teste");
        userTest.setEmail("tester@puc-rio.br");

        userDAO.createUser(userTest);

        Optional<User> hacker = userDAO.getUserByNameUnsafe("hacker");
        assert (hacker.isEmpty());

        Optional<User> userResult = userDAO.getUserByNameUnsafe("\'; insert into user (name, email) values (\'hacker\', \'pwnd@puc-rio.br\' ); select * from user where \'\'=\'");
        userResult = userDAO.getUserByNameUnsafe("hacker");
        assertThat(userResult).isNotEmpty().get().extracting("name").isEqualTo("hacker");


    }

    @Test
    public void testAntiHacking() {
        User userTest = new User();
        userTest.setName("Teste");
        userTest.setEmail("tester@puc-rio.br");

        userDAO.createUser(userTest);

        Optional<User> hacker = userDAO.getUserByNameSafe("hacker");
        assert (hacker.isEmpty());

        Optional<User> userResult = userDAO.getUserByNameSafe("\'; insert into user (name, email) values (\'hacker\', \'pwnd@puc-rio.br\' ); select * from user where \'\'=\'");
        userResult = userDAO.getUserByNameUnsafe("hacker");
        assertThat(userResult).isEmpty();
    }
}
