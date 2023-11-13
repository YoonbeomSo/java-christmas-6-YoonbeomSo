package christmas.model.event;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EventDateTest {
    
    @DisplayName("옳바른 일자를 입력 하지 않으면 IllegalArgumentException 을 발생 한다.")
    @Test
    void 날짜_입력_테스트() {
        int validDay = 1;
        int inValidDay = 32;

        assertThat(EventDate.findLocalDateByDay(validDay)).isInstanceOf(LocalDate.class);
        assertThatThrownBy(() -> EventDate.findLocalDateByDay(inValidDay)).isInstanceOf(IllegalArgumentException.class);
    }

}