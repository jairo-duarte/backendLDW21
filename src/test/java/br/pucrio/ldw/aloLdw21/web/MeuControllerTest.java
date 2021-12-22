package br.pucrio.ldw.aloLdw21.web;

import br.pucrio.ldw.aloLdw21.service.Animal;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class MeuControllerTest {


    private MeuController meuController;

    @Mock
    Animal pseudoAnimal;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        meuController = new MeuController(pseudoAnimal, null, null, null);
    }

    @Test
    public void  getMessage() {
        assert Objects.equals(meuController.getMessage(), "Hello LDW21!");
    }

    @Test
    public void  getAnimalMessage() {
        String msg = "Ugabuga";
        Mockito.when(pseudoAnimal.speak()).thenReturn(msg);

        assertThat(meuController.getAnimalMessage()).isEqualTo(msg);
        Mockito.verify(pseudoAnimal, Mockito.times(1)).speak();
    }

    @Test
    public void getErrado(){
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> meuController.getErrado("NPE",""));
    }


}
