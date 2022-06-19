package com.example.javatest;

import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @Test
    @DisplayName("스터디 만들기")
    void create() {
        assertThrows(IllegalArgumentException.class, () -> new Study(-1));
        Study study = new Study(3);
        assertNotNull(study);
        assertEquals(StudyStatus.DRAFT,study.getStatus(),"스터디 객체는 처음 만들면 DRAFT");
        // parmeter로 assert문 받을 수 있음
        // assertAll();
        assertTimeout(Duration.ofMillis(10),() -> {
            new Study(10);
        });
    }

    @Test
    @DisplayName("스터디 만들기2")
    void create2() {
        System.out.println("StudyTest.create2");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("StudyTest.beforeAll");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("StudyTest.afterAll");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("StudyTest.beforeEach");
    }

    @AfterEach
    void afterEach() {
        System.out.println("StudyTest.afterEach");
    }
}