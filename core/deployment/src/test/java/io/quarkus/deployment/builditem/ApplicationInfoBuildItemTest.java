package io.quarkus.deployment.builditem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class ApplicationInfoBuildItemTest {

    @Test
    public void testDefaultConstructor() {
        ApplicationInfoBuildItem buildItem = new ApplicationInfoBuildItem(
                Optional.of("test-app"),
                Optional.of("1.0.0"));

        assertEquals("test-app", buildItem.getName());
        assertEquals("1.0.0", buildItem.getVersion());
        assertFalse(buildItem.isLts());
    }

    @Test
    public void testConstructorWithLtsTrue() {
        ApplicationInfoBuildItem buildItem = new ApplicationInfoBuildItem(
                Optional.of("test-app"),
                Optional.of("3.8.0"),
                true);

        assertEquals("test-app", buildItem.getName());
        assertEquals("3.8.0", buildItem.getVersion());
        assertTrue(buildItem.isLts());
    }

    @Test
    public void testConstructorWithLtsFalse() {
        ApplicationInfoBuildItem buildItem = new ApplicationInfoBuildItem(
                Optional.of("test-app"),
                Optional.of("3.9.0"),
                false);

        assertEquals("test-app", buildItem.getName());
        assertEquals("3.9.0", buildItem.getVersion());
        assertFalse(buildItem.isLts());
    }

    @Test
    public void testUnsetValues() {
        ApplicationInfoBuildItem buildItem = new ApplicationInfoBuildItem(
                Optional.empty(),
                Optional.empty(),
                false);

        assertEquals(ApplicationInfoBuildItem.UNSET_VALUE, buildItem.getName());
        assertEquals(ApplicationInfoBuildItem.UNSET_VALUE, buildItem.getVersion());
        assertFalse(buildItem.isLts());
    }
}
