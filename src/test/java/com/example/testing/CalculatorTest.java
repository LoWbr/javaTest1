package com.example.testing;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CalculatorTest {

    private Calculator calculatorUnderTest;
    private static Instant startedAt;

    @BeforeAll
    public static void initProcess(){
        System.out.println("Démarrage");
        startedAt = Instant.now();
    }

    @AfterAll
    public static void showResult(){
        System.out.println("Fin");
        Instant endedAt = Instant.now();
        long duration = Duration.between(startedAt,endedAt).toMillis();
        System.out.println(MessageFormat.format("Durée : {0}", duration));
    }

    @BeforeEach
    public void initCalculator(){
        calculatorUnderTest = new Calculator();
        System.out.println("Appel Initial");
    }

    @AfterEach
    public void disableCalculator(){
        calculatorUnderTest = null;
        System.out.println("Appel Final");
    }

    @Test
    void testAddTwoPositiveNumbers() {
        //Arrange
        int a = 2;
        int b = 3;
        //Act
        int somme = calculatorUnderTest.add(a,b);
        //Assert
/*
        assertEquals(5,somme);
*/
        assertThat(somme).isEqualTo(5);
    }

    @Test
    void testMultiplyTwoPositiveNumbers() {
        //Arrange
        int a = 2;
        int b = 3;
        //Act
        int product = calculatorUnderTest.multiply(a,b);
        //Assert
/*
        assertEquals(6,product);
*/
        assertThat(product).isEqualTo(6);

    }

    @ParameterizedTest(name ="{0} x 0 doit être égal à 0")
    @ValueSource(ints = {1,2,42,1001,5089})
    public void multiplyReturnZero(int arg){
        //Arrange
        //Act
        int actualResult = calculatorUnderTest.multiply(arg,0);
        //Assert
/*
        assertEquals(0,actualResult);
*/
        assertThat(actualResult).isEqualTo(0);
    }

    @ParameterizedTest(name ="{0} + {1} doit être égal à {2}")
    @CsvSource({"1,1,2","2,3,5","42,57,99"})
    public void multiplyReturnSpecific(int arg1,int arg2,int arg3){
        //Arrange
        //Act
        int actualResult = calculatorUnderTest.add(arg1,arg2);
        //Assert
        assertEquals(arg3,actualResult);
    }

    @Test
    @Timeout(1)
    public void longCalcul(){

        calculatorUnderTest.longCalculator();

    }

    @Test
    public void listDigits_shouldReturnsTheListOfDigits_ofPositiveInteger(){

        //Given
        int number = 95897;
        //When
        Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);
        //Then
        /*Set<Integer> expectedDigits = Stream.of(5,7,8,9).collect(Collectors.toSet());
        assertEquals(expectedDigits, actualDigits);*/
        assertThat(actualDigits).containsExactlyInAnyOrder(9,5,8,7);
    }

    @Test
    public void listDigits_shouldReturnsTheListOfDigits_ofNegativeInteger(){
        int number = -124432;
        Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);
        assertThat(actualDigits).containsExactlyInAnyOrder(1,2,3,4);

    }


    @Test
    public void listDigits_shouldReturnsTheListOfZero_ofZero(){
        int number = 0;
        Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);
        assertThat(actualDigits).containsExactly(0);
    }


}
