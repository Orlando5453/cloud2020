import org.junit.Test;

import java.time.ZonedDateTime;

public class T2 {
    @Test
    public void main() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();//默认时区
        System.out.println(zonedDateTime);
    }
}
