package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isThisUnknownFigure() {
        Box box = new Box(14, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void whenEdgeEqualsZero() {
        Box box = new Box(4, 0);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void whenCountAreaTetrahedron() {
        Box box = new Box(4, 10);
        double area = box.getArea();
        assertThat(area).isEqualTo(173.20D, withPrecision(0.01d));
    }

    @Test
    void whenCountAreaWhenVertexZero() {
        Box box = new Box(-1, 10);
        double area = box.getArea();
        assertThat(area).isEqualTo(0);
    }

    @Test
    void whenGetNumberVertex() {
        Box box = new Box(4, 10);
        int area = box.getNumberOfVertices();
        assertThat(area).isEqualTo(4);
    }

    @Test
    void whenGetVertexNotPositive() {
        Box box = new Box(-1, 10);
        boolean rsl = box.isExist();
        assertThat(rsl).isFalse();
    }

    @Test
    void whenGetVertexPositive() {
        Box box = new Box(4, 10);
        boolean rsl = box.isExist();
        assertThat(rsl).isTrue();
    }
}