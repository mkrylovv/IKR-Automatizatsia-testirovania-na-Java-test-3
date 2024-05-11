import com.max.BookingService;
import com.max.CantBookException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class BookingServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(BookingServiceTest.class);
    static BookingService mockBookingService;

    @BeforeAll
    static void init() {
        mockBookingService = mock(BookingService.class);
        logger.info("Mock object BookingService created");
    }

    @Test
    void testCreateBookWithMockTrue() throws CantBookException {
        logger.info("Test CreateBookWithMockTrue run");

        when(mockBookingService.book(anyString(), any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(true);
        logger.debug("BookingService return true");

        Assertions.assertTrue(mockBookingService.book("New booking", LocalDateTime.now(), LocalDateTime.now()));
        logger.info("Test CreateBookWithMockTrue completed");
    }

    @Test
    void testCreateBookWithMockThrowException() throws CantBookException {
        logger.info("Test CreateBookWithMockThrowException run");

        when(mockBookingService.book(anyString(), any(LocalDateTime.class), any(LocalDateTime.class))).thenThrow(CantBookException.class);
        logger.debug("BookingService throw CantBookException");

        Assertions.assertThrows(CantBookException.class,
                () -> mockBookingService.book("New booking", LocalDateTime.now(), LocalDateTime.now()));
        logger.info("Test CreateBookWithMockThrowException completed");
    }
}
