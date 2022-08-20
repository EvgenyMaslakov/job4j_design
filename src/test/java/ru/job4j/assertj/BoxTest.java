package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .contains("phere")
                .containsIgnoringCase("sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron")
                .isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .contains("ahedron")
                .containsIgnoringCase("tEtraheDron");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube")
                .isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .contains("ube")
                .containsIgnoringCase("cUBE");
    }

    @Test
    void isThisDefault() {
        Box box = new Box(7, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .contains("known", "ject")
                .containsIgnoringCase("unknown Object");
    }

    @Test
    void isThisDefaultVertexMinus1() {
        Box box = new Box(-1, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .contains("known", "ject")
                .containsIgnoringCase("unknown Object");
    }

    @Test
    void isThisVertexSphere() {
        Box box = new Box(0, 10);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isZero()
                .isEven()
                .isGreaterThan(-1)
                .isLessThan(1)
                .isEqualTo(0);
    }

    @Test
    void isThisVertexTetrahedron() {
        Box box = new Box(4, 10);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isNotZero()
                .isPositive()
                .isEven()
                .isGreaterThan(3)
                .isLessThan(5)
                .isEqualTo(4);
    }

    @Test
    void isThisVertexCube() {
        Box box = new Box(8, 10);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isNotZero()
                .isPositive()
                .isEven()
                .isGreaterThan(7)
                .isLessThan(9)
                .isEqualTo(8);
    }

    @Test
    void isExistTrue() {
        Box box = new Box(8, 10);
        boolean rsl = box.isExist();
        assertThat(rsl).isTrue();
    }

    @Test
    void isExistFalse() {
        Box box = new Box(-1, 10);
        boolean rsl = box.isExist();
        assertThat(rsl).isFalse();
    }

    @Test
    void case0CheckDoubleChain() {
        Box box = new Box(0, 2);
        double rsl = box.getArea();
        assertThat(rsl).isEqualTo(50.265d, withPrecision(0.006d))
                .isCloseTo(50.27d, withPrecision(0.01d))
                .isCloseTo(50d, Percentage.withPercentage(1.0d))
                .isGreaterThan(50.26d)
                .isLessThan(50.27d);
    }

    @Test
    void case4CheckDoubleChain() {
        Box box = new Box(4, 2);
        double rsl = box.getArea();
        assertThat(rsl).isEqualTo(6.928d, withPrecision(0.006d))
                .isCloseTo(6.93d, withPrecision(0.01d))
                .isCloseTo(6.86d, Percentage.withPercentage(1.0d))
                .isGreaterThan(6.9d)
                .isLessThan(7d);
    }

    @Test
    void case8CheckDoubleChain() {
        Box box = new Box(8, 2);
        double rsl = box.getArea();
        assertThat(rsl).isEqualTo(24.0d, withPrecision(0.001d))
                .isCloseTo(24.01d, withPrecision(0.01d))
                .isCloseTo(23.8d, Percentage.withPercentage(1.0d))
                .isGreaterThan(23.99d)
                .isLessThan(24.01d);
    }
}