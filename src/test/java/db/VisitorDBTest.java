package db;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class VisitorDBTest {
    @Disabled
    @Test
    void totalMember() throws SQLException {
        assertEquals(3,VisitorDB.totalMember());
    }
}