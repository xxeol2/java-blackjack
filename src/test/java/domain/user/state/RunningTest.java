package domain.user.state;

import static domain.card.Denomination.ACE;
import static domain.card.Denomination.FOUR;
import static domain.card.Denomination.SIX;
import static domain.card.Denomination.TEN;
import static domain.card.Denomination.TWO;
import static domain.card.Suits.DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunningTest {

    State state;

    @BeforeEach
    void setUpRunningState() {
        this.state = new Ready()
            .draw(Card.of(FOUR, DIAMOND))
            .draw(Card.of(SIX, DIAMOND)); // Ready -> Running
    }

    @DisplayName("카드를 받았는데, 총 합이 21점보다 작으면 그대로 Running상태이다.")
    @Test
    void stateToRunning() {
        State newState = state.draw(Card.of(TEN, DIAMOND));

        assertThat(newState).isInstanceOf(Running.class);
    }

    @DisplayName("카드를 받았는데, 총 합이 21점보다 크면 Bust상태로 변한다.")
    @Test
    void stateToBust() {
        State newState = state
            .draw(Card.of(TEN, DIAMOND))
            .draw(Card.of(TWO, DIAMOND));

        assertThat(newState).isInstanceOf(Bust.class);
    }

    @DisplayName("카드를 받았는데, 총 합이 21점이면 Stay상태로 변한다.")
    @Test
    void stateToStay() {
        State newState = state
            .draw(Card.of(TEN, DIAMOND))
            .draw(Card.of(ACE, DIAMOND));

        assertThat(newState).isInstanceOf(Stay.class);
    }

    @DisplayName("카드를 더 받을 수 있다.")
    @Test
    void isDrawable() {
        assertThat(state.isDrawable()).isTrue();
    }

    @DisplayName("stay할 수 있다.")
    @Test
    void canStay() {
        assertThat(state.stay()).isInstanceOf(Stay.class);
    }
}